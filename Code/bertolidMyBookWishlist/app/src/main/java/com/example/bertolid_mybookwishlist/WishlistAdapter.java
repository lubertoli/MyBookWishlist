package com.example.bertolid_mybookwishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;

import java.util.ArrayList;


/**
 * Purpose: The WishlistAdapter class is a ArrayAdapter responsible for displaying the book list
 * in ListView.
 *
 * Design Rationale: The adapter bridges the Book object data and the UI, inflating a custom layout
 * for each Book object in ListView sets the fields with the book's attributes. This ensures all of
 * the book details (title,author, genre, publication year and read status) are properly displayed.
 *
 * Outstanding Issues: If the number of books becomes too large, the performance may become an
 * issue.
 */

public class WishlistAdapter extends ArrayAdapter<Book> {

    /**
     * Purpose: Constructor for the WishlistAdapter
     * @param context - context of the activity where the adapter is used
     * @param books - list of books to be displayed in the Listview
     */
    public WishlistAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books); // Calls the superclass constructor

    }

    /**
     * Purpose: Create a view for each Book object
     * @param position - position of the book in the wishlist
     * @param view - the recycled view to fill or null if it is a new view
     * @param parent - the parent view
     * @return - the updated view for the book at the chosen position
     */
    @Override
    public View getView(int position, @Nullable View view, ViewGroup parent) {
        Book book = getItem(position);

        if (view == null) { // If no view available, inflate a new view
            view = LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false);
        }

        TextView title = view.findViewById(R.id.book_title);
        TextView author = view.findViewById(R.id.book_author);
        TextView genre = view.findViewById(R.id.book_genre);
        TextView publicationYear = view.findViewById(R.id.book_year);
        TextView status = view.findViewById(R.id.book_status);

        // Set the book details in each TextView
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        genre.setText(book.getGenre());
        publicationYear.setText(String.valueOf(book.getPublicationYear()));
        String read = book.getStatus() ? "Read" : "Unread"; // If status is true set text to "Read" and if false set text to "Unread"
        status.setText(read);

        return view;
    }
}
