package com.app.dashbill.controller;


import com.app.dashbill.entity.Person;
import com.app.dashbill.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/person/new", method = RequestMethod.POST)
    public ResponseEntity<String> newPerson(@RequestBody Person person) {
        boolean result = personService.createPerson(person);

        if (!result) {
            return new ResponseEntity<>("BAD REQUEST", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ResponseEntity<?> getPeople() {

        List<Person> result = personService.listPeople();

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
