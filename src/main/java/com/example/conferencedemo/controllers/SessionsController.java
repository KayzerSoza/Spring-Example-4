package com.example.conferencedemo.controllers;

import com.example.conferencedemo.models.Session;
import com.example.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
  @Autowired
  private SessionRepository sessionRepository;

  @GetMapping
  public List<Session> list() {
    return sessionRepository.findAll();
  }

  @GetMapping
  @RequestMapping("{id}")
  public Session get(@PathVariable Long id) {
    return sessionRepository.getOne(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Session create(@RequestBody final Session session){
    return sessionRepository.saveAndFlush(session); //
  }


  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable Long id) {
    //Also need to check for children records before deleting.
    sessionRepository.deleteById(id);
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  public Session update(@PathVariable Long id, @RequestBody Session session) {
    //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what has changed.
    //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
    Session existingSession = sessionRepository.getOne(id);
    //BeanUtils copies incoming session data to existing session
    //we dont want to replace session_id primary key.
    BeanUtils.copyProperties(session, existingSession, "session_id");
    return sessionRepository.saveAndFlush(existingSession);
  }
//
//
//  @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
//  public Session updatePartially(@PathVariable Long id, @RequestBody Session session) {
//    // A PATCH would only need what has changed.
//    //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
//    Session existingSession = sessionRepository.getOne(id);
//
//    BeanUtils.copyProperties(session, existingSession, "session_id");
//    return sessionRepository.saveAndFlush(existingSession);
//  }


}
