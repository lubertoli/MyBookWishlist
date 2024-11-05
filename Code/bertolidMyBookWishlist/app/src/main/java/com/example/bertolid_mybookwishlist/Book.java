package com.example.bertolid_mybookwishlist;


/**
 * Purpose: The Book class represents a single book in the wishlist. It holds all of the necessary
 * details of a books, such as the title, author, genre, publication year and read status.
 *
 * Design Rationale: The Book class holds all of the attributes related to a book and provides
 * getters for each one, facilitating the creation and management of books in the
 * wishlist. The book status is stored as a boolean in order to facilitate the toggle switch between
 * read and unread.
 *
 * Outstanding issues: None.
 */

public class Book {
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private boolean status;

    /**
     * Purpose: Constructor for Book object
     * @param title - title of the book
     * @param author - author of the book
     * @param genre - genre of the book
     * @param publicationYear - year the book was published
     * @param status - true = read and false = unread
     */
    public Book(String title, String author, String genre, int publicationYear, boolean status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.status = status;
    }

    // Getter method for title
    public String getTitle() {
        return title;
    }

    // Getter method for author
    public String getAuthor() {
        return author;
    }

    // Getter method for genre
    public String getGenre() {
        return genre;
    }

    // Getter method for publicationYear
    public int getPublicationYear() {
        return publicationYear;
    }

    // Getter method for status
    public boolean getStatus() {
        return status;
    }
}
