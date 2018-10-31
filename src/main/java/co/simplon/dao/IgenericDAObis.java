package co.simplon.dao;
//interface "IgenericDAObis" paramétrée par le type "grand T"
public interface IgenericDAObis<T> {
	// 3 méthodes qui prennent un argument grand T et qui retourne une reference de type grand T
	T create (T Objet);
	T update (T Objet);
	T getById (Long Id);
	void deleteById (Long Id);
	
	
	/*Une interface définit un comportement (d'une classe) qui doit être implémenté 
	par une classe, sans implémenter ce comportement. C'est un ensemble de méthodes abstraites,
	et de constantes. ... Les différences entre les interfaces et les classes abstraites :
		Une interface n'implémente aucune méthode.
			*/
}
