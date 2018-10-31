package co.simplon.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/*
* classe User qui permet de modéliser des utilisateurs de notre application.
* Chaque utilisateur peut avoir visité plusieurs monuments et chaque monument peut avoir été visité par
* plusieurs utilisateurs.
*/

@Entity
	@Table(name = "USERS")
	public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID")
		private Long id;

		@Column(name = "NAME", nullable = false, length = 100)
		private String name;
		
		@ManyToMany
	        @JoinTable(name= "USER_MONUMENT",
	                   joinColumns = {@JoinColumn(name = "FK_USER", referencedColumnName= "ID" ) },
	                   inverseJoinColumns = { @JoinColumn(name = "FK_MONUMENT", referencedColumnName= "ID") })
	        private Set<Monument> monuments = new HashSet<Monument>();
		
		@ManyToMany(mappedBy="monuments", cascade = CascadeType.ALL)
		private Set<User> users = new HashSet<User>();

		
		/*public User(String name, Monument monument) {
			this.name = name;
			monuments.add(monument);
		}*/
		public User() {
			
		}
			
		public User(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name= name;
		}
		public void addMonument(Monument m){
			//ajout de momnument dans l'objet liste monuments
			monuments.add(m);
			//ajout de user(objet this) à table User du user qui a visité le monument m
			m.getUsers().add(this);
		}
		public Set<Monument> getMonuments(){
			return monuments;
		}
		public void setMonuments(Set<Monument> monuments) {
			this.monuments= monuments;
		}
		public String toString() {
			return "User :{ id= "+id+"\n name= "+name+"\n nb momunents"+ monuments.size()+"\n}";
		}

		public Long getId() {
			return id;
		}
		
}
