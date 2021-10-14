package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static sample.EditContactController.Call;

public class ContactControler {
    public static ObservableList<Phonebook> phonebook = FXCollections.observableArrayList();

    public String name,phone,email,birthday;
    int deletetimes=0;
    @FXML
    Button signo;

    @FXML
    TableView table;

    @FXML
    TableColumn nam;
    @FXML
    TableColumn phn;
    @FXML
    TableColumn eml;
    @FXML
    TableColumn birth;
    @FXML
    DialogPane contact;

    // Add Testing Data

    public void initialize() {
        //Loading Data from database
        Database database=new Database();
        phonebook= database.load();

        //Setting column and type to table view
        nam.setCellValueFactory(new PropertyValueFactory<Phonebook, String>("name"));
        phn.setCellValueFactory(new PropertyValueFactory<Phonebook, String>("phoneno"));
        eml.setCellValueFactory(new PropertyValueFactory<Phonebook, String>("email"));
        birth.setCellValueFactory(new PropertyValueFactory<Phonebook, String>("birth"));

        //Setting table with phonebook
        table.setItems(phonebook);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


    }
        @FXML
        public void Addcontact(){
                Dialog<ButtonType> dialog=new Dialog<>();
                dialog.initOwner(contact.getScene().getWindow());
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("AddContact.fxml"));
                    dialog.getDialogPane().setContent(root);
                }
                catch (IOException e){
                    System.out.println("Error in loading window");
                    e.printStackTrace();
                }
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                Optional<ButtonType> result=dialog.showAndWait();
//            if(result.isPresent()&&result.get()==ButtonType.OK)
//                System.out.println("OK pressed");
//            else
//                System.out.println("cancel pressed");
        }

    @FXML
    public void Editcontact(){
        int index= table.getSelectionModel().getFocusedIndex();

        name=nam.getCellObservableValue(index).getValue().toString();
        email=eml.getCellObservableValue(index).getValue().toString();
        phone=phn.getCellObservableValue(index).getValue().toString();
        birthday=birth.getCellObservableValue(index).getValue().toString();
//        System.out.println(name);
//        System.out.println(email);
//        System.out.println(phone);
//        System.out.println(birthday);
        Call(index,name,phone,email,birthday);


        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.initOwner(contact.getScene().getWindow());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("EditContact.fxml"));
            dialog.getDialogPane().setContent(root);

        }
        catch (IOException e){
            System.out.println("Error in loading update window");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Optional<ButtonType> result=dialog.showAndWait();
    }
    @FXML
    public void Deletecontact(){
        deletetimes++;

        int index= table.getSelectionModel().getFocusedIndex();
        name=nam.getCellObservableValue(index).getValue().toString();
        email=eml.getCellObservableValue(index).getValue().toString();
        phone=phn.getCellObservableValue(index).getValue().toString();
        birthday=birth.getCellObservableValue(index).getValue().toString();
//        System.out.println(name);
//        System.out.println(email);
//        System.out.println(phone);
//        System.out.println(birthday);

//        Database database=new Database();
        Database.Delete(phone,index);

    }

    @FXML
    public void signout(){
        int i =0;
        while (i<(phonebook.size()))
            phonebook.remove(i);
//        phonebook.removeAll();

        Stage stage = (Stage) signo.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}