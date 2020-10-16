package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String pass = "student";
	
	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	}

  @Override
  public Film findFilmById(int filmId) throws SQLException{
	  Film film = null;
	  Connection conn = DriverManager.getConnection(URL, user, pass);
	  String sqltxt =  "SELECT film.*, language.name FROM film JOIN language ON film.language_id = "
				+ "language.id WHERE film.id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setInt(1, filmId);
		
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setRelease_year(rs.getInt("release_year"));
			film.setLanguage_id(rs.getString("name"));
			film.setRental_duration(rs.getInt("rental_duration"));
			film.setRental_rate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacement_cost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecial_features(rs.getString("special_features"));
			film.setActors(findActorsByFilmId(filmId));
			
			
	      }
	    stmt.close();
		conn.close();
	
		return film;
	}	
	  


@Override
public Actor findActorById(int actorId) throws SQLException {
	 Actor actor = null;
	 Connection conn = DriverManager.getConnection(URL, user, pass);
	 String sql = "SELECT * FROM actor WHERE id = ?";
	 PreparedStatement stmt = conn.prepareStatement(sql);
	 stmt.setInt(1, actorId);
	  ResultSet actorResult = stmt.executeQuery();
	  if (actorResult.next()) {
	    actor = new Actor(); 
	    actor.setId(actorResult.getInt("id"));
		actor.setFirstName(actorResult.getString("first_name"));
		actor.setLastName(actorResult.getString("last_name"));	 
		actor.setFilms(findFilmsByActorId(actorId));
	  }
	  stmt.close();
	  conn.close();

	  return actor;
	
}

@Override
public List<Actor> findActorsByFilmId(int filmId) {
	
	List<Actor> actors = new ArrayList<Actor>();
	try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sqltxt = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
				+ "WHERE film_actor.film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Actor actor = new Actor();
			actor.setId(rs.getInt("id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			actors.add(actor);
		}
	
	rs.close();
	stmt.close();
	conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return actors;
}

@Override
public List<Film> findFilmsByKeyword(String keyword) {
	List<Film> films = new ArrayList<Film>();
	
	String sql = "SELECT * FROM film JOIN language ON language.id = film.language_id WHERE film.title LIKE ? OR film.description LIKE ? ";
	try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + keyword + "%");
		stmt.setString(2, "%" + keyword + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Film film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setRelease_year(rs.getInt("release_year"));
			film.setLanguage_id(rs.getString("name"));
			film.setRental_duration(rs.getInt("rental_duration"));
			film.setRental_rate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacement_cost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecial_features(rs.getString("special_features"));
			film.setActors(findActorsByFilmId(rs.getInt("id")));

			films.add(film);
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return films;
}

@Override
public List<Film> findFilmsByActorId(int actorId) {
	List<Film> films = new ArrayList<Film>();
	String sql = "SELECT film.* from film JOIN film_actor on film.id ="
			+ "film_actor.film_id WHERE film_actor.actor_id = ?";
	try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Film film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setRelease_year(rs.getInt("release_year"));
			film.setLanguage_id(rs.getString("name"));
			film.setRental_duration(rs.getInt("rental_duration"));
			film.setRental_duration(rs.getInt("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacement_cost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecial_features(rs.getString("special_features"));

			films.add(film);
		}
		rs.close();
		stmt.close();
		conn.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}

	return films;
}


}






	
	



