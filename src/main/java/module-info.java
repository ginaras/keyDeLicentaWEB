module org.softconsult.keydelicenta {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens org.softconsult.keydelicenta to javafx.fxml;
    exports org.softconsult.keydelicenta;
}