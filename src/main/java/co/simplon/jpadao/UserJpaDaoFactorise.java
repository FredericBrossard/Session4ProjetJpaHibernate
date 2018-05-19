package co.simplon.jpadao;

import javax.persistence.EntityManager;

import co.simplon.model.User;

public class UserJpaDaoFactorise extends GenericJpaDao<User>{

	public UserJpaDaoFactorise(EntityManager em) {
		super(User.class, em);
	}

}
