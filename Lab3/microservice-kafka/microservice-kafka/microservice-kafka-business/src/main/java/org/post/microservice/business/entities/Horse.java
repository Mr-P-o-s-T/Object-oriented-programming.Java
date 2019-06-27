package org.post.microservice.business.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "horses")
public class Horse implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "nickname")
	private String horseNickname;

	@OneToMany(mappedBy = "horse", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RaceCast> raceCasts = new HashSet<>();

	@OneToOne(mappedBy = "firstHorse", cascade = CascadeType.ALL, orphanRemoval = true)
	private Bet firstPlaceBet;

	@OneToOne(mappedBy = "secondHorse", cascade = CascadeType.ALL, orphanRemoval = true)
	private Bet secondPlaceBet;

	@OneToOne(mappedBy = "thirdHorse", cascade = CascadeType.ALL, orphanRemoval = true)
	private Bet thirdPlaceBet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHorseNickname() {
		return horseNickname;
	}

	public void setHorseNickname(String horseNickname) {
		this.horseNickname = horseNickname;
	}

	public Set<RaceCast> getRaceCasts() {
		return raceCasts;
	}

	public void setRaceCasts(Set<RaceCast> raceCasts) {
		this.raceCasts = raceCasts;
	}

	public void addRaceCast(RaceCast raceCast) {
		raceCasts.add(raceCast);
	}

	public void addRaceCastSymm(RaceCast raceCast) {
		raceCast.setHorse(this);
		addRaceCast(raceCast);
	}

	public Bet getFirstPlaceBet() {
		return firstPlaceBet;
	}

	public void setFirstPlaceBet(Bet firstPlaceBet) {
		this.firstPlaceBet = firstPlaceBet;
	}

	public Bet getSecondPlaceBet() {
		return secondPlaceBet;
	}

	public void setSecondPlaceBet(Bet secondPlaceBet) {
		this.secondPlaceBet = secondPlaceBet;
	}

	public Bet getThirdPlaceBet() {
		return thirdPlaceBet;
	}

	public void setThirdPlaceBet(Bet thirdPlaceBet) {
		this.thirdPlaceBet = thirdPlaceBet;
	}
}
