package com.vladborisov.filmsearchproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityAddFilm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);
        ViewGroup container = findViewById(R.id.activity_add_main_container);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_main, container, false);
        container.addView(view);
    }
}