package com.voltaire.rest.webservices.restfulwebservices.versioning;



public class Personv2 {
    private Name name;

    public Personv2(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Personv2{" +
                "name=" + name + "From API V2" +
                '}';
    }
}
