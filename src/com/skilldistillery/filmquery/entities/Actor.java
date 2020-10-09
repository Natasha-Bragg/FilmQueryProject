package com.skilldistillery.filmquery.entities;

public class Actor {
	private int id;
	  private String firstName;
	  private String lastName;
	  private String film;
	  
	  
	public Actor(int id, String firstName, String lastName, String film) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.film = film;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFilm() {
		return film;
	}
	public void setFilm(String film) {
		this.film = film;
	}
	@Override
	public String toString() {
		return "Actor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", film=" + film + "]";
	}
	  
	  

}
