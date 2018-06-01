package com.fiiadmission.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "region",length=30)
	private String region;

	@OneToMany(mappedBy = "region")
	private List<City> cities;

	@OneToMany(mappedBy = "region")
	private List<Highschool> highschools;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Highschool> getHighschools() {
		return highschools;
	}

	public void setHighschools(List<Highschool> highschools) {
		this.highschools = highschools;
	}
}