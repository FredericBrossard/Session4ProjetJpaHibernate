package co.simplon.dao;
//interface "IgenericDAObis" param�tr�e par le type "grand T"
public interface IgenericDAObis<T> {
	// 3 m�thodes qui prennent un argument grand T et qui retourne une reference de type grand T
	T create (T Objet);
	T update (T Objet);
	T getById (Long Id);
	void deleteById (Long Id);
	
	
	/*Une interface d�finit un comportement (d'une classe) qui doit �tre impl�ment� 
	par une classe, sans impl�menter ce comportement. C'est un ensemble de m�thodes abstraites,
	et de constantes. ... Les diff�rences entre les interfaces et les classes abstraites :
		Une interface n'impl�mente aucune m�thode.
			*/
}
