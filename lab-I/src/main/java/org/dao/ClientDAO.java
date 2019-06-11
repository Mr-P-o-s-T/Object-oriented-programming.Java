package org.dao;

import org.config.DBConnectionPool;
import org.data.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO {

	private DBConnectionPool connectionPool;

	public ClientDAO() {
		try {
			this.connectionPool = DBConnectionPool.getInstance();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Client save(Client client) {
		Connection connection = connectionPool.getConnection();
		try {
			String query =
					String.format(
							"insert into %s (%s, %s) values (?, ?)",
							Client.DB_NAME, Client.Columns.FIRSTNAME, Client.Columns.LASTNAME);

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, client.getFirstname());
			statement.setString(2, client.getLastname());
			statement.executeUpdate();
			statement.close();
			return client;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	public Client findByName(String name) {
		Connection connection = connectionPool.getConnection();
		Client client = null;
		try {
			String query = String.format("select * from %s where concat(firstname, ' ',lastname)='%s'", Client.DB_NAME, name);
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet res = statement.executeQuery();
			while (res.next()) {
				client = map(res);
			}
			statement.close();
			return client;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	public List<Client> findAll() {
		List<Client> clients = new ArrayList<>();
		Connection connection = connectionPool.getConnection();
		try {
			String query = String.format("select * from %s", Client.DB_NAME);
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				clients.add(map(res));
			}
			statement.close();
			return clients;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			connectionPool.releaseConnection(connection);
		}
	}

	private Client map(ResultSet resultSet) throws SQLException {
		if (resultSet == null) {
			return null;
		}
		Client client = new Client();
		client.setId(resultSet.getLong(Client.Columns.ID.toString()));
		client.setFirstname(resultSet.getString(Client.Columns.FIRSTNAME.toString()));
		client.setLastname(resultSet.getString(Client.Columns.LASTNAME.toString()));

		return client;
	}
}
