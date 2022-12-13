package models;

import java.util.ArrayList;

public class Store {
    ArrayList<Movie> movies;

    public Store() {
        this.movies = new ArrayList<Movie>();
    }

    public Movie getMovie(int index) {
        return new Movie(this.movies.get(index));
        // this getter will return a new copy of arraylist at that index
    }

    public Movie getMovie(String name) {
        /*
         * in main manageMovies function we wanted to check if the the movie is
         * rented than if user buy rented movie our application crashes
         * so this methodwill returns a movie based on name
         */
        for (int i = 0; i < this.movies.size(); i++) {
            if (this.movies.get(i).getName().equals(name)) {
                return new Movie(this.movies.get(i));// return tyhe movie object at that index
            }
        }
        return null; // if above return work than this won't execute
    }

    public void setMovie(int index, Movie movie) {
        this.movies.set(index, new Movie(movie));
        // if caller passes null object this method will throw null pointer exception
    }

    public void addMovie(Movie movie) {
        this.movies.add(new Movie(movie));
        // if caller passes null object this method will throw null pointer exception
    }

    public void action(String name, String action) {
        // if store objct doesnot have anymovie than its not in a legal state to call
        // the action method
        if (movies.isEmpty()) {
            throw new IllegalStateException("Store is not in a valid state to perform the action");
        }
        if (!(action.equals("sell") || action.equals("rent") || action.equals("return"))) {
            throw new IllegalArgumentException("action must be sell, rent or return");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null/blank");
        }
        for (int i = 0; i < this.movies.size(); i++) {
            if (this.movies.get(i).getName().equals(name)) {
                switch (action) {
                    case "sell":

                        if (!(movies.get(i).isAvailable())) {
                            throw new IllegalStateException("Cannot sell movie that was rented out");
                        }
                        this.movies.remove(i);
                        break;
                    case "rent":
                        this.movies.get(i).setAvailable(false);
                        break;
                    // if case is rent we will set the availability of movie to false
                    case "return":
                        this.movies.get(i).setAvailable(true);
                        break;
                }
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < movies.size(); i++) {
            temp += this.movies.get(i).toString();
            temp += "\n\n";
        }
        return temp;
    }

}

/*
 * this was initially written in Store class but both have the same
 * functionality so instead of writing 2 diff fun combine and write single fn
 * named as action
 * public void sellMovie(String name) {
 * for (int i = 0; i < movies.size(); i++) {
 * if (this.movies.get(i).getName().equals(name)) {
 * this.movies.remove(i);
 * }
 * }
 * }
 * 
 * public void rentMovie(String name) {
 * for (int i = 0; i < movies.size(); i++) {
 * if (this.movies.get(i).getName().equals(name)) {
 * this.movies.remove(i);
 * }
 * }
 * }
 */