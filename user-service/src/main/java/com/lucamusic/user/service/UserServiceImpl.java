package com.lucamusic.user.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucamusic.user.entity.User;
import com.lucamusic.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
* Nombre de la clase: UserServiceImpl
 * Esta clase es la encargada de ejecutar los metodos del UserService
 * @author:Emanuel
 * @version: 14/09/2021/v1
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	public User createUser(User user) {
		User userDB = userRepo.findByEmail(user.getEmail());
		if(userDB != null) {
			 return userDB;
		}
		user.setStatus("CREATED");
		user.setRegisterDate(LocalDate.now());
		return userRepo.save(user);
	}

	@Override
	public User findByID(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	/*@Override
	public void deleteUser(User user) {
            Optional<User> userDB = userRepo.findById(user.getId());
                if (userDB == null){
                    log.error("Deleting user error...");
                }
		//return eventDB.orElseGet(() -> {
                log.info("Deleting user...");
                user.setStatus("DELETED");
                userRepo.save(user);

	}*/

        /*@Override
        public User modifyUser(User user) {
            Optional<User> userDB = userRepo.findById(user.getId());


            if(userDB.isPresent()) {
                     return userDB.get();
            }

            return userRepo.save(user);
        }*/

        /*@Override
        public List<User> usersByStatus(String status) {
            return userRepo.findAllByStatus(status);
        }*/

    


}
