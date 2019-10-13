package com.example.englishiseasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Theory_tenses extends AppCompatActivity {

    String group, time, tense;

    MyTextView tv_greeting, tv_using, tv_attended_words, tv_formula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_tenses);

        tv_greeting = (MyTextView)findViewById(R.id.greeting_inTheoryTenses);
        tv_using = (MyTextView)findViewById(R.id.using);
        tv_attended_words = (MyTextView)findViewById(R.id.attended_words);
        tv_formula = (MyTextView)findViewById(R.id.formula);

        Bundle bundle = getIntent().getExtras();
        group = bundle.getString("group");
        time = bundle.getString("time");
        tense = time + " " + group;

        switch (tense){
            case "Present Simple":{
                tv_greeting.setText("Present Simple");
                tv_using.setText(getString(R.string.PresentSimple_using));
                tv_attended_words.setText(R.string.PresentSimple_attended_words);
                tv_formula.setText(R.string.PresentSimple_formula);
                break;
            }
            case "Past Simple":{
                tv_greeting.setText("Past Simple");
                tv_using.setText(getString(R.string.PastSimple_using));
                tv_attended_words.setText(R.string.PastSimple_attended_words);
                tv_formula.setText(R.string.PastSimple_formula);
                tv_formula.setTextSize(25f);
                break;
            }
            case "Future Simple":{
                tv_greeting.setText("Future Simple");
                tv_using.setText(getString(R.string.FutureSimple_using));
                tv_attended_words.setText(R.string.FutureSimple_attended_words);
                tv_formula.setText(R.string.FutureSimple_formula);
                break;
            }
            case "Present Progressive":{
                tv_greeting.setText("Present Progressive");
                tv_using.setText(getString(R.string.PresentProgressive_using));
                tv_attended_words.setText(R.string.PresentProgressive_attended_words);
                tv_formula.setText(R.string.PresentProgressive_formula);
                break;
            }
            case "Past Progressive":{
                tv_greeting.setText("Past Progressive");
                tv_using.setText(getString(R.string.PastProgressive_using));
                tv_attended_words.setText(R.string.PastProgressive_attended_words);
                tv_formula.setText(R.string.PastProgressive_formula);
                break;
            }
            case "Future Progressive":{
                tv_greeting.setText("Future Progressive");
                tv_using.setText(getString(R.string.FutureProgressive_using));
                tv_attended_words.setText(R.string.FutureProgressive_attended_words);
                tv_formula.setText(R.string.FutureProgressive_formula);
                break;
            }
            case "Present Perfect":{
                tv_greeting.setText("Present Perfect");
                tv_using.setText(getString(R.string.PresentPerfect_using));
                tv_attended_words.setText(R.string.PresentPerfect_attended_words);
                tv_formula.setText(R.string.PresentPerfect_formula);
                break;
            }
            case "Past Perfect":{
                tv_greeting.setText("Past Perfect");
                tv_using.setText(getString(R.string.PastPerfect_using));
                tv_attended_words.setText(R.string.PastPerfect_attended_words);
                tv_formula.setText(R.string.PastPerfect_formula);
                break;
            }
            case "Future Perfect":{
                tv_greeting.setText("Future Perfect");
                tv_using.setText(getString(R.string.FuturePerfect_using));
                tv_attended_words.setText(R.string.FuturePerfect_attended_words);
                tv_formula.setText(R.string.FuturePerfect_formula);
                break;
            }
            case "Present Perfect Progressive":{
                tv_greeting.setText("Present Perfect Progressive");
                tv_using.setText(getString(R.string.PresentPerfectProgressive_using));
                tv_attended_words.setText(R.string.PresentPerfectProgressive_attended_words);
                tv_formula.setText(R.string.PresentPerfectProgressive_formula);
                break;
            }
            case "Past Perfect Progressive":{
                tv_greeting.setText("Past Perfect Progressive");
                tv_using.setText(getString(R.string.PastPerfectProgressive_using));
                tv_attended_words.setText(R.string.PastPerfectProgressive_attended_words);
                tv_formula.setText(R.string.PastPerfectProgressive_formula);
                break;
            }
            case "Future Perfect Progressive":{
                tv_greeting.setText("Future Perfect Progressive");
                tv_using.setText(getString(R.string.FuturePerfectProgressive_using));
                tv_attended_words.setText(R.string.FuturePerfectProgressive_attended_words);
                tv_formula.setText(R.string.FuturePerfectProgressive_formula);
                break;
            }
        }
    }

    public void onBackPressed(){
        Intent i = new Intent(this, ChooseTime.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putString("group",group);
        i.putExtras(bundle);
        startActivity(i);
        overridePendingTransition(R.anim.left_to_right, R.anim.top_in);
    }
}
