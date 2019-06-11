package org.data;

public class Bet {
	private Long id;
	private Long clientId;
	private Long raceId;
	private Long horseId;
	private Float bet;
	private String betType;
	private Long scndHorseId;
	private Long thrdHorseId;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setRaceId(Long raceId) {
		this.raceId = raceId;
	}

	public Long getRaceId() {
		return raceId;
	}

	public void setHorseId(Long horseId) {
		this.horseId = horseId;
	}

	public Long getHorseId() {
		return horseId;
	}

	public void setBet(Float bet) {
		this.bet = bet;
	}

	public Float getBet() {
		return bet;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public String getBetType() {
		return betType;
	}

	public void setScndHorseId(Long scndHorseId) {
		this.scndHorseId = scndHorseId;
	}

	public Long getScndHorseId() {
		return scndHorseId;
	}

	public void setThrdHorseId(Long thrdHorseId) {
		this.thrdHorseId = thrdHorseId;
	}

	public Long getThrdHorseId() {
		return thrdHorseId;
	}

	public static final String DB_NAME = "bets";

	public enum Columns {
		ID("id"),
		CLIENT_ID("client_id"),
		RACE_ID("race_id"),
		HORSE_ID("horse_id"),
		BET("bet"),
		BET_TYPE("bet_type"),
		SCND_HORSE_ID("scnd_horse_id"),
		THRD_HORSE_ID("thrd_horse_id");

		Columns(String columnName) {
			this.columnName = columnName;
		}

		public String toString() {
			return columnName;
		}

		private String columnName;
	}
}
