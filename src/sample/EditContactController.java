package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditContactController {
    private static String setnam,setnum,seteml,setbirth;
    private static int indexx;
    @FXML
    TextField namm,numm,emll;
    @FXML
    DatePicker birthhh;
    @FXML
    Button edit;

    public void initialize() {
        namm.setText(setnam);
        numm.setText(setnum);
        emll.setText(seteml);
        birthhh.setValue(LocalDate.parse(setbirth));
    }

    @FXML
    public void Update(){
        System.out.println("Updating");
        String number= numm.getText();
        String name= namm.getText();
        String email=emll.getText();
        String birth=birthhh.getValue().toString();

        Database database=new Database();
        database.Update(indexx,setnum,name,number,email,birth);

        Stage stage = (Stage) edit.getScene().getWindow();
        // do what you have to do
        stage.close();

    }
    public static void Call(int index, String nam, String num, String email, String birthday){
        setnum=num;
        setnam = nam;
        seteml=email;
        setbirth=birthday;
        indexx=index;
    }
}
