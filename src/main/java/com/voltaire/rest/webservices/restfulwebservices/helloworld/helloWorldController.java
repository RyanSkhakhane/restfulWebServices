package com.voltaire.rest.webservices.restfulwebservices.helloworld;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class helloWorldController {
   // Const dir = "/hello-world";
    private MessageSource messageSource;

    public  helloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }


    @GetMapping( path="/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello world");
    }

    @GetMapping( path="/hello-world-path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean("hello "+name);
    }

    @GetMapping( path="/helloworldpathvariable/{name}")
    public String elloWorldPathVariable(@PathVariable String name){
        return "hello "+name;
    }

    @GetMapping( path="/hello-world-i")
    public String helloWorldi18n(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null,"GOOD MORNING", locale);


    }

}
