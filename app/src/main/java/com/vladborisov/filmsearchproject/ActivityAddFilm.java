package com.vladborisov.filmsearchproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ActivityAddFilm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);
        ViewGroup container = findViewById(R.id.activity_add_main_container);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_main, container, false);
        container.addView(view);
        Button createFilmBtn = findViewById(R.id.activity_add_main_edit_film_button);
        createFilmBtn.setOnClickListener(v -> Snackbar.make(container, "Фильм добавлен", Snackbar.LENGTH_LONG).show());
    }
}