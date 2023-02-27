public class UserDB {

	User[] users;

	public UserDB() {
		users = new User[4];
		users[0] = new User("user1", "user1");
		users[1] = new User("user2", "user2");
		users[2] = new User("user3", "user3");
		users[3] = new User("user4", "user4");
	}

	public User[] getUsers() {
		return users;
	}

	public void setUsers(User[] users) {
		this.users = users;
	}

}