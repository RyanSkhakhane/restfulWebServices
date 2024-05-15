package com.voltaire.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class userDaoService {

    private static List<user> users = new ArrayList<>();

    private static int usercount = 0;

    static {
        users.add(new user(++usercount,"Kali", LocalDate.now().minusYears(24)));
        users.add(new user(++usercount,"Kai", LocalDate.now().minusYears(64)));
        users.add(new user(++usercount,"Kate", LocalDate.now().minusYears(44)));
    }

    public List<user> findAll(){
        return users;
    }

    public user findOne(int id){
        Predicate<? super user> predicate = user -> user.getId() == id;
        return users.stream().filter(predicate).findFirst().get();

    }


    public void DeleteById (int id){
        Predicate<? super user> predicate = user -> user.getId() == id;
        users.removeIf(predicate);
    }


    public user SaveUser(user usr){
        usr.setId(++usercount);
        users.add(usr);
        return usr;
    }


}
