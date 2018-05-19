package co.simplon.dao;
// interface "Igenericdao" paramétrée par le type "grand T"
public interface IgenericDAO<T> {
	// 3 méthodes qui prennent un argument grand T et qui retourne une reference de type grand T
	public T create(T objet);
	public T update(T objet);
	public T getById(Long id);
	void deleteById(Long id);
}
