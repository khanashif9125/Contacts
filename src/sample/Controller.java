package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Controller {


    boolean bol = false;
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    Label success,setsuccess;
    @FXML
    GridPane mainGridPane;

    @FXML
    TextField signname,signpass,signemail,signnumber;

    Database database=new Database();

    @FXML
    public void Signup(){
        String name=signname.getText();
        String pass=signpass.getText();
        String emaill=signemail.getText();
        String phn=signnumber.getText();
        setsuccess.setText("Signup was successful now you can login");
        database.Saveid(name,phn,emaill,pass);
        signname.clear();
        signemail.clear();
        signnumber.clear();
        signpass.clear();
    }


    @FXML
    public void login(){
        //---------------------------------Getting Data from Database
        List<LoginData> loginDatas=new LinkedList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = con.createStatement();
            statement.execute("SELECT * FROM data");
            ResultSet result= statement.getResultSet();
            while (result.next()){
                String nam= result.getString("name");
                String phn= result.getString("phone");
                String emaal= result.getString("email");
                String passwd= result.getString("pass");

                LoginData logindata=new LoginData(nam,phn,emaal,passwd);
                loginDatas.add(logindata);

            }
        }
        catch (SQLException e) {
            System.out.println("not working load" + e.getMessage());
        }


        //---------------------------------
        String eml=email.getText();
    String pass=password.getText();
    int i=0;

    while (i<=loginDatas.size()){
        if((loginDatas.get(i).getEmail()).equalsIgnoreCase(eml)&&(loginDatas.get(i).getPassword()).equals(pass)){
            bol=true;
            Database.setTable(eml);
            break;
        }
        i++;
    }



        if(bol){
            email.clear();
            password.clear();

            success.setText("Login successful");
            Dialog<ButtonType> dialog=new Dialog<>();
            dialog.initOwner(mainGridPane.getScene().getWindow());
            try {
                Parent root = FXMLLoader.load(getClass().getResource("contact.fxml"));
                dialog.getDialogPane().setContent(root);
            }
            catch (IOException e){
                System.out.println("Error in loading window");
                e.printStackTrace();
            }

            Optional<ButtonType> result=dialog.showAndWait();
       }
        else
            success.setText("Invalid login attempt");

    }
}
