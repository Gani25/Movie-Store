package src.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.models.Movie;
import src.main.models.Store;

public class StoreTest {

    Store store;

    @Before // before annotation going to run before each test
    public void setup() {
        store = new Store();
        store.addMovie(new Movie("The Shawshank Redemption", "Blue-Ray", 9.2));
        store.addMovie(new Movie("The Godfather", "Blue-Ray", 9.1));
        // checking movie is present or not in movieAdded() 1st we need to add the
        // movie.

    }

    @Test
    public void movieAdded() {
        // 1st Test case: Test if the store contains the movie after it's added.

        // to check addMovie method working or not properly
        // assertTrue this will return true if movie is present
        assertTrue(store.contains(new Movie("The Godfather", "Blue-Ray", 9.1)));
        // when visualizing using debugger contains method goes to store.contains()
        // store.contains() thn goes to arraList method from where it is then come to
        // Movie.java where it check from our equals() method which we override from
        // object class
        // in our equals method we are not comparing the reference instead we are
        // checking the fields are equal or not
    }

    @Test
    public void sellMovieTest() {
        store.sellMovie("The Shawshank Redemption");
        // this test will pass if store.contain(The Shawshank redemtion) returns false
        assertFalse(store.contains(new Movie("The Shawshank Redemption", "Blue-Ray", 9.2)));
        // assertion passes becoz our movies doesn't contain anymore
    }

    @Test
    public void rentMovieTest() {
        store.rentMovie("The Godfather");
        assertFalse(store.getMovie(1).isAvailable()); // check if movie available after rented
    }

    @Test
    public void returnMovieTest() {
        store.rentMovie("The Godfather");
        store.returnMovie("The Godfather");
        assertTrue(store.getMovie(1).isAvailable());
    }

    @Test(expected = IllegalStateException.class)
    public void movieNotInStock() {
        store.rentMovie("The Godfather");
        store.sellMovie("The Godfather");
    }

}
