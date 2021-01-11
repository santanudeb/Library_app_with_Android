package com.code.library;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private SharedPreferences sharedPreferences;
    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS_KEY = "already_read_books";
    private static final String WANT_TO_READ_BOOKS_KEY = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS_KEY = "currently_reading_books";
    private static final String FAVORITE_BOOKS_KEY = "favorite_books";

    // for calling all books in app
    // private static ArrayList<Book> allBooks;
    // private static ArrayList<Book> alreadyReadBooks;
    // private static ArrayList<Book> wantToReadBooks;
    // private static ArrayList<Book> currentlyReadingBooks;
    // private static ArrayList<Book> favoriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_database", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAllBooks()){
            initData();
        }

        if (null == getAlreadyReadBooks()){
            editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(new ArrayList<>()));
            editor.commit();
        }

        if (null == getWantToReadBooks()){
            editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(new ArrayList<>()));
            editor.commit();
        }

        if (null == getCurrentlyReadingBooks()){
            editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(new ArrayList<>()));
            editor.commit();
        }

        if (null == getFavoriteBooks()){
            editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(new ArrayList<>()));
            editor.commit();
        }
    }

    private void initData() {

        // array list of books
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "1Q84", "Murakami", 1350,
                "https://images-na.ssl-images-amazon.com/images/I/81M0jxrDz5L.jpg",
                "Fiction, Parallel universes.", "1Q84 is a dystopian novel written by Japanese writer Haruki Murakami, first published in three volumes in Japan in 2009–10. It covers a fictionalized year of 1984 in parallel with a real one. The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world."));

        books.add(new Book(2, "Crime and Punishment", "Fyodor Dostoevsky", 600,
                "https://kbimages1-a.akamaihd.net/b1c96137-0ddf-4ee4-8f46-73bdfa9b8621/1200/1200/False/crime-and-punishment-by-fyodor-dostoevsky-1.jpg",
                "Crime Fiction", "Crime and Punishment is a novel by the Russian author Fyodor Dostoevsky. It was first published in the literary journal The Russian Messenger in twelve monthly installments during 1866. It was later published in a single volume."));

        books.add(new Book(3, "The Metamorphosis", "Franz Kafka", 100,
                "https://i.pinimg.com/originals/81/de/3a/81de3afa4ccdeee0ed19e388da33479d.jpg",
                "Fantasy Fiction", "The Metamorphosis (German: Die Verwandlung) is a novella written by Franz Kafka which was first published in 1915. One of Kafka's best-known works, The Metamorphosis tells the story of salesman Gregor Samsa who wakes one morning to find himself inexplicably transformed into a huge insect."));

        books.add(new Book(4, "Siddhartha", "Hermann Hesse", 150,
                "https://images-na.ssl-images-amazon.com/images/I/61VEsSn+0EL.jpg",
                "Philosophical fiction", "Siddhartha is a 1922 novel by Hermann Hesse that deals with the spiritual journey of self-discovery of a man named Siddhartha during the time of the Gautama Buddha. The book, Hesse's ninth novel, was written in German, in a simple, lyrical style."));

        books.add(new Book(5, "Nineteen Eighty-Four", "George Orwell", 450,
                "https://images-na.ssl-images-amazon.com/images/I/81TC+ObGCqL.jpg",
                "Social science fiction", "Nineteen Eighty-Four: A Novel, often published as 1984, is a dystopian social science fiction novel by English novelist George Orwell. It was published on 8 June 1949 by Secker & Warburg as Orwell's ninth and final book completed in his lifetime."));

        books.add(new Book(6, "And Then There Were None", "Agatha Christie", 280,
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1391120695l/16299.jpg",
                "Psychological thriller", "And Then There Were None is a mystery novel by the English writer Agatha Christie, described by her as the most difficult of her books to write. It was first published in the United Kingdom by the Collins Crime Club on 6 November 1939."));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();

    }

    // Singleton or One Instance
    public static Utils getInstance(Context context) {
        if(null!=instance){
            return instance;
        } else {
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS_KEY, null), type);
        return books;
    }

    public Book getBookByID(int Id) {
        ArrayList<Book> books = getAllBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == Id) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS_KEY);
                editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS_KEY);
                editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS_KEY);
                editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavorite(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS_KEY);
                editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS_KEY);
                        editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS_KEY);
                        editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS_KEY);
                        editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavorite(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS_KEY);
                        editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
