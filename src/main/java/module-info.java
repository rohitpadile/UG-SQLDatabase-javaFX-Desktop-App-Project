module com.rohitpadile.ugdatabase {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.rohitpadile.ugdatabase to javafx.fxml;
    exports com.rohitpadile.ugdatabase;
}