module com.shunli.fxmusicplayer {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.shunli.fxmusicplayer to javafx.fxml;
    exports com.shunli.fxmusicplayer;
}