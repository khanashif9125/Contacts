package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddContactController {
    @FXML
    TextField namm,emll,numm;
    @FXML
    DatePicker birthh;
    @FXML
    Button closebutton;

    Database database=new Database();


    public void savedata(){
//        Getting Text from fields
        String name=namm.getText();
        String email=emll.getText();
        String number=numm.getText();
        String birth=birthh.getValue().toString();

//        Saving it to the database
        database.save(name,number,email,birth);

//        Close Window
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
    }
}
