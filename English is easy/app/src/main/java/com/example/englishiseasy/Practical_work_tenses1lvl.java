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

public class Practical_work_tenses1lvl extends AppCompatActivity {

    String topic = "tenses";
    String sentence_group;
    String sentence_time;
    String tense;

    int presentSimpleSize = 181;
    int presentProgressiveSize = 4;
    int presentPerfectSize = 4;
    int presentPerfectProgressiveSize = 2;

    int pastSimpleSize = 30;
    int pastProgressiveSize = 2;
    int pastPerfectSize = 2;
    int pastPerfectProgressiveSize = 2;

    int futureSimpleSize = 2;
    int futureProgressiveSize = 2;
    int futurePerfectSize = 2;
    int futurePerfectProgressiveSize = 2;

    int presentGroupsSize;
    int pastGroupsSize;
    int futureGroupsSize;

    int simpleTimesSize;
    int progressiveTimesSize;
    int perfectTimesSize;
    int perfect_progressiveTimesSize;

    int other_size = 29;
    int sentences_size;

    String array_sentences[], array_grammar[];
    String sentence_str, grammar_str;

    LinkedList<Mistake> list_mistakes;

    final String separator_for_topic_and_points = "/";

    ImageView imageRightAnswear, imageWrongAnswear;

    Animation animation_in, animation_out;

    private SoundPool soundPool;
    private int wrongSound, rightSound, Stream;

    public int sentence_index;
    public int points = 0;

    CountDownTimer timer;
    long countDownInterval = 1000;
    long countdownPeriod = 60000;
    final long add_time = 2000;
    final long remove_time = 2000;

    MyTextView sentence, points_activity, points_tv, time_activity, time_tv;
    MyTextView textViews[] = new MyTextView[8];
    boolean true_textViews[] = new boolean[8];

