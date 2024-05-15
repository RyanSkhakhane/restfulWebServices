package com.voltaire.rest.webservices.restfulwebservices.user;

import com.voltaire.rest.webservices.restfulwebservices.jpa.userRepository;
import com.voltaire.rest.webservices.restfulwebservices.jpa.PostRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class userJpaResource {
    private userDaoService service;

    private userRepository repository;

    private PostRepository postRepository;

    public userJpaResource(userDaoService service, userRepository repository , PostRepository postRepository){
        this.service = service;
        this.repository = repository;
        this.postRepository = postRepository;
    }


    @GetMapping("/jpa/users")
    public List<user> retrieveAllUsers(){
        return repository.findAll();
    }


    @GetMapping("/jpa/users/{id}")
    public EntityModel<user> oneUser(@PathVariable int id){
        Optional<user> usr = repository.findById(id);

        if(usr.isEmpty()){
            throw  new UserNotFoundException("id: "+id);
        }

        EntityModel<user> entityModel = EntityModel.of(usr.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }


    @DeleteMapping("/jpa/users/{id}")
    public void DeleteUser(@PathVariable int id){
        repository.deleteById(id);
    }


    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostForUser(@PathVariable int id){
        Optional<user> user = repository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);
        return user.get().getPosts();
    }


    @PostMapping("/jpa/users")
    public ResponseEntity<user> createUser(@Valid  @RequestBody user usr){
        user savedUser = repository.save(usr);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser
                        .getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid  @RequestBody Post post ){
        Optional<user> user = repository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        post.setUsr(user.get());

        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
