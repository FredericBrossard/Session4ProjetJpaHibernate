
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import co.simplon.model.City;
import co.simplon.model.Monument;
import co.simplon.model.User;

public class App implements AutoCloseable {
	private static final String PERSISTENCE_UNIT_NAME = "demo-jpa-1";
	private EntityManagerFactory factory;

	// Constructeur App
	App() {
		Map<String, String> env = System.getenv();
		Map<String, Object> configOverrides = new HashMap<String, Object>();
		for (String envName : env.keySet()) {
			if (envName.contains("DB_USER")) {

				configOverrides.put("javax.persistence.jdbc.user", env.get(envName));
			}

			if (envName.contains("DB_PASS")) {
				configOverrides.put("javax.persistence.jdbc.password", env.get(envName));
			}

			if (envName.contains("DB_URL")) {
				configOverrides.put("javax.persistence.jdbc.url", env.get(envName));
			}
		}

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, configOverrides);
	}

	public void close() {
		factory.close();
	}

	public City createCity() {
		EntityManager em = factory.createEntityManager();
		City city = new City("Mer", 2, 0.5);
		city = create(em, city);
		em.close();
		return city;
	}

	public City create(EntityManager em, City city) {
		em.getTransaction().begin();
		em.persist(city);
		em.getTransaction().commit();
		return city;
	}

	/*
	 * public Monument createMonument() { EntityManager em =
	 * factory.createEntityManager(); Monument monument= new
	 * Monument("Tour Eiffel"); monument = create(em, monument); em.close(); return
	 * monument; }
	 */

	// Exo 6-2 Relation N-N
	public User createUser() {
		EntityManager em = factory.createEntityManager();
		User user = new User("Catwoman");
		City c = create(em, new City("London", 12., 4.));
		System.out.println("Cr�ation de la city :" + c);
		Monument m = create(em, new Monument("Big Ben", c));
		System.out.println("Creation du monument : " + m);
		
		user.addMonument(m);
		user = create(em, user);
		System.out.println("Creation du user : " + user);
		System.err.println("");
		System.err.println("--------------------------------------------");
		System.err.println("");
		
		
		System.err.println("Appel de delete city");
		deleteCity(c.getId());
		em.close();
		return user;
	}

	public User create(EntityManager em, User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}

	public Monument createMonument() {
		EntityManager em = factory.createEntityManager();
		Monument monument = new Monument("Effeil", createCity());
		monument = create(em, monument);
		em.close();
		return monument;
	}

	public Monument create(EntityManager em, Monument monument) {
		em.getTransaction().begin();
		em.persist(monument);
		em.getTransaction().commit();
		return monument;
	}

	public City createCityAndUpdate() {
		EntityManager em = factory.createEntityManager();
		City city = new City("Tokyo", 13.67, 0.5);
		em.getTransaction().begin();
		em.persist(city); // Persist fait un insert
		city.setLatitude(1000.);
		em.getTransaction().commit();// MAGIC HAPPENS HERE !
		em.close();
		return city;
	}

	public City updateCity() {
		return update(new City(6L, "PaRiS", -1., -2.));
	}

	public City update(City city) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		city = em.merge(city);
		city.setLatitude(111.); // Plac� ici alors maj en base postgres
		em.getTransaction().commit();
		// city.setLatitude(555.); //Plac� ici alors pas de maj en base postgres
		return city;
	}

	// m�thode de delete sur identifiant
	public void delete(City city) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		// City citie = readCity(em, 3L); ou ci dessous egalement
		City citie = em.find(City.class, 3L);
		em.remove(citie);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteCity(Long id) {
		City city = new City(id, "PaRiS", 111., -2.);
		delete2(city);
	}

	// m�thode de delete sur merge
	public void delete2(City city) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		City citie = em.merge(city);
		em.remove(citie);
		em.getTransaction().commit();
		em.close();
	}

	public City readCity() {
		EntityManager em = factory.createEntityManager();
		City city = readCity(em, 2L);
		city.setLatitude(5000.);

		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();
		return city;
	}

	public City readCity(EntityManager em, Long id) {
		return em.find(City.class, id);
	}

	public void selectCityAvecJpql() {
		EntityManager em = factory.createEntityManager();
		TypedQuery<City> query = em.createQuery("SELECT c FROM City AS c WHERE c.name=:nameParam", City.class);
		query.setParameter("nameParam", "Paris");
		for (City c : query.getResultList()) {
			System.out.println("Voici le r�sultat de la recherche en table Cities:" + c);
		}
		em.close();
	}
	
	public void selectMonumentAvecJpql() {
		EntityManager em = factory.createEntityManager();
		TypedQuery<Monument> query = em.createQuery("SELECT c FROM Monument AS c WHERE c.name=:nameParam", Monument.class);
		query.setParameter("nameParam", "Tour Effeil");
		for (Monument m : query.getResultList()) {
			System.out.println("Voici le r�sultat de la recherche en table Monument:" + m);
				}
		em.close();
	}

	public void listFindAllCities() {
		EntityManager em = factory.createEntityManager();
		System.out.println(em.createNamedQuery("City.findAll").getResultList());
		em.close();
	}
	
	public List<City> findAllCity(int first, int size) {
		EntityManager em = factory.createEntityManager();
		List<City> c = em.createNamedQuery("City.findAll", City.class).setFirstResult(first).setMaxResults(size).getResultList();
		System.out.println("Ici les villes:" + c); 
		em.close();
		return c;
		 }
	
	public List<Monument> findAllMonument(int first, int size) {
		EntityManager em = factory.createEntityManager();
		List<Monument> m = em.createNamedQuery("Monument.findAll", Monument.class).setFirstResult(first).setMaxResults(size).getResultList();
		System.out.println("Ici les monuments:" + m); 
		em.close();
		return m;
		 }
	
	public void deletebyIdCity() {
		EntityManager em = factory.createEntityManager();
	    em.getTransaction().begin();
		Query query = em.createNamedQuery("City.deleteById").setParameter("id", 1L);
		int nbDelete= query.executeUpdate();
		em.getTransaction().commit();
		System.out.println("Retour de la m�thode deletebyIdCity nombre de delete:" + nbDelete);
		em.close();
	}
	
	
	public static void main(String[] args) {
		try (App app = new App()) {
			System.out.println(app.createCity());
			// System.out.println(app.createCityAndUpdate());
			// System.out.println(app.readCity());
			// System.out.println(app.updateCity());
			//app.deleteCity();
			//System.out.println(app.createCity());
			//System.out.println(app.createMonument());
			
			/////System.out.println(app.createUser());
			//app.selectCityAvecJpql();
			//app.selectMonumentAvecJpql();
			//app.listFindAllCities();
			//app.findAllCity(0, 3);
			//app.findAllMonument(0, 2);
			app.deletebyIdCity();

		} catch (Exception e) {
			System.out.println("Exception captut�e : " + e);
		}
	}

}
