package co.simplon.jpadao;

import javax.persistence.EntityManager;

import co.simplon.dao.UserDao;
import co.simplon.model.City;
import co.simplon.model.User;

public class UserJpaDao implements UserDao {
EntityManager em;

	public UserJpaDao(EntityManager em) {
	super();
	this.em = em;
}

	@Override
	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User getUserById(Long id) {
		User resultUser = em.find(User.class, id);
		return resultUser;
	}

	@Override
	public User updateUser(User user) {
		user = em.merge(user);
		return user;
	}

	@Override
	public void deleteUserById(Long id) {
		em.remove(id);
		
	}

}
