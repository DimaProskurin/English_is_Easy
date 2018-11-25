package com.example.englishiseasy;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Practical_work1lvl extends AppCompatActivity {

    String words[];
    String translations[];
    LinkedList<Mistake> list_mistakes;

    int colors_size = 17;
    int relatives_size = 24;
    int verbs_size = 53;
    int automobile_size = 86;
    int airport_size = 85;
    int apperenance_size = 93;
    int city_size = 169;
    int whpronouns_size = 11;
    int inn_size = 46;
    int day_size = 119;
    int house_size = 197;
    int animals_size = 155;
    int health_size = 313;
    int zodiac_size = 12;
    int property_size = 248;
    int career_size = 159;
    int cinema_size = 95;
    int computer_size = 119;
    int cookery_size = 428;
    int cooking_size = 91;
    int kindsofshops_size = 92;
    int furniture_size = 96;
    int music_size = 81;
    int clothes_size = 166;
    int professions_size = 115;
    int trip_size = 95;
    int sport_size = 184;
    int transport_size = 240;
    int character_size = 77;

    int others_size = 13424;
    int everything_size;



    final String separator_for_topic_and_points = "/";

    ImageView imageRightAnswear, imageWrongAnswear;

    Animation animation_in, animation_out;

    private SoundPool soundPool;
    private int wrongSound, rightSound, Stream;

    public boolean true_answear = (int) (Math.random() * 2) == 1;
    public int points = 0;

    CountDownTimer timer;
    long countDownInterval = 1000;
    long countdownPeriod = 60000;
    final long add_time = 1000;
    final long remove_time = 5000;

    private void init(){
        true_answear = (int) (Math.random() * 2) == 1;
        int word_index = (int)(Math.random()*words.length);
        word.setText(words[word_index]);
        if (true_answear){
            translation.setText(translations[word_index]);
        }else{
            int translation_index = (int)(Math.random()*words.length);
            while (translation_index == word_index){
                translation_index = (int)(Math.random()*words.length);
            }
            translation.setText(translations[translation_index]);
        }
    }

    MyTextView word, translation, points_activity, points_tv, time_activity, time_tv, tv_yes, tv_no;

    String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_work1lvl);

        topic = getIntent().getStringExtra("topic");

        switch (topic){
            case "colors":{
                words = new String[colors_size];
                readFile(words, "words/colors_words.txt");

                translations = new String[colors_size];
                readFile(translations, "translations/colors_translations.txt");

                break;
            }
            case "relatives":{
                words = new String[relatives_size];
                readFile(words, "words/relatives_words.txt");

                translations = new String[relatives_size];
                readFile(translations, "translations/relatives_translations.txt");

                break;
            }
            case "verbs":{
                words = new String[verbs_size];
                readFile(words, "words/verbs_words.txt");

                translations = new String[verbs_size];
                readFile(translations, "translations/verbs_translations.txt");

                break;
            }
            case "automobile":{
                words = new String[automobile_size];
                readFile(words, "words/automobile_words.txt");

                translations = new String[automobile_size];
                readFile(translations, "translations/automobile_translations.txt");

                break;
            }
            case "airport":{
                words = new String[airport_size];
                readFile(words, "words/airport_words.txt");

                translations = new String[airport_size];
                readFile(translations, "translations/airport_translations.txt");

                break;
            }
            case "appereance":{
                words = new String[apperenance_size];
                readFile(words, "words/appereance_words.txt");

                translations = new String[apperenance_size];
                readFile(translations, "translations/appereance_translations.txt");

                break;
            }
            case "city":{
                words = new String[city_size];
                readFile(words, "words/city_words.txt");

                translations = new String[city_size];
                readFile(translations, "translations/city_translations.txt");

                break;
            }
            case "whpronouns":{
                words = new String[whpronouns_size];
                readFile(words, "words/whpronouns_words.txt");

                translations = new String[whpronouns_size];
                readFile(translations, "translations/whpronouns_translations.txt");

                break;
            }
            case "inn":{
                words = new String[inn_size];
                readFile(words, "words/inn_words.txt");

                translations = new String[inn_size];
                readFile(translations, "translations/inn_translations.txt");

                break;
            }
            case "day":{
                words = new String[day_size];
                readFile(words, "words/day_words.txt");

                translations = new String[day_size];
                readFile(translations, "translations/day_translations.txt");

                break;
            }
            case "house":{
                words = new String[house_size];
                readFile(words, "words/house_words.txt");

                translations = new String[house_size];
                readFile(translations, "translations/house_translations.txt");

                break;
            }
            case "animals":{
                words = new String[animals_size];
                readFile(words, "words/animals_words.txt");

                translations = new String[animals_size];
                readFile(translations, "translations/animals_translations.txt");

                break;
            }
            case "health":{
                words = new String[health_size];
                readFile(words, "words/health_words.txt");

                translations = new String[health_size];
                readFile(translations, "translations/health_translations.txt");

                break;
            }
            case "zodiac":{
                words = new String[zodiac_size];
                readFile(words, "words/zodiac_words.txt");

                translations = new String[zodiac_size];
                readFile(translations, "translations/zodiac_translations.txt");

                break;
            }
            case "property":{
                words = new String[property_size];
                readFile(words, "words/property_words.txt");

                translations = new String[property_size];
                readFile(translations, "translations/property_translations.txt");

                break;
            }
            case "career":{
                words = new String[career_size];
                readFile(words, "words/career_words.txt");

                translations = new String[career_size];
                readFile(translations, "translations/career_translations.txt");

                break;
            }
            case "cinema":{
                words = new String[cinema_size];
                readFile(words, "words/cinema_words.txt");

                translations = new String[cinema_size];
                readFile(translations, "translations/cinema_translations.txt");

                break;
            }
            case "computer":{
                words = new String[computer_size];
                readFile(words, "words/computer_words.txt");

                translations = new String[computer_size];
                readFile(translations, "translations/computer_translations.txt");

                break;
            }
            case "cookery":{
                words = new String[cookery_size];
                readFile(words, "words/cookery_words.txt");

                translations = new String[cookery_size];
                readFile(translations, "translations/cookery_translations.txt");

                break;
            }
            case "cooking":{
                words = new String[cooking_size];
                readFile(words, "words/cooking_words.txt");

                translations = new String[cooking_size];
                readFile(translations, "translations/cooking_translations.txt");

                break;
            }
            case "kindsofshops":{
                words = new String[kindsofshops_size];
                readFile(words, "words/kindsofshops_words.txt");

                translations = new String[kindsofshops_size];
                readFile(translations, "translations/kindsofshops_translations.txt");

                break;
            }
            case "furniture":{
                words = new String[furniture_size];
                readFile(words, "words/furniture_words.txt");

                translations = new String[furniture_size];
                readFile(translations, "translations/furniture_translations.txt");

                break;
            }
            case "music":{
                words = new String[music_size];
                readFile(words, "words/music_words.txt");

                translations = new String[music_size];
                readFile(translations, "translations/music_translations.txt");

                break;
            }
            case "clothes":{
                words = new String[clothes_size];
                readFile(words, "words/clothes_words.txt");

                translations = new String[clothes_size];
                readFile(translations, "translations/clothes_translations.txt");

                break;
            }
            case "professions":{
                words = new String[professions_size];
                readFile(words, "words/professions_words.txt");

                translations = new String[professions_size];
                readFile(translations, "translations/professions_translations.txt");

                break;
            }
            case "trip":{
                words = new String[trip_size];
                readFile(words, "words/trip_words.txt");

                translations = new String[trip_size];
                readFile(translations, "translations/trip_translations.txt");

                break;
            }
            case "sport":{
                words = new String[sport_size];
                readFile(words, "words/sport_words.txt");

                translations = new String[sport_size];
                readFile(translations, "translations/sport_translations.txt");

                break;
            }
            case "transport":{
                words = new String[transport_size];
                readFile(words, "words/transport_words.txt");

                translations = new String[transport_size];
                readFile(translations, "translations/transport_translations.txt");

                break;
            }
            case "character":{
                words = new String[character_size];
                readFile(words, "words/character_words.txt");

                translations = new String[character_size];
                readFile(translations, "translations/character_translations.txt");

                break;
            }
            case "everything":{
                everything_size = colors_size + relatives_size + verbs_size + automobile_size + airport_size + apperenance_size + city_size + whpronouns_size + inn_size + day_size +
                        house_size + animals_size + health_size + zodiac_size + property_size + career_size + cinema_size + computer_size + cookery_size + cooking_size + kindsofshops_size +
                        furniture_size + music_size + clothes_size + professions_size + trip_size + sport_size + transport_size + character_size + others_size;

                String[] colors_words = new String[colors_size];
                readFile(colors_words, "words/colors_words.txt");
                String[] colors_translations = new String[colors_size];
                readFile(colors_translations, "translations/colors_translations.txt");

                String[] relatives_words = new String[relatives_size];
                readFile(relatives_words, "words/relatives_words.txt");
                String[] relatives_translations = new String[relatives_size];
                readFile(relatives_translations, "translations/relatives_translations.txt");

                String[] verbs_words = new String[verbs_size];
                readFile(verbs_words, "words/verbs_words.txt");
                String[] verbs_translations = new String[verbs_size];
                readFile(verbs_translations, "translations/verbs_translations.txt");

                String[] automobile_words = new String[automobile_size];
                readFile(automobile_words, "words/automobile_words.txt");
                String[] automobile_translations = new String[automobile_size];
                readFile(automobile_translations, "translations/automobile_translations.txt");

                String[] airport_words = new String[airport_size];
                readFile(airport_words, "words/airport_words.txt");
                String[] airport_translations = new String[airport_size];
                readFile(airport_translations, "translations/airport_translations.txt");

                String[] appereance_words = new String[apperenance_size];
                readFile(appereance_words, "words/appereance_words.txt");
                String[] appereance_translations = new String[apperenance_size];
                readFile(appereance_translations, "translations/appereance_translations.txt");

                String[] city_words = new String[city_size];
                readFile(city_words, "words/city_words.txt");
                String[] city_translations = new String[city_size];
                readFile(city_translations, "translations/city_translations.txt");

                String[] whpronouns_words = new String[whpronouns_size];
                readFile(whpronouns_words, "words/whpronouns_words.txt");
                String[] whpronouns_translations = new String[whpronouns_size];
                readFile(whpronouns_translations, "translations/whpronouns_translations.txt");

                String[] inn_words = new String[inn_size];
                readFile(inn_words, "words/inn_words.txt");
                String[] inn_translations = new String[inn_size];
                readFile(inn_translations, "translations/inn_translations.txt");

                String[] day_words = new String[day_size];
                readFile(day_words, "words/day_words.txt");
                String[] day_translations = new String[day_size];
                readFile(day_translations, "translations/day_translations.txt");

                String[] house_words = new String[house_size];
                readFile(house_words, "words/house_words.txt");
                String[] house_translations = new String[house_size];
                readFile(house_translations, "translations/house_translations.txt");

                String[] animals_words = new String[animals_size];
                readFile(animals_words, "words/animals_words.txt");
                String[] animals_translations = new String[animals_size];
                readFile(animals_translations, "translations/animals_translations.txt");

                String[] health_words = new String[health_size];
                readFile(health_words, "words/health_words.txt");
                String[] health_translations = new String[health_size];
                readFile(health_translations, "translations/health_translations.txt");

                String[] zodiac_words = new String[zodiac_size];
                readFile(zodiac_words, "words/zodiac_words.txt");
                String[] zodiac_translations = new String[zodiac_size];
                readFile(zodiac_translations, "translations/zodiac_translations.txt");

                String[] property_words = new String[property_size];
                readFile(property_words, "words/property_words.txt");
                String[] property_translations = new String[property_size];
                readFile(property_translations, "translations/property_translations.txt");

                String[] career_words = new String[career_size];
                readFile(career_words, "words/career_words.txt");
                String[] career_translations = new String[career_size];
                readFile(career_translations, "translations/career_translations.txt");

                String[] cinema_words = new String[cinema_size];
                readFile(cinema_words, "words/cinema_words.txt");
                String[] cinema_translations = new String[cinema_size];
                readFile(cinema_translations, "translations/cinema_translations.txt");

                String[] computer_words = new String[computer_size];
                readFile(computer_words, "words/computer_words.txt");
                String[] computer_translations = new String[computer_size];
                readFile(computer_translations, "translations/computer_translations.txt");

                String[] cookery_words = new String[cookery_size];
                readFile(cookery_words, "words/cookery_words.txt");
                String[] cookery_translations = new String[cookery_size];
                readFile(cookery_translations, "translations/cookery_translations.txt");

                String[] cooking_words = new String[cooking_size];
                readFile(cooking_words, "words/cooking_words.txt");
                String[] cooking_translations = new String[cooking_size];
                readFile(cooking_translations, "translations/cooking_translations.txt");

                String[] kindsofshops_words = new String[kindsofshops_size];
                readFile(kindsofshops_words, "words/kindsofshops_words.txt");
                String[] kindsofshops_translations = new String[kindsofshops_size];
                readFile(kindsofshops_translations, "translations/kindsofshops_translations.txt");

                String[] furniture_words = new String[furniture_size];
                readFile(furniture_words, "words/furniture_words.txt");
                String[] furniture_translations = new String[furniture_size];
                readFile(furniture_translations, "translations/furniture_translations.txt");

                String[] music_words = new String[music_size];
                readFile(music_words, "words/music_words.txt");
                String[] music_translations = new String[music_size];
                readFile(music_translations, "translations/music_translations.txt");

                String[] clothes_words = new String[clothes_size];
                readFile(clothes_words, "words/clothes_words.txt");
                String[] clothes_translations = new String[clothes_size];
                readFile(clothes_translations, "translations/clothes_translations.txt");

                String[] professions_words = new String[professions_size];
                readFile(professions_words, "words/professions_words.txt");
                String[] professions_translations = new String[professions_size];
                readFile(professions_translations, "translations/professions_translations.txt");

                String[] trip_words = new String[trip_size];
                readFile(trip_words, "words/trip_words.txt");
                String[] trip_translations = new String[trip_size];
                readFile(trip_translations, "translations/trip_translations.txt");

                String[] sport_words = new String[sport_size];
                readFile(sport_words, "words/sport_words.txt");
                String[] sport_translations = new String[sport_size];
                readFile(sport_translations, "translations/sport_translations.txt");

                String[] transport_words = new String[transport_size];
                readFile(transport_words, "words/transport_words.txt");
                String[] transport_translations = new String[transport_size];
                readFile(transport_translations, "translations/transport_translations.txt");

                String[] character_words = new String[character_size];
                readFile(character_words, "words/character_words.txt");
                String[] character_translations = new String[character_size];
                readFile(character_translations, "translations/character_translations.txt");

                String[] others_words = new String[others_size];
                readFile(others_words, "words/everything_words.txt");
                String[] others_translations = new String[others_size];
                readFile(others_translations, "translations/everything_translations.txt");

                words = new String[everything_size];
                translations = new String[everything_size];

                // Объединение всех массивов(много копипаста)
                System.arraycopy(colors_words, 0, words, 0, colors_words.length);
                System.arraycopy(relatives_words, 0, words, colors_words.length, relatives_words.length);
                System.arraycopy(verbs_words, 0, words, colors_words.length + relatives_words.length, verbs_words.length);
                System.arraycopy(automobile_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length, automobile_words.length);
                System.arraycopy(airport_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length, airport_words.length);
                System.arraycopy(appereance_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length, appereance_words.length);
                System.arraycopy(city_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length, city_words.length);
                System.arraycopy(whpronouns_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length, whpronouns_words.length);
                System.arraycopy(inn_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length, inn_words.length);
                System.arraycopy(day_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length, day_words.length);
                System.arraycopy(house_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length, house_words.length);
                System.arraycopy(animals_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length, animals_words.length);
                System.arraycopy(health_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length, health_words.length);
                System.arraycopy(zodiac_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length, zodiac_words.length);
                System.arraycopy(property_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length, property_words.length);
                System.arraycopy(career_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length, career_words.length);
                System.arraycopy(cinema_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length, cinema_words.length);
                System.arraycopy(computer_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length, computer_words.length);
                System.arraycopy(cookery_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length, cookery_words.length);
                System.arraycopy(cooking_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length, cooking_words.length);
                System.arraycopy(kindsofshops_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length + cooking_words.length, kindsofshops_words.length);
                System.arraycopy(furniture_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length + cooking_words.length +
                        kindsofshops_words.length, furniture_words.length);
                System.arraycopy(music_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length + cooking_words.length +
                        kindsofshops_words.length + furniture_words.length, music_words.length);
                System.arraycopy(clothes_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length + cooking_words.length +
                        kindsofshops_words.length + furniture_words.length + music_words.length, clothes_words.length);
                System.arraycopy(professions_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length + cooking_words.length +
                        kindsofshops_words.length + furniture_words.length + music_words.length + clothes_words.length, professions_words.length);
                System.arraycopy(trip_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length + cooking_words.length +
                        kindsofshops_words.length + furniture_words.length + music_words.length + clothes_words.length + professions_words.length, trip_words.length);
                System.arraycopy(sport_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length + cooking_words.length +
                        kindsofshops_words.length + furniture_words.length + music_words.length + clothes_words.length + professions_words.length + trip_words.length, sport_words.length);
                System.arraycopy(transport_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length + cooking_words.length +
                        kindsofshops_words.length + furniture_words.length + music_words.length + clothes_words.length + professions_words.length + trip_words.length + sport_words.length, transport_words.length);
                System.arraycopy(character_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length + cooking_words.length +
                        kindsofshops_words.length + furniture_words.length + music_words.length + clothes_words.length + professions_words.length + trip_words.length + sport_words.length +
                        transport_words.length, character_words.length);
                System.arraycopy(others_words, 0, words, colors_words.length + relatives_words.length + verbs_words.length + automobile_words.length + airport_words.length +
                        appereance_words.length + city_words.length + whpronouns_words.length + inn_words.length + day_words.length + house_words.length + animals_words.length + health_words.length +
                        zodiac_words.length + property_words.length + career_words.length + cinema_words.length + computer_words.length + cookery_words.length + cooking_words.length +
                        kindsofshops_words.length + furniture_words.length + music_words.length + clothes_words.length + professions_words.length + trip_words.length + sport_words.length +
                        transport_words.length + character_words.length, others_words.length);

                System.arraycopy(colors_translations, 0, translations, 0, colors_translations.length);
                System.arraycopy(relatives_translations, 0, translations, colors_translations.length, relatives_translations.length);
                System.arraycopy(verbs_translations, 0, translations, colors_translations.length + relatives_translations.length, verbs_translations.length);
                System.arraycopy(automobile_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length, automobile_translations.length);
                System.arraycopy(airport_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length, airport_translations.length);
                System.arraycopy(appereance_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length, appereance_translations.length);
                System.arraycopy(city_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length, city_translations.length);
                System.arraycopy(whpronouns_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length, whpronouns_translations.length);
                System.arraycopy(inn_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length, inn_translations.length);
                System.arraycopy(day_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length, day_translations.length);
                System.arraycopy(house_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length, house_translations.length);
                System.arraycopy(animals_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length, animals_translations.length);
                System.arraycopy(health_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length, health_translations.length);
                System.arraycopy(zodiac_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length, zodiac_translations.length);
                System.arraycopy(property_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length, property_translations.length);
                System.arraycopy(career_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length, career_translations.length);
                System.arraycopy(cinema_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length, cinema_translations.length);
                System.arraycopy(computer_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length, computer_translations.length);
                System.arraycopy(cookery_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length, cookery_translations.length);
                System.arraycopy(cooking_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length, cooking_translations.length);
                System.arraycopy(kindsofshops_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length + cooking_translations.length, kindsofshops_translations.length);
                System.arraycopy(furniture_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length + cooking_translations.length +
                        kindsofshops_translations.length, furniture_translations.length);
                System.arraycopy(music_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length + cooking_translations.length +
                        kindsofshops_translations.length + furniture_translations.length, music_translations.length);
                System.arraycopy(clothes_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length + cooking_translations.length +
                        kindsofshops_translations.length + furniture_translations.length + music_translations.length, clothes_translations.length);
                System.arraycopy(professions_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length + cooking_translations.length +
                        kindsofshops_translations.length + furniture_translations.length + music_translations.length + clothes_translations.length, professions_translations.length);
                System.arraycopy(trip_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length + cooking_translations.length +
                        kindsofshops_translations.length + furniture_translations.length + music_translations.length + clothes_translations.length + professions_translations.length, trip_translations.length);
                System.arraycopy(sport_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length + cooking_translations.length +
                        kindsofshops_translations.length + furniture_translations.length + music_translations.length + clothes_translations.length + professions_translations.length + trip_translations.length, sport_translations.length);
                System.arraycopy(transport_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length + cooking_translations.length +
                        kindsofshops_translations.length + furniture_translations.length + music_translations.length + clothes_translations.length + professions_translations.length + trip_translations.length + sport_translations.length, transport_translations.length);
                System.arraycopy(character_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length + cooking_translations.length +
                        kindsofshops_translations.length + furniture_translations.length + music_translations.length + clothes_translations.length + professions_translations.length + trip_translations.length + sport_translations.length +
                        transport_translations.length, character_translations.length);
                System.arraycopy(others_translations, 0, translations, colors_translations.length + relatives_translations.length + verbs_translations.length + automobile_translations.length + airport_translations.length +
                        appereance_translations.length + city_translations.length + whpronouns_translations.length + inn_translations.length + day_translations.length + house_translations.length + animals_translations.length + health_translations.length +
                        zodiac_translations.length + property_translations.length + career_translations.length + cinema_translations.length + computer_translations.length + cookery_translations.length + cooking_translations.length +
                        kindsofshops_translations.length + furniture_translations.length + music_translations.length + clothes_translations.length + professions_translations.length + trip_translations.length + sport_translations.length +
                        transport_translations.length + character_translations.length, others_translations.length);

                break;
            }
        }

        list_mistakes = new LinkedList<>();

        word = (MyTextView)findViewById(R.id.word);
        translation = (MyTextView)findViewById(R.id.translation);
        points_activity = (MyTextView)findViewById(R.id.points);
        points_tv = (MyTextView)findViewById(R.id.points_tv);
        time_activity = (MyTextView)findViewById(R.id.time);
        time_tv = (MyTextView)findViewById(R.id.time_tv);
        tv_yes = (MyTextView) findViewById(R.id.click_yes);
        tv_no = (MyTextView)findViewById(R.id.click_no);

        points_activity.setText(String.valueOf(points));

        imageRightAnswear = (ImageView)findViewById(R.id.imageView_RightAnswear);
        imageWrongAnswear = (ImageView)findViewById(R.id.imageView_WrongAnswear);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        try {
            wrongSound = soundPool.load(getAssets().openFd("sounds/wrong.wav"),1);
            rightSound = soundPool.load(getAssets().openFd("sounds/right.wav"),1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        createAndStart(countdownPeriod, countDownInterval);

        init();

        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation_in = AnimationUtils.loadAnimation(Practical_work1lvl.this, R.anim.alpha_in);
                animation_out = AnimationUtils.loadAnimation(Practical_work1lvl.this, R.anim.alpha_out);
                animation_in.setDuration(5000);
                animation_out.setDuration(1000);
                if (true_answear) {
                    points += 1;
                    points_activity.setText(String.valueOf(points));
                    playSound(rightSound);
                    imageRightAnswear.setAnimation(animation_in);
                    imageRightAnswear.setAnimation(animation_out);
                    timer.cancel();
                    countdownPeriod += add_time;
                    createAndStart(countdownPeriod, countDownInterval);
                } else {
                    playSound(wrongSound);
                    imageWrongAnswear.setAnimation(animation_in);
                    imageWrongAnswear.setAnimation(animation_out);

                    Mistake mistake = new Mistake(word.getText().toString()+" <> "+translation.getText().toString(), "Да", "Нет");
                    list_mistakes.add(mistake);

                    timer.cancel();
                    countdownPeriod -= remove_time;
                    createAndStart(countdownPeriod, countDownInterval);
                }
                init();
            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation_in = AnimationUtils.loadAnimation(Practical_work1lvl.this, R.anim.alpha_in);
                animation_out = AnimationUtils.loadAnimation(Practical_work1lvl.this, R.anim.alpha_out);
                animation_in.setDuration(5000);
                animation_out.setDuration(1000);
                if (!true_answear) {
                    points+=1;
                    points_activity.setText(String.valueOf(points));
                    playSound(rightSound);
                    imageRightAnswear.setAnimation(animation_in);
                    imageRightAnswear.setAnimation(animation_out);
                    timer.cancel();
                    countdownPeriod += add_time;
                    createAndStart(countdownPeriod,countDownInterval);
                }else{
                    playSound(wrongSound);
                    imageWrongAnswear.setAnimation(animation_in);
                    imageWrongAnswear.setAnimation(animation_out);

                    Mistake mistake = new Mistake(word.getText().toString()+" <> "+translation.getText().toString(), "Нет", "Да");
                    list_mistakes.add(mistake);

                    timer.cancel();
                    countdownPeriod -= remove_time;
                    createAndStart(countdownPeriod, countDownInterval);
                }
                init();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        createAndStart(countdownPeriod, countDownInterval);
    }

    private int playSound(int sound) {
        if (sound > 0) {
            Stream = soundPool.play(sound,1,1,1,0,1);
        }
        return Stream;
    }

    private void createAndStart(long cdP, long cdI) {
        timer = new CountDownTimer(cdP, cdI) {

            public void onTick(long millisUntilFinished) {
                countdownPeriod-=countDownInterval;
                time_activity.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                Intent i = new Intent(Practical_work1lvl.this, Mistakes.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                String topic_and_points = topic + "1lvl" + separator_for_topic_and_points + points;

                Bundle bundle = new Bundle();
                bundle.putString("topic_and_points", topic_and_points);
                bundle.putInt("right_count", points);
                bundle.putInt("mistakes_count", list_mistakes.size());
                ArrayList<Mistake> arrayList = new ArrayList<>(list_mistakes); // потому что linkedlist нельзя передать
                bundle.putParcelableArrayList("list_mistakes", arrayList);

                i.putExtras(bundle);
                startActivity(i);
                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
            }

        }.start();
    }

    public void readFile(String[] array, String filename){
        try {
            AssetManager assetManager = this.getAssets();
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(filename), "windows-1251");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int i = 0;
            while (i<array.length) {
                array[i] = bufferedReader.readLine();
                i++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed(){
        timer.cancel();
        Intent i = new Intent(this, ChooseDifficulty.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("topic",topic);
        startActivity(i);
        overridePendingTransition(R.anim.left_to_right, R.anim.top_in);
    }
}
