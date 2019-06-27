package org.post.microservice.business.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "racecourse")
	private String racecourse;

	@Column(name = "date")
	private Date date;

	@OneToMany(mappedBy = "race", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Bet> bets = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRacecourse() {
		return racecourse;
	}

	public void setRacecourse(String racecourse) {
		this.racecourse = racecourse;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Bet> getBet() {
		return bets;
	}

	public void setBet(Set<Bet> bets) {
		this.bets = bets;
	}

	public void addBet(Bet bet) {
		bets.add(bet);
	}

	public void addBetSymm(Bet bet) {
		bet.setRace(this);
		addBet(bet);
	}
}
