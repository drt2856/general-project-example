package com.cu.generalprojectexample.controller;

import com.cu.generalprojectexample.dto.UserDto;
import com.cu.generalprojectexample.model.Authority;
import com.cu.generalprojectexample.model.User;
import com.cu.generalprojectexample.service.AuthorityService;
import com.cu.generalprojectexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    @GetMapping(path = {"/findAll"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> findAll() {
        try {
            return new ResponseEntity<List<UserDto>>(traslateListEntityToDto(userService.findAll()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = {"/find/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> findById(@PathVariable int id) {
        try {
            return new ResponseEntity<UserDto>(traslateEntityToDto(userService.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = {"/existByUsername/{username}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Boolean> existByUsername(@PathVariable int username) {

        return new ResponseEntity<Boolean>(userService.existByUsername(username), HttpStatus.OK);
    }


    @PostMapping(path = {"/create"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) throws URISyntaxException {
        
        User user = translateDtoToEntity(userDto);
        User result = userService.save(user);
        for (Authority a : userDto.getAuthorityList()) {
            authorityService.save(a);
        }

        System.out.println(result);
        return ResponseEntity.created(new URI("/User/create/" + result.getId())).body(result);
    }

    @PutMapping(path = {"/update"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> updateCarrera(@RequestBody User user) throws URISyntaxException {
        if (user.getId() == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        try {
            User result = userService.update(user);
            return ResponseEntity.created(new URI("/User/updated/" + result.getId())).body(result);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = {"/delete/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    private UserDto traslateEntityToDto(User user_entidad) {
        UserDto userDto = new UserDto(user_entidad);
        userDto.setAuthorityList(authorityService.findByUserId(user_entidad.getId()));
        return userDto;
    }

    private User translateDtoToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUserName(),userDto.getEmail(),userDto.getPassword(),userDto.getEnabled());
    }

    private List<UserDto> traslateListEntityToDto(List<User> userList) {
        List<UserDto> aux = new ArrayList<>();
        for (User user : userList) {
            aux.add(traslateEntityToDto(user));
        }
        return aux;
    }
}
