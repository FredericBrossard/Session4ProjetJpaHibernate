package co.simplon.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//on a annoter une classe java standard pour gérer une connexion
@Entity
@Table(name="CITIES")
@NamedQueries({
@NamedQuery(name = "City.findAll", query = " SELECT c FROM City c ORDER BY c.name "),
@NamedQuery(name = "City.deleteById", query = " DELETE FROM City c WHERE c.id = :id") })
public class City {
	//les annotations@ indiquent COMMENT FAIRE CORRESPONDrE à la classe City, une table qui aura les memes attributs que la classe
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "cities_generator")
		@SequenceGenerator(name="cities_generator", sequenceName = "cities_seq", allocationSize=1)
		@Column(name="ID")
		 private Long id;
		
		@Column(name="NAME", nullable=false, length=255)
		 private String name;
		
		@Column(name="LATITUDE", nullable=false)
		 private Double latitude;
		
		@Column(name="LONGITUDE", nullable=false)
		 private Double longitude;
		 //Exo 6
		@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
		private List<Monument> monuments = new ArrayList<Monument>();
		//private Set<Monument> monuments;
				 
		 public City() {
		 }
		 
		 
		 //Constructeur qui appelle le constructeur dessus
		 public City(String name, double latitude, double longitude) {
		 this(null, name, latitude, longitude);
		 }
		 //Constructeur avec un Id. this indique que c'est un constructeur de la classe. sI SUPER, cela indique que l'on appelle un constructeur de classe parente
		 public City(Long id, String name, double latitude, double longitude) {
		 this.id= id;
		 this.name= name;
		 this.latitude= latitude;
		 this.longitude= longitude;
		 }
		
		 
		 public Long getId() {
		 return id;
		 }
		
		 public void setId(Long id) {
		 this.id = id;
		 }
		
		 public String getName() {
		 return name;
		
		 }
		
		 public void setName(String nom) {
		 this.name = nom;
		 }
		
		 public Double getLongitude() {
		 return this.longitude;
		 }
		
		 public void setLongitude(Double longitude) {
		 this.longitude = longitude;
		 }
		
		 public Double getLatitude() {
		 return this.latitude;
		 }
		
		 public void setLatitude(Double latitude) {
		 this.latitude = latitude;
		 }
		
		 @Override
		 public String toString() {
		 return "City [id=" + id + ", name=" + name + ", latitude=" + latitude
		 + ", longitude=" + longitude + "]";
		 }
		 
}
