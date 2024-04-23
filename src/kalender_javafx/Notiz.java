/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalender_javafx;

import java.time.LocalDate;

/**
 *
 * @author Kasim
 */
public class Notiz {
    private LocalDate date;
    private String notiz;
    
    public Notiz(){};
    
    public Notiz(LocalDate date, String notiz){
        this.date = date;
        this.notiz = notiz;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotiz() {
        return notiz;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }
    
    
}
