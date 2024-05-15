package com.voltaire.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public  Personv1 getFirstPerson(){
        return new Personv1("Ryan Skhakhane");
    }

    @GetMapping("/v2/person")
    public  Personv2 getFirstPersonv2(){
        return new Personv2(new Name("Ryan", "Nzimase"));
    }
}
