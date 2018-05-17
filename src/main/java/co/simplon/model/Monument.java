package co.simplon.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MONUMENT")
@NamedQueries({
@NamedQuery(name = "Monument.findAll", query = " SELECT c FROM Monument c"),
@NamedQuery(name = "Monument.deleteById", query = " DELETE FROM Monument c WHERE c.id = :id") })
public class Monument {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "monument_generator")
	@SequenceGenerator(name="monument_generator", sequenceName = "monument_seq", allocationSize=1)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	
	//Exo 6
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cities_id", foreignKey= @ForeignKey(name = "fk_cities_id"))
	 private City city; 
	
	@ManyToMany(mappedBy="monuments", cascade = CascadeType.ALL)
	private Set<User> users = new HashSet<User>();
	
	/*public Monument(String name) {
		super();
		this.name = name;
	}*/
	
	public Monument(String name, City city) {
		super();
		this.name = name;
		this.city = city;
	}

	public Monument() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	  public City getCity() { return city; };
	  
	  public void setCity(City city) { this.city = city; };
	 
	@Override
	public String toString() {
		return "Monument [id=" + id + ", name=" + name + ", city=" + city + "]";
	}

	public Set<User> getUsers() {
		return this.users;	
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
