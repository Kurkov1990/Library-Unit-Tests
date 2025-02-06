package com.homework.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private static Library library;

    @BeforeAll
    public static void setup() {
        library = new Library();
    }

    @AfterEach
    public void init() {
        library.clearLibrary();
    }

    @Test
    @DisplayName("Test if book is really added.")
    void whenAddBook_ThenBookIsAdded() {
        Book book = new Book("Taras Shevchenko", "Kobzar");
        int expectedBookCount = 1;

        library.addBook(book);

        assertEquals(expectedBookCount, library.getBookCount(),
                "Library should have at least 1 product!");
    }

    @Test
    @DisplayName("When null book is adding then exception can be thrown.")
    public void whenAddNullBook_ThenThrowException() {
        Book invalidProduct = null;
        String expectedExceptionMessage = "Parameter [book] must not be null!";
        NullPointerException exception =
                assertThrows(NullPointerException.class, () ->
                        library.addBook(invalidProduct));

        assertEquals(expectedExceptionMessage, exception.getMessage(),
                "Incorrect exception message!");
    }

    @Test
    @DisplayName("Test if book is really removed.")
    void whenRemoveExistingBook_ThenBooktIsRemoved() {
        Book book = new Book("Taras Shevchenko", "Kobzar");
        int expectedBookCount = 0;

        library.addBook(book);

        boolean isProductRemoved = library.removeBook(book);

        assertTrue(isProductRemoved, "Result should be 'true'!");
        assertEquals(expectedBookCount, library.getBookCount(),
                "Library shouldn't have any books!");
    }

    @Test
    @DisplayName("Test removing non-existing book.")
    public void whenRemoveNonExistingBook_ThenBookIsNotRemoved() {
        Book book = new Book("Taras Shevchenko", "Kobzar");
        Book toRemove = new Book("Stephen King", "It");
        int expectedBookCount = 1;

        library.addBook(book);

        boolean isBookRemoved = library.removeBook(toRemove);

        assertFalse(isBookRemoved, "Result should be 'false'!");
        assertEquals(expectedBookCount, library.getBookCount(),
                "Book should have at least 1 book!");
    }

    @Test
    @DisplayName("When null book is removing then exception can be thrown.")
    public void whenRemoveNullBook_ThenThrowException() {
        Book toRemove = null;
        String expectedExceptionMessage =
                "Parameter [book] must not be null!";

        NullPointerException exception =
                assertThrows(NullPointerException.class,
                        () -> library.removeBook(toRemove));

        assertEquals(expectedExceptionMessage, exception.getMessage(),
                "Incorrect exception message!");
    }

    @Test
    @DisplayName("Test adding duplicate books.")
    void whenAddDuplicateBooks_ThenBothAreStored() {
        Book book = new Book("Dune", "Frank Herbert");

        library.addBook(book);
        library.addBook(book);

        assertEquals(2, library.getBookCount(), "Library should have 2 books!");
    }

    @Test
    @DisplayName("Test if library returns correct books list.")
    void whenGetBooks_ThenReturnCorrectList() {
        Book book1 = new Book("George R. R. Martin", "Game of Thrones");
        Book book2 = new Book("Stephen King", "The Green Mile");

        library.addBook(book1);
        library.addBook(book2);

        List<Book> books = library.getBooks();

        assertEquals(2, books.size(), "Library should have exactly 2 books!");
        assertTrue(books.containsAll(List.of(book1, book2)), "Library should contain both books!");
    }
}