    private void init(){
        for (int i=0; i<true_textViews.length; i++){
            true_textViews[i] = false;
        }

        sentence_index = (int)(Math.random()*array_sentences.length);
        sentence_str = array_sentences[sentence_index];
        grammar_str = "";
        String tmp[] = array_grammar[sentence_index].split("/");
        for (int i=0; i<tmp.length; i++){
            if (i == tmp.length-1){
                grammar_str += tmp[i].trim();
            }else{
                grammar_str += tmp[i].trim() + ", ";
            }
        }
        sentence.setText(sentence_str);

        int random_index;
        int random_button;
        boolean check = false;

        for (int i = 0; i < textViews.length; i++){
            String current_grammar = "";
            random_index = (int)(Math.random()*array_grammar.length);
            tmp = array_grammar[random_index].split("/");
            for (int j=0; j<tmp.length; j++){
                if (j == tmp.length-1){
                    current_grammar += tmp[j].trim();
                }else{
                    current_grammar += tmp[j].trim() + ", ";
                }
            }

            if (current_grammar.equals(grammar_str)){
                true_textViews[i] = true;
            }
            textViews[i].setText(current_grammar);
        }

        for (int i=0; i<true_textViews.length; i++){
            if (true_textViews[i]){
                check = true;
                break;
            }
        }

        if (!check){
            random_button = (int)(Math.random()*true_textViews.length);
            true_textViews[random_button] = true;
            textViews[random_button].setText(grammar_str);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_work_tenses1lvl);

        Bundle bundle = getIntent().getExtras();
        sentence_group = bundle.getString("group");
        sentence_time = bundle.getString("time");
        tense =  sentence_time + " " + sentence_group;

        presentGroupsSize = presentSimpleSize + presentProgressiveSize + presentPerfectSize + presentPerfectProgressiveSize;
        pastGroupsSize = pastSimpleSize + pastProgressiveSize + pastPerfectSize + pastPerfectProgressiveSize;
        futureGroupsSize = futureSimpleSize + futureProgressiveSize + futurePerfectSize + futurePerfectProgressiveSize;

        simpleTimesSize = pastSimpleSize + presentSimpleSize + futureSimpleSize;
        progressiveTimesSize = pastProgressiveSize + presentProgressiveSize + futureProgressiveSize;
        perfectTimesSize = pastPerfectSize + presentPerfectSize + futurePerfectSize;
        perfect_progressiveTimesSize = pastPerfectProgressiveSize + presentPerfectProgressiveSize + futurePerfectProgressiveSize;

        sentences_size = presentGroupsSize + pastGroupsSize + futureGroupsSize;

        // Далее следует ооочень много копипасты, который в зависимости от предпочтений игрока выбирает, заполняет нужные массивы, а потом их объединяет, если это требуется
        switch (tense){
            case "Present Simple":{
                array_sentences = new String[presentSimpleSize];
                readFile(array_sentences, "words/tenses/PresentSimple.txt");

                array_grammar = new String[presentSimpleSize];
                readFile(array_grammar, "words/tenses/PresentSimple_grammar.txt");

                break;
            }
            case "Present Progressive":{
                array_sentences = new String[presentProgressiveSize];
                readFile(array_sentences, "words/tenses/PresentProgressive.txt");

                array_grammar = new String[presentProgressiveSize];
                readFile(array_grammar, "words/tenses/PresentProgressive_grammar.txt");

                break;
            }
            case "Present Perfect":{
                array_sentences = new String[presentPerfectSize];
                readFile(array_sentences, "words/tenses/PresentPerfect.txt");

                array_grammar = new String[presentPerfectSize];
                readFile(array_grammar, "words/tenses/PresentPerfect_grammar.txt");

                break;
            }
            case "Present Perfect Progressive":{
                array_sentences = new String[presentPerfectProgressiveSize];
                readFile(array_sentences, "words/tenses/PresentPerfectProgressive.txt");

                array_grammar = new String[presentPerfectProgressiveSize];
                readFile(array_grammar, "words/tenses/PresentPerfectProgressive_grammar.txt");

                break;
            }

            case "Past Simple":{
                array_sentences = new String[pastSimpleSize];
                readFile(array_sentences, "words/tenses/PastSimple.txt");

                array_grammar = new String[pastSimpleSize];
                readFile(array_grammar, "words/tenses/PastSimple_grammar.txt");

                break;
            }
            case "Past Progressive":{
                array_sentences = new String[pastProgressiveSize];
                readFile(array_sentences, "words/tenses/PastProgressive.txt");

                array_grammar = new String[pastProgressiveSize];
                readFile(array_grammar, "words/tenses/PastProgressive_grammar.txt");

                break;
            }
            case "Past Perfect":{
                array_sentences = new String[pastPerfectSize];
                readFile(array_sentences, "words/tenses/PastPerfect.txt");

                array_grammar = new String[pastPerfectSize];
                readFile(array_grammar, "words/tenses/PastPerfect_grammar.txt");

                break;
            }
            case "Past Perfect Progressive":{
                array_sentences = new String[pastPerfectProgressiveSize];
                readFile(array_sentences, "words/tenses/PastPerfectProgressive.txt");

                array_grammar = new String[pastPerfectProgressiveSize];
                readFile(array_grammar, "words/tenses/PastPerfectProgressive_grammar.txt");

                break;
            }

            case "Future Simple":{
                array_sentences = new String[futureSimpleSize];
                readFile(array_sentences, "words/tenses/FutureSimple.txt");

                array_grammar = new String[futureSimpleSize];
                readFile(array_grammar, "words/tenses/FutureSimple_grammar.txt");

                break;
            }
            case "Future Progressive":{
                array_sentences = new String[futureProgressiveSize];
                readFile(array_sentences, "words/tenses/FutureProgressive.txt");

                array_grammar = new String[futureProgressiveSize];
                readFile(array_grammar, "words/tenses/FutureProgressive_grammar.txt");

                break;
            }
            case "Future Perfect":{
                array_sentences = new String[futurePerfectSize];
                readFile(array_sentences, "words/tenses/FuturePerfect.txt");

                array_grammar = new String[futurePerfectSize];
                readFile(array_grammar, "words/tenses/FuturePerfect_grammar.txt");

                break;
            }
            case "Future Perfect Progressive":{
                array_sentences = new String[futurePerfectProgressiveSize];
                readFile(array_sentences, "words/tenses/FuturePerfectProgressive.txt");

                array_grammar = new String[futurePerfectProgressiveSize];
                readFile(array_grammar, "words/tenses/FuturePerfectProgressive_grammar.txt");

                break;
            }

            case "Present All groups":{
                array_sentences = new String[presentGroupsSize];
                array_grammar = new String[presentGroupsSize];

                String presentSimple[] = new String[presentSimpleSize];
                readFile(presentSimple, "words/tenses/PresentSimple.txt");
                String[] grammar_presentSimple = new String[presentSimpleSize];
                readFile(grammar_presentSimple, "words/tenses/PresentSimple_grammar.txt");

                String presentProgressive[] = new String[presentProgressiveSize];
                readFile(presentProgressive, "words/tenses/PresentProgressive.txt");
                String[] grammar_presentProgressive = new String[presentProgressiveSize];
                readFile(grammar_presentProgressive, "words/tenses/PresentProgressive_grammar.txt");

                String presentPerfect[] = new String[presentPerfectSize];
                readFile(presentPerfect, "words/tenses/PresentPerfect.txt");
                String[] grammar_presentPerfect = new String[presentPerfectSize];
                readFile(grammar_presentPerfect, "words/tenses/PresentPerfect_grammar.txt");

                String presentPerfectProgressive[] = new String[presentPerfectProgressiveSize];
                readFile(presentPerfectProgressive, "words/tenses/PresentPerfectProgressive.txt");
                String[] grammar_presentPerfectProgressive = new String[presentPerfectProgressiveSize];
                readFile(grammar_presentPerfectProgressive, "words/tenses/PresentPerfectProgressive_grammar.txt");

                System.arraycopy(presentSimple, 0, array_sentences, 0, presentSimple.length);
                System.arraycopy(presentProgressive, 0, array_sentences, presentSimple.length, presentProgressive.length);
                System.arraycopy(presentPerfect, 0, array_sentences, presentSimple.length + presentProgressive.length, presentPerfect.length);
                System.arraycopy(presentPerfectProgressive, 0, array_sentences, presentSimple.length + presentProgressive.length + presentPerfect.length, presentPerfectProgressive.length);

                System.arraycopy(grammar_presentSimple, 0, array_grammar, 0, grammar_presentSimple.length);
                System.arraycopy(grammar_presentProgressive, 0, array_grammar, grammar_presentSimple.length, grammar_presentProgressive.length);
                System.arraycopy(grammar_presentPerfect, 0, array_grammar, grammar_presentSimple.length + grammar_presentProgressive.length, grammar_presentPerfect.length);
                System.arraycopy(grammar_presentPerfectProgressive, 0, array_grammar, grammar_presentSimple.length + grammar_presentProgressive.length + grammar_presentPerfect.length, grammar_presentPerfectProgressive.length);

                break;
            }

            case "Past All groups":{
                array_sentences = new String[pastGroupsSize];
                array_grammar = new String[pastGroupsSize];

                String pastSimple[] = new String[pastSimpleSize];
                readFile(pastSimple, "words/tenses/PastSimple.txt");
                String[] grammar_pastSimple = new String[pastSimpleSize];
                readFile(grammar_pastSimple, "words/tenses/PastSimple_grammar.txt");

                String pastProgressive[] = new String[pastProgressiveSize];
                readFile(pastProgressive, "words/tenses/PastProgressive.txt");
                String[] grammar_pastProgressive = new String[pastProgressiveSize];
                readFile(grammar_pastProgressive, "words/tenses/PastProgressive_grammar.txt");

                String pastPerfect[] = new String[pastPerfectSize];
                readFile(pastPerfect, "words/tenses/PastPerfect.txt");
                String[] grammar_pastPerfect = new String[pastPerfectSize];
                readFile(grammar_pastPerfect, "words/tenses/PastPerfect_grammar.txt");

                String pastPerfectProgressive[] = new String[pastPerfectProgressiveSize];
                readFile(pastPerfectProgressive, "words/tenses/PastPerfectProgressive.txt");
                String[] grammar_pastPerfectProgressive = new String[pastPerfectProgressiveSize];
                readFile(grammar_pastPerfectProgressive, "words/tenses/PastPerfectProgressive_grammar.txt");

                System.arraycopy(pastSimple, 0, array_sentences, 0, pastSimple.length);
                System.arraycopy(pastProgressive, 0, array_sentences, pastSimple.length, pastProgressive.length);
                System.arraycopy(pastPerfect, 0, array_sentences, pastSimple.length + pastProgressive.length, pastPerfect.length);
                System.arraycopy(pastPerfectProgressive, 0, array_sentences, pastSimple.length + pastProgressive.length + pastPerfect.length, pastPerfectProgressive.length);

                System.arraycopy(grammar_pastSimple, 0, array_grammar, 0, grammar_pastSimple.length);
                System.arraycopy(grammar_pastProgressive, 0, array_grammar, grammar_pastSimple.length, grammar_pastProgressive.length);
                System.arraycopy(grammar_pastPerfect, 0, array_grammar, grammar_pastSimple.length + grammar_pastProgressive.length, grammar_pastPerfect.length);
                System.arraycopy(grammar_pastPerfectProgressive, 0, array_grammar, grammar_pastSimple.length + grammar_pastProgressive.length + grammar_pastPerfect.length, grammar_pastPerfectProgressive.length);

                break;
            }

            case "Future All groups":{
                array_sentences = new String[futureGroupsSize];
                array_grammar = new String[futureGroupsSize];

                String futureSimple[] = new String[futureSimpleSize];
                readFile(futureSimple, "words/tenses/FutureSimple.txt");
                String[] grammar_futureSimple = new String[futureSimpleSize];
                readFile(grammar_futureSimple, "words/tenses/FutureSimple_grammar.txt");

                String futureProgressive[] = new String[futureProgressiveSize];
                readFile(futureProgressive, "words/tenses/FutureProgressive.txt");
                String[] grammar_futureProgressive = new String[futureProgressiveSize];
                readFile(grammar_futureProgressive, "words/tenses/FutureProgressive_grammar.txt");

                String futurePerfect[] = new String[futurePerfectSize];
                readFile(futurePerfect, "words/tenses/FuturePerfect.txt");
                String[] grammar_futurePerfect = new String[futurePerfectSize];
                readFile(grammar_futurePerfect, "words/tenses/FuturePerfect_grammar.txt");

                String futurePerfectProgressive[] = new String[futurePerfectProgressiveSize];
                readFile(futurePerfectProgressive, "words/tenses/FuturePerfectProgressive.txt");
                String[] grammar_futurePerfectProgressive = new String[futurePerfectProgressiveSize];
                readFile(grammar_futurePerfectProgressive, "words/tenses/FuturePerfectProgressive_grammar.txt");

                System.arraycopy(futureSimple, 0, array_sentences, 0, futureSimple.length);
                System.arraycopy(futureProgressive, 0, array_sentences, futureSimple.length, futureProgressive.length);
                System.arraycopy(futurePerfect, 0, array_sentences, futureSimple.length + futureProgressive.length, futurePerfect.length);
                System.arraycopy(futurePerfectProgressive, 0, array_sentences, futureSimple.length + futureProgressive.length + futurePerfect.length, futurePerfectProgressive.length);

                System.arraycopy(grammar_futureSimple, 0, array_grammar, 0, grammar_futureSimple.length);
                System.arraycopy(grammar_futureProgressive, 0, array_grammar, grammar_futureSimple.length, grammar_futureProgressive.length);
                System.arraycopy(grammar_futurePerfect, 0, array_grammar, grammar_futureSimple.length + grammar_futureProgressive.length, grammar_futurePerfect.length);
                System.arraycopy(grammar_futurePerfectProgressive, 0, array_grammar, grammar_futureSimple.length + grammar_futureProgressive.length + grammar_futurePerfect.length, grammar_futurePerfectProgressive.length);

                break;
            }

            case "All times Simple":{
                array_sentences = new String[simpleTimesSize];
                array_grammar = new String[simpleTimesSize];

                String presentSimple[] = new String[presentSimpleSize];
                readFile(presentSimple, "words/tenses/PresentSimple.txt");
                String[] grammar_presentSimple = new String[presentSimpleSize];
                readFile(grammar_presentSimple, "words/tenses/PresentSimple_grammar.txt");

                String pastSimple[] = new String[pastSimpleSize];
                readFile(pastSimple, "words/tenses/PastSimple.txt");
                String[] grammar_pastSimple = new String[pastSimpleSize];
                readFile(grammar_pastSimple, "words/tenses/PastSimple_grammar.txt");

                String futureSimple[] = new String[futureSimpleSize];
                readFile(futureSimple, "words/tenses/FutureSimple.txt");
                String[] grammar_futureSimple = new String[futureSimpleSize];
                readFile(grammar_futureSimple, "words/tenses/FutureSimple_grammar.txt");

                System.arraycopy(presentSimple, 0, array_sentences, 0, presentSimple.length);
                System.arraycopy(pastSimple, 0, array_sentences, presentSimple.length, pastSimple.length);
                System.arraycopy(futureSimple, 0, array_sentences, presentSimple.length + pastSimple.length, futureSimple.length);

                System.arraycopy(grammar_presentSimple, 0, array_grammar, 0, grammar_presentSimple.length);
                System.arraycopy(grammar_pastSimple, 0, array_grammar, grammar_presentSimple.length, grammar_pastSimple.length);
                System.arraycopy(grammar_futureSimple, 0, array_grammar, grammar_presentSimple.length + grammar_pastSimple.length, grammar_futureSimple.length);

                break;
            }

            case "All times Progressive":{
                array_sentences = new String[progressiveTimesSize];
                array_grammar = new String[progressiveTimesSize];

                String presentProgressive[] = new String[presentProgressiveSize];
                readFile(presentProgressive, "words/tenses/PresentProgressive.txt");
                String[] grammar_presentProgressive = new String[presentProgressiveSize];
                readFile(grammar_presentProgressive, "words/tenses/PresentProgressive_grammar.txt");

                String pastProgressive[] = new String[pastProgressiveSize];
                readFile(pastProgressive, "words/tenses/PastProgressive.txt");
                String[] grammar_pastProgressive = new String[pastProgressiveSize];
                readFile(grammar_pastProgressive, "words/tenses/PastProgressive_grammar.txt");

                String futureProgressive[] = new String[futureProgressiveSize];
                readFile(futureProgressive, "words/tenses/FutureProgressive.txt");
                String[] grammar_futureProgressive = new String[futureProgressiveSize];
                readFile(grammar_futureProgressive, "words/tenses/FutureProgressive_grammar.txt");

                System.arraycopy(presentProgressive, 0, array_sentences, 0, presentProgressive.length);
                System.arraycopy(pastProgressive, 0, array_sentences, presentProgressive.length, pastProgressive.length);
                System.arraycopy(futureProgressive, 0, array_sentences, presentProgressive.length + pastProgressive.length, futureProgressive.length);

                System.arraycopy(grammar_presentProgressive, 0, array_grammar, 0, grammar_presentProgressive.length);
                System.arraycopy(grammar_pastProgressive, 0, array_grammar, grammar_presentProgressive.length, grammar_pastProgressive.length);
                System.arraycopy(grammar_futureProgressive, 0, array_grammar, grammar_presentProgressive.length + grammar_pastProgressive.length, grammar_futureProgressive.length);

                break;
            }

            case "All times Perfect":{
                array_sentences = new String[perfectTimesSize];
                array_grammar = new String[perfectTimesSize];

                String presentPerfect[] = new String[presentPerfectSize];
                readFile(presentPerfect, "words/tenses/PresentPerfect.txt");
                String[] grammar_presentPerfect = new String[presentPerfectSize];
                readFile(grammar_presentPerfect, "words/tenses/PresentPerfect_grammar.txt");

                String pastPerfect[] = new String[pastPerfectSize];
                readFile(pastPerfect, "words/tenses/PastPerfect.txt");
                String[] grammar_pastPerfect = new String[pastPerfectSize];
                readFile(grammar_pastPerfect, "words/tenses/PastPerfect_grammar.txt");

                String futurePerfect[] = new String[futurePerfectSize];
                readFile(futurePerfect, "words/tenses/FuturePerfect.txt");
                String[] grammar_futurePerfect = new String[futurePerfectSize];
                readFile(grammar_futurePerfect, "words/tenses/FuturePerfect_grammar.txt");

                System.arraycopy(presentPerfect, 0, array_sentences, 0, presentPerfect.length);
                System.arraycopy(pastPerfect, 0, array_sentences, presentPerfect.length, pastPerfect.length);
                System.arraycopy(futurePerfect, 0, array_sentences, presentPerfect.length + pastPerfect.length, futurePerfect.length);

                System.arraycopy(grammar_presentPerfect, 0, array_grammar, 0, grammar_presentPerfect.length);
                System.arraycopy(grammar_pastPerfect, 0, array_grammar, grammar_presentPerfect.length, grammar_pastPerfect.length);
                System.arraycopy(grammar_futurePerfect, 0, array_grammar, grammar_presentPerfect.length + grammar_pastPerfect.length, grammar_futurePerfect.length);

                break;
            }

            case "All times Perfect Progressive":{
                array_sentences = new String[perfect_progressiveTimesSize];
                array_grammar = new String[perfect_progressiveTimesSize];

                String presentPerfectProgressive[] = new String[presentPerfectProgressiveSize];
                readFile(presentPerfectProgressive, "words/tenses/PresentPerfectProgressive.txt");
                String[] grammar_presentPerfectProgressive = new String[presentPerfectProgressiveSize];
                readFile(grammar_presentPerfectProgressive, "words/tenses/PresentPerfectProgressive_grammar.txt");

                String pastPerfectProgressive[] = new String[pastPerfectProgressiveSize];
                readFile(pastPerfectProgressive, "words/tenses/PastPerfectProgressive.txt");
                String[] grammar_pastPerfectProgressive = new String[pastPerfectProgressiveSize];
                readFile(grammar_pastPerfectProgressive, "words/tenses/PastPerfectProgressive_grammar.txt");

                String futurePerfectProgressive[] = new String[futurePerfectProgressiveSize];
                readFile(futurePerfectProgressive, "words/tenses/FuturePerfectProgressive.txt");
                String[] grammar_futurePerfectProgressive = new String[futurePerfectProgressiveSize];
                readFile(grammar_futurePerfectProgressive, "words/tenses/FuturePerfectProgressive_grammar.txt");

                System.arraycopy(presentPerfectProgressive, 0, array_sentences, 0, presentPerfectProgressive.length);
                System.arraycopy(pastPerfectProgressive, 0, array_sentences, presentPerfectProgressive.length, pastPerfectProgressive.length);
                System.arraycopy(futurePerfectProgressive, 0, array_sentences, presentPerfectProgressive.length + pastPerfectProgressive.length, futurePerfectProgressive.length);

                System.arraycopy(grammar_presentPerfectProgressive, 0, array_grammar, 0, grammar_presentPerfectProgressive.length);
                System.arraycopy(grammar_pastPerfectProgressive, 0, array_grammar, grammar_presentPerfectProgressive.length, grammar_pastPerfectProgressive.length);
                System.arraycopy(grammar_futurePerfectProgressive, 0, array_grammar, grammar_presentPerfectProgressive.length + grammar_pastPerfectProgressive.length, grammar_futurePerfectProgressive.length);

                break;
            }

            case "All times All groups":{
                array_sentences = new String[sentences_size + other_size];
                array_grammar = new String[sentences_size + other_size];

                String[] present_sentences = new String[presentGroupsSize];
                String[] grammar_present_sentences = new String[presentGroupsSize];

                String presentSimple[] = new String[presentSimpleSize];
                readFile(presentSimple, "words/tenses/PresentSimple.txt");
                String[] grammar_presentSimple = new String[presentSimpleSize];
                readFile(grammar_presentSimple, "words/tenses/PresentSimple_grammar.txt");

                String presentProgressive[] = new String[presentProgressiveSize];
                readFile(presentProgressive, "words/tenses/PresentProgressive.txt");
                String[] grammar_presentProgressive = new String[presentProgressiveSize];
                readFile(grammar_presentProgressive, "words/tenses/PresentProgressive_grammar.txt");

                String presentPerfect[] = new String[presentPerfectSize];
                readFile(presentPerfect, "words/tenses/PresentPerfect.txt");
                String[] grammar_presentPerfect = new String[presentPerfectSize];
                readFile(grammar_presentPerfect, "words/tenses/PresentPerfect_grammar.txt");

                String presentPerfectProgressive[] = new String[presentPerfectProgressiveSize];
                readFile(presentPerfectProgressive, "words/tenses/PresentPerfectProgressive.txt");
                String[] grammar_presentPerfectProgressive = new String[presentPerfectProgressiveSize];
                readFile(grammar_presentPerfectProgressive, "words/tenses/PresentPerfectProgressive_grammar.txt");

                System.arraycopy(presentSimple, 0, present_sentences, 0, presentSimple.length);
                System.arraycopy(presentProgressive, 0, present_sentences, presentSimple.length, presentProgressive.length);
                System.arraycopy(presentPerfect, 0, present_sentences, presentSimple.length + presentProgressive.length, presentPerfect.length);
                System.arraycopy(presentPerfectProgressive, 0, present_sentences, presentSimple.length + presentProgressive.length + presentPerfect.length, presentPerfectProgressive.length);

                System.arraycopy(grammar_presentSimple, 0, grammar_present_sentences, 0, grammar_presentSimple.length);
                System.arraycopy(grammar_presentProgressive, 0, grammar_present_sentences, grammar_presentSimple.length, grammar_presentProgressive.length);
                System.arraycopy(grammar_presentPerfect, 0, grammar_present_sentences, grammar_presentSimple.length + grammar_presentProgressive.length, grammar_presentPerfect.length);
                System.arraycopy(grammar_presentPerfectProgressive, 0, grammar_present_sentences, grammar_presentSimple.length + grammar_presentProgressive.length + grammar_presentPerfect.length, grammar_presentPerfectProgressive.length);

                String[] past_sentences = new String[pastGroupsSize];
                String[] grammar_past_sentences = new String[pastGroupsSize];

                String pastSimple[] = new String[pastSimpleSize];
                readFile(pastSimple, "words/tenses/PastSimple.txt");
                String[] grammar_pastSimple = new String[pastSimpleSize];
                readFile(grammar_pastSimple, "words/tenses/PastSimple_grammar.txt");

                String pastProgressive[] = new String[pastProgressiveSize];
                readFile(pastProgressive, "words/tenses/PastProgressive.txt");
                String[] grammar_pastProgressive = new String[pastProgressiveSize];
                readFile(grammar_pastProgressive, "words/tenses/PastProgressive_grammar.txt");

                String pastPerfect[] = new String[pastPerfectSize];
                readFile(pastPerfect, "words/tenses/PastPerfect.txt");
                String[] grammar_pastPerfect = new String[pastPerfectSize];
                readFile(grammar_pastPerfect, "words/tenses/PastPerfect_grammar.txt");

                String pastPerfectProgressive[] = new String[pastPerfectProgressiveSize];
                readFile(pastPerfectProgressive, "words/tenses/PastPerfectProgressive.txt");
                String[] grammar_pastPerfectProgressive = new String[pastPerfectProgressiveSize];
                readFile(grammar_pastPerfectProgressive, "words/tenses/PastPerfectProgressive_grammar.txt");

                System.arraycopy(pastSimple, 0, past_sentences, 0, pastSimple.length);
                System.arraycopy(pastProgressive, 0, past_sentences, pastSimple.length, pastProgressive.length);
                System.arraycopy(pastPerfect, 0, past_sentences, pastSimple.length + pastProgressive.length, pastPerfect.length);
                System.arraycopy(pastPerfectProgressive, 0, past_sentences, pastSimple.length + pastProgressive.length + pastPerfect.length, pastPerfectProgressive.length);

                System.arraycopy(grammar_pastSimple, 0, grammar_past_sentences, 0, grammar_pastSimple.length);
                System.arraycopy(grammar_pastProgressive, 0, grammar_past_sentences, grammar_pastSimple.length, grammar_pastProgressive.length);
                System.arraycopy(grammar_pastPerfect, 0, grammar_past_sentences, grammar_pastSimple.length + grammar_pastProgressive.length, grammar_pastPerfect.length);
                System.arraycopy(grammar_pastPerfectProgressive, 0, grammar_past_sentences, grammar_pastSimple.length + grammar_pastProgressive.length + grammar_pastPerfect.length, grammar_pastPerfectProgressive.length);

                String[] future_sentences = new String[futureGroupsSize];
                String[] grammar_future_sentences = new String[futureGroupsSize];

                String futureSimple[] = new String[futureSimpleSize];
                readFile(futureSimple, "words/tenses/FutureSimple.txt");
                String[] grammar_futureSimple = new String[futureSimpleSize];
                readFile(grammar_futureSimple, "words/tenses/FutureSimple_grammar.txt");

                String futureProgressive[] = new String[futureProgressiveSize];
                readFile(futureProgressive, "words/tenses/FutureProgressive.txt");
                String[] grammar_futureProgressive = new String[futureProgressiveSize];
                readFile(grammar_futureProgressive, "words/tenses/FutureProgressive_grammar.txt");

                String futurePerfect[] = new String[futurePerfectSize];
                readFile(futurePerfect, "words/tenses/FuturePerfect.txt");
                String[] grammar_futurePerfect = new String[futurePerfectSize];
                readFile(grammar_futurePerfect, "words/tenses/FuturePerfect_grammar.txt");

                String futurePerfectProgressive[] = new String[futurePerfectProgressiveSize];
                readFile(futurePerfectProgressive, "words/tenses/FuturePerfectProgressive.txt");
                String[] grammar_futurePerfectProgressive = new String[futurePerfectProgressiveSize];
                readFile(grammar_futurePerfectProgressive, "words/tenses/FuturePerfectProgressive_grammar.txt");

                System.arraycopy(futureSimple, 0, future_sentences, 0, futureSimple.length);
                System.arraycopy(futureProgressive, 0, future_sentences, futureSimple.length, futureProgressive.length);
                System.arraycopy(futurePerfect, 0, future_sentences, futureSimple.length + futureProgressive.length, futurePerfect.length);
                System.arraycopy(futurePerfectProgressive, 0, future_sentences, futureSimple.length + futureProgressive.length + futurePerfect.length, futurePerfectProgressive.length);

                System.arraycopy(grammar_futureSimple, 0, grammar_future_sentences, 0, grammar_futureSimple.length);
                System.arraycopy(grammar_futureProgressive, 0, grammar_future_sentences, grammar_futureSimple.length, grammar_futureProgressive.length);
                System.arraycopy(grammar_futurePerfect, 0, grammar_future_sentences, grammar_futureSimple.length + grammar_futureProgressive.length, grammar_futurePerfect.length);
                System.arraycopy(grammar_futurePerfectProgressive, 0, grammar_future_sentences, grammar_futureSimple.length + grammar_futureProgressive.length + grammar_futurePerfect.length, grammar_futurePerfectProgressive.length);

                //Объединяем группы
                System.arraycopy(present_sentences, 0, array_sentences, 0, present_sentences.length);
                System.arraycopy(past_sentences, 0, array_sentences, present_sentences.length, past_sentences.length);
                System.arraycopy(future_sentences, 0, array_sentences, present_sentences.length + past_sentences.length, future_sentences.length);

                System.arraycopy(grammar_present_sentences, 0, array_grammar, 0, grammar_present_sentences.length);
                System.arraycopy(grammar_past_sentences, 0, array_grammar, grammar_present_sentences.length, grammar_past_sentences.length);
                System.arraycopy(grammar_future_sentences, 0, array_grammar, grammar_present_sentences.length + grammar_past_sentences.length, grammar_future_sentences.length);

                //Прибавляем еще other
                String other[] = new String[other_size];
                readFile(other, "words/tenses/other.txt");
                String[] grammar_other = new String[other_size];
                readFile(grammar_other, "words/tenses/other_grammar.txt");

                System.arraycopy(other, 0, array_sentences, sentences_size, other.length);
                System.arraycopy(grammar_other, 0, array_grammar, sentences_size, grammar_other.length);

                break;
            }
        }

        list_mistakes = new LinkedList<>();

        sentence = (MyTextView)findViewById(R.id.sentence);
        points_activity = (MyTextView)findViewById(R.id.points);
        points_tv = (MyTextView)findViewById(R.id.points_tv);
        time_activity = (MyTextView)findViewById(R.id.time);
        time_tv = (MyTextView)findViewById(R.id.time_tv);
        textViews[0] = (MyTextView)findViewById(R.id.textView0);
        textViews[1] = (MyTextView)findViewById(R.id.textView1);
        textViews[2] = (MyTextView)findViewById(R.id.textView2);
        textViews[3] = (MyTextView)findViewById(R.id.textView3);
        textViews[4] = (MyTextView)findViewById(R.id.textView4);
        textViews[5] = (MyTextView)findViewById(R.id.textView5);
        textViews[6] = (MyTextView)findViewById(R.id.textView6);
        textViews[7] = (MyTextView)findViewById(R.id.textView7);

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
        for (int i=0; i<textViews.length; i++){
            final int finalI = i;
            textViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animation_in = AnimationUtils.loadAnimation(Practical_work_tenses1lvl.this, R.anim.alpha_in);
                    animation_out = AnimationUtils.loadAnimation(Practical_work_tenses1lvl.this, R.anim.alpha_out);
                    animation_in.setDuration(5000);
                    animation_out.setDuration(1000);
                    if (true_textViews[finalI]){
                        points+=1;
                        points_activity.setText(String.valueOf(points));
                        playSound(rightSound);
                        imageRightAnswear.setAnimation(animation_in);
                        imageRightAnswear.setAnimation(animation_out);
                        timer.cancel();
                        countdownPeriod += add_time;
                        createAndStart(countdownPeriod, countDownInterval);
                    }else{
                        playSound(wrongSound);
                        imageWrongAnswear.setAnimation(animation_in);
                        imageWrongAnswear.setAnimation(animation_out);

                        // Добавляем ошибку
                        Mistake mistake = new Mistake(makeSentenceBeautiful(sentence.getText().toString()), textViews[finalI].getText().toString(), grammar_str);
                        list_mistakes.add(mistake);

                        timer.cancel();
                        countdownPeriod -= remove_time;
                        createAndStart(countdownPeriod, countDownInterval);
                    }
                    init();
                }
            });
        }
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
                Intent i = new Intent(Practical_work_tenses1lvl.this, Mistakes.class);
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

    // Далее следует вещь, которая делает предложение красивым и компактным, чтобы оно в будущем поместилось в "ошибках"
    private String makeSentenceBeautiful(String mysentence){
        String tmp_Arr[] = mysentence.split(" ");
        if (tmp_Arr.length > 2){
            if (tmp_Arr.length > 5){
                if (tmp_Arr.length > 8){
                    if (tmp_Arr.length > 11){
                        if (tmp_Arr.length > 14){
                            tmp_Arr[1] += "\n";
                            tmp_Arr[4] += "\n";
                            tmp_Arr[7] += "\n";
                            tmp_Arr[10] += "\n";
                        }else{
                            tmp_Arr[1] += "\n";
                            tmp_Arr[4] += "\n";
                            tmp_Arr[7] += "\n";
                            tmp_Arr[10] += "\n";
                        }
                    }else{
                        tmp_Arr[1] += "\n";
                        tmp_Arr[4] += "\n";
                        tmp_Arr[7] += "\n";
                    }
                }else{
                    tmp_Arr[1] += "\n";
                    tmp_Arr[4] += "\n";
                }
            }else{
                tmp_Arr[1] += "\n";
            }
        }
        mysentence = "";
        for (int i = 0; i < tmp_Arr.length; i++){
            if (tmp_Arr[i].endsWith("\n")){
                mysentence += tmp_Arr[i];
            }else{
                mysentence += tmp_Arr[i] + " ";
            }
        }

        return mysentence;
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
