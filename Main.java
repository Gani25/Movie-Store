import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import models.Movie;
import models.Store;

public class Main {
    static Store store = new Store(); // class variable

    public static void main(String[] args) {
        System.out.println("\n********************JAVA VIDEO STORE********************\n");
        try {
            loadMovies("movies.txt");
            System.out.println("Movies Loaded\n\n");
            System.out.println(store);
            manageMovies();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Name: manageMovies
     * Inside the function:
     * • 1. Starts a new instance of Scanner;
     * • 2. In an infinite loop, the user can choose to a) purchase b) rent c)
     * return d) exit.
     * • case a: ask for the name and sell.
     * • case b: ask for the name and rent.
     * • case c: ask for the name and return.
     * • 3. call close() from the Scanner object.
     */
    public static void manageMovies() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nWould you like to \n\ta) purchase\n\tb) rent \n\tc) return.");
            String response = sc.nextLine();
            if (!(response.equals("a") || response.equals("b") || response.equals("c"))) {
                System.out
                        .println("\n\n********************Thanks for visiting our movie store********************\n\n");
                sc.close();

                break;
            }
            System.out.print("Enter the name of the movie: "); // we will ask user movie name that
            // they wanna rent, purchase or return;
            String name = sc.nextLine();
            if (store.getMovie(name) == null) {
                // this work when the user give name which is not available in store and if
                // itpasses blank
                System.out.println("\n\nThe input you provided is not valid. Please try again\n");
                continue;
            }
            switch (response) {
                case "a":
                    if (!(store.getMovie(name).isAvailable())) {
                        // this will become true if the movie is rented and user tries to buy
                        System.out.println("\n\n\n\nThe movie is not available for purchase. Please try again\n");
                        continue;
                    }
                    store.action(name, "sell");
                    break;
                case "b":
                    store.action(name, "rent");
                    break;
                case "c":
                    store.action(name, "return");
                    break;
            }
            System.out.println("\n\nUPDATED MOVIES\n\n" + store);
        }
    }

    /**
     * Name: loadMovies
     * 
     * @param fileName (String)
     * @throws FileNotFoundException
     *
     *                               Inside the function:
     *                               • 1. loads movies from <fileName>.txt.
     *                               • 2. adds all movies to the store object's
     *                               movie field.
     *                               Hint: You will need to 'split' a String into
     *                               three Strings.
     */

    public static void loadMovies(String fileName) throws FileNotFoundException {
        // this function will read the txt file which we will pass and will fetch data
        // and add in our store
        FileInputStream fis = new FileInputStream("movies.txt");
        Scanner scFile = new Scanner(fis);

        while (scFile.hasNextLine()) {
            String line = scFile.nextLine();
            String word[] = line.split("--");
            store.addMovie(new Movie(word[0], word[1], Double.parseDouble(word[2])));
        }
        scFile.close();
    }

}

/*
 * Inside main
 * Movie movie = new Movie("Bazigar", "Blue-Ray", 7.5);
 * movie.setFormat("DVD");
 * System.out.println(movie);
 * Movie movie2 = new Movie(movie); // copy every object from movie
 * movie2.setAvailable(false);
 * 
 * System.out.println(movie2);
 */

/*
 * inside main no need for this bcoz we are loading file directly
 * store.addMovie(new Movie("The Shawshank Redemption", "Blue-Ray", 9.2));
 * store.addMovie(new Movie("The Godfather", "Blue-Ray", 9.1));
 * store.addMovie(new Movie("The Godfather: Part II", "DVD", 9.0));
 * System.out.println(store);
 * store.action("The Shawshank Redemption", "rent");
 * 
 * System.out.println(store);
 * store.action("The Shawshank Redemption", "return");
 * 
 * System.out.println(store);
 */
