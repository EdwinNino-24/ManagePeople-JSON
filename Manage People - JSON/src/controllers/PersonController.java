/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import models.Person;

/**
 *
 * @author Edwin Niño
 */
public class PersonController {
    
    private final String path;

    public PersonController() {
        path="person.json";
    }
    
    public void save(ArrayList<Person> people) throws IOException{
        FileOutputStream file = new FileOutputStream(path);
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(people);
        output.close();
        file.close();
    }
    
    public ArrayList<Person> read() throws IOException, ClassNotFoundException{
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream input = new ObjectInputStream(file);
        ArrayList<Person> people = (ArrayList<Person>) input.readObject();
        input.close();
        file.close();
        return people;
    }
   
    
    public String personToJson(ArrayList<Person> person) {
        Gson gson = new Gson();
        return gson.toJson(person);
    }

    public ArrayList<Person> jsonToPerson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<ArrayList<Person>>() {
        }.getType());
    }
    
    
    /*
    public String personToJson(Person person) {
        Gson gson = new Gson();
        return gson.toJson(person);
    }

    public Person jsonToPerson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Person.class);
    }
    */
    
}
