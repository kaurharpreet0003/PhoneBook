package com.example.phonebook;

public class Person {

    int id;
    String Firstname;
    String Lastname;
    String Phone_number;
    String address;

    public Person(int id, String firstname, String lastname, String phone_number, String address) {


        this.id = id;
        Firstname = firstname;
        Lastname = lastname;
        Phone_number = phone_number;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public String getAddress() {
        return address;
    }
}
