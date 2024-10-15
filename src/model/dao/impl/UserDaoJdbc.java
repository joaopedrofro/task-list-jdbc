package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import model.dao.UserDao;
import model.entities.Task;
import model.entities.User;

public class UserDaoJdbc implements UserDao {

	private Connection conn;

	public UserDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public User getById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		User u = null;

		try {
			st = conn.prepareStatement("SELECT * FROM users WHERE id=?");
			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				u = instantiateUser(rs);

				List<Task> tasks = new ArrayList<Task>();

				st = conn.prepareStatement("SELECT * FROM tasks WHERE user_id=?");
				st.setInt(1, u.getId());
				rs = st.executeQuery();

				while (rs.next()) {
					Task t = new Task();

					t.setId(rs.getInt("id"));
					t.setTitle(rs.getString("title"));
					t.setMoment(rs.getDate("moment"));
					t.setDone(rs.getBoolean("done"));

					tasks.add(t);
				}

				u.getTasks().addAll(tasks);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeResultSet(rs);
			Database.closeStatement(st);
		}

		return u;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User u) {
		// TODO Auto-generated method stub

	}

	private static User instantiateUser(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setName(rs.getString("name"));
		u.setLastName(rs.getString("last_name"));
		return u;
	}

}
