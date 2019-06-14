package org.post.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "race_cast")
public class RaceCast implements Serializable {
	@Id @GeneratedValue
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "race_id")
	private Race race;

	@ManyToOne
	@JoinColumn(name = "horse_id")
	private Horse horse;

	@Column(name = "jockey_firstname")
	private String jockeyFirstname;

	@Column(name = "jockey_lastname")
	private String jockeyLastname;

	@Column(name = "coefficient")
	private Float coefficient;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Horse getHorse() {
		return horse;
	}

	public void setHorse(Horse horse) {
		this.horse = horse;
	}

	public String getJockeyFirstname() {
		return jockeyFirstname;
	}

	public void setJockeyFirstname(String jockeyFirstname) {
		this.jockeyFirstname = jockeyFirstname;
	}

	public String getJockeyLastname() {
		return jockeyLastname;
	}

	public void setJockeyLastname(String jockeyLastname) {
		this.jockeyLastname = jockeyLastname;
	}

	public Float getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Float coefficient) {
		this.coefficient = coefficient;
	}
}
