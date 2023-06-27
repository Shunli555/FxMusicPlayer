module com.shunli.fxmusicplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
            
                            
    opens com.shunli.fxmusicplayer to javafx.fxml;
    exports com.shunli.fxmusicplayer;
}