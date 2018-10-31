package co.simplon.jpadao;

import javax.persistence.EntityManager;
import co.simplon.dao.IgenericDAO;

/*En programmation orientée objet (POO), une classe abstraite est une classe 
dont l'implémentation n'est pas complète et qui n'est pas instanciable. 
Elle sert de base à d'autres classes dérivées (héritées).
*/
public abstract class GenericJpaDao<T> implements IgenericDAO<T> {

	private Class<T> myClass;

	private EntityManager em;

	public Class<T> getMyClass() {
		return myClass;
	}

	public void setMyClass(Class<T> myClass) {
		this.myClass = myClass;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public GenericJpaDao(Class<T> myClass, EntityManager em) {
		this.myClass = myClass;
		this.em = em;
	}

	public T create(T objet) {
		em.persist(objet);
		return objet;
	}


	public T getById(Long id) {
		return em.find(this.myClass, id);
	}

	public T update(T objet) {
		return em.merge(objet);
	}

	public void deleteById(Long id) {
		em.remove(getById(id));

	}

	public String getClassName() {
		return this.myClass.getSimpleName();
	}
	
	
	
}
