package com.voltaire.rest.webservices.restfulwebservices.user;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.MediaSize;
import java.net.URI;
import java.util.List;

@RestController
public class userResource {
    private userDaoService service;
    public userResource(userDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<user> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<user> oneUser(@PathVariable int id){
        user usr = service.findOne(id);

        if(usr == null){
            throw  new UserNotFoundException("id: "+id);
        }

        EntityModel<user> entityModel = EntityModel.of(usr);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }


    @DeleteMapping("/users/{id}")
    public void DeleteUser(@PathVariable int id){
        service.DeleteById(id);
    }


    @PostMapping("/users")
    public ResponseEntity<user> createUser(@Valid  @RequestBody user usr){
        user savedUser = service.SaveUser(usr);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser
                        .getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
