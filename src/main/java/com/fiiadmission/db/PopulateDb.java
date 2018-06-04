package com.fiiadmission.db;

import com.fiiadmission.domain.*;
import com.fiiadmission.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private HighSchoolRepository highSchoolRepository;

    public void run(ApplicationArguments args) {
        Role standardRole = null;
        Role adminRole = null;
        if(roleRepository.count() == 0) {
            standardRole = this.createRole("STANDARD_USER", "Standard User - Has no admin rights");
            adminRole = this.createRole("ADMIN_USER", "Admin User - Has permission to perform admin tasks");
        }

        if(userRepository.count() == 0){
            this.createUser("John", "Doe", "jwtpass", "johndoe", standardRole, "PENDING", "john.doe@mail.com");
            this.createUser("Andrei", "Popa", "jwtpass", "andrei.popa", standardRole,"PENDING", "andrei.popa@mail.com");
            this.createUser("Admin", "Admin", "jwtpass", "admin.admin", adminRole, "", "admin.admin@mail.com");
        }

        if(announcementRepository.count() == 0){
            this.createAnnouncement("Examenul se sustine pe 21 iulie la ora 11");
            this.createAnnouncement("Rezultatele vor fi postate pe site o saptamana mai tarziu");
            this.createAnnouncement("Locurile trebuiesc confirmate");
        }

        if(countryRepository.count() == 0){
        	Country romania = this.createCountry("Romania");
            Country spania = this.createCountry("Spania");
            Region iasiR = this.createRegion("Iasi", romania);
            Region catalunia = this.createRegion("Catalunia", spania);
            City barcelona = this.createCity("Barcelona", catalunia);
            City pascani = this.createCity("Pascani", iasiR);
            this.createHighschool("Colegiul Pascani", iasiR);

            Region botosaniR = this.createRegion("Botosani", romania);
            City botosaniC = this.createCity("Botosani", botosaniR);
            this.createHighschool("Colegiul National Mihai Eminescu", botosaniR);
        }
    }

    private Role createRole(String roleName, String descrption){
        Role role = new Role();
        role.setDescription(descrption);
        role.setRoleName(roleName);
        return roleRepository.save(role);
    }

    private void createUser(String firstName, String lastName, String password, String username, Role role, String admissionStatus, String email){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(shaPasswordEncoder.encodePassword(password,""));
        user.setUsername(username);
        user.setRole(role);
        user.setAdmissionStatus(admissionStatus);
        user.setEmail(email);
        userRepository.save(user);
    }

    private void createAnnouncement(String info){
        Announcement announcement = new Announcement();
        announcement.setInfo(info);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        announcement.setExpiry_date(new Timestamp(cal.getTime().getTime()));
        announcementRepository.save(announcement);
    }

    private Country createCountry(String name){
        Country country = new Country();
        country.setCountry(name);
        return countryRepository.save(country);
    }

    private Region createRegion(String name, Country country){
        Region region = new Region();
        region.setRegion(name);
        region.setCountry(country);
        return regionRepository.save(region);
    }

    private City createCity(String name, Region region){
        City city = new City();
        city.setCity(name);
        city.setRegion(region);
        return cityRepository.save((city));
    }

    private Highschool createHighschool(String name, Region region){
        Highschool highschool = new Highschool();
        highschool.setHighSchoolName(name);
        highschool.setRegion(region);
        return highSchoolRepository.save(highschool);
    }
}
