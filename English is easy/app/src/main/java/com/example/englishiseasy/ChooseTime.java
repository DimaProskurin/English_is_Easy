package com.example.englishiseasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ChooseTime extends AppCompatActivity {

    int lvl;
    String group;

    MyTextView tv_pastTime, tv_presentTime, tv_futureTime, tv_allTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);

        tv_pastTime = (MyTextView)findViewById(R.id.past_time);
        tv_presentTime = (MyTextView)findViewById(R.id.present_time);
        tv_futureTime = (MyTextView)findViewById(R.id.future_time);
        tv_allTimes = (MyTextView)findViewById(R.id.all_times);

        Bundle bundle = getIntent().getExtras();
        lvl = bundle.getInt("lvl");
        group = bundle.getString("group");

        if (lvl != 0) {
            tv_pastTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i;
                    Bundle bundle = new Bundle();
                    bundle.putString("group", group);
                    bundle.putString("time", "Past");
                    if (lvl == 1) {
                        i = new Intent(ChooseTime.this, Practical_work_tenses1lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtras(bundle);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    } else {
                        i = new Intent(ChooseTime.this, Practical_work_tenses3lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtras(bundle);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                }
            });

            tv_presentTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i;
                    Bundle bundle = new Bundle();
                    bundle.putString("group", group);
                    bundle.putString("time", "Present");
                    if (lvl == 1) {
                        i = new Intent(ChooseTime.this, Practical_work_tenses1lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtras(bundle);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    } else {
                        i = new Intent(ChooseTime.this, Practical_work_tenses3lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtras(bundle);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                }
            });

            tv_futureTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i;
                    Bundle bundle = new Bundle();
                    bundle.putString("group", group);
                    bundle.putString("time", "Future");
                    if (lvl == 1) {
                        i = new Intent(ChooseTime.this, Practical_work_tenses1lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtras(bundle);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    } else {
                        i = new Intent(ChooseTime.this, Practical_work_tenses3lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtras(bundle);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                }
            });

            tv_allTimes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i;
                    Bundle bundle = new Bundle();
                    bundle.putString("group", group);
                    bundle.putString("time", "All times");
                    if (lvl == 1) {
                        i = new Intent(ChooseTime.this, Practical_work_tenses1lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtras(bundle);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    } else {
                        i = new Intent(ChooseTime.this, Practical_work_tenses3lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtras(bundle);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                }
            });
        }else{
            tv_allTimes.setVisibility(View.INVISIBLE);

            tv_presentTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseTime.this, Theory_tenses.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("group",group);
                    bundle.putString("time","Present");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });

            tv_pastTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseTime.this, Theory_tenses.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("group",group);
                    bundle.putString("time","Past");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });

            tv_futureTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseTime.this, Theory_tenses.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("group",group);
                    bundle.putString("time","Future");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });
        }
    }

    public void onBackPressed(){
        Intent i = new Intent(this, ChooseGroup.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (lvl != 0){
            i.putExtra("lvl", lvl);
        }
        startActivity(i);
        overridePendingTransition(R.anim.left_to_right, R.anim.top_in);
    }
}
