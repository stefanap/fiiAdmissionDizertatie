package com.fiiadmission.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.jboss.aerogear.security.otp.api.Base32;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "app_user")
public class User {
	
    public User() {
        this.secret=Base32.random();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name",length=30)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name",length=50)
    private String firstName;

    @Column(name = "last_name",length=30)
    private String lastName;
    
    @Column(name = "email",length=50)
    private String email;
    
    @Column(name = "admission_status",length=30)
    private String admissionStatus;
    
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private AdmissionData admissionData;

    @Column(name = "secret")
    private String secret;
    
    @Column(name ="register_date")
    private Timestamp registerDate;
    
    @Column(name="register_number")
    private Long registerNumber;
    
    
	/**
     * Roles are being eagerly loaded here because
     * they are a fairly small collection of items for this example.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public Long getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(Long registerNumber) {
		this.registerNumber = registerNumber;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAdmissionStatus() {
		return admissionStatus;
	}

	public void setAdmissionStatus(String admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	public AdmissionData getAdmissionData() {
		return admissionData;
	}

	public void setAdmissionData(AdmissionData admissionData) {
		this.admissionData = admissionData;
	}

    public String getSecret() {
        return secret;
    }
    
    
}

