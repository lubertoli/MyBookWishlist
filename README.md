# MyBookWishlist

## Purpose:
MyBookWishlist is an Android app designed to help users manage a wishlist of books they want to read. Users can add, edit, and delete books on their list and view a summary of the total books and the number of books marked as read.

## Features:
- Add New Books: Users can add new books to their wishlist with details like title, author, genre, publication year, and read/unread status.
- Edit Book Details: Allows editing details of any book on the wishlist.
- Delete Books: Users can remove books they no longer want on the list.
- Total Counts: Shows the total number of books and those marked as read.
- Data Persistence: Data does not persist across app sessions, providing a clean slate on each launch.

## Installation:
- Download the project zip or clone the repository.
- Open the project in Android Studio.
- Connect an Android device or start an emulator.
- Build and run the app on the connected device or emulator.

## Requirements:
- Android Studio
- Android SDK

## Language:
- Java

## Usage
- Launch the app.
- Use the Add button to insert a new book into your wishlist.
- Tap on a book to edit its details or mark it as read/unread.
- Use the Delete option to remove a book.
- View the total number of books and the count of books read at the bottom of the main screen.

## Code Overview:
- MainActivity.java: Handles the main user interface and controls.
- Book.java: The model class representing each book's details.
- BookAdapter.java: Manages displaying the list of books.
- Layout files (res/layout): Defines the UI components, including main screens and dialogs.

## Future Enhancements:
- Persist data across app sessions using a database or local storage.
- Add search or filter functionality for a better user experience.

# #License
- This project is licensed under the MIT License.
