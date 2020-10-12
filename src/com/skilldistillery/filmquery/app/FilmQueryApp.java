package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	ArrayList<Film> filmDB = new ArrayList<Film>();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(2);
		System.out.println(film);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	@SuppressWarnings("unlikely-arg-type")
	private void startUserInterface(Scanner input) throws SQLException {

		System.out.println(
				"1. Look up a film by its id.\n2. Look up a film by a search keyword.\n3. Exit the application.");

		int choice = input.nextInt();

		switch (choice)

		{
		case 1:
			System.out.println("Enter Film Id: ");
			int filmId = input.nextInt();
			Film film;
			try {
				film = db.findFilmById(filmId);
				System.out.println("Title: " + film.getTitle());
				System.out.println("Year Released: " + film.getRelease_year());
				System.out.println("MPAA Rating: " + film.getRating());
				System.out.println("Film Description: " + film.getDescription());
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
		break;
		
		
		case 2 :
			System.out.println("Enter Film Keyword: ");
			String keyword = input.next();
			
			for (Film film2 : filmDB) {
				if(keyword.equals(film2.getTitle())) {
					
				}
				
				System.out.println(film2);
			}
			
			
			

//			if(keyword.equals(db.toString().contains(keyword)))
//				if(keyword.equals(db.)
			
			
			
			break;
			
			
			
			case 3 : System.out.println("Goodbye.");
			break;
			default: System.out.println("Wrong Choice!");
		}
	}
}
