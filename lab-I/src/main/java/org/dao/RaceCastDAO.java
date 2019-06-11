package org.dao;

import org.config.DBConnectionPool;
import org.data.Race;
import org.data.RaceCast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RaceCastDAO {
	private DBConnectionPool connectionPool;

	public RaceCastDAO() {
		try {
			this.connectionPool = DBConnectionPool.getInstance();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public RaceCast save(RaceCast raceCast) {
		Connection connection = connectionPool.getConnection();
		try {
			connection.setAutoCommit(false);
			String query =
					String.format(
							"insert into %s (%s, %s, %s, %s, %s) values (?, ?, ?, ?, ?)", RaceCast.DB_NAME,
							RaceCast.Columns.RACE_ID, RaceCast.Columns.HORSE_ID, RaceCast.Columns.JOCKEY_FIRSTNAME,
							RaceCast.Columns.JOCKEY_LASTNAME, RaceCast.Columns.COEFFICIENT);
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, raceCast.getRaceId());
			statement.setLong(2, raceCast.getHorseId());
			statement.setString(3, raceCast.getJockeyFirstname());
			statement.setString(4, raceCast.getJockeyLastname());
			statement.setFloat(5, raceCast.getCoefficient());

			statement.executeUpdate();
			connection.commit();
			statement.close();

			return raceCast;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new RuntimeException(e.getMessage());
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connectionPool.releaseConnection(connection);
		}
	}

	public List<RaceCast> findAll() {
		List<RaceCast> raceCasts = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try {
			String query = String.format("select * from %s", RaceCast.DB_NAME);
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				raceCasts.add(map(res));
			}
			statement.close();
			return raceCasts;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	private RaceCast map(ResultSet resultSet) throws SQLException {
		if (resultSet == null) {
			return null;
		}
		RaceCast raceCast = new RaceCast();
		raceCast.setId(resultSet.getLong(RaceCast.Columns.ID.toString()));
		raceCast.setRaceId(resultSet.getString(RaceCast.Columns.RACE_ID.toString()));
		raceCast.setHorseId(resultSet.getLong(RaceCast.Columns.HORSE_ID.toString()));
		raceCast.setJockeyFirstname(resultSet.getString(RaceCast.Columns.JOCKEY_FIRSTNAME.toString()));
		raceCast.setJockeyLastname(resultSet.getString(RaceCast.Columns.JOCKEY_LASTNAME.toString()));
		raceCast.setCoefficient(resultSet.getFloat(RaceCast.Columns.COEFFICIENT.toString()));
		return raceCast;
	}
}
