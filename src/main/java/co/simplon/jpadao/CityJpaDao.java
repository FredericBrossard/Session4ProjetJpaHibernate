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
		return null;
	}

	@Override
	public City getCityById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City updateCity(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCityById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
