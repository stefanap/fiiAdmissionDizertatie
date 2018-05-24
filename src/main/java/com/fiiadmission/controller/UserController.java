package com.fiiadmission.controller;

import org.jboss.aerogear.security.otp.Totp;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import utils.AdmissionStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import com.fiiadmission.domain.Role;
import com.fiiadmission.domain.User;
import com.fiiadmission.repository.RoleRepository;
import com.fiiadmission.service.UserService;

import javax.websocket.server.PathParam;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    ShaPasswordEncoder passwordEncoder;

    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public @ResponseBody User response(@RequestBody User user) {
        //encode the password
    	user.setRoles(Arrays.asList(roleRepository.findByRoleName("STANDARD_USER")));
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), ""));
        return userService.save(user);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public @ResponseBody User response(@RequestBody User user,@PathVariable("id") Long id) {
    	User searchedUser = userService.findById(id);
    	searchedUser.setEmail(user.getEmail());
    	searchedUser.setPassword(user.getPassword());
    	searchedUser.setRoles(user.getRoles());
    	searchedUser.setAdmissionStatus(user.getAdmissionStatus());
        return userService.save(searchedUser);
    }
    
    @GetMapping(value ="/{status}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getByStatus( @PathVariable("status") String status){
        return userService.findAllUsersWithStatus(status.toUpperCase());
    }

    @GetMapping(value ="/search")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(@RequestParam(value = "firstname") Optional<String> firstName,
            @RequestParam("lastname") Optional<String> lastName){
        return userService.findByName(firstName, lastName);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }

    @GetMapping(value = "/{id}/qr-code")
    //@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public String getUsers(@PathVariable("id") Long id){
        User user = this.userService.findById(id);
        String url = "";
        try {
            url = userService.generateQRUrl(user);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}
