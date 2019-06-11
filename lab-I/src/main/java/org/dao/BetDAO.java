package org.dao;

import org.config.DBConnectionPool;
import org.data.Bet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BetDAO {

	private DBConnectionPool connectionPool;

	public BetDAO() {
		try {
			this.connectionPool = DBConnectionPool.getInstance();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Bet save(Bet bet) {
		Connection connection = connectionPool.getConnection();
		try {
			connection.setAutoCommit(false);
			String query =
					String.format("insert into %s (%s, %s, %s, %s, %s, %s, %s) values (?, ?, ?, ?, ?, ?, ?)",
							Bet.DB_NAME, Bet.Columns.CLIENT_ID, Bet.Columns.RACE_ID, Bet.Columns.HORSE_ID,
							Bet.Columns.BET, Bet.Columns.BET_TYPE, Bet.Columns.SCND_HORSE_ID,
							Bet.Columns.THRD_HORSE_ID);
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, bet.getClientId());
			statement.setLong(2, bet.getRaceId());
			statement.setLong(3, bet.getHorseId());
			statement.setFloat(4, bet.getBet());
			statement.setString(5, bet.getBetType());
			statement.setLong(6, bet.getScndHorseId());
			statement.setLong(7, bet.getThrdHorseId());

			statement.executeUpdate();
			connection.commit();
			statement.close();

			return bet;
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

	public List<Bet> findAll() {
		List<Bet> bets = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try {
			String query = String.format("select * from %s", Bet.DB_NAME);
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				bets.add(map(res));
			}
			statement.close();
			return bets;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	private Bet map(ResultSet resultSet) throws SQLException {
		if (resultSet == null) return null;

		Bet bet = new Bet();
		bet.setId(resultSet.getLong(Bet.Columns.ID.toString()));
		bet.setClientId(resultSet.getLong(Bet.Columns.CLIENT_ID.toString()));
		bet.setRaceId(resultSet.getLong(Bet.Columns.RACE_ID.toString()));
		bet.setRaceId(resultSet.getLong(Bet.Columns.HORSE_ID.toString()));
		bet.setBet(resultSet.getFloat(Bet.Columns.BET.toString()));
		bet.setBetType(resultSet.getString(Bet.Columns.BET_TYPE.toString()));
		bet.setScndHorseId(resultSet.getLong(Bet.Columns.SCND_HORSE_ID.toString()));
		bet.setThrdHorseId(resultSet.getLong(Bet.Columns.THRD_HORSE_ID.toString()));
		return bet;
	}
}
