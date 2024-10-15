package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String lastName;
	private String password;
	private ArrayList<Task> tasks;

	public User() {
		tasks = new ArrayList<Task>();
	}

	public User(String name, String lastName, String password, ArrayList<Task> tasks) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.password = password;
		this.tasks = tasks;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", password=" + password + "]";
	}

}
