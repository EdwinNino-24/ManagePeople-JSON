/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;

/**
 *
 * @author Edwin Niño
 */
public class Person implements Serializable{
    
    private String names;
    private String surnames;
    private String numberPhone;

    public Person(String names, String surnames, String numberPhone) {
        this.names = names;
        this.surnames = surnames;
        this.numberPhone = numberPhone;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
    
    
    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    
    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    
    @Override
    public String toString() {
        return "\nPERSONA: " +
                "\n Nombres: " + names + "\n Apellidos: " + surnames + "\n Numero celular: " + numberPhone;
    }
   
}
