package com.vladborisov.filmsearchproject;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {
    static final String ANSWER_CHECKBOX = "Checkbox";
    static final String ANSWER_COMMENT = "Comments";
    static final String ANSWER = "Answer";
    static final String NAME_NUMBER_OF_CHOOSE_FILM_EXTRA = "Число";
    final static String TAG = MainActivity.class.getSimpleName();
    final static int REQUEST_CODE = 228;
    private static final String INVITE_MASSAGE = "Заходи на огонек!";
    private static final String INVITE_TITLE = "Пригласить друга";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Create Navigation view and listener on toolbar menu*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_draw_open, R.string.nav_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setOnMenuItemClickListener(this);

        /*Listener on fab*/
        FloatingActionButton floatingActionButton = findViewById(R.id.default_activity_fab_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAddFilm.class);
                startActivity(intent);
            }
        });

    }
    public void inviteFriend() {
        Intent sendInvite = new Intent();
        sendInvite.setAction(Intent.ACTION_SEND);
        sendInvite.putExtra(Intent.EXTRA_TEXT, INVITE_MASSAGE);
        sendInvite.setType("text/plain");
        Intent chooser = Intent.createChooser(sendInvite, INVITE_TITLE);
        if (sendInvite.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    public void startActivityForResultWithPutParams(Integer num) {
        Intent intent = new Intent(MainActivity.this, ShowFilm.class);
        intent.putExtra(NAME_NUMBER_OF_CHOOSE_FILM_EXTRA, num);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                boolean answerCheckbox = data.getBooleanExtra(ANSWER_CHECKBOX, true);
                String answerComment = data.getStringExtra(ANSWER_COMMENT);
                String answer = data.getStringExtra(ANSWER);
                Log.d(TAG, "onActivityResult ANSWER_CHECKBOX: [" + answerCheckbox + "]");
                Log.d(TAG, "onActivityResult ANSWER_COMMENT: [" + answerComment + "]");
                Log.d(TAG, "onActivityResult TEST_ANSWER: [" + answer + "]");

            }
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            inviteFriend();
            return true;
        } else return false;
    }

    public void animationAlphaToNonAlpha(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", (float) 1.0);
        AnimatorSet set = new AnimatorSet();
        set.play(animator);
        set.setDuration(1000);
        set.setInterpolator(new DecelerateInterpolator());
        set.setStartDelay(1000);
        set.start();
    }
    public void onImageClick(View view){
        int id = view.getId();
        TextView textView;
        if(id == R.id.image_view1){
            textView = findViewById(R.id.sub_title_film1);
            animationAlphaToNonAlpha(textView);
        }else if(id == R.id.image_view2){
            textView = findViewById(R.id.sub_title_film2);
            animationAlphaToNonAlpha(textView);
        }else if(id == R.id.image_view3){
            textView = findViewById(R.id.sub_title_film3);
            animationAlphaToNonAlpha(textView);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.action_details_film1){
            startActivityForResultWithPutParams(1);
        }else if(id == R.id.action_details_film2){
            startActivityForResultWithPutParams(2);
        }else if(id == R.id.action_details_film3){
            startActivityForResultWithPutParams(3);
        }
    }
}