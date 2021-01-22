package com.duan.elasticsearch;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RestController
@RequestMapping("/person")
public class IndexController {

  @Resource
  private PersonService personService;

  @GetMapping
  List<Person> list (PersonDTO personDTO) {
    return personService.getList(personDTO);
  }

  @PostMapping
  ResponseEntity<String> add (@RequestBody Person person) {
    return new ResponseEntity<>(personService.add(person), HttpStatus.CREATED);
  }

  @PostMapping("/bulk")
  ResponseEntity<String> bulk (@RequestBody Person person) {
    return new ResponseEntity<>(personService.bulk(person), HttpStatus.CREATED);
  }

}
