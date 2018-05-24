package com.fiiadmission.db;

import com.fiiadmission.domain.Announcement;
import com.fiiadmission.domain.Role;
import com.fiiadmission.domain.User;
import com.fiiadmission.repository.AnnouncementRepository;
import com.fiiadmission.repository.RoleRepository;
import com.fiiadmission.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * This class will run every time the app is started.
 */
@Component
public class PopulateDb implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @Autowired
    private AnnouncementRepository announcementRepository;

    public void run(ApplicationArguments args) {
        Role standardRole = null;
        Role adminRole = null;
        if(roleRepository.count() == 0) {
            standardRole = this.createRole("STANDARD_USER", "Standard User - Has no admin rights");
            adminRole = this.createRole("ADMIN_USER", "Admin User - Has permission to perform admin tasks");
        }

        if(userRepository.count() == 0){
            this.createUser("John", "Doe", "jwtpass", "john.doe", Arrays.asList(standardRole), "PENDING", "john.doe@mail.com");
            this.createUser("Andrei", "Popa", "jwtpass", "andrei.popa", Arrays.asList(standardRole),"PENDING", "andrei.popa@mail.com");
            this.createUser("Admin", "Admin", "jwtpass", "admin.admin", Arrays.asList(adminRole), "", "admin.admin@mail.com");
        }

        if(announcementRepository.count() == 0){
            this.createAnnouncement("Examenul se sustine pe 21 iulie la ora 11");
            this.createAnnouncement("Rezultatele vor fi postate pe site o saptamana mai tarziu");
            this.createAnnouncement("Locurile trebuiesc confirmate");
        }
    }

    private Role createRole(String roleName, String descrption){
        Role role = new Role();
        role.setDescription(descrption);
        role.setRoleName(roleName);
        return roleRepository.save(role);
    }

    private void createUser(String firstName, String lastName, String password, String username, List<Role> roles, String admissionStatus, String email){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(shaPasswordEncoder.encodePassword(password,""));
        user.setUsername(username);
        user.setRoles(roles);
        user.setAdmissionStatus(admissionStatus);
        user.setEmail(email);
        userRepository.save(user);
    }

    private void createAnnouncement(String info){
        Announcement announcement = new Announcement();
        announcement.setInfo(info);
        announcementRepository.save(announcement);
    }
}
