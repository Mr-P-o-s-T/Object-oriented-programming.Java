package org.data;

public class Horse {
	private Long id;
	private String horseNickname;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setHorseNickname(String horseNickname) {
		this.horseNickname = horseNickname;
	}

	public String getHorseNickname() {
		return horseNickname;
	}

	public static final String DB_NAME = "horses";

	public enum Columns {
		ID("id"),
		NICKNAME("nickname");

		Columns(String columnName) {
			this.columnName = columnName;
		}

		public String toString() {
			return columnName;
		}

		private String columnName;
	}
}
