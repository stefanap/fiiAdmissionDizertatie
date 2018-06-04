package com.fiiadmission.api.controller;

import com.fiiadmission.api.dto.UserDTO;
import com.fiiadmission.api.dto.mappers.UserMapper;
import com.fiiadmission.api.exceptions.BadRequestException;
import com.fiiadmission.domain.User;
import com.fiiadmission.service.EmailService;
import com.fiiadmission.service.RoleService;
import com.fiiadmission.service.UserService;
import com.fiiadmission.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    ShaPasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createUser(@RequestBody UserDTO userDTO) throws UnsupportedEncodingException, BadRequestException {
        User user = UserMapper.INSTANCE.toUser(userDTO);

        // validate that the username is not used
        if(userService.findByUsername(user.getUsername()) != null){
            throw new BadRequestException("Username already used.");
        }
        //validate that the email is not used
        if(userService.findByEmail(user.getEmail()) != null){
            throw new BadRequestException("Email already used.");
        }
        //encode the password
        user.setRole(roleService.findByRoleName(Constants.STANDARD_USER));
        user.setPassword(passwordEncoder.encodePassword(userDTO.getPassword(), Constants.APP_SALT));
        user.setAdmissionStatus(Constants.ADMISION_STATUS_PENDING);
        user = userService.save(user);
        String qrUrl = userService.generateQRUrl(user);

        //send email with the qr url
        this.sendRegistrationEmail(user, qrUrl);

        return qrUrl;
    }

    private void sendRegistrationEmail(User user, String qrUrl){
        String subject = "Fii Admission QR CODE";
        String to = user.getEmail();
        String bodyHtml = "<img src=\"" + qrUrl + "\">";

        emailService.sendEmail(subject,to,"",bodyHtml);
    }

//    @GetMapping(value = "/{id}/qr-code")
//    public String qrCodeLink(@PathVariable("id") Long id){
//        User user = this.userService.findById(id);
//        String url = "";
//        try {
//            url = userService.generateQRUrl(user);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return url;
//    }
}
