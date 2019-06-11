package org.data;

public class RaceCast {
	private Long id;
	private String raceId;
	private Long horseId;
	private String jockeyFirstname;
	private String jockeyLastname;
	private Float coefficient;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}

	public String getRaceId() {
		return raceId;
	}

	public void setHorseId(Long horseId) {
		this.horseId = horseId;
	}

	public Long getHorseId() {
		return horseId;
	}

	public void setJockeyFirstname(String jockeyFirstname) {
		this.jockeyFirstname = jockeyFirstname;
	}

	public String getJockeyFirstname() {
		return jockeyFirstname;
	}

	public void setJockeyLastname(String jockeyLastname) {
		this.jockeyLastname = jockeyLastname;
	}

	public String getJockeyLastname() {
		return jockeyLastname;
	}

	public void setCoefficient(Float coefficient) {
		this.coefficient = coefficient;
	}

	public Float getCoefficient() {
		return coefficient;
	}

	public static final String DB_NAME = "race_cast";

	public enum Columns {
		ID("id"),
		RACE_ID("race_id"),
		HORSE_ID("horse_id"),
		JOCKEY_FIRSTNAME("jockey_firstname"),
		JOCKEY_LASTNAME("jockey_lastname"),
		COEFFICIENT("coefficient");

		Columns(String columnName) {
			this.columnName = columnName;
		}

		public String toString() {
			return columnName;
		}

		private String columnName;
	}
}
