package co.simplon.jpadao;

import javax.persistence.EntityManager;

import co.simplon.dao.MonumentDao;
import co.simplon.model.City;
import co.simplon.model.Monument;

public class MonumentJpaDao implements MonumentDao {
EntityManager em;
	
	public MonumentJpaDao(EntityManager em) {
	this.em = em;
	}
	
	@Override
	public Monument createMonument(Monument monument) {
		em.persist(monument);
		return monument;
	}

	@Override
	public Monument getMonumentById(Long id) {	
		return em.find(Monument.class, id);
		
	}

	@Override
	public Monument updateMonument(Monument monument) {
		em.merge(monument);
		return monument;
	}

	@Override
	public void deleteMonumentById(Long id) {
		em.remove(getMonumentById(id));
		
	}
	
	
}
