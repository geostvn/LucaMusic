package com.lucamusic.user.controller;

import javax.validation.Valid;

import com.lucamusic.user.service.UserService;
import com.lucamusic.user.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucamusic.user.entity.User;
import org.springframework.web.server.ResponseStatusException;

/**
 * Nombre de la clase: UserControl
 * Esta clase se encarga de poner en uso los eventos de UserRepository
 * @author:Emanuel
 * @version: 14/09/2021/v1
 */

@Slf4j
@RestController
@RequestMapping("/users")
public class UserControl {
	@Autowired 
	private UserService userServ;

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody  User user, BindingResult result){
		log.info("Creating User: {}", user);
		if(result.hasErrors()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Utils.formatBindingResult(result));
		}
		User userDB = userServ.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDB);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
		log.info("Fetching User with id {}", id);
		User user = userServ.findByID(id);
		if(user == null){
			log.error("#########sUser with id {} not found", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
}
