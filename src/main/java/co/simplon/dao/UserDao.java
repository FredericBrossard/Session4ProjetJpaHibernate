package co.simplon.dao;

import co.simplon.model.User;

public interface UserDao {
	 User createUser(User user);
     User getUserById(Long id);
     User updateUser(User user);
     void deleteUserById(Long id);
}
