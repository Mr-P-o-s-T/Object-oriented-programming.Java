package org.post.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bets")
public class Bet implements Serializable {
	@Id @GeneratedValue
	@Column(name = "id")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "race_id", referencedColumnName = "id")
	private Race race;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "horse_id", referencedColumnName = "id")
	private Horse firstHorse;

	@Column(name = "bet")
	private Float bet;

	@Column(name = "bet_type")
	private String betType;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "scnd_horse_id")
	private Horse secondHorse;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "thrd_horse_id")
	private Horse thirdHorse;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		client.addBet(this);
		this.client = client;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		race.addBet(this);
		this.race = race;
	}

	public Horse getFirstHorse() {
		return firstHorse;
	}

	public void setFirstHorse(Horse horse) {
		horse.setFirstPlaceBet(this);
		this.firstHorse = horse;
	}

	public Float getBet() {
		return bet;
	}

	public void setBet(Float bet) {
		this.bet = bet;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public Horse getSecondHorse() {
		return secondHorse;
	}

	public void setSecondHorse(Horse horse) {
		horse.setSecondPlaceBet(this);
		this.secondHorse = horse;
	}

	public Horse getThirdHorse() {
		return thirdHorse;
	}

	public void setThirdHorse(Horse horse) {
		horse.setThirdPlaceBet(this);
		this.thirdHorse = horse;
	}
}
