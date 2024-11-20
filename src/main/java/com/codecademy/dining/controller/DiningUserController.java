package com.codecademy.dining.controller;

import com.codecademy.dining.exception.DuplicateEntityException;
import com.codecademy.dining.model.DiningUser;
import com.codecademy.dining.repository.DiningUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dining/users")
public class DiningUserController {

    private final DiningUserRepository userRepository;

    public DiningUserController(DiningUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path="/", method=RequestMethod.GET)
    public Iterable<DiningUser> findAllUsers() {
        return this.userRepository.findAll();
    }

    @RequestMapping(path="/", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public DiningUser addNewUser(@RequestBody DiningUser newUser) {
        if (newUser.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name field is required");
        }
        if (this.userRepository.findByName(newUser.getName()).isPresent()) {
            throw new DuplicateEntityException();
        }

        return this.userRepository.save(newUser);
    }

    @RequestMapping(path="/{username}", method=RequestMethod.GET)
    public DiningUser findUser(@PathVariable("username") String username) {
        Optional<DiningUser> optionalUser = this.userRepository.findByName(username);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "diningUser with name " + username + " does not exist");
        }
        return optionalUser.get();
    }

    @RequestMapping(path="/{username}", method=RequestMethod.PUT)
    public DiningUser updateUser(@PathVariable("username") String username, @RequestBody DiningUser updatedUser) {
        Optional<DiningUser> optionalUser = this.userRepository.findByName(username);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "diningUser with name " + username + " does not exist");
        }
        DiningUser userToUpdate = optionalUser.get();
        updateUserFields(userToUpdate, updatedUser);
        return this.userRepository.save(userToUpdate);
    }

    private void updateUserFields(DiningUser userToUpdate, DiningUser updatedFields) {
        userToUpdate.setName(updatedFields.getName());
        userToUpdate.setCity(updatedFields.getCity());
        userToUpdate.setState(updatedFields.getState());
        userToUpdate.setZipCode(updatedFields.getZipCode());
        userToUpdate.setPeanutAllergy(updatedFields.isPeanutAllergy());
        userToUpdate.setEggAllergy(updatedFields.isEggAllergy());
        userToUpdate.setDairyAllergy(updatedFields.isDairyAllergy());
    }
}
