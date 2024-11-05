package com.example.bertolid_mybookwishlist;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/**
 * Purpose: The MainActivity class is responsible for managing the display and user interactions of
 * the book wishlist.
 *
 * Design Rationale: The activity uses a ListView to display the books, as it is simple to implement
 * and the number of books is expected to be relatively small. The books are stored in memory with
 * an Array List. The activity provides a FloatingActionButton to add books and responds to item
 * clicks to edit or delete books. It implements the BookDialogListener to manage the communication
 * between the activity and the Dialogue Fragment. Two int variables are used to keep track of the
 * total and read book counts.
 *
 * Outstanding Issues: The data is cleared once the app restarts, thus there could be an improvement
 * to maintain the data across restarts. If the number of books gets too large, the Listview will
 * not be able to handle the display, generating a need for a more advanced list management.
 */

public class MainActivity extends AppCompatActivity implements AddBookFragment.BookDialogListener {
    private ArrayList<Book> dataList = new ArrayList<>();
    private ListView bookWishlist;
    private WishlistAdapter wishlistAdapter;
    private TextView totalBooks, readBooks;
    private int booksNum = 0, readNum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        totalBooks = findViewById(R.id.total_books);
        readBooks = findViewById(R.id.books_read);
        bookWishlist = findViewById(R.id.wishlist);

        // Set up WishlistAdapter for ListView
        wishlistAdapter = new WishlistAdapter(this, dataList);
        bookWishlist.setAdapter(wishlistAdapter);

        // Set up FloatingAction Button to open AddBookFragment for adding new book
        FloatingActionButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            new AddBookFragment(this, null, -1).show(getSupportFragmentManager(), "Add Book");
        });

        // Handle item clicks to open AddBookFragment for editing or deleting the selected book
        bookWishlist.setOnItemClickListener((parent, view, position, id) -> {
            Book editBook = dataList.get(position);
            new AddBookFragment(this, editBook, position).show(getSupportFragmentManager(), "Edit Book");
        });
    }

    /**
     * Purpose: Add a new book or edit an existing book in the wishlist.
     * @param book - The book to be added or edited.
     * @param position - Position of the book in the wishlist.
     */
    @Override
    public void addBook(Book book, int position) {
        if (position == -1) { // A new book is edited to the end of the wishlist
            dataList.add(book);
            booksNum++;
            if (book.getStatus()) {
                readNum++;
            }
        }
        else { // An existing book in the wishlist is edited
            Book oldBook = dataList.get(position);
            dataList.set(position, book);

            if (!oldBook.getStatus() && book.getStatus()) {
                readNum++;
            }
            else if (oldBook.getStatus() && !book.getStatus()) {
                readNum--;
            }
        }
        updateCounts();
        wishlistAdapter.notifyDataSetChanged(); // Update the ListView
    }

    /**
     * Purpose: Deletes an existing book from the wishlist.
     * @param position - The position of the book to be deleted in the wishlist.
     */
    @Override
    public void deleteBook(int position) {
        Book book = dataList.get(position);
        booksNum--;
        if (book.getStatus()) {
            readNum--;
        }
        dataList.remove(position); // Removes the book from the list
        updateCounts();
        wishlistAdapter.notifyDataSetChanged(); // Update the Listview
    }

    /**
     * Purpose: Updates the counts of total and read books displayed.
     */
    private void updateCounts() {
        totalBooks.setText("Total Books: " + booksNum);
        readBooks.setText("Books Read: " + readNum);
    }


}