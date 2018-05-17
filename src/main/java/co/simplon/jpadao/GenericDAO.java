package co.simplon.jpadao;

import javax.persistence.EntityManager;

import co.simplon.dao.IgenericDAO;

public abstract class GenericDAO<T> implements IgenericDAO<T> {
private Class<T> myclass = null;
private EntityManager em;

public GenericDAO(Class<T> myclass, EntityManager em) {
	super();
	this.myclass = myclass;
	this.em = em;
}


}
