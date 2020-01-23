package com.example.virtualbadge.dataBase;

public class Employee {

    private String pin;
    private String login;
    private String name;
    private String surname;
    private String photoPath;

    public Employee(){}

    public Employee(String login, String pin, String name, String surname) {
        this.login = login;
        this.pin = pin;
        this.name = name;
        this.surname = surname;
//        this.photoPath = photoPath;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}