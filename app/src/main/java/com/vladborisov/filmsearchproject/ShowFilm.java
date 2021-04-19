package com.vladborisov.filmsearchproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowFilm extends AppCompatActivity {
    final static String TAG = "134";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        int data;
        data = intent.getIntExtra("Число", 1);
        String color = intent.getStringExtra("Цвет");
        switch (data) {
            case 0:
                setContentView(R.layout.activity_show_film);
                break;
            case 1:
                setContentView(R.layout.film1);
                TextView text = findViewById(R.id.textView);
                Button button = findViewById(R.id.button3);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText = findViewById(R.id.editTextTextPersonName2);
                        CheckBox checkBox = findViewById(R.id.checkBox);
                        intent.putExtra(MainActivity.ANSWER_CHECKBOX, checkBox.isChecked());
                        intent.putExtra(MainActivity.ANSWER_COMMENT, editText.getText().toString());
                        System.out.println("Чекбокс - " + checkBox.isChecked() + "; " + editText.getText().toString() + ";");
                        onBackPressed();
                    }
                });
                text.setTextColor(Color.parseColor(color));
                break;
            case 2:
                setContentView(R.layout.film2);
                text = findViewById(R.id.textView2);
                text.setTextColor(Color.parseColor(color));
                button = findViewById(R.id.button3);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText = findViewById(R.id.editTextTextPersonName2);
                        CheckBox checkBox = findViewById(R.id.checkBox);
                        intent.putExtra(MainActivity.ANSWER_CHECKBOX, checkBox.isChecked());
                        intent.putExtra(MainActivity.ANSWER_COMMENT, editText.getText().toString());
                        System.out.println("Чекбокс - " + checkBox.isChecked() + "; " + editText.getText().toString() + ";");
                        onBackPressed();
                    }
                });
                break;
        }
        //setResult(RESULT_OK, intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShowFilm.this, MainActivity.class);
        EditText editText = findViewById(R.id.editTextTextPersonName2);
        CheckBox checkBox = findViewById(R.id.checkBox);
        Log.d(TAG, checkBox.isChecked() + editText.getText().toString());
        intent.putExtra(MainActivity.ANSWER_CHECKBOX, checkBox.isChecked());
        intent.putExtra(MainActivity.ANSWER_COMMENT, editText.getText().toString());
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}


