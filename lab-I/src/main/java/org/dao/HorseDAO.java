package org.dao;

import org.config.DBConnectionPool;
import org.data.Horse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HorseDAO {
	private DBConnectionPool connectionPool;

	public HorseDAO() {
		try {
			this.connectionPool = DBConnectionPool.getInstance();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Horse save(Horse horse) {
		Connection connection = connectionPool.getConnection();
		try {
			String query = String.format("insert into %s (%s) value (?)", Horse.DB_NAME, Horse.Columns.NICKNAME);
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, horse.getHorseNickname());

			statement.executeUpdate();
			statement.close();

			return horse;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	public List<Horse> findAll() {
		List<Horse> horses = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try {
			String query = String.format("select * from %s", Horse.DB_NAME);
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				horses.add(map(res));
			}
			statement.close();
			return horses;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	private Horse map(ResultSet resultSet) throws SQLException {
		if (resultSet == null) {
			return null;
		}
		Horse horse = new Horse();
		horse.setId(resultSet.getLong(Horse.Columns.ID.toString()));
		horse.setHorseNickname(resultSet.getString(Horse.Columns.NICKNAME.toString()));
		return horse;
	}

}
