module com.example.poo4_proy2p_fernandez_maldonado_veintimilla {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.Ventanas.VentanaPrincipal to javafx.fxml;
    exports com.example.Ventanas.VentanaPrincipal;

    opens com.example.Ventanas.Controller to javafx.fxml;
    exports com.example.Ventanas.Controller;

}