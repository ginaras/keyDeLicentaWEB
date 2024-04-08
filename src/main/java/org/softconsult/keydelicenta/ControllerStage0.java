package org.softconsult.keydelicenta;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    static String generatedDB;
    public TextField textFieldServer;
    public Button buttonSave;
    public Button buttonGenerateDB;
    public Button buttonCopyKey;
    String generatedUSerName;
    String generatedPass;

    public ControllerStage0() throws SQLException {
    }

    public static String bazaDeDate = "information_schema";
    public static String backUpbazaDeDate = "autoNelYFunDa250223Bbackup";
//    public static String USER=  "aUtONeli";
//    public static String PASSWORD=  "pas@NeLy2gynLog23";
    public static String USER=  "root";
    public static String PASSWORD=  "root";
    //    public static String SERVER="95.214.135.69";
//        public static String SERVER="localhost";
//        public static String SERVER="10.59.4.8";
    String SERVER;
    public static String PORT = "3306";

    String app=null;
    String monthStr=null;
    String yearStr=null;
    String inverseMonthStr=null;
    String inverseYearhStr=null;
    String server=null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonSave.setDisable(true);
        textFieldServer.setVisible(false);
        buttonGenerateDB.setDisable(true);
        buttonCopyKey.setDisable(true);
        comboBoxApp.setItems(FXCollections.observableArrayList("Auto", "Managementul Proiectelor"));
        comboBoxMonth.setItems(FXCollections.observableArrayList("jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"));
        comboBoxYear.setItems(FXCollections.observableArrayList("24", "25", "26", "27", "28", "29", "30"));

        ToggleGroup toggleGroup=new ToggleGroup();
        radioButtonNet.setToggleGroup(toggleGroup);
        radioButtonLocal.setToggleGroup(toggleGroup);
        radioButtonServerPropriu.setToggleGroup(toggleGroup);
        radioButtonNet.setSelected(true);


    }

    public void buttonGenerateAct(ActionEvent actionEvent) throws SQLException, IOException {
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
        if(radioButtonServerPropriu.isSelected()){
        if(textFieldServer.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Serverul de intranet");
            alert.showAndWait();
            return;
        }}
        if(comboBoxMonth.getValue()==null){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Nu ai ales luna");
            alert.showAndWait();
            return;
        }

            if(comboBoxYear.getValue()==null ){
                Alert alert=new Alert(Alert.AlertType.ERROR,"Nu ai ales anul");
                alert.showAndWait();
                return;
            }

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        generatedDB = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        generatedUSerName = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        generatedPass = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        textFieldDB.setText(generatedDB);
        textFieldUserName.setText(generatedUSerName);
        textFieldPass.setText(generatedPass);


        if(comboBoxApp.getValue()=="Auto") {            app="auto";        }
        if(comboBoxApp.getValue()=="Managementul Proiectelor") {            app="mgpr";        }

         monthStr =comboBoxMonth.getValue().toString();
         yearStr =comboBoxYear.getValue().toString();
         inverseMonthStr=monthStr.substring(2,3)+monthStr.substring(1,2)+monthStr.substring(0,1);
         inverseYearhStr=yearStr.substring(1,2)+yearStr.substring(0,1);

        if(radioButtonNet.isSelected()) { server="n";  SERVER= "95.214.135.69";     }
        if(radioButtonLocal.isSelected()){server="l"; SERVER="localhost";}
        if(radioButtonServerPropriu.isSelected()){server="s"; SERVER=textFieldServer.getText();}


        buttonGenerate.setDisable(true);
        buttonSave.setDisable(false);
    }

