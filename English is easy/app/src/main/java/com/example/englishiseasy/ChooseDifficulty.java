package com.example.englishiseasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseDifficulty extends AppCompatActivity {

    MyTextView greeting, but_1lvl, but_2lvl, but_3lvl;
    String tmp;
    String topic1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulty);
        greeting = (MyTextView)findViewById(R.id.greetings_in_choose_difficulty);
        but_1lvl = (MyTextView)findViewById(R.id.button_1lvl);
        but_2lvl = (MyTextView)findViewById(R.id.button_2lvl);
        but_3lvl = (MyTextView)findViewById(R.id.button_3lvl);

        topic1 = getIntent().getStringExtra("topic_from_score_choosing");
        if (topic1 == null){
            final String topic = getIntent().getStringExtra("topic");
            tmp = topic;
            if (!topic.equals("irregular_verbs") && !topic.equals("tenses")){
                but_1lvl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ChooseDifficulty.this, Practical_work1lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra("topic",topic);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                });

                but_2lvl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ChooseDifficulty.this, Practical_work2lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra("topic",topic);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                });

                but_3lvl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ChooseDifficulty.this, Practical_work3lvl.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra("topic",topic);
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                });
            }else{
                    if (topic.equals("irregular_verbs")){
                        but_1lvl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ChooseDifficulty.this, Practical_work_irregular_verbs1lvl.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                            }
                        });

                        but_2lvl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ChooseDifficulty.this, Practical_work_irregular_verbs2lvl.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                            }
                        });

                        but_3lvl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ChooseDifficulty.this, Practical_work_irregular_verbs3lvl.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                            }
                        });
                    }else{
                        but_1lvl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ChooseDifficulty.this, ChooseGroup.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.putExtra("lvl", 1);
                                startActivity(i);
                                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                            }
                        });

                        but_2lvl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ChooseDifficulty.this, Practical_work_tenses2lvl.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                            }
                        });

                        but_3lvl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ChooseDifficulty.this, ChooseGroup.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.putExtra("lvl", 3);
                                startActivity(i);
                                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                            }
                        });
                    }
                }
            }else{
                tmp = topic1;
                but_1lvl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ChooseDifficulty.this, Score.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra("topic",topic1 + "1lvl");
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                });

                but_2lvl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ChooseDifficulty.this, Score.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra("topic",topic1 + "2lvl");
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                });

                but_3lvl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ChooseDifficulty.this, Score.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra("topic",topic1 + "3lvl");
                        startActivity(i);
                        overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
                    }
                });
        }

        switch (tmp){
            case "colors": greeting.setText("Цвета"); break;
            case "relatives": greeting.setText("Семья"); break;
            case "verbs": greeting.setText("Глаголы"); break;
            case "irregular_verbs": greeting.setText("Неправильные глаголы"); break;
            case "tenses": greeting.setText("Времена"); break;
            case "automobile": greeting.setText("Автомобиль"); break;
            case "airport": greeting.setText("Аэропорт"); break;
            case "appereance": greeting.setText("Внешность"); break;

            case "city": greeting.setText("Город"); break;
            case "whpronouns": {greeting.setText("Вопросительные местоимения"); break;}
            case "inn": greeting.setText("Гостиница"); break;
            case "day": greeting.setText("День"); break;

            case "house": greeting.setText("Дом"); break;
            case "animals": greeting.setText("Животные"); break;
            case "health": greeting.setText("Здоровье"); break;
            case "zodiac": greeting.setText("Знаки зодиака"); break;

            case "property": greeting.setText("Имущество"); break;
            case "career": greeting.setText("Карьера"); break;
            case "cinema": greeting.setText("Кино"); break;
            case "computer": greeting.setText("Компьютер"); break;

            case "cookery": greeting.setText("Кулинария"); break;
            case "cooking": greeting.setText("Готовка"); break;
            case "kindsofshops": greeting.setText("Виды магазинов"); break;
            case "furniture": greeting.setText("Мебель"); break;

            case "music": greeting.setText("Музыка"); break;
            case "clothes": greeting.setText("Одежда"); break;
            case "professions": greeting.setText("Профессии"); break;
            case "trip": greeting.setText("Путешествие"); break;

            case "sport": greeting.setText("Спорт"); break;
            case "transport": greeting.setText("Транспорт"); break;
            case "character": greeting.setText("Характер"); break;
            case "everything": greeting.setText("Всё"); break;
        }

    }
    public void onBackPressed(){
        Intent i;
        if (topic1 == null){
            i = new Intent(this, Practical_work.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else{
            i = new Intent(this, Score_choosing.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        startActivity(i);
        overridePendingTransition(R.anim.left_to_right, R.anim.top_in);
    }
}
