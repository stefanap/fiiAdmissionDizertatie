package com.fiiadmission.domain;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "city",length=30)
	private String city;

	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
}