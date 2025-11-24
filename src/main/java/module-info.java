module fatec.lanchoneteapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jtds;
    requires javafx.graphics;

    opens fatec.lanchoneteapp.run to javafx.fxml;
    opens fatec.lanchoneteapp.adapters.ui to javafx.fxml;
    opens fatec.lanchoneteapp.application.facade to javafx.fxml;

    exports fatec.lanchoneteapp.run;
    exports fatec.lanchoneteapp.adapters.ui;
}