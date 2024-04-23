/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalender_javafx;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasim
 */
public class DatenbankVerbindung {
    
    // Verbindungsdaten
    private final String dbUrl = "jdbc:mysql://localhost:3306/notiz?autoReconnect=true&serverTimezone=UTC";
    private final String dbUsername = "root";
    private final String dbPassword = "";
    
    private String query;
    
    private Connection connection = null;   // Für Verbindungsaufbau
    private Statement statement = null;     // Für das SQL Befehlsobjekt
    private ResultSet resultSet = null;     // Für die Ergebnismenge
    
    private ArrayList<Notiz> notizen;
    
    private boolean prüfen = false;
    
    private void getInfo(String aufgabe){
        try {
            notizen = new ArrayList<>();
            
            // Driver laden
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            
            // Datenbankverbindung aufbauen.
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            
            // SQL-Befehlsobjekt erstellen.
            statement = connection.createStatement();
            
            // SQL-Abfrage ausführen
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()){
                if(aufgabe.equals("getNotes")){
                    LocalDate localDate = LocalDate.parse(resultSet.getDate("date").toString());
                    Notiz notiz = new Notiz(localDate, resultSet.getString("notiz"));
                    notizen.add(notiz);
                }else if(aufgabe.equals("prüfen")){
                    prüfen = true;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            Logger.getLogger(DatenbankVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(statement != null){ statement.close(); }
                if(connection != null){ connection.close(); }
                if(resultSet != null){ resultSet.close(); }
            } catch (SQLException ex) {
                Logger.getLogger(DatenbankVerbindung.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void insertUpdateInfo(){
        try{
            // Driver laden
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            
            // Datenbankverbindung aufbauen.
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            
            // SQL-Befehlsobjekt erstellen.
            statement = connection.createStatement();
            
            // SQL-Abfrage ausführen
            statement.executeUpdate(query);
            
        }catch (Exception ex) {
            Logger.getLogger(DatenbankVerbindung.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(statement != null){ statement.close(); }
                if(connection != null){ connection.close(); }
            } catch (SQLException ex) {
                Logger.getLogger(DatenbankVerbindung.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean istVorhanden(LocalDate localDate){
        query = "SELECT * FROM t_notizen WHERE date = '" + localDate + "'";
        getInfo("prüfen");
        return prüfen;
    }

    public ArrayList<Notiz> getNotizen() {
        query = "SELECT * FROM t_notizen";
        getInfo("getNotes");
        return notizen;
    }
    
    public void insertNotiz(Notiz notiz) {
        query = "INSERT INTO t_notizen (date, notiz) VALUES ('" + notiz.getDate().toString() + "', '" + notiz.getNotiz() + "')";
        insertUpdateInfo();
    }
    
    public void updateNotiz(Notiz notiz){
        query = "UPDATE t_notizen SET date = '" + notiz.getDate().toString() +  "', notiz = '" + notiz.getNotiz() + "' WHERE date = '" + notiz.getDate() + "'";
        insertUpdateInfo();
    }

    public boolean isPrüfen() {
        return prüfen;
    }

    public void setPrüfen(boolean prüfen) {
        this.prüfen = prüfen;
    }
    
    
}