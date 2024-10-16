package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.dao.UserDao;
import model.entities.Task;
import model.entities.User;

public class UserDaoJdbc implements UserDao {

	private Connection conn;

	public UserDaoJdbc(Connection conn) {
		this.conn = conn;
	}
	
	public Optional<User> getUsername(String username) {
		Optional<User> u = Optional.empty();

		try (PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE username=?")) {
			st.setString(1, username);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				u = Optional.of(instantiateUser(rs));
				User user = u.get();
				user.getTasks().addAll(getUserTaskList(user));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	@Override
	public Optional<User> getById(Integer id) {
		Optional<User> u = Optional.empty();

		try (PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE id=?")) {
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				u = Optional.of(instantiateUser(rs));
				User user = u.get();
				user.getTasks().addAll(getUserTaskList(user));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();

		try (PreparedStatement st = conn.prepareStatement("SELECT * FROM users")) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				users.add(instantiateUser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public void add(User u) {
		try (PreparedStatement st = conn.prepareStatement(
				"INSERT INTO users (name, username, password) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			st.setString(1, u.getName());
			st.setString(2, u.getUsername());
			st.setString(3, u.getPassword());

			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				u.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User u) {
		try (PreparedStatement st = conn
				.prepareStatement("UPDATE users SET name=?, username=?, password=? WHERE id=?")) {
			st.setString(1, u.getName());
			st.setString(2, u.getUsername());
			st.setString(3, u.getPassword());
			st.setInt(4, u.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User u) {
		try (PreparedStatement st = conn.prepareStatement("DELETE FROM users WHERE id=?");) {
			st.setInt(1, u.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private List<Task> getUserTaskList(User user) {
		List<Task> tasks = new ArrayList<Task>();

		try (PreparedStatement st2 = conn.prepareStatement("SELECT * FROM tasks WHERE user_id=?")) {
			st2.setInt(1, user.getId());
			ResultSet rs = st2.executeQuery();

			while (rs.next()) {
				Task t = new Task();

				t.setId(rs.getInt("id"));
				t.setTitle(rs.getString("title"));
				t.setMoment(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("moment")));
				t.setDone(rs.getBoolean("done"));
				t.setUserId(rs.getInt("user_id"));

				tasks.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return tasks;
	}

	private static User instantiateUser(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setName(rs.getString("name"));
		u.setUsername(rs.getString("username"));
		u.setPassword(rs.getString("password"));
		return u;
	}

}
