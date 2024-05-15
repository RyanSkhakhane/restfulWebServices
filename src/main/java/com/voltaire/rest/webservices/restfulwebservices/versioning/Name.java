package com.voltaire.rest.webservices.restfulwebservices.versioning;

public class Name {
    private String name;
    private String surname;

    public Name(String name, String surname) {
        super();
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
