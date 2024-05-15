package com.voltaire.rest.webservices.restfulwebservices.jpa;

import com.voltaire.rest.webservices.restfulwebservices.user.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<user, Integer> {
}
