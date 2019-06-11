package org.dao;

import org.config.DBConnectionPool;
import org.data.Race;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RaceDAO {

	private DBConnectionPool connectionPool;

	public RaceDAO() {
		try {
			connectionPool = DBConnectionPool.getInstance();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Race save(Race race) {
		Connection connection = connectionPool.getConnection();
		try {
			connection.setAutoCommit(false);
			String query =
					String.format(
							"insert into %s (%s, %s) values (?, ?)", Race.DB_NAME, Race.Columns.RACECOURSE,
							Race.Columns.DATE);
			PreparedStatement statement =
					connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			statement.setString(1, race.getRacecourse());
			statement.setDate(2, race.getDate());

			statement.executeUpdate();
			statement.close();

			return race;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	public List<Race> findAll() {
		List<Race> races = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try {
			String query = String.format("select * from %s", Race.DB_NAME);
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				races.add(map(res));
			}
			statement.close();
			return races;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	private Race map(ResultSet resultSet) throws SQLException {
		if (resultSet == null) return null;

		Race race = new Race();
		race.setId(resultSet.getString(Race.Columns.ID.toString()));
		race.setRacecourse(resultSet.getString(Race.Columns.RACECOURSE.toString()));
		race.setDate(resultSet.getDate(Race.Columns.DATE.toString()));

		return race;
	}
}
