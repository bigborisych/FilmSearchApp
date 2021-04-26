package com.vladborisov.filmsearchproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String ANSWER_CHECKBOX = "Checkbox";
    static final String ANSWER_COMMENT = "Comments";
    static final String ANSWER = "Answer";
    final static String TAG = MainActivity.class.getSimpleName();
    final static int REQUEST_CODE = 228;
    private static final String msg = "Заходи на огонек!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.action_details_film1);
        Button button2 = findViewById(R.id.action_details_film2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(2);
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

    public void startIntent(Integer num) {
        Intent intent = new Intent(MainActivity.this, ShowFilm.class);
        intent.putExtra("Число", num);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            String answerComment = null;
            String answer;
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