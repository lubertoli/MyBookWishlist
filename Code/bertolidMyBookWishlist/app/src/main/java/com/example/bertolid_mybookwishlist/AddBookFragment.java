package com.example.bertolid_mybookwishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


/**
 * Purpose: The AddBookFragment is a Dialog Fragment that provides functionality to add, delete or
 * edit a book in the wishlist.
 *
 * Design Rationale: The fragment serves as a dialog which provides a focused user interface that
 * allows the user to input details (title, author, genre, publication year and read status) of a
 * new book or edit the pre-filled details of an existing book. It validates the input and uses the
 * BookDialogListener to communicate with the MainActivity in order to add, update or delete a book
 * in the wishlist.
 *
 * Outstanding Issues: The validation could be improved with more precise checks and more detailed
 * error messages.
 */

public class AddBookFragment extends DialogFragment {
    private EditText newTitle, newAuthor, newGenre, newYear;
    private Switch newStatus;
    private BookDialogListener listener;
    private Book currentBook;
    private int position;
    private Button confirmButton, deleteButton;

    /**
     * Purpose: Interface that communicates add, delete and edit actions to the MainActivity.
     */
    public interface BookDialogListener {
        void addBook(Book book, int position); // Add or edit a book in the list
        void deleteBook(int position); // Delete a book from the list
    }

    /**
     * Purpose: Constructor for AddBookFragment.
     * @param listener - listener for dialog book actions.
     * @param currentBook - The book selected to be edited or deleted or null if new book to be added.
     * @param index - position of the book selected in the wishlist or -1 if new book to be added.
     */
    public AddBookFragment(BookDialogListener listener, @Nullable Book currentBook, int index) {
        this.listener = listener;
        this.currentBook = currentBook;
        this.position = index;
    }

    /**
     * Purpose: Adjust the dialog window size.
     */
    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow(); // Get the dialog window
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();

            // Set custom window size
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            window.setAttributes(params);
        }
    }

    /**
     * Purpose: Inflate the layout and initialize UI elements.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_book_fragment, container, false);

        // Initialize input fields
        newTitle = view.findViewById(R.id.edit_title);
        newAuthor = view.findViewById(R.id.edit_author);
        newGenre = view.findViewById(R.id.edit_genre);
        newYear = view.findViewById(R.id.edit_year);
        newStatus = view.findViewById(R.id.switch_status);

        //Initialize buttons
        confirmButton = view.findViewById(R.id.confirm_button);
        deleteButton = view.findViewById(R.id.delete_button);

        // If editing an existing book, pre-fill the input fields its details
        if (currentBook != null) {
            newTitle.setText(currentBook.getTitle());
            newAuthor.setText(currentBook.getAuthor());
            newGenre.setText(currentBook.getGenre());
            newYear.setText(String.valueOf(currentBook.getPublicationYear()));
            newStatus.setChecked(currentBook.getStatus());
            deleteButton.setVisibility(View.VISIBLE);
        }
        confirmButton.setOnClickListener(v -> confirmChanges()); // Handle the confirm button click to add or edit a book
        deleteButton.setOnClickListener(v -> delete()); // Handle the delete button click to delete a book

        return view;
    }

    /**
     * Purpose: Validate the input data and communicate the changes in the book data to MainActivity
     */
    private void confirmChanges() {
        String title = newTitle.getText().toString();
        String author = newAuthor.getText().toString();
        String genre = newGenre.getText().toString();
        String publicationYear = newYear.getText().toString();
        boolean status = newStatus.isChecked();

        boolean valid = true;

        // Input validation checks
        if (title.isEmpty() || author.isEmpty() || publicationYear.isEmpty() || genre.isEmpty() || title.equals("title") || author.equals("author") || publicationYear.equals("Publication Year") || genre.equals("Genre")) {
            valid = false;
            Toast.makeText(getContext(), "You must fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
        if (title.length() > 50) {
            valid = false;
            Toast.makeText(getContext(), "Book title cannot be greater than 50 characters.", Toast.LENGTH_SHORT).show();
        }
        if (author.length() > 30) {
            valid = false;
            Toast.makeText(getContext(), "Author name cannot be greater than 30 characters.", Toast.LENGTH_SHORT).show();
        }
        if (publicationYear.length() != 4) {
            valid = false;
            Toast.makeText(getContext(), "Publication Year must be 4 digits.", Toast.LENGTH_SHORT).show();
        }
        if (valid) {
            int yearInt = Integer.parseInt(publicationYear); // Change publication year from string to int
            Book newBook = new Book(title, author, genre, yearInt, status); // Create new Book object
            listener.addBook(newBook, position); // Notify Main Activity
            dismiss(); // Close dialog
        }
    }

    /**
     * Delete selected book and communicate the changes in data to MainActivity
     */
    public void delete() {
        if (position != -1) { // Cannot delete a new book
            listener.deleteBook(position); // Notify Main Activity
        }
        dismiss(); // Close dialog
    }
}
