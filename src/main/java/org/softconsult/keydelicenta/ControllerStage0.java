package org.softconsult.keydelicenta;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerStage0 implements Initializable {
    public TextField textFieldDB;
    public TextField textFieldUserName;
    public TextField textFieldPass;
    public TextField textFieldClient;
    public ComboBox comboBoxApp;
    public Button buttonGenerate;
    public TextField textFieldKeie;
    public Label labelKeie;
    public RadioButton radioButtonNet;
    public RadioButton radioButtonServerPropriu;
    public RadioButton radioButtonLocal;
    public ComboBox comboBoxMonth;
    public ComboBox comboBoxYear;


    public void buttonGenerateAct(ActionEvent actionEvent) {
        if(comboBoxApp.getValue()==null){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Nu ai ales aplicatia");
            alert.showAndWait();
            return;
        }
        if(textFieldClient.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Nu ai introdus clientul");
            alert.showAndWait();
            return;
        }
        if(comboBoxMonth.getValue()==null){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Nu ai ales luna");
            alert.showAndWait();
            return;
        }
        if(comboBoxYear.getValue()==null){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Nu ai ales anul");
            alert.showAndWait();
            return;
        }
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedDB = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        String generatedUSerName = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        String generatedPass = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        textFieldDB.setText(generatedDB);
        textFieldUserName.setText(generatedUSerName);
        textFieldPass.setText(generatedPass);

        String app=null;
        if(comboBoxApp.getValue()=="Auto") {            app="auto";        }
        if(comboBoxApp.getValue()=="Managementul Proiectelor") {            app="mgpr";        }

        String monthStr =comboBoxMonth.getValue().toString();
        String yearStr =comboBoxYear.getValue().toString();
        String inverseMonthStr=monthStr.substring(2,3)+monthStr.substring(1,2)+monthStr.substring(0,1);
        String inverseYearhStr=yearStr.substring(1,2)+yearStr.substring(0,1);
        String server=null;
        System.out.println("autoZ80uhWQ9MlJAyfaOKyHApHx13xl70Z52ram".length());


        if(radioButtonNet.isSelected()) { server="n";        }
        if(radioButtonLocal.isSelected()){server="l";}
        if(radioButtonServerPropriu.isSelected()){server="s";}

        labelKeie.setText(app+textFieldDB.getText()+textFieldUserName.getText()+textFieldPass.getText()+inverseYearhStr+inverseMonthStr+server);
        textFieldKeie.setText(app+textFieldDB.getText()+textFieldUserName.getText()+textFieldPass.getText()+inverseYearhStr+inverseMonthStr+server);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxApp.setItems(FXCollections.observableArrayList("Auto", "Managementul Proiectelor"));
        comboBoxMonth.setItems(FXCollections.observableArrayList("ian", "feb", "mar", "apr", "mai", "iun", "iul", "aug", "sep", "oct", "noi", "dec"));
        comboBoxYear.setItems(FXCollections.observableArrayList("24", "25", "26", "27", "28", "29", "30"));

        ToggleGroup toggleGroup=new ToggleGroup();
        radioButtonNet.setToggleGroup(toggleGroup);
        radioButtonLocal.setToggleGroup(toggleGroup);
        radioButtonServerPropriu.setToggleGroup(toggleGroup);
        radioButtonNet.setSelected(true);
    }
}
