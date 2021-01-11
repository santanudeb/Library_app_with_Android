package com.code.library;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAllBooks, btnCurrentlyReading, btnAlreadyRead, btnWantToRead, btnFavoriteBooks, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hiding action bar
        getSupportActionBar().hide();

        btnAllBooks = (Button)findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = (Button)findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = (Button)findViewById(R.id.btnAlreadyRead);
        btnWantToRead = (Button)findViewById(R.id.btnWantToRead);
        btnFavoriteBooks = (Button)findViewById(R.id.btnFavoriteBooks);
        btnAbout = (Button)findViewById(R.id.btnAbout);

        // calling all constructor from Utils
        Utils.getInstance(this);
    }

    public void onAllBooks(View view){
        Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
        startActivity(intent);
    }

    public void onAlreadyReadBooks(View view){
        Intent intent = new Intent(MainActivity.this, AlreadyReadBookActivity.class);
        startActivity(intent);
    }

    public void onCurrentlyReading(View view){
        Intent intent = new Intent(MainActivity.this, CurrentlyReadingActivity.class);
        startActivity(intent);
    }

    public void onWantToRead(View view){
        Intent intent = new Intent(MainActivity.this, WantToReadActivity.class);
        startActivity(intent);
    }

    public void onFavoriteBooks(View view){
        Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
        startActivity(intent);
    }

    public void onAbout(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("Developed by Santanu. \n" +
                "Check https://github.com/santanudeb for more cool apps.");
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                intent.putExtra("url", "https://github.com/santanudeb");
                startActivity(intent);
            }
        });
        builder.create().show();
    }
}