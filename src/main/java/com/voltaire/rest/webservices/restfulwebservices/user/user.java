package com.voltaire.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class user {
    @Id
    @GeneratedValue
    @JsonProperty("user_Id")
    private  int Id;
    @Size(min = 2, message = "Name must contain at least 2 characters")
    @JsonProperty("user_name")
    private String name;

    @JsonProperty("Birth_date")
    @Past(message = "The Date of birth should be in the past")
    private LocalDate Birth_date;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @OneToMany(mappedBy = "usr")
    @JsonIgnore
    private List<Post> posts;

    public user(int id, String name, LocalDate Birth_date) {
        super();
        this.Id = id;
        this.name = name;
        this.Birth_date = Birth_date;

    }
    protected user(){ }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.Birth_date = birth_date;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth_date() {
        return Birth_date;
    }

    @Override
    public String toString() {
        return "User [id=" + Id + ", name=" + name + ", birthDate=" + Birth_date + "]";
    }
}
