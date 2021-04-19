package com.vladborisov.filmsearchproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String ANSWER_CHECKBOX = "Checkbox";
    static final String ANSWER_COMMENT = "Comments";
    static final String ANSWER = "Answer";
    private Button button1;
    private Button button2;
    private TextView text1;
    private TextView text2;
    final static String TAG = MainActivity.class.getSimpleName();
    final static String KEY = "1488";
    final static int REQUEST_CODE = 228;
    private static final String msg = "Заходи на огонек!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);
        if (savedInstanceState != null) {
            String oldColorText1 = savedInstanceState.getString(KEY + 1);
            String oldColorText2 = savedInstanceState.getString(KEY + 2);
            text1.setTextColor(Color.parseColor(oldColorText1));
            text2.setTextColor(Color.parseColor(oldColorText2));
            Log.d(TAG, "Restore data:" + oldColorText1);
            Log.d(TAG, "Restore data:" + oldColorText2);
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setTextColor(Color.parseColor("red"));
                startIntent(1, "red");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2.setTextColor(Color.parseColor("blue"));
                startIntent(2, "blue");
            }
        });

    }


    public void inviteFriend(View view) {
        Intent sendInvite = new Intent();
        sendInvite.setAction(Intent.ACTION_SEND);
        sendInvite.putExtra(Intent.EXTRA_TEXT, msg);
        sendInvite.setType("text/plain");

        String title = "Пригласить друга";

        Intent chooser = Intent.createChooser(sendInvite, title);
        if (sendInvite.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String textColor1 = "red";
        String textColor2 = "blue";
        outState.putString(KEY + 1, textColor1);
        outState.putString(KEY + 2, textColor2);

    }


    public void startIntent(Integer num, String color) {
        Intent intent = new Intent(MainActivity.this, ShowFilm.class);
        intent.putExtra("Число", num);
        intent.putExtra("Цвет", color);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            String answerComment = null;
            String answer = null;
            boolean answerCheckbox = true;
            if (resultCode == RESULT_OK && data != null) {
                answerCheckbox = data.getBooleanExtra(ANSWER_CHECKBOX, true);
                answerComment = data.getStringExtra(ANSWER_COMMENT);
                answer = data.getStringExtra(ANSWER);
                Log.d(TAG, "onActivityResult ANSWER_CHECKBOX: [" + answerCheckbox + "]");
                Log.d(TAG, "onActivityResult ANSWER_COMMENT: [" + answerComment + "]");
                Log.d(TAG, "onActivityResult TEST_ANSWER: [" + answer + "]");

            }
            Log.d(TAG, "onActivityResult ANSWER_CHECKBOX: [" + answerCheckbox + "]");
            Log.d(TAG, "onActivityResult ANSWER_COMMENT: [" + answerComment + "]");
        }
    }
}