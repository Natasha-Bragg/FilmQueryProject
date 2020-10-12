package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	
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
	  String user = "student";
	  String pass = "student";
	  Connection conn = DriverManager.getConnection(URL, user, pass);
	  String sqltxt =  "SELECT * FROM film WHERE id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setInt(1, filmId);
		
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			film = new Film();
			film.setId(filmId);
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setRelease_year(rs.getInt("release_year"));
			film.setLanguage_id(rs.getString("language_id"));    
			film.setRental_duration(rs.getInt("rental_duration"));
			film.setRental_rate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacement_cost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecial_features("special_features");
			film.setActors(this.findActorsByFilmId(filmId));
			
	      }
	    stmt.close();
		conn.close();
	
		return film;
	}	
	  


@Override
public Actor findActorById(int actorId) throws SQLException {
	 Actor actor = null;
	 String user = "student";
	 String pass = "student";
	 Connection conn = DriverManager.getConnection(URL, user, pass);
	 String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
	 PreparedStatement stmt = conn.prepareStatement(sql);
	 stmt.setInt(1,actorId);
	  ResultSet actorResult = stmt.executeQuery();
	  if (actorResult.next()) {
	    actor = new Actor(); 
	    actor.setId(actorResult.getInt(1));
	    actor.setFirstName(actorResult.getString(2));
	    actor.setLastName(actorResult.getString(3));
	  }
	  stmt.close();
	  conn.close();

	  return actor;
	
}

@Override
public List<Actor> findActorsByFilmId(int filmId) {
	 List<Actor> actorList = new ArrayList<>();
	 String user = "student";
	 String pass = "student";
	  try {
	    Connection conn = DriverManager.getConnection(URL, user, pass);
	    String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
	                sql += " rental_rate, length, replacement_cost, rating, special_features "
	               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
	               + " WHERE actor_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, filmId);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	      int filmId1 = rs.getInt(1);
	      String title = rs.getString(2);
	      String desc = rs.getString(3);
	      short releaseYear = rs.getShort(4);
	      int langId = rs.getInt(5);
	      int rentDur = rs.getInt(6);
	      double rate = rs.getDouble(7);
	      int length = rs.getInt(8);
	      double repCost = rs.getDouble(9);
	      String rating = rs.getString(10);
	      String features = rs.getString(11);
	      Actor actor = new Actor();
	      actorList.add(actor);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  return actorList;
	}
	
	
}


