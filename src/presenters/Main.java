/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenters;

import controllers.FileController;
import controllers.PersonController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import models.Person;

/**
 *
 * @author Edwin Niño
 */
public final class Main {
    
    static ArrayList<Person> people = new ArrayList<>();
    PersonController personController = new PersonController();
    FileController fileController = new FileController();
    
    Scanner scanner = new Scanner(System.in);
    
    public Main(){
        run();
    }
    
    public void run() {
        readInit();
        //createTestData();
        String banner =        "__________________________________________________________________________________________________\n" +
                               " ________________________________________________________________________________________________\n" +
                               "|                                         _______________                         [ManagePeople®]|\n" +
                               "|--------------------------------------- | MANAGE PEOPLE | --------------------------------------|\n" +
                               "|________________________________________________________________________________________________|\n" +
                               "__________________________________________________________________________________________________";
        int optionMenu = 0;
        String mainMenu =  " ________________________________________________________________________________________________\n" +
                               "|                                        ________________                                        |\n" +
                               "|-------------------------------------- | MENÚ PRINCIPAL | --------------------------------------|\n" +
                               "|________________________________________________________________________________________________|\n" +
                               " ________________________________________________________________________________________________\n" +
                               "|                         [--->] 1. Registrar persona nueva------------>(1)                      |\n" +
                               "|                         [--->] 2. Mostrar personas registradas------->(2)                      |\n" +
                               "|                         [--->] 3. Remover persona de los registros--->(3)                      |\n" +
                               "|                         [--->] 4. Leer o escribir un archivo----->(4)-> No funcional todavia...|\n" +
                               "|                         [--->] 5. Cerrar el programa----------------->(5)                      |\n" +
                               "|________________________________________________________________________________________________|";
        String formatOption = 
                               "__________________________________________________________________________________________________";
        String format1 =   " ________________________________________________________________________________________________ ";
        String format2 =   "|________________________________________________________________________________________________|";
        String closeSucccessfully = " EL PROGRAMA SE HA CERRADO EXITOSAMENTE...";
        String optionMenuError = " SELECCIONE UNA OPCION QUE SE ENCUENTRE EN EL MENU...";
            
        System.out.println(banner);
        do {
            System.out.println(mainMenu);
            try{
                System.out.println(formatOption);
                optionMenu = Integer.parseInt(scanner.nextLine());
                System.out.println(formatOption);
            }
            catch(NumberFormatException x){ 
            }    
            
            switch(optionMenu) {
		case 1:
                    addPerson();
                    break;
                case 2:
                    showPeople();
                    break;
                case 3:
                    removePerson();
                    break;
                case 4:
                    readPlainTextFile();
                    break;
                case 5:
                    System.out.println(formatOption);
                    System.out.println(closeSucccessfully);
                    System.out.println(formatOption);
                    break;
		default:
                    System.out.println(formatOption);
                    System.out.println(optionMenuError);
                    System.out.println(formatOption);
                    break;
            }
            
        }while(optionMenu != 5);
    
    }
    
    
    public void addPerson(){
        
        String formatOption = "__________________________________________________________________________________________________";
        String enterNames = " Ingrese sus nombres: ";
        String enterSurnames = " Ingrese sus apellidos: ";
        String enterNumberPhone = " Ingrese su numero celular: ";
        String successfulRegistration = " LA PERSONA SE HA REGISTRADO EXITOSAMENTE EN EL SISTEMA...";
        String navigation = "                     PRESIONE CUALQUIER TECLA PARA REGRESAR AL MENÚ PRINCIPAL.                     ";
        
        System.out.print(enterNames);
        String names = scanner.nextLine();
        System.out.println(formatOption);
        System.out.print(enterSurnames);
        String surnames = scanner.nextLine();
        System.out.println(formatOption);
        System.out.print(enterNumberPhone);
        String numberPhone = scanner.nextLine();
        
        Person person = new Person(names,surnames,numberPhone);
        people.add(person);
        saveData();
        
        System.out.println(formatOption);
        System.out.println(successfulRegistration);
        
        System.out.println(formatOption);
        jsonConversion();
        
        System.out.println(formatOption);
        System.out.println(navigation);
        System.out.println(formatOption);
        scanner.nextLine();
    }
    
    
    public void showPeople(){
        
        String formatOption = "__________________________________________________________________________________________________";
        String emptySystem = " AUN NO SE ENCUENTRAN REGISTROS EN EL SISTEMA...";
        String navigation = "                     PRESIONE CUALQUIER TECLA PARA REGRESAR AL MENÚ PRINCIPAL                     ";
        String optionMenuError = " SELECCIONE UNA OPCION QUE SE ENCUENTRE EN EL MENU...";
        String menuSecond = " INGRESE LA OPCION DE VISUALIZACION QUE REQUIERA:";
        String textMenuSecond = " 1. Formato Json --> Persona " + "|" + " 2. Formato Persona --> Json";
        
        if(people == null || people.isEmpty()){
            System.out.println(emptySystem);
        }
        else{
            System.out.println(formatOption);
            System.out.println(menuSecond);
            System.out.println(textMenuSecond);
            System.out.println(formatOption);
            int option = scanner.nextInt();
            System.out.println(formatOption);
            switch (option) {
                case 1:
                    jsonConversion();
                    break;
                case 2:
                    System.out.println(personController.personToJson(people));
                    break;
                default:
                    System.out.println(optionMenuError);
                    break;
            } 
        }
        
        System.out.println(formatOption);
        System.out.println(navigation);
        System.out.println(formatOption);
        scanner.nextLine();
        scanner.nextLine();
        
    }
    
   
    public void removePerson(){
        
        String formatOption = "__________________________________________________________________________________________________";
        String emptySystem = " AUN NO SE ENCUENTRAN REGISTROS EN EL SISTEMA...";
        String removeIdPerson = " Ingrese el ID de la persona a remover: ";
        String removeSuccessfully = " LA PERSONA SE HA REMOVIDO EXITOSAMENTE DEL SISTEMA...";
        String NonExistentId = " EL ID INGRESADO NO SE ENCUENTRA REGISTRADO EN EL SISTEMA...";
        String navigation = "                     PRESIONE CUALQUIER TECLA PARA REGRESAR AL MENÚ PRINCIPAL                     ";
        
        if(people == null || people.isEmpty()){
            System.out.println(emptySystem); 
        }
        else{
            try{
                jsonConversion();
                System.out.println(formatOption);
                System.out.print(removeIdPerson);
                int removeId = scanner.nextInt();
                System.out.println(formatOption);
                people.remove(removeId);
                saveData();
                jsonConversion();
            }
            catch(IndexOutOfBoundsException x){
                System.out.println(NonExistentId);
            }
        }
        System.out.println(formatOption);
        System.out.println(removeSuccessfully);
        System.out.println(formatOption);
        System.out.println(formatOption);
        System.out.println(navigation);
        System.out.println(formatOption);
        scanner.nextLine();
        scanner.nextLine();
        
    }
        
        
    public void createTestData() {
        
        Person firstPerson = new Person("EDUARDO","PEREZ","3294734532");
        people.add(firstPerson);
        
        Person secondPerson = new Person("CLAUDIO","GUTIERREZ","3294734532");
        people.add(secondPerson);
        
        Person thirdPerson = new Person("STAR","BUTTERFLY","3294734532");
        people.add(thirdPerson);
        
    }  
   
    
    public void saveData(){
        try {
            personController.save(people);
        } 
        catch (IOException e) {
        }   
    }
    
    
    public void readInit(){
        try {
            people = personController.read();
        } 
        catch (IOException | ClassNotFoundException e) {
        }
    }
    
    
    public void jsonConversion(){
        String messageId = "ID--> #";
        ArrayList<Person> peoples = personController.jsonToPerson(personController.personToJson(people));
        peoples.forEach(people -> System.out.println(messageId + people + "\n"));
    }
    
    
    public void readPlainTextFile(){
        String subMenu = " Ingrese la accion que desea realizar: ";
        String optionMenuError = " SELECCIONE UNA OPCION QUE SE ENCUENTRE EN EL MENU...";
        String options = " 1. Leer un archivo... | 2. Escribir un archivo...";
        String formatOption = "__________________________________________________________________________________________________";
        
        /*
        System.out.println(subMenu);
        System.out.println(options);
        System.out.println(formatOption);
        int option = scanner.nextInt();
        System.out.println(formatOption);
        try{
            switch(option){
                case 1:
                    fileController.readFile();
                break;
                case 2:
                    fileController.writeFile();
                break;
                default:
                    System.out.println(optionMenuError);
                break;
            }
        }catch (IOException e) {
        }*/
    }
    
    
    public static void main(String[] args) {
        Main main = new Main();
        
    }
    
}
