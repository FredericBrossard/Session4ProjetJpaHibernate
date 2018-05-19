package co.simplon.jpadao;

import javax.persistence.EntityManager;

import co.simplon.model.City;

public class CityJpaDaoFactorise extends GenericJpaDao<City>{

	public CityJpaDaoFactorise(EntityManager em) {
		super(City.class, em);
	}
	
	
}
