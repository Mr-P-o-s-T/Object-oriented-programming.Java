package org.post.microservice.business.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private Set<Bet> bets = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getName() {
		return getFirstname() + " " + getLastname();
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<Bet> getBets() {
		return bets;
	}

	public void setBets(Set<Bet> bets) {
		this.bets = bets;
	}

	public void addBet(Bet bet) {
		bets.add(bet);
	}

	public void addBetSymm(Bet bet) {
		bet.setClient(this);
		addBet(bet);
	}
}
