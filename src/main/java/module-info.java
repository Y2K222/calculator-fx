module com.zawhtetaung.calculatorfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.zawhtetaung.calculatorfx to javafx.fxml;
    exports com.zawhtetaung.calculatorfx;
}