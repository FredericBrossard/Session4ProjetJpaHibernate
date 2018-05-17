package co.simplon.jpadao;

import javax.persistence.EntityManager;

import co.simplon.dao.CityDao;
import co.simplon.model.City;

public class CityJpaDao implements CityDao{
	EntityManager em;
	
	public CityJpaDao(EntityManager em) {
	this.em = em;
	}
	
	@Override
	public City createCity(City city) {
		em.persist(city);
		return city;
	}

	@Override
	public City getCityById(Long id) {
		return em.find(City.class, id);
	}

	@Override
	public City updateCity(City city) {
		//cityResult est en objet managé
		City cityResult = em.merge(city);
		return cityResult;
	}

	@Override
	public void deleteCityById(Long id) {	
		em.remove(getCityById(id));
		
	}

}
