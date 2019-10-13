package com.example.englishiseasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Theory_choosing extends AppCompatActivity {

    MyTextView buttons[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_work);
        buttons = new MyTextView[32];
        buttons[0] = (MyTextView)findViewById(R.id.button_colors);
        buttons[1] = (MyTextView)findViewById(R.id.button_relatives);
        buttons[2] = (MyTextView)findViewById(R.id.button_verbs);
        buttons[3] = (MyTextView)findViewById(R.id.button_irregular_verbs);

        buttons[4] = (MyTextView)findViewById(R.id.button_tenses);
        buttons[5] = (MyTextView)findViewById(R.id.button_automobile);
        buttons[6] = (MyTextView)findViewById(R.id.button_airport);
        buttons[7] = (MyTextView)findViewById(R.id.button_appereance);

        buttons[8] = (MyTextView)findViewById(R.id.button_city);
        buttons[9] = (MyTextView)findViewById(R.id.button_whpronouns);
        buttons[10] = (MyTextView)findViewById(R.id.button_inn);
        buttons[11] = (MyTextView)findViewById(R.id.button_day);

        buttons[12] = (MyTextView)findViewById(R.id.button_house);
        buttons[13] = (MyTextView)findViewById(R.id.button_animals);
        buttons[14] = (MyTextView)findViewById(R.id.button_health);
        buttons[15] = (MyTextView)findViewById(R.id.button_zodiac);

        buttons[16] = (MyTextView)findViewById(R.id.button_property);
        buttons[17] = (MyTextView)findViewById(R.id.button_career);
        buttons[18] = (MyTextView)findViewById(R.id.button_cinema);
        buttons[19] = (MyTextView)findViewById(R.id.button_computer);

        buttons[20] = (MyTextView)findViewById(R.id.button_cookery);
        buttons[21] = (MyTextView)findViewById(R.id.button_cooking);
        buttons[22] = (MyTextView)findViewById(R.id.button_kindsofshops);
        buttons[23] = (MyTextView)findViewById(R.id.button_furniture);

        buttons[24] = (MyTextView)findViewById(R.id.button_music);
        buttons[25] = (MyTextView)findViewById(R.id.button_clothes);
        buttons[26] = (MyTextView)findViewById(R.id.button_professions);
        buttons[27] = (MyTextView)findViewById(R.id.button_trip);

        buttons[28] = (MyTextView)findViewById(R.id.button_sport);
        buttons[29] = (MyTextView)findViewById(R.id.button_transport);
        buttons[30] = (MyTextView)findViewById(R.id.button_character);
        buttons[31] = (MyTextView)findViewById(R.id.button_everything);

        for (int i=0; i<buttons.length; i++){
            final String topic;
            switch (i){
                case 0: topic = "colors"; break;
                case 1: topic = "relatives"; break;
                case 2: topic = "verbs"; break;
                case 3: topic = "irregular_verbs"; break;

                case 4: topic = "tenses"; break;
                case 5: topic = "automobile"; break;
                case 6: topic = "airport"; break;
                case 7: topic = "appereance"; break;

                case 8: topic = "city"; break;
                case 9: topic = "whpronouns"; break;
                case 10: topic = "inn"; break;
                case 11: topic = "day"; break;

                case 12: topic = "house"; break;
                case 13: topic = "animals"; break;
                case 14: topic = "health"; break;
                case 15: topic = "zodiac"; break;

                case 16: topic = "property"; break;
                case 17: topic = "career"; break;
                case 18: topic = "cinema"; break;
                case 19: topic = "computer"; break;

                case 20: topic = "cookery"; break;
                case 21: topic = "cooking"; break;
                case 22: topic = "kindsofshops"; break;
                case 23: topic = "furniture"; break;

                case 24: topic = "music"; break;
                case 25: topic = "clothes"; break;
                case 26: topic = "professions"; break;
                case 27: topic = "trip"; break;

                case 28: topic = "sport"; break;
                case 29: topic = "transport"; break;
                case 30: topic = "character"; break;
                case 31: topic = "everything"; break;

                default: topic = null; break;
            }
            if ((i == 3) || (i == 4)){
                if (i == 3){
                    buttons[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Theory_choosing.this, Theory_irregular_verbs.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                        }
                    });
                }else{
                    buttons[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Theory_choosing.this, ChooseGroup.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                        }
                    });
                }
            }else{
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Theory_choosing.this, Theory.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra("topic",topic);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                });
            }
        }
    }

    public void onBackPressed(){
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        overridePendingTransition(R.anim.left_to_right, R.anim.top_in);
    }
}
