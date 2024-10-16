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

import model.dao.TaskDao;
import model.entities.Task;

public class TaskDaoJdbc implements TaskDao {

	private Connection conn;

	public TaskDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Optional<Task> getById(Integer id) {
		Optional<Task> task = Optional.empty();

		try (PreparedStatement st = conn.prepareStatement("SELECT * FROM tasks WHERE id=?")) {
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				task = Optional.of(instantiateTask(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return task;
	}

	@Override
	public List<Task> getAll() {
		List<Task> tasks = new ArrayList<Task>();

		try (PreparedStatement st = conn.prepareStatement("SELECT * FROM tasks")) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				tasks.add(instantiateTask(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return tasks;
	}

	@Override
	public void add(Task t) {
		try (PreparedStatement st = conn.prepareStatement(
				"INSERT INTO tasks (title, moment, done, user_id) VALUES (?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {
			st.setString(1, t.getTitle());
			st.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(t.getMoment()));
			st.setBoolean(3, t.getDone());
			st.setInt(4, t.getUserId());

			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				t.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Task t) {
		try (PreparedStatement st = conn
				.prepareStatement("UPDATE tasks SET title=?, moment=?, done=?, user_id=? WHERE id=?")) {
			st.setString(1, t.getTitle());
			st.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(t.getMoment()));
			st.setBoolean(3, t.getDone());
			st.setInt(4, t.getUserId());
			st.setInt(5, t.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Task t) {
		try (PreparedStatement st = conn.prepareStatement("DELETE FROM tasks WHERE id=?");) {
			st.setInt(1, t.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Task instantiateTask(ResultSet rs) throws SQLException, ParseException {
		Task t = new Task();
		t.setId(rs.getInt("id"));
		t.setMoment(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("moment")));
		t.setTitle(rs.getString("title"));
		t.setDone(rs.getBoolean("done"));
		t.setUserId(rs.getInt("user_id"));
		return t;
	}

}
