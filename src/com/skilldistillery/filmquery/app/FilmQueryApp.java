package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
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

	private void startUserInterface(Scanner input) throws SQLException {
		List<Film> films = new ArrayList<>();
		boolean menu = true;

		System.out.println("Welcome to BlockBuster+ ");
		while (menu) {
			System.out.println(
					"1. Look up a film by its id.\n2. Look up a film by a search keyword.\n3. Exit the application.");

			int choice = 0;

			try {
				choice = input.nextInt();

			} catch (InputMismatchException e) {
				String wrongInput = input.next();
				System.out.println("You entered an Invalid input of: " + wrongInput + ". \nPlease enter 1-3.");
				continue;
			}

			switch (choice)

			{
			case 1:
				System.out.println("Enter Film Id: ");
				int filmId = input.nextInt();
				Film film = db.findFilmById(filmId);
				if(film == null) {
					System.out.println("No Matching Films. Please Try Again.");
				}
				else {
					printFilm(film);

				}
				break;
			case 2:
				System.out.println("Please enter keyword to search: ");
				String keyword = input.next();
				db.findFilmsByKeyword(keyword);
				List<Film> film2 = db.findFilmsByKeyword(keyword);
				if (film2.size() == 0) {
					System.out.println(
							"\nWe could not find the film that you're looking for." + "\n\tPlease try again\n");
				} else {
					printFilmsByKeyword(film2);
				}

				break;

			case 3:
				System.out.println("Goodbye.");
				menu = false;
				break;
			default:
				System.out.println("Please choose again!");
			}
		}
	}

	private void printFilmsByKeyword(List<Film> films) {
		System.out.println("Films found for this keyword are: ");
		for (Film film : films) {
			printFilm(film);
		}

	}

	private void printFilm(Film film) {
		System.out.println("Film title: " + film.getTitle() + "\nRelease Year: " + film.getRelease_year() + "\nRating: "
				+ film.getRating() + "\nDescription: " + film.getDescription() + "\nLanguage: "
				+ film.getLanguage_id());
		printActors(film.getActors());
	}

	private void printActors(List<Actor> actors) {
		System.out.println("List of Actors: ");
		for (Actor actor : actors) {
			System.out.println("\t" + actor.getFirstName() + " " + actor.getLastName());
		}
	}

}
