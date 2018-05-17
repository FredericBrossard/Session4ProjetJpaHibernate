package co.simplon.dao;

public interface IgenericDAO<T> {
	public T create(T instance);
	public T update(T instance);
	public T getById(Long id);
	void deleteById(Long id);
}
