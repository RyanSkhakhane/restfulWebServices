package com.voltaire.rest.webservices.restfulwebservices.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {


    @Id
    @GeneratedValue
    private Integer id;


    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private user usr;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public user getUsr() {
        return usr;
    }

    public void setUsr(user usr) {
        this.usr = usr;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", Description='" + description + '\'' +
                '}';
    }
}
