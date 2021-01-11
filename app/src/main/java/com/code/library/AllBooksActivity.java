package com.code.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    RecyclerView booksRecyclerView;
    private booksRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_books_layout);

        // animation for entering the layout (manually)
        //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        // up back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new booksRecViewAdapter(this, "allBooks");
        booksRecyclerView = (RecyclerView)findViewById(R.id.booksRecyclerView);

        booksRecyclerView.setAdapter(adapter);
        // booksRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getAllBooks());
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

    /*
    @Override
    public void finish() {
        super.finish();
        // animation for leaving the layout (manually)
        //overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
    }
     */
}