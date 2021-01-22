package com.duan.es;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RequestMapping("person")
@RestController
public class PersonController {

  @Resource
  private PersonService personService;

  @GetMapping
  List<Person> list () {
    Person person = new Person();
    return personService.getList(person);
  }

  @PostMapping
  void add (@RequestBody Person person) {
    personService.add(person);
  }
}
