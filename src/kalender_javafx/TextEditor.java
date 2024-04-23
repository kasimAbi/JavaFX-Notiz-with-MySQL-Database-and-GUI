/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalender_javafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasim
 */
public class TextEditor{

    private File file;
    private String fileName = "Notizen.txt";
    private Formatter formatter;
    private ArrayList<Notiz> notizen;
    
    public TextEditor(){
        file = new File(fileName);
        // kontrolliert ob File existiert
        if(!file.exists()){
            try {
                // wenn file nicht vorhanden, wird file erstellt
                formatter = new Formatter(fileName);
            } catch (FileNotFoundException ex) {
                System.out.println("Ein Fehler ist aufgetreten beim Erstellen des Files.");
            }
        }
    }
    
    public ArrayList<Notiz> notizenBekommen(){
        try {
            notizen = new ArrayList<>();
            Scanner scanner = new Scanner(new File(fileName));
            while(scanner.hasNext()){
                Notiz notiz  = new Notiz();
                notiz.setDate(LocalDate.parse(scanner.nextLine()));
                notiz.setNotiz(scanner.nextLine());
                notizen.add(notiz);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Ein Fehler ist aufgetreten beim Lesen der Notizen.");
        }
        return notizen;
    }
    
    public void löscheNotiz(LocalDate date){
        System.out.println("datum1: " + date.toString());
        notizenBekommen();
        System.out.println("datum1: " + notizen.get(0).getDate().toString());
        for(int index = 0; index < notizen.size(); index++){
            if(notizen.get(index).getDate().equals(date)){
                notizen.remove(index);
                aktuallisiereFile();
                return;
            }
        }
    }
    
    public boolean istVorhanden(Notiz notiz){
        boolean istVorhanden = false;
        notizenBekommen();
        for(int index = 0; index < notizen.size(); index++){
            if(notizen.get(index).getDate().equals(notiz.getDate())){
                notizen.get(index).setNotiz(notiz.getNotiz());
                istVorhanden = true;
            }
        }
        if(istVorhanden){
            aktuallisiereFile();
        }
        return istVorhanden;
    }
    
    public void aktuallisiereFile(){
        file.delete();
        try {
            formatter = new Formatter(fileName);
            for(int index = 0; index < notizen.size(); index++){
                notizHinzufügen(notizen.get(index));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void notizHinzufügen(Notiz notiz){
        if(file.exists()){
            try{
                FileWriter fileWriter = new FileWriter(fileName, true);
                formatter = new Formatter(fileWriter);
                formatter.format("%s", notiz.getDate().toString() + "\n");
                formatter.format("%s", notiz.getNotiz() + "\n");
                formatterSchließen();
            } catch (IOException e){
                System.out.println("Ein Fehler ist aufgetreten beim Hinzufügen des Notizes.");
            }
        }else{
            System.out.println("File nicht vorhanden.");
        }
    }
    
    public void formatterSchließen(){
        formatter.close();
    }
}