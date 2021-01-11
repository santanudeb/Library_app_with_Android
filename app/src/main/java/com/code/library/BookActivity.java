package com.code.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    Button btnCurrentlyReading, btnAlreadyRead, btnWantToRead, btnAddToFavorite;
    ImageView imgBook;
    TextView bookNameText, authorNameText, pages, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity_layout);

        // up back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCurrentlyReading = (Button)findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = (Button)findViewById(R.id.btnAlreadyRead);
        btnWantToRead = (Button)findViewById(R.id.btnWantToRead);
        btnAddToFavorite = (Button)findViewById(R.id.btnAddToFavorite);
        imgBook = (ImageView)findViewById(R.id.imgBook);
        bookNameText = (TextView)findViewById(R.id.bookNameText);
        authorNameText = (TextView)findViewById(R.id.authorNameText);
        pages = (TextView)findViewById(R.id.pages);
        description = (TextView)findViewById(R.id.description);

        /*
        String longDescription = "1Q84 is a dystopian novel written by Japanese writer Haruki Murakami, first published in three volumes in Japan in 2009–10. It covers a fictionalized year of 1984 in parallel with a real one. The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world.";

        Book book = new Book(1, "1Q84", "Murakami", 1350,
                "https://images-na.ssl-images-amazon.com/images/I/81M0jxrDz5L.jpg",
                "Brilliant", longDescription);
        */

        // getting data from recyclerView
        Intent intent = getIntent();
        if (null != intent) {
            int bookID = intent.getIntExtra("bookID", -1);
            if (bookID != -1) {
                Book incomingBook = Utils.getInstance(this).getBookByID(bookID);

                if (null != incomingBook) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToRead(incomingBook);
                    handleCurrentlyReading(incomingBook);
                    handleFavorite(incomingBook);
                }
            }
        }
    }

    // up back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setData(Book book) {
        bookNameText.setText(book.getName());
        authorNameText.setText(book.getAuthor());
        pages.setText(String.valueOf(book.getPages()));
        description.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(imgBook);
    }

    // enable & disable button
    // add the book to already read
    private void handleAlreadyRead(final Book book) {
        // checking if the book already exists in already read book list
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks) {
            // disabling the button
            btnAlreadyRead.setEnabled(false);
        } else {
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Error! Try Again", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    // add the book to Want to Read
    private void handleWantToRead(final Book book) {
        // checking if the book already exists in already read book list
        ArrayList<Book> wantToRead = Utils.getInstance(this).getWantToReadBooks();

        boolean existInWantToRead = false;

        for (Book b : wantToRead) {
            if (b.getId() == book.getId()) {
                existInWantToRead = true;
            }
        }

        if (existInWantToRead) {
            // disabling the button
            btnWantToRead.setEnabled(false);
        } else {
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToWantToRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Error! Try Again", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    // add the book to Currently Reading
    private void handleCurrentlyReading(final Book book) {
        // checking if the book already exists in already read book list
        ArrayList<Book> currentlyReading = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existInCurrentlyReading = false;

        for (Book b : currentlyReading) {
            if (b.getId() == book.getId()) {
                existInCurrentlyReading = true;
            }
        }

        if (existInCurrentlyReading) {
            // disabling the button
            btnCurrentlyReading.setEnabled(false);
        } else {
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Error! Try Again", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    // add the book to Favorite
    private void handleFavorite(final Book book) {
        // checking if the book already exists in already read book list
        ArrayList<Book> favorite = Utils.getInstance(this).getFavoriteBooks();

        boolean existInfavorite = false;

        for (Book b : favorite) {
            if (b.getId() == book.getId()) {
                existInfavorite = true;
            }
        }

        if (existInfavorite) {
            // disabling the button
            btnAddToFavorite.setEnabled(false);
        } else {
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToFavorite(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Error! Try Again", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}