//        public static final String CREATE_DATABASE="CREATE DATABASE IF NOT EXISTS '"+textF+"'";
        public static final String CREATE_AUTO_TBL= "CREATE TABLE IF NOT EXISTS auto (nrCrt INT(4) AUTO_INCREMENT PRIMARY KEY, nrInventar VARCHAR (15), nrInmatriculare VARCHAR (12), marca VARCHAR (40), model VARCHAR (40), tip VARCHAR (50), combustibil VARCHAR (20), capacitateRezervor INT(4), anvelopeMarca VARCHAR (30), anvelopeTip VARCHAR (30), serieSasiu VARCHAR (20), valAchizitie VARCHAR (12) , dataAchizitiei DATE, dataPrimeiInmatr DATE, nrAxe INT(2), kmInitiali INT(7), lungime VARCHAR(6), latime VARCHAR(6), inaltime VARCHAR(4), gabarit VARCHAR(6), operare VARCHAR (100)  )";
        public static final String CREATE_RCA_ITP_AURORIZARI= "CREATE TABLE IF NOT EXISTS rcaITP (nrCrt INT(5) AUTO_INCREMENT PRIMARY KEY, document VARCHAR (20), nrInmatriculare VARCHAR (12), tipDocument VARCHAR(20), nrDoc VARCHAR (20), dataDoc DATE, dataExpirare DATE, durataRamasaTemp INT (4), valoare DOUBLE, inlocuita INT (4), operare VARCHAR (100) )";
        public static final String CREATE_LISTA_DOCUMENTE= "CREATE TABLE IF NOT EXISTS listaDocumente (nrCrtLista INT(2), denDoc VARCHAR (50), inactiv VARCHAR (2) )";
        public static final String CREATE_TABLE_FACTURI_CARBURANTI="CREATE TABLE IF NOT EXISTS factCarburant ( nrCrt INT (6) AUTO_INCREMENT PRIMARY KEY, furnizor VARCHAR(70), nrFactura VARCHAR(10), dataFactura DATE, tipCombustibil VARCHAR(12), cantitate VARCHAR(7), pretUnitar VARCHAR(15), operare VARCHAR (100), nrInmatriculare VARCHAR(12) )";
        public static final String CREATE_CONSUM_CARBURANTI= "CREATE TABLE IF NOT EXISTS consumCarburant (nrCrtCombust INT(6) AUTO_INCREMENT PRIMARY KEY, nrInmatriculare VARCHAR (12), tipCombustibil VARCHAR(20), cantitate  VARCHAR (6), kmLaAlimentare VARCHAR (7), consum DOUBLE, dataAlimentarii DATE, operare VARCHAR (100)  )";
        public static final String CREATE_PIESE= "CREATE TABLE IF NOT EXISTS piese (nrCrtPiese INT(5) AUTO_INCREMENT PRIMARY KEY, furnizor VARCHAR (50), nrFactura VARCHAR (20), dataFactura DATE, piesa VARCHAR(50), pret VARCHAR (10), bonConsum VARCHAR(20), dataBonConsum DATE, nrInmatriculare VARCHAR (12), operare VARCHAR (100) )";
        public static final String CREATE_TABLE_RESP_PRIVILEGE = "CREATE TABLE IF NOT EXISTS respPrivilege (nrCrt INT (3) AUTO_INCREMENT PRIMARY KEY, respProiect VARCHAR(50), macNr VARCHAR(17), adminu CHAR(10), sex CHAR (1), inactiv VARCHAR (3), rezerva1 VARCHAR (50))";
        public static final String CREATE_TABLE_AUTORIZARI_AUTO = "CREATE TABLE IF NOT EXISTS autorizariAuto ( nrCrt INT(4) AUTO_INCREMENT PRIMARY KEY, nrInmatriculare VARCHAR (12), tipAutorizatie VARCHAR (30), nrDocumentAutorizare VARCHAR (20), dataAutorizarii DATE, dataExpirariiAutorizarii DATE, valoare VARCHAR (12), operare VARCHAR (100))";
        public static final String CREATE_TABLE_REVIZII="CREATE TABLE IF NOT EXISTS revizii (nrCrt INT(5) AUTO_INCREMENT PRIMARY KEY, nrAuto VARCHAR(12), document VARCHAR (100), dataReviziei DATE, dataExpirarii DATE, durataRamasaTemp INT(4), kmLaRevizie DOUBLE, kmLaUrmRevizie DOUBLE, valoare DOUBLE,  inlocuita INT(4), operare VARCHAR (100) )";
        public static final String CREATE_TABLE_LOG = "CREATE TABLE IF NOT EXISTS logFile (dataLogarii VARCHAR (60), utilizator VARCHAR(50), mesaj VARCHAR (1000), rezervaLog VARCHAR(70))";
        public static final String CREATE_TABLE_ADMINSTRATIV="CREATE TABLE IF NOT EXISTS administrativ (nrCrt INT(4) AUTO_INCREMENT PRIMARY KEY, detaliu VARCHAR(100), data DATE, parametru VARCHAR(20), descriere VARCHAR(200))";


    public void radioButtonServerPropriuAct(ActionEvent actionEvent) {
        textFieldServer.setVisible(true);
    }

    public void buttonSaveAct(ActionEvent actionEvent) throws IOException {
        System.out.println(SERVER);
        labelKeie.setText(app+textFieldDB.getText()+textFieldUserName.getText()+textFieldPass.getText()+inverseYearhStr+inverseMonthStr+server);
        textFieldKeie.setText(app+textFieldDB.getText()+textFieldUserName.getText()+textFieldPass.getText()+inverseYearhStr+inverseMonthStr+server);

        FileWriter fileWriter=new FileWriter("credentiale/"+comboBoxApp.getValue().toString()+"-"+textFieldClient.getText());
        fileWriter.append(textFieldClient.getText()+"\n");
        fileWriter.append("DB-"+textFieldDB.getText()+"\n");
        fileWriter.append("UN-"+textFieldUserName.getText()+"\n");
        fileWriter.append("P-"+textFieldPass.getText()+"\n");
        if(radioButtonNet.isSelected()){ fileWriter.append("pe net+\n");}
        if(radioButtonLocal.isSelected()){ fileWriter.append("local+\n");}
        if(radioButtonServerPropriu.isSelected()){ fileWriter.append("srv-"+textFieldServer.getText()+"\n");}
        fileWriter.append("   -----------------"+"\n");
        fileWriter.append("Licenta: "+labelKeie.getText()+"\n");
        fileWriter.close();

        textFieldDB.setDisable(true);
        textFieldUserName.setDisable(true);
        textFieldPass.setDisable(true);
        textFieldClient.setDisable(true);
        textFieldServer.setDisable(true);
        buttonGenerateDB.setDisable(false);
        buttonCopyKey.setDisable(false);
        System.out.println(labelKeie.getText().length());

    }

    public void buttonGenerateDBAct(ActionEvent actionEvent) {
        String CREATEUSERNAME = "CREATE USER IF NOT EXISTS "+USER+"@"+SERVER+" identified by "+PASSWORD+" ";
        String URL = String.format( "jdbc:mariadb://"+SERVER+":"+PORT+"/"+bazaDeDate+"" );
//        public static String URL = String.format( "jdbc:mariadb://95.214.135.69:3306/autoNelYFunDa250223" );
//    public static String URLbackup = String.format( "jdbc:mariadb://'"+SERVER+"':'"+PORT+"'/'"+backUpbazaDeDate+"'" );

        String URLbackup = String.format( "jdbc:mariadb://"+SERVER+":"+PORT+"/"+backUpbazaDeDate+"" );
        String USE_DATABASE = "USE "+generatedDB+" ";
        String USE_DATABASE_backup = "USE '"+backUpbazaDeDate+"'";
        String selectUser ="SELECT USER '"+USER+"' FROM mysql.user";
        try {
            Connection connectionFirst = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statementINV = connectionFirst.createStatement();
            statementINV.executeQuery("CREATE DATABASE IF NOT EXISTS "+textFieldDB.getText()+"");
            statementINV.executeUpdate(USE_DATABASE);
            statementINV.executeUpdate(CREATE_AUTO_TBL);
            statementINV.executeUpdate(CREATE_RCA_ITP_AURORIZARI);
            statementINV.executeUpdate(CREATE_LISTA_DOCUMENTE);
            statementINV.executeUpdate(CREATE_TABLE_FACTURI_CARBURANTI);
            statementINV.executeUpdate(CREATE_CONSUM_CARBURANTI);
            statementINV.executeUpdate(CREATE_PIESE);
            statementINV.executeUpdate(CREATE_TABLE_LOG);
            statementINV.executeUpdate(CREATE_TABLE_AUTORIZARI_AUTO);
            statementINV.executeUpdate(CREATE_TABLE_RESP_PRIVILEGE);
            statementINV.executeUpdate(CREATE_TABLE_REVIZII);
            statementINV.executeUpdate(CREATE_TABLE_ADMINSTRATIV);
        } catch(SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "nu s-a putut conecta la baza de date");
            alert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ai creat tabelele");
        alert.setHeaderText("AI CREAT TABLELE");
        alert.showAndWait();

    }

    public void buttonCopyKeyAct(ActionEvent actionEvent) {
        Clipboard clipboard=Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent=new ClipboardContent();
        clipboardContent.putString(labelKeie.getText());
        clipboard.setContent(clipboardContent);

//        Desktop.getDesktop().open(new File(raport + replaceNumeData1 + ".csv"));
    }

//    }

}
