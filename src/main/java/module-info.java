module es.alejandrosalazargonzalez.stratagemhero {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires transitive org.controlsfx.controls;
    requires javafx.graphics;
    requires javafx.base;

    opens es.alejandrosalazargonzalez.stratagemhero to javafx.fxml;
    exports es.alejandrosalazargonzalez.stratagemhero;
    exports es.alejandrosalazargonzalez.stratagemhero.config;
    exports es.alejandrosalazargonzalez.stratagemhero.controller;
    exports es.alejandrosalazargonzalez.stratagemhero.controller.abstractas;
    exports es.alejandrosalazargonzalez.stratagemhero.model;
    exports es.alejandrosalazargonzalez.stratagemhero.model.conexion;
    opens es.alejandrosalazargonzalez.stratagemhero.controller to javafx.fxml;
}