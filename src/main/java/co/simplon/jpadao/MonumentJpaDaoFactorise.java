package co.simplon.jpadao;

import javax.persistence.EntityManager;

import co.simplon.model.Monument;

public class MonumentJpaDaoFactorise extends GenericJpaDao<Monument>{

	public MonumentJpaDaoFactorise(EntityManager em) {
		super(Monument.class, em);
	}

}
