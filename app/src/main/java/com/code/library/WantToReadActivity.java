package com.code.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class WantToReadActivity extends AppCompatActivity {

    RecyclerView bookRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.want_to_read_layout);

        // up back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookRecView = (RecyclerView)findViewById(R.id.bookRecView);

        booksRecViewAdapter adapter = new booksRecViewAdapter(this, "wantToRead");
        bookRecView.setAdapter(adapter);
        bookRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getWantToReadBooks());
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);

        // clearing stored pages for back button. clearing back stack.
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}