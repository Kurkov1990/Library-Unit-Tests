package com.homework.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        Objects.requireNonNull(book,
                "Parameter [book] must not be null!");
        books.add(book);
    }

    public boolean removeBook(Book book) {
        Objects.requireNonNull(book,
                "Parameter [book] must not be null!");
        return books.remove(book);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public int getBookCount() {
        return books.size();
    }

    public void clearLibrary(){
        books.clear();
    }
}
