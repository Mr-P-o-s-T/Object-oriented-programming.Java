package org.data;

public class Client {
	private Long id;
	private String firstname;
	private String lastname;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return lastname;
	}

	public static final String DB_NAME = "clients";

	public enum Columns {
		ID("id"),
		FIRSTNAME("firstname"),
		LASTNAME("lastname");

		Columns(String columnName) {
			this.columnName = columnName;
		}

		public String toString() {
			return columnName;
		}

		private String columnName;
	}
}
