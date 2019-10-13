package com.example.englishiseasy;

import android.content.Intent;
import android.service.chooser.ChooserTargetService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseGroup extends AppCompatActivity {

    int lvl;

    MyTextView tv_simpleGroup, tv_progressiveGroup, tv_perfectGroup, tv_perfect_progressiveGroup, tv_allGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_group);

        lvl = getIntent().getIntExtra("lvl",0);

        tv_simpleGroup = (MyTextView)findViewById(R.id.simple_group);
        tv_progressiveGroup = (MyTextView)findViewById(R.id.progressive_group);
        tv_perfectGroup = (MyTextView)findViewById(R.id.perfect_group);
        tv_perfect_progressiveGroup = (MyTextView)findViewById(R.id.perfect_progressive_group);
        tv_allGroups = (MyTextView)findViewById(R.id.all_groups);

        if (lvl != 0) {
            tv_simpleGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseGroup.this, ChooseTime.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putInt("lvl", lvl);
                    bundle.putString("group", "Simple");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });

            tv_progressiveGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseGroup.this, ChooseTime.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putInt("lvl", lvl);
                    bundle.putString("group", "Progressive");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });

            tv_perfectGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseGroup.this, ChooseTime.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putInt("lvl", lvl);
                    bundle.putString("group", "Perfect");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });

            tv_perfect_progressiveGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseGroup.this, ChooseTime.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putInt("lvl", lvl);
                    bundle.putString("group", "Perfect Progressive");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });

            tv_allGroups.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseGroup.this, ChooseTime.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putInt("lvl", lvl);
                    bundle.putString("group", "All groups");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });
        }else{
            tv_allGroups.setVisibility(View.INVISIBLE);

            tv_simpleGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseGroup.this, ChooseTime.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("group", "Simple");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });

            tv_progressiveGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseGroup.this, ChooseTime.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("group", "Progressive");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });

            tv_perfectGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseGroup.this, ChooseTime.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("group", "Perfect");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });

            tv_perfect_progressiveGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ChooseGroup.this, ChooseTime.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Bundle bundle = new Bundle();
                    bundle.putString("group", "Perfect Progressive");
                    i.putExtras(bundle);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                }
            });
        }
    }

    public void onBackPressed(){
        if (lvl != 0) {
            Intent i = new Intent(this, ChooseDifficulty.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("topic", "tenses");
            startActivity(i);
            overridePendingTransition(R.anim.left_to_right, R.anim.top_in);
        }else{
            Intent i = new Intent(this, Theory_choosing.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            overridePendingTransition(R.anim.left_to_right, R.anim.top_in);
        }
    }
}
