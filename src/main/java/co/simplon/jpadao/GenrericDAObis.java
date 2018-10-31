package co.simplon.jpadao;

import javax.persistence.EntityManager;

import co.simplon.dao.IgenericDAObis;

public abstract class GenrericDAObis<T> implements IgenericDAObis<T>{
	private EntityManager em;
	
	private Class<T> myClass;

	public GenrericDAObis(EntityManager em, Class<T> myClass) {
		this.setEm(em);
		this.setMyClass(myClass);
	}

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
	
	
	

}
