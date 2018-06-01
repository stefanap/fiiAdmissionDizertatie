package com.fiiadmission.api.controller;

import com.fiiadmission.api.dto.UserDTO;
import com.fiiadmission.api.dto.mappers.UserMapper;
import com.fiiadmission.api.exceptions.BadRequestException;
import com.fiiadmission.api.exceptions.NotFoundException;
import com.fiiadmission.service.RoleService;
import com.fiiadmission.utils.Constants;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.fiiadmission.domain.EmptyJsonResponse;
import com.fiiadmission.domain.User;
import com.fiiadmission.repository.RoleRepository;
import com.fiiadmission.service.UserService;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/fii/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    ShaPasswordEncoder passwordEncoder;
    
    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMIN_USER')")
    public @ResponseBody UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) throws BadRequestException, NotFoundException {
        if(userDTO.getId() != id){
            throw new BadRequestException("Path id and user id do not match");
        }
    	User searchedUser = userService.findById(id);
        if(searchedUser == null){
            throw new NotFoundException("User with id=" + id + " was not found");
        }
    	searchedUser.setEmail(userDTO.getEmail());
        return UserMapper.INSTANCE.toUserDTO(userService.save(searchedUser));
    }

    @GetMapping(value ="/search")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<UserDTO> getUsers(@RequestParam(value = "firstname") Optional<String> firstName,
            @RequestParam("lastname") Optional<String> lastName){
        return UserMapper.INSTANCE.toUserDTOs(userService.findByName(firstName, lastName));
    }

    @GetMapping(value ="/{id}")
    public UserDTO getUser(@PathVariable("id") Long id) throws NotFoundException {
        User user= userService.findById(id);
        if(user == null){
            throw new NotFoundException("User with id=" + id + " was not found");
        }

        return UserMapper.INSTANCE.toUserDTO(user);
    }
    
    @GetMapping
    public List<UserDTO> getUsers(){
        return UserMapper.INSTANCE.toUserDTOs(userService.findAllUsers());
    }
}
