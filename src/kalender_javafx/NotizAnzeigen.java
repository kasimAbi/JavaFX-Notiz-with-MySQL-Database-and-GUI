/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalender_javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Kasim
 */
public class NotizAnzeigen {
    
    public void notizAnzeigen(String title, String message){
        Stage window = new Stage();
        
        // blockt hintergrund bis fenster geschlossen wird.
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(450);
        Label meldung = new Label();
        meldung.setText(message);
        
        Button closeButton = new Button("schließen");
        closeButton.setOnAction(e -> window.close());
        
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(meldung, closeButton);
        layout.setSpacing(10);
        layout.setPadding(new Insets(20));
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        // warten bis fenster geschlossen wird.
        window.showAndWait();
    }
}