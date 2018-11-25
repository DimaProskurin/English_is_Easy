package com.example.englishiseasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyTextView tv_Greeting, tv_StartGame, tv_Score, tv_Theory, tv_Help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_Greeting = (MyTextView)findViewById(R.id.greeting);
        tv_StartGame = (MyTextView)findViewById(R.id.textView_StartGame);
        tv_Score = (MyTextView)findViewById(R.id.textView_Score);
        tv_Theory = (MyTextView)findViewById(R.id.textView_Theory);
        tv_Help = (MyTextView)findViewById(R.id.textView_Help);


        tv_StartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Practical_work.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
            }
        });
        tv_Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Score_choosing.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
            }
        });
        tv_Theory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Theory_choosing.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
            }
        });
        tv_Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Help.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
            }
        });
    }

    public void onBackPressed() {
        //ничего не должно происходить
    }

}