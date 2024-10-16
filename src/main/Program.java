package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;

import db.Database;
import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.dao.UserDao;
import model.entities.Task;
import model.entities.User;

public class Program {

	public static void main(String[] args) {
		Connection conn = Database.getConnection();
		
		initDatabase(conn);
		
		TaskDao td = DaoFactory.getTaskDao(conn);
		UserDao ud = DaoFactory.getUserDao(conn);
		Optional<User> u = ud.getById(1);
		
		if (u.isPresent()) {
			User user = u.get();
			System.out.println(user);
			System.out.println("Tarefas:");
			user.getTasks().forEach(System.out::println);
			System.out.println("Adicionando nova tarefa...");
			Task t = new Task(0, "Jogar lixo fora", new Date(), false, user.getId());
			td.add(t);
			user.getTasks().add(t);
			System.out.println("Tarefa nova:");
			System.out.println(t);
			
		} else {
			System.out.println("User not found!");
		}
		
		Database.closeConnection();
	}
	
	private static void initDatabase(Connection conn) {
		try (Statement st = conn.createStatement()) {
			st.executeUpdate("DROP TABLE IF EXISTS users");
			st.executeUpdate("DROP TABLE IF EXISTS tasks");
			st.executeUpdate(
					"CREATE TABLE users ("
					 + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					 + "name TEXT NOT NULL,"
					 + "username TEXT NOT NULL,"
					 + "password TEXT NOT NULL)");
			st.executeUpdate(
					"CREATE TABLE tasks ("
					 + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					 + "title TEXT NOT NULL,"
					 + "moment TEXT NOT NULL,"
					 + "done INTEGER NOT NULL,"
					 + "user_id INTEGER NOT NULL,"
					 + "FOREIGN KEY (user_id) REFERENCES users(id))");
			st.executeUpdate("INSERT INTO users (name, username, password) VALUES (\"admin\", \"admin\", \"123\")");
			st.executeUpdate("INSERT INTO users (name, username, password) VALUES (\"João Pedro\", \"joaop\", \"123\")");
			st.executeUpdate("INSERT INTO tasks (title, moment, done, user_id) VALUES (\"Task 1\", \"2024-10-16\", 0, 2)");
			st.executeUpdate("INSERT INTO tasks (title, moment, done, user_id) VALUES (\"Task 2\", \"2024-10-15\", 1, 2)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
