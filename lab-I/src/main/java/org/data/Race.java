package org.data;

import java.sql.Date;

public class Race {
	private String id = "one";
	private String racecourse;
	private Date date;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setRacecourse(String racecourse) {
		this.racecourse = racecourse;
	}

	public String getRacecourse() {
		return racecourse;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public static final String DB_NAME = "races";

	public enum Columns {
		ID("id"),
		RACECOURSE("racecourse"),
		DATE("date");

		Columns(String columnName) {
			this.columnName = columnName;
		}

		public String toString() {
			return columnName;
		}

		private String columnName;
	}
}
