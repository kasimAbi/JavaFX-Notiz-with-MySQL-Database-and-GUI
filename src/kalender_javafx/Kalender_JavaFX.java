/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalender_javafx;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author Kasim
 */
public class Kalender_JavaFX extends Application {
    
    private final int fenstergröße = 800, fensterbreite = 1000;
    
    // Listview
    private ListView essensListe;
    private DatePicker datePicker;
    private Button BtnSpeichern;
    private VBox vBox;
    private TextEditor textEditor;
    
    // Text Felder
    private static TextField TFnotiz;
    
    @Override
    public void start(Stage primaryStage) {
        initialisieren();
        
        Scene scene = new Scene(vBox, fensterbreite, fenstergröße);
        
        primaryStage.setTitle("Kalender");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    static class ListeDesign extends ListCell<Notiz> implements EventHandler<ActionEvent> {
        private final HBox hBox = new HBox();
        private final Label lDatum = new Label(""), lNotiz = new Label("");
        private final Button btnÖffnen = new Button("öffnen");
        private final Button btnLöschen = new Button("X");
        
        public ListeDesign(){
            super();
            hBox.getChildren().addAll(lDatum, lNotiz, btnÖffnen, btnLöschen);
            hBox.setSpacing(10);
            lDatum.setMinWidth(100);
            lDatum.setMaxWidth(100);
            lNotiz.setMinWidth(500);
            lNotiz.setMaxWidth(500);
            btnÖffnen.setOnAction(this);
            btnLöschen.setOnAction(this);
        }
        
        @Override
        public void updateItem(Notiz notiz, boolean empty){
            super.updateItem(notiz, empty);
            setText(null);
            setGraphic(null);
            
            if(notiz != null && !empty){
                lDatum.setText(notiz.getDate().toString());
                lNotiz.setText(notiz.getNotiz());
                setGraphic(hBox);
            }
        }

        @Override
        public void handle(ActionEvent event) {
            if(event.getSource() == btnÖffnen){
                NotizAnzeigen notizAnzeigen = new NotizAnzeigen();
                notizAnzeigen.notizAnzeigen(lDatum.getText(), lNotiz.getText());
            }else if(event.getSource() == btnLöschen){
                LocalDate localDate = LocalDate.parse(lDatum.getText());
                TextEditor textEditor = new TextEditor();
                textEditor.löscheNotiz(localDate);
                getListView().getItems().remove(getItem());
            }
        }
    }
    
    private void initialisieren(){
        textEditor = new TextEditor();
        vBox = new VBox();
        TFnotiz = new TextField();
        TFnotiz.setMinWidth(500);
        TFnotiz.setMaxWidth(500);
        TFnotiz.setPromptText("Notiz hinzufügen.");
        
        datePicker = new DatePicker();
        datePicker.setPromptText("Wähle ein Datum aus");
        datePicker.setMaxWidth(200);
        datePicker.setMinWidth(200);
        
        BtnSpeichern = new Button("Speichern");
        
        essensListe = new ListView();
        essensListe.setMinHeight(600);
        essensListe.setMaxHeight(600);
        essensListe.setMinWidth(960);
        essensListe.setMaxWidth(960);
        
        essensListe.setCellFactory(e -> new ListeDesign());
        fillList();
        
        HBox hBox = new HBox(datePicker, TFnotiz);
        hBox.setSpacing(10);
        
        vBox.getChildren().addAll(hBox, BtnSpeichern, essensListe);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20));
        
        BtnSpeichern.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!datePicker.getEditor().getText().isEmpty()){
                    Notiz notiz = new Notiz(datePicker.getValue(), TFnotiz.getText());
                    if(!textEditor.istVorhanden(notiz)){
                        textEditor.notizHinzufügen(notiz);
                        TFnotiz.setText("");
                        System.out.println("Inserted");
                    }else{
                        System.out.println("Updated");
                    }
                    fillList();
                }
            }
        });
    }
    
    public void fillList(){
        // notizen vom Editor bekommen und in eine arraylist laden.
        ArrayList<Notiz> notizen = textEditor.notizenBekommen();
        if(!notizen.isEmpty()){
            essensListe.getItems().clear();
            for(int index = 0; index < notizen.size(); index++){
                essensListe.getItems().add(notizen.get(index));
            }
        }
    }
}