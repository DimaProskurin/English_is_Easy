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
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Practical_work_tenses3lvl extends AppCompatActivity {

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


    String array_sentences[], array_sentences_translations[];

    LinkedList<Mistake> list_mistakes;

    final String separator_for_topic_and_points = "/";

    ImageView imageRightAnswear, imageWrongAnswear;

    Animation animation_in, animation_out;

    private SoundPool soundPool;
    private int wrongSound, rightSound, Stream;

    public int word_index;
    public int points = 0;

    CountDownTimer timer;
    long countDownInterval = 1000;
    long countdownPeriod = 120000;
    final long add_time = 5000;
    final long remove_time = 2000;

    MyTextView points_activity, points_tv, time_activity, time_tv, tv_word_sentence, tv_go;
    TextView tv_delete;
    MyTextView tv_buttons[];
    Part_of_sentence parts[];
    String parts_sentences[];

    private void init(){
        for (int i=0; i<tv_buttons.length; i++){
            tv_buttons[i].setText(" ");
            parts[i].textView.setText(" ");
        }

        word_index = (int)(Math.random()*array_sentences.length);

        tv_word_sentence.setText(array_sentences_translations[word_index]);

        parts_sentences = array_sentences[word_index].split(" ");
        shuffleArray(parts_sentences);
        for (int i=0; i<parts_sentences.length; i++){
            tv_buttons[i].setText(parts_sentences[i]);
        }

        check();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_work_tenses3lvl);

        Bundle bundle = getIntent().getExtras();
        sentence_group = bundle.getString("group");
        sentence_time = bundle.getString("time");
        tense =  sentence_time + " " + sentence_group;

        presentGroupsSize = presentSimpleSize + presentProgressiveSize + presentPerfectSize + presentPerfectProgressiveSize;
        pastGroupsSize = pastSimpleSize + pastProgressiveSize + pastPerfectSize + pastPerfectProgressiveSize;
        futureGroupsSize = futureSimpleSize + futureProgressiveSize + futurePerfectSize + futurePerfectProgressiveSize;

        simpleTimesSize = presentSimpleSize + pastSimpleSize + futureSimpleSize;
        progressiveTimesSize = presentProgressiveSize + pastProgressiveSize + futureProgressiveSize;
        perfectTimesSize = presentPerfectSize + pastPerfectSize + futurePerfectSize;
        perfect_progressiveTimesSize = presentPerfectProgressiveSize + pastPerfectProgressiveSize + futurePerfectProgressiveSize;

        sentences_size = presentGroupsSize + pastGroupsSize + futureGroupsSize;

        // Далее следует ооочень много копипасты, который в зависимости от предпочтений игрока выбирает, заполняет нужные массивы, а потом их объединяет, если это требуется
        switch (tense){
            case "Present Simple":{
                array_sentences = new String[presentSimpleSize];
                readFile(array_sentences, "words/tenses/PresentSimple.txt");

                String[] grammar = new String[presentSimpleSize];
                readFile(grammar, "words/tenses/PresentSimple_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[presentSimpleSize];
                readFile(array_sentences_translations, "translations/tenses/PresentSimple_translations.txt");

                break;
            }
            case "Present Progressive":{
                array_sentences = new String[presentProgressiveSize];
                readFile(array_sentences, "words/tenses/PresentProgressive.txt");

                String[] grammar = new String[presentProgressiveSize];
                readFile(grammar, "words/tenses/PresentProgressive_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[presentProgressiveSize];
                readFile(array_sentences_translations, "translations/tenses/PresentProgressive_translations.txt");

                break;
            }
            case "Present Perfect":{
                array_sentences = new String[presentPerfectSize];
                readFile(array_sentences, "words/tenses/PresentPerfect.txt");

                String[] grammar = new String[presentPerfectSize];
                readFile(grammar, "words/tenses/PresentPerfect_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[presentPerfectSize];
                readFile(array_sentences_translations, "translations/tenses/PresentPerfect_translations.txt");

                break;
            }
            case "Present Perfect Progressive":{
                array_sentences = new String[presentPerfectProgressiveSize];
                readFile(array_sentences, "words/tenses/PresentPerfectProgressive.txt");

                String[] grammar = new String[presentPerfectProgressiveSize];
                readFile(grammar, "words/tenses/PresentPerfectProgressive_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[presentPerfectProgressiveSize];
                readFile(array_sentences_translations, "translations/tenses/PresentPerfectProgressive_translations.txt");

                break;
            }

            case "Past Simple":{
                array_sentences = new String[pastSimpleSize];
                readFile(array_sentences, "words/tenses/PastSimple.txt");

                String[] grammar = new String[pastSimpleSize];
                readFile(grammar, "words/tenses/PastSimple_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[pastSimpleSize];
                readFile(array_sentences_translations, "translations/tenses/PastSimple_translations.txt");

                break;
            }
            case "Past Progressive":{
                array_sentences = new String[pastProgressiveSize];
                readFile(array_sentences, "words/tenses/PastProgressive.txt");

                String[] grammar = new String[pastProgressiveSize];
                readFile(grammar, "words/tenses/PastProgressive_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[pastProgressiveSize];
                readFile(array_sentences_translations, "translations/tenses/PastProgressive_translations.txt");

                break;
            }
            case "Past Perfect":{
                array_sentences = new String[pastPerfectSize];
                readFile(array_sentences, "words/tenses/PastPerfect.txt");

                String[] grammar = new String[pastPerfectSize];
                readFile(grammar, "words/tenses/PastPerfect_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[pastPerfectSize];
                readFile(array_sentences_translations, "translations/tenses/PastPerfect_translations.txt");

                break;
            }
            case "Past Perfect Progressive":{
                array_sentences = new String[pastPerfectProgressiveSize];
                readFile(array_sentences, "words/tenses/PastPerfectProgressive.txt");

                String[] grammar = new String[pastPerfectProgressiveSize];
                readFile(grammar, "words/tenses/PastPerfectProgressive_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[pastPerfectProgressiveSize];
                readFile(array_sentences_translations, "translations/tenses/PastPerfectProgressive_translations.txt");

                break;
            }

            case "Future Simple":{
                array_sentences = new String[futureSimpleSize];
                readFile(array_sentences, "words/tenses/FutureSimple.txt");

                String[] grammar = new String[futureSimpleSize];
                readFile(grammar, "words/tenses/FutureSimple_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[futureSimpleSize];
                readFile(array_sentences_translations, "translations/tenses/FutureSimple_translations.txt");

                break;
            }
            case "Future Progressive":{
                array_sentences = new String[futureProgressiveSize];
                readFile(array_sentences, "words/tenses/FutureProgressive.txt");

                String[] grammar = new String[futureProgressiveSize];
                readFile(grammar, "words/tenses/FutureProgressive_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[futureProgressiveSize];
                readFile(array_sentences_translations, "translations/tenses/FutureProgressive_translations.txt");

                break;
            }
            case "Future Perfect":{
                array_sentences = new String[futurePerfectSize];
                readFile(array_sentences, "words/tenses/FuturePerfect.txt");

                String[] grammar = new String[futurePerfectSize];
                readFile(grammar, "words/tenses/FuturePerfect_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[futurePerfectSize];
                readFile(array_sentences_translations, "translations/tenses/FuturePerfect_translations.txt");

                break;
            }
            case "Future Perfect Progressive":{
                array_sentences = new String[futurePerfectProgressiveSize];
                readFile(array_sentences, "words/tenses/FuturePerfectProgressive.txt");

                String[] grammar = new String[futurePerfectProgressiveSize];
                readFile(grammar, "words/tenses/FuturePerfectProgressive_grammar.txt");
                array_sentences = collectSentences(array_sentences, grammar);

                array_sentences_translations = new String[futurePerfectProgressiveSize];
                readFile(array_sentences_translations, "translations/tenses/FuturePerfectProgressive_translations.txt");

                break;
            }

            case "Present All groups":{
                array_sentences = new String[presentGroupsSize];
                array_sentences_translations = new String[presentGroupsSize];

                String presentSimple[] = new String[presentSimpleSize];
                readFile(presentSimple, "words/tenses/PresentSimple.txt");
                String[] grammar = new String[presentSimpleSize];
                readFile(grammar, "words/tenses/PresentSimple_grammar.txt");
                presentSimple = collectSentences(presentSimple, grammar);
                String presentSimple_translations[] = new String[presentSimpleSize];
                readFile(presentSimple_translations, "translations/tenses/PresentSimple_translations.txt");

                String presentProgressive[] = new String[presentProgressiveSize];
                readFile(presentProgressive, "words/tenses/PresentProgressive.txt");
                grammar = new String[presentProgressiveSize];
                readFile(grammar, "words/tenses/PresentProgressive_grammar.txt");
                presentProgressive = collectSentences(presentProgressive, grammar);
                String presentProgressive_translations[] = new String[presentProgressiveSize];
                readFile(presentProgressive_translations, "translations/tenses/PresentProgressive_translations.txt");

                String presentPerfect[] = new String[presentPerfectSize];
                readFile(presentPerfect, "words/tenses/PresentPerfect.txt");
                grammar = new String[presentPerfectSize];
                readFile(grammar, "words/tenses/PresentPerfect_grammar.txt");
                presentPerfect = collectSentences(presentPerfect, grammar);
                String presentPerfect_translations[] = new String[presentPerfectSize];
                readFile(presentPerfect_translations, "translations/tenses/PresentPerfect_translations.txt");

                String presentPerfectProgressive[] = new String[presentPerfectProgressiveSize];
                readFile(presentPerfectProgressive, "words/tenses/PresentPerfectProgressive.txt");
                grammar = new String[presentPerfectProgressiveSize];
                readFile(grammar, "words/tenses/PresentPerfectProgressive_grammar.txt");
                presentPerfectProgressive = collectSentences(presentPerfectProgressive, grammar);
                String presentPerfectProgressive_translations[] = new String[presentPerfectProgressiveSize];
                readFile(presentPerfectProgressive_translations, "translations/tenses/PresentPerfectProgressive_translations.txt");

                System.arraycopy(presentSimple, 0, array_sentences, 0, presentSimple.length);
                System.arraycopy(presentProgressive, 0, array_sentences, presentSimple.length, presentProgressive.length);
                System.arraycopy(presentPerfect, 0, array_sentences, presentSimple.length + presentProgressive.length, presentPerfect.length);
                System.arraycopy(presentPerfectProgressive, 0, array_sentences, presentSimple.length + presentProgressive.length + presentPerfect.length, presentPerfectProgressive.length);

                System.arraycopy(presentSimple_translations, 0, array_sentences_translations, 0, presentSimple_translations.length);
                System.arraycopy(presentProgressive_translations, 0, array_sentences_translations, presentSimple_translations.length, presentProgressive_translations.length);
                System.arraycopy(presentPerfect_translations, 0, array_sentences_translations, presentSimple_translations.length + presentProgressive_translations.length, presentPerfect_translations.length);
                System.arraycopy(presentPerfectProgressive_translations, 0, array_sentences_translations, presentSimple_translations.length + presentProgressive_translations.length + presentPerfect_translations.length, presentPerfectProgressive_translations.length);

                break;
            }

            case "Past All groups":{
                array_sentences = new String[pastGroupsSize];
                array_sentences_translations = new String[pastGroupsSize];

                String pastSimple[] = new String[pastSimpleSize];
                readFile(pastSimple, "words/tenses/PastSimple.txt");
                String[] grammar = new String[pastSimpleSize];
                readFile(grammar, "words/tenses/PastSimple_grammar.txt");
                pastSimple = collectSentences(pastSimple, grammar);
                String pastSimple_translations[] = new String[pastSimpleSize];
                readFile(pastSimple_translations, "translations/tenses/PastSimple_translations.txt");

                String pastProgressive[] = new String[pastProgressiveSize];
                readFile(pastProgressive, "words/tenses/PastProgressive.txt");
                grammar = new String[pastProgressiveSize];
                readFile(grammar, "words/tenses/PastProgressive_grammar.txt");
                pastProgressive = collectSentences(pastProgressive, grammar);
                String pastProgressive_translations[] = new String[pastProgressiveSize];
                readFile(pastProgressive_translations, "translations/tenses/PastProgressive_translations.txt");

                String pastPerfect[] = new String[pastPerfectSize];
                readFile(pastPerfect, "words/tenses/PastPerfect.txt");
                grammar = new String[pastPerfectSize];
                readFile(grammar, "words/tenses/PastPerfect_grammar.txt");
                pastPerfect = collectSentences(pastPerfect, grammar);
                String pastPerfect_translations[] = new String[pastPerfectSize];
                readFile(pastPerfect_translations, "translations/tenses/PastPerfect_translations.txt");

                String pastPerfectProgressive[] = new String[pastPerfectProgressiveSize];
                readFile(pastPerfectProgressive, "words/tenses/PastPerfectProgressive.txt");
                grammar = new String[pastPerfectProgressiveSize];
                readFile(grammar, "words/tenses/PastPerfectProgressive_grammar.txt");
                pastPerfectProgressive = collectSentences(pastPerfectProgressive, grammar);
                String pastPerfectProgressive_translations[] = new String[pastPerfectProgressiveSize];
                readFile(pastPerfectProgressive_translations, "translations/tenses/PastPerfectProgressive_translations.txt");

                System.arraycopy(pastSimple, 0, array_sentences, 0, pastSimple.length);
                System.arraycopy(pastProgressive, 0, array_sentences, pastSimple.length, pastProgressive.length);
                System.arraycopy(pastPerfect, 0, array_sentences, pastSimple.length + pastProgressive.length, pastPerfect.length);
                System.arraycopy(pastPerfectProgressive, 0, array_sentences, pastSimple.length + pastProgressive.length + pastPerfect.length, pastPerfectProgressive.length);

                System.arraycopy(pastSimple_translations, 0, array_sentences_translations, 0, pastSimple_translations.length);
                System.arraycopy(pastProgressive_translations, 0, array_sentences_translations, pastSimple_translations.length, pastProgressive_translations.length);
                System.arraycopy(pastPerfect_translations, 0, array_sentences_translations, pastSimple_translations.length + pastProgressive_translations.length, pastPerfect_translations.length);
                System.arraycopy(pastPerfectProgressive_translations, 0, array_sentences_translations, pastSimple_translations.length + pastProgressive_translations.length + pastPerfect_translations.length, pastPerfectProgressive_translations.length);

                break;
            }

            case "Future All groups":{
                array_sentences = new String[futureGroupsSize];
                array_sentences_translations = new String[futureGroupsSize];

                String futureSimple[] = new String[futureSimpleSize];
                readFile(futureSimple, "words/tenses/FutureSimple.txt");
                String[] grammar = new String[futureSimpleSize];
                readFile(grammar, "words/tenses/FutureSimple_grammar.txt");
                futureSimple = collectSentences(futureSimple, grammar);
                String futureSimple_translations[] = new String[futureSimpleSize];
                readFile(futureSimple_translations, "translations/tenses/FutureSimple_translations.txt");

                String futureProgressive[] = new String[futureProgressiveSize];
                readFile(futureProgressive, "words/tenses/FutureProgressive.txt");
                grammar = new String[futureProgressiveSize];
                readFile(grammar, "words/tenses/FutureProgressive_grammar.txt");
                futureProgressive = collectSentences(futureProgressive, grammar);
                String futureProgressive_translations[] = new String[futureProgressiveSize];
                readFile(futureProgressive_translations, "translations/tenses/FutureProgressive_translations.txt");

                String futurePerfect[] = new String[futurePerfectSize];
                readFile(futurePerfect, "words/tenses/FuturePerfect.txt");
                grammar = new String[futurePerfectSize];
                readFile(grammar, "words/tenses/FuturePerfect_grammar.txt");
                futurePerfect = collectSentences(futurePerfect, grammar);
                String futurePerfect_translations[] = new String[futurePerfectSize];
                readFile(futurePerfect_translations, "translations/tenses/FuturePerfect_translations.txt");

                String futurePerfectProgressive[] = new String[futurePerfectProgressiveSize];
                readFile(futurePerfectProgressive, "words/tenses/FuturePerfectProgressive.txt");
                grammar = new String[futurePerfectProgressiveSize];
                readFile(grammar, "words/tenses/FuturePerfectProgressive_grammar.txt");
                futurePerfectProgressive = collectSentences(futurePerfectProgressive, grammar);
                String futurePerfectProgressive_translations[] = new String[futurePerfectProgressiveSize];
                readFile(futurePerfectProgressive_translations, "translations/tenses/FuturePerfectProgressive_translations.txt");

                System.arraycopy(futureSimple, 0, array_sentences, 0, futureSimple.length);
                System.arraycopy(futureProgressive, 0, array_sentences, futureSimple.length, futureProgressive.length);
                System.arraycopy(futurePerfect, 0, array_sentences, futureSimple.length + futureProgressive.length, futurePerfect.length);
                System.arraycopy(futurePerfectProgressive, 0, array_sentences, futureSimple.length + futureProgressive.length + futurePerfect.length, futurePerfectProgressive.length);

                System.arraycopy(futureSimple_translations, 0, array_sentences_translations, 0, futureSimple_translations.length);
                System.arraycopy(futureProgressive_translations, 0, array_sentences_translations, futureSimple_translations.length, futureProgressive_translations.length);
                System.arraycopy(futurePerfect_translations, 0, array_sentences_translations, futureSimple_translations.length + futureProgressive_translations.length, futurePerfect_translations.length);
                System.arraycopy(futurePerfectProgressive_translations, 0, array_sentences_translations, futureSimple_translations.length + futureProgressive_translations.length + futurePerfect_translations.length, futurePerfectProgressive_translations.length);

                break;
            }

            case "All times Simple":{
                array_sentences = new String[simpleTimesSize];
                array_sentences_translations = new String[simpleTimesSize];

                String presentSimple[] = new String[presentSimpleSize];
                readFile(presentSimple, "words/tenses/PresentSimple.txt");
                String[] grammar = new String[presentSimpleSize];
                readFile(grammar, "words/tenses/PresentSimple_grammar.txt");
                presentSimple = collectSentences(presentSimple, grammar);
                String presentSimple_translations[] = new String[presentSimpleSize];
                readFile(presentSimple_translations, "translations/tenses/PresentSimple_translations.txt");

                String pastSimple[] = new String[pastSimpleSize];
                readFile(pastSimple, "words/tenses/PastSimple.txt");
                grammar = new String[pastSimpleSize];
                readFile(grammar, "words/tenses/PastSimple_grammar.txt");
                pastSimple = collectSentences(pastSimple, grammar);
                String pastSimple_translations[] = new String[pastSimpleSize];
                readFile(pastSimple_translations, "translations/tenses/PastSimple_translations.txt");

                String futureSimple[] = new String[futureSimpleSize];
                readFile(futureSimple, "words/tenses/FutureSimple.txt");
                grammar = new String[futureSimpleSize];
                readFile(grammar, "words/tenses/FutureSimple_grammar.txt");
                futureSimple = collectSentences(futureSimple, grammar);
                String futureSimple_translations[] = new String[futureSimpleSize];
                readFile(futureSimple_translations, "translations/tenses/FutureSimple_translations.txt");

                System.arraycopy(presentSimple, 0, array_sentences, 0, presentSimple.length);
                System.arraycopy(pastSimple, 0, array_sentences, presentSimple.length, pastSimple.length);
                System.arraycopy(futureSimple, 0, array_sentences, presentSimple.length + pastSimple.length, futureSimple.length);

                System.arraycopy(presentSimple_translations, 0, array_sentences_translations, 0, presentSimple_translations.length);
                System.arraycopy(pastSimple_translations, 0, array_sentences_translations, presentSimple_translations.length, pastSimple_translations.length);
                System.arraycopy(futureSimple_translations, 0, array_sentences_translations, presentSimple_translations.length + pastSimple_translations.length, futureSimple_translations.length);

                break;
            }

            case "All times Progressive":{
                array_sentences = new String[progressiveTimesSize];
                array_sentences_translations = new String[progressiveTimesSize];

                String presentProgressive[] = new String[presentProgressiveSize];
                readFile(presentProgressive, "words/tenses/PresentProgressive.txt");
                String[] grammar = new String[presentProgressiveSize];
                readFile(grammar, "words/tenses/PresentProgressive_grammar.txt");
                presentProgressive = collectSentences(presentProgressive, grammar);
                String presentProgressive_translations[] = new String[presentProgressiveSize];
                readFile(presentProgressive_translations, "translations/tenses/PresentProgressive_translations.txt");

                String pastProgressive[] = new String[pastProgressiveSize];
                readFile(pastProgressive, "words/tenses/PastProgressive.txt");
                grammar = new String[pastProgressiveSize];
                readFile(grammar, "words/tenses/PastProgressive_grammar.txt");
                pastProgressive = collectSentences(pastProgressive, grammar);
                String pastProgressive_translations[] = new String[pastProgressiveSize];
                readFile(pastProgressive_translations, "translations/tenses/PastProgressive_translations.txt");

                String futureProgressive[] = new String[futureProgressiveSize];
                readFile(futureProgressive, "words/tenses/FutureProgressive.txt");
                grammar = new String[futureProgressiveSize];
                readFile(grammar, "words/tenses/FutureProgressive_grammar.txt");
                futureProgressive = collectSentences(futureProgressive, grammar);
                String futureProgressive_translations[] = new String[futureProgressiveSize];
                readFile(futureProgressive_translations, "translations/tenses/FutureProgressive_translations.txt");

                System.arraycopy(presentProgressive, 0, array_sentences, 0, presentProgressive.length);
                System.arraycopy(pastProgressive, 0, array_sentences, presentProgressive.length, pastProgressive.length);
                System.arraycopy(futureProgressive, 0, array_sentences, presentProgressive.length + pastProgressive.length, futureProgressive.length);

                System.arraycopy(presentProgressive_translations, 0, array_sentences_translations, 0, presentProgressive_translations.length);
                System.arraycopy(pastProgressive_translations, 0, array_sentences_translations, presentProgressive_translations.length, pastProgressive_translations.length);
                System.arraycopy(futureProgressive_translations, 0, array_sentences_translations, presentProgressive_translations.length + pastProgressive_translations.length, futureProgressive_translations.length);

                break;
            }

            case "All times Perfect":{
                array_sentences = new String[perfectTimesSize];
                array_sentences_translations = new String[perfectTimesSize];

                String presentPerfect[] = new String[presentPerfectSize];
                readFile(presentPerfect, "words/tenses/PresentPerfect.txt");
                String[] grammar = new String[presentPerfectSize];
                readFile(grammar, "words/tenses/PresentPerfect_grammar.txt");
                presentPerfect = collectSentences(presentPerfect, grammar);
                String presentPerfect_translations[] = new String[presentPerfectSize];
                readFile(presentPerfect_translations, "translations/tenses/PresentPerfect_translations.txt");

                String pastPerfect[] = new String[pastPerfectSize];
                readFile(pastPerfect, "words/tenses/PastPerfect.txt");
                grammar = new String[pastPerfectSize];
                readFile(grammar, "words/tenses/PastPerfect_grammar.txt");
                pastPerfect = collectSentences(pastPerfect, grammar);
                String pastPerfect_translations[] = new String[pastPerfectSize];
                readFile(pastPerfect_translations, "translations/tenses/PastPerfect_translations.txt");

                String futurePerfect[] = new String[futurePerfectSize];
                readFile(futurePerfect, "words/tenses/FuturePerfect.txt");
                grammar = new String[futurePerfectSize];
                readFile(grammar, "words/tenses/FuturePerfect_grammar.txt");
                futurePerfect = collectSentences(futurePerfect, grammar);
                String futurePerfect_translations[] = new String[futurePerfectSize];
                readFile(futurePerfect_translations, "translations/tenses/FuturePerfect_translations.txt");

                System.arraycopy(presentPerfect, 0, array_sentences, 0, presentPerfect.length);
                System.arraycopy(pastPerfect, 0, array_sentences, presentPerfect.length, pastPerfect.length);
                System.arraycopy(futurePerfect, 0, array_sentences, presentPerfect.length + pastPerfect.length, futurePerfect.length);

                System.arraycopy(presentPerfect_translations, 0, array_sentences_translations, 0, presentPerfect_translations.length);
                System.arraycopy(pastPerfect_translations, 0, array_sentences_translations, presentPerfect_translations.length, pastPerfect_translations.length);
                System.arraycopy(futurePerfect_translations, 0, array_sentences_translations, presentPerfect_translations.length + pastPerfect_translations.length, futurePerfect_translations.length);

                break;
            }

            case "All times Perfect Progressive":{
                array_sentences = new String[perfect_progressiveTimesSize];
                array_sentences_translations = new String[perfect_progressiveTimesSize];

                String presentPerfectProgressive[] = new String[presentPerfectProgressiveSize];
                readFile(presentPerfectProgressive, "words/tenses/PresentPerfectProgressive.txt");
                String[] grammar = new String[presentPerfectProgressiveSize];
                readFile(grammar, "words/tenses/PresentPerfectProgressive_grammar.txt");
                presentPerfectProgressive = collectSentences(presentPerfectProgressive, grammar);
                String presentPerfectProgressive_translations[] = new String[presentPerfectProgressiveSize];
                readFile(presentPerfectProgressive_translations, "translations/tenses/PresentPerfectProgressive_translations.txt");

                String pastPerfectProgressive[] = new String[pastPerfectProgressiveSize];
                readFile(pastPerfectProgressive, "words/tenses/PastPerfectProgressive.txt");
                grammar = new String[pastPerfectProgressiveSize];
                readFile(grammar, "words/tenses/PastPerfectProgressive_grammar.txt");
                pastPerfectProgressive = collectSentences(pastPerfectProgressive, grammar);
                String pastPerfectProgressive_translations[] = new String[pastPerfectProgressiveSize];
                readFile(pastPerfectProgressive_translations, "translations/tenses/PastPerfectProgressive_translations.txt");

                String futurePerfectProgressive[] = new String[futurePerfectProgressiveSize];
                readFile(futurePerfectProgressive, "words/tenses/FuturePerfectProgressive.txt");
                grammar = new String[futurePerfectProgressiveSize];
                readFile(grammar, "words/tenses/FuturePerfectProgressive_grammar.txt");
                futurePerfectProgressive = collectSentences(futurePerfectProgressive, grammar);
                String futurePerfectProgressive_translations[] = new String[futurePerfectProgressiveSize];
                readFile(futurePerfectProgressive_translations, "translations/tenses/FuturePerfectProgressive_translations.txt");

                System.arraycopy(presentPerfectProgressive, 0, array_sentences, 0, presentPerfectProgressive.length);
                System.arraycopy(pastPerfectProgressive, 0, array_sentences, presentPerfectProgressive.length, pastPerfectProgressive.length);
                System.arraycopy(futurePerfectProgressive, 0, array_sentences, presentPerfectProgressive.length + pastPerfectProgressive.length, futurePerfectProgressive.length);

                System.arraycopy(presentPerfectProgressive_translations, 0, array_sentences_translations, 0, presentPerfectProgressive_translations.length);
                System.arraycopy(pastPerfectProgressive_translations, 0, array_sentences_translations, presentPerfectProgressive_translations.length, pastPerfectProgressive_translations.length);
                System.arraycopy(futurePerfectProgressive_translations, 0, array_sentences_translations, presentPerfectProgressive_translations.length + pastPerfectProgressive_translations.length, futurePerfectProgressive_translations.length);

                break;
            }

            case "All times All groups":{
                array_sentences = new String[sentences_size + other_size];
                array_sentences_translations = new String[sentences_size + other_size];

                String[] present_sentences = new String[presentGroupsSize];
                String[] present_sentences_translations = new String[presentGroupsSize];

                String presentSimple[] = new String[presentSimpleSize];
                readFile(presentSimple, "words/tenses/PresentSimple.txt");
                String[] grammar = new String[presentSimpleSize];
                readFile(grammar, "words/tenses/PresentSimple_grammar.txt");
                presentSimple = collectSentences(presentSimple, grammar);
                String presentSimple_translations[] = new String[presentSimpleSize];
                readFile(presentSimple_translations, "translations/tenses/PresentSimple_translations.txt");

                String presentProgressive[] = new String[presentProgressiveSize];
                readFile(presentProgressive, "words/tenses/PresentProgressive.txt");
                grammar = new String[presentProgressiveSize];
                readFile(grammar, "words/tenses/PresentProgressive_grammar.txt");
                presentProgressive = collectSentences(presentProgressive, grammar);
                String presentProgressive_translations[] = new String[presentProgressiveSize];
                readFile(presentProgressive_translations, "translations/tenses/PresentProgressive_translations.txt");

                String presentPerfect[] = new String[presentPerfectSize];
                readFile(presentPerfect, "words/tenses/PresentPerfect.txt");
                grammar = new String[presentPerfectSize];
                readFile(grammar, "words/tenses/PresentPerfect_grammar.txt");
                presentPerfect = collectSentences(presentPerfect, grammar);
                String presentPerfect_translations[] = new String[presentPerfectSize];
                readFile(presentPerfect_translations, "translations/tenses/PresentPerfect_translations.txt");

                String presentPerfectProgressive[] = new String[presentPerfectProgressiveSize];
                readFile(presentPerfectProgressive, "words/tenses/PresentPerfectProgressive.txt");
                grammar = new String[presentPerfectProgressiveSize];
                readFile(grammar, "words/tenses/PresentPerfectProgressive_grammar.txt");
                presentPerfectProgressive = collectSentences(presentPerfectProgressive, grammar);
                String presentPerfectProgressive_translations[] = new String[presentPerfectProgressiveSize];
                readFile(presentPerfectProgressive_translations, "translations/tenses/PresentPerfectProgressive_translations.txt");

                System.arraycopy(presentSimple, 0, present_sentences, 0, presentSimple.length);
                System.arraycopy(presentProgressive, 0, present_sentences, presentSimple.length, presentProgressive.length);
                System.arraycopy(presentPerfect, 0, present_sentences, presentSimple.length + presentProgressive.length, presentPerfect.length);
                System.arraycopy(presentPerfectProgressive, 0, present_sentences, presentSimple.length + presentProgressive.length + presentPerfect.length, presentPerfectProgressive.length);

                System.arraycopy(presentSimple_translations, 0, present_sentences_translations, 0, presentSimple_translations.length);
                System.arraycopy(presentProgressive_translations, 0, present_sentences_translations, presentSimple_translations.length, presentProgressive_translations.length);
                System.arraycopy(presentPerfect_translations, 0, present_sentences_translations, presentSimple_translations.length + presentProgressive_translations.length, presentPerfect_translations.length);
                System.arraycopy(presentPerfectProgressive_translations, 0, present_sentences_translations, presentSimple_translations.length + presentProgressive_translations.length + presentPerfect_translations.length, presentPerfectProgressive_translations.length);

                String[] past_sentences = new String[pastGroupsSize];
                String[] past_sentences_translations = new String[pastGroupsSize];

                String pastSimple[] = new String[pastSimpleSize];
                readFile(pastSimple, "words/tenses/PastSimple.txt");
                grammar = new String[pastSimpleSize];
                readFile(grammar, "words/tenses/PastSimple_grammar.txt");
                pastSimple = collectSentences(pastSimple, grammar);
                String pastSimple_translations[] = new String[pastSimpleSize];
                readFile(pastSimple_translations, "translations/tenses/PastSimple_translations.txt");

                String pastProgressive[] = new String[pastProgressiveSize];
                readFile(pastProgressive, "words/tenses/PastProgressive.txt");
                grammar = new String[pastProgressiveSize];
                readFile(grammar, "words/tenses/PastProgressive_grammar.txt");
                pastProgressive = collectSentences(pastProgressive, grammar);
                String pastProgressive_translations[] = new String[pastProgressiveSize];
                readFile(pastProgressive_translations, "translations/tenses/PastProgressive_translations.txt");

                String pastPerfect[] = new String[pastPerfectSize];
                readFile(pastPerfect, "words/tenses/PastPerfect.txt");
                grammar = new String[pastPerfectSize];
                readFile(grammar, "words/tenses/PastPerfect_grammar.txt");
                pastPerfect = collectSentences(pastPerfect, grammar);
                String pastPerfect_translations[] = new String[pastPerfectSize];
                readFile(pastPerfect_translations, "translations/tenses/PastPerfect_translations.txt");

                String pastPerfectProgressive[] = new String[pastPerfectProgressiveSize];
                readFile(pastPerfectProgressive, "words/tenses/PastPerfectProgressive.txt");
                grammar = new String[pastPerfectProgressiveSize];
                readFile(grammar, "words/tenses/PastPerfectProgressive_grammar.txt");
                pastPerfectProgressive = collectSentences(pastPerfectProgressive, grammar);
                String pastPerfectProgressive_translations[] = new String[pastPerfectProgressiveSize];
                readFile(pastPerfectProgressive_translations, "translations/tenses/PastPerfectProgressive_translations.txt");


                System.arraycopy(pastSimple, 0, past_sentences, 0, pastSimple.length);
                System.arraycopy(pastProgressive, 0, past_sentences, pastSimple.length, pastProgressive.length);
                System.arraycopy(pastPerfect, 0, past_sentences, pastSimple.length + pastProgressive.length, pastPerfect.length);
                System.arraycopy(pastPerfectProgressive, 0, past_sentences, pastSimple.length + pastProgressive.length + pastPerfect.length, pastPerfectProgressive.length);

                System.arraycopy(pastSimple_translations, 0, past_sentences_translations, 0, pastSimple_translations.length);
                System.arraycopy(pastProgressive_translations, 0, past_sentences_translations, pastSimple_translations.length, pastProgressive_translations.length);
                System.arraycopy(pastPerfect_translations, 0, past_sentences_translations, pastSimple_translations.length + pastProgressive_translations.length, pastPerfect_translations.length);
                System.arraycopy(pastPerfectProgressive_translations, 0, past_sentences_translations, pastSimple_translations.length + pastProgressive_translations.length + pastPerfect_translations.length, pastPerfectProgressive_translations.length);

                String[] future_sentences = new String[futureGroupsSize];
                String[] future_sentences_translations = new String[futureGroupsSize];

                String futureSimple[] = new String[futureSimpleSize];
                readFile(futureSimple, "words/tenses/FutureSimple.txt");
                grammar = new String[futureSimpleSize];
                readFile(grammar, "words/tenses/FutureSimple_grammar.txt");
                futureSimple = collectSentences(futureSimple, grammar);
                String futureSimple_translations[] = new String[futureSimpleSize];
                readFile(futureSimple_translations, "translations/tenses/FutureSimple_translations.txt");

                String futureProgressive[] = new String[futureProgressiveSize];
                readFile(futureProgressive, "words/tenses/FutureProgressive.txt");
                grammar = new String[futureProgressiveSize];
                readFile(grammar, "words/tenses/FutureProgressive_grammar.txt");
                futureProgressive = collectSentences(futureProgressive, grammar);
                String futureProgressive_translations[] = new String[futureProgressiveSize];
                readFile(futureProgressive_translations, "translations/tenses/FutureProgressive_translations.txt");

                String futurePerfect[] = new String[futurePerfectSize];
                readFile(futurePerfect, "words/tenses/FuturePerfect.txt");
                grammar = new String[futurePerfectSize];
                readFile(grammar, "words/tenses/FuturePerfect_grammar.txt");
                futurePerfect = collectSentences(futurePerfect, grammar);
                String futurePerfect_translations[] = new String[futurePerfectSize];
                readFile(futurePerfect_translations, "translations/tenses/FuturePerfect_translations.txt");

                String futurePerfectProgressive[] = new String[futurePerfectProgressiveSize];
                readFile(futurePerfectProgressive, "words/tenses/FuturePerfectProgressive.txt");
                grammar = new String[futurePerfectProgressiveSize];
                readFile(grammar, "words/tenses/FuturePerfectProgressive_grammar.txt");
                futurePerfectProgressive = collectSentences(futurePerfectProgressive, grammar);
                String futurePerfectProgressive_translations[] = new String[futurePerfectProgressiveSize];
                readFile(futurePerfectProgressive_translations, "translations/tenses/FuturePerfectProgressive_translations.txt");

                System.arraycopy(futureSimple, 0, future_sentences, 0, futureSimple.length);
                System.arraycopy(futureProgressive, 0, future_sentences, futureSimple.length, futureProgressive.length);
                System.arraycopy(futurePerfect, 0, future_sentences, futureSimple.length + futureProgressive.length, futurePerfect.length);
                System.arraycopy(futurePerfectProgressive, 0, future_sentences, futureSimple.length + futureProgressive.length + futurePerfect.length, futurePerfectProgressive.length);

                System.arraycopy(futureSimple_translations, 0, future_sentences_translations, 0, futureSimple_translations.length);
                System.arraycopy(futureProgressive_translations, 0, future_sentences_translations, futureSimple_translations.length, futureProgressive_translations.length);
                System.arraycopy(futurePerfect_translations, 0, future_sentences_translations, futureSimple_translations.length + futureProgressive_translations.length, futurePerfect_translations.length);
                System.arraycopy(futurePerfectProgressive_translations, 0, future_sentences_translations, futureSimple_translations.length + futureProgressive_translations.length + futurePerfect_translations.length, futurePerfectProgressive_translations.length);

                //Объединяем группы
                System.arraycopy(present_sentences, 0, array_sentences, 0, present_sentences.length);
                System.arraycopy(past_sentences, 0, array_sentences, present_sentences.length, past_sentences.length);
                System.arraycopy(future_sentences, 0, array_sentences, present_sentences.length + past_sentences.length, future_sentences.length);

                System.arraycopy(present_sentences_translations, 0, array_sentences_translations, 0, present_sentences_translations.length);
                System.arraycopy(past_sentences_translations, 0, array_sentences_translations, present_sentences_translations.length, past_sentences_translations.length);
                System.arraycopy(future_sentences_translations, 0, array_sentences_translations, present_sentences_translations.length + past_sentences_translations.length, future_sentences_translations.length);

                //Прибавляем еще other
                String other[] = new String[other_size];
                readFile(other, "words/tenses/other.txt");
                grammar = new String[other_size];
                readFile(grammar, "words/tenses/other_grammar.txt");
                other = collectSentences(other, grammar);
                String other_translations[] = new String[other_size];
                readFile(other_translations, "translations/tenses/other_translations.txt");

                System.arraycopy(other, 0, array_sentences, sentences_size, other.length);
                System.arraycopy(other_translations, 0, array_sentences_translations, sentences_size, other_translations.length);

                break;
            }
        }

        list_mistakes = new LinkedList<>();

        tv_buttons = new MyTextView[16];
        tv_buttons[0] = (MyTextView)findViewById(R.id.tv1);
        tv_buttons[1] = (MyTextView)findViewById(R.id.tv2);
        tv_buttons[2] = (MyTextView)findViewById(R.id.tv3);
        tv_buttons[3] = (MyTextView)findViewById(R.id.tv4);
        tv_buttons[4] = (MyTextView)findViewById(R.id.tv5);
        tv_buttons[5] = (MyTextView)findViewById(R.id.tv6);
        tv_buttons[6] = (MyTextView)findViewById(R.id.tv7);
        tv_buttons[7] = (MyTextView)findViewById(R.id.tv8);
        tv_buttons[8] = (MyTextView)findViewById(R.id.tv9);
        tv_buttons[9] = (MyTextView)findViewById(R.id.tv10);
        tv_buttons[10] = (MyTextView)findViewById(R.id.tv11);
        tv_buttons[11] = (MyTextView)findViewById(R.id.tv12);
        tv_buttons[12] = (MyTextView)findViewById(R.id.tv13);
        tv_buttons[13] = (MyTextView)findViewById(R.id.tv14);
        tv_buttons[14] = (MyTextView)findViewById(R.id.tv15);
        tv_buttons[15] = (MyTextView)findViewById(R.id.tv16);

        parts = new Part_of_sentence[16];
        for (int i=0; i<parts.length; i++){
            parts[i] = new Part_of_sentence();
        }
        parts[0].textView = (MyTextView)findViewById(R.id.text1);
        parts[1].textView = (MyTextView)findViewById(R.id.text2);
        parts[2].textView = (MyTextView)findViewById(R.id.text3);
        parts[3].textView = (MyTextView)findViewById(R.id.text4);
        parts[4].textView = (MyTextView)findViewById(R.id.text5);
        parts[5].textView = (MyTextView)findViewById(R.id.text6);
        parts[6].textView = (MyTextView)findViewById(R.id.text7);
        parts[7].textView = (MyTextView)findViewById(R.id.text8);
        parts[8].textView = (MyTextView)findViewById(R.id.text9);
        parts[9].textView = (MyTextView)findViewById(R.id.text10);
        parts[10].textView = (MyTextView)findViewById(R.id.text11);
        parts[11].textView = (MyTextView)findViewById(R.id.text12);
        parts[12].textView = (MyTextView)findViewById(R.id.text13);
        parts[13].textView = (MyTextView)findViewById(R.id.text14);
        parts[14].textView = (MyTextView)findViewById(R.id.text15);
        parts[15].textView = (MyTextView)findViewById(R.id.text16);

        for (int i=0; i<tv_buttons.length; i++){
            final int finalI = i;
            tv_buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = tv_buttons[finalI].getText().toString();
                    tv_buttons[finalI].setText(" ");
                    for (int j=0; j<parts.length; j++){
                        if (parts[j].textView.getText().toString().equals(" ")){
                            parts[j].textView.setText(text);
                            parts[j].number = finalI;
                            break;
                        }
                    }
                    check();
                }
            });
            parts[i].textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = parts[finalI].textView.getText().toString();
                    parts[finalI].textView.setText(" ");
                    tv_buttons[parts[finalI].number].setText(text);
                    check();
                }
            });
        }

        tv_word_sentence = (MyTextView)findViewById(R.id.tv_word);
        tv_go = (MyTextView)findViewById(R.id.tv_go);
        tv_delete = (TextView)findViewById(R.id.tv_delete);

        points_activity = (MyTextView)findViewById(R.id.points);
        points_tv = (MyTextView)findViewById(R.id.points_tv);
        time_activity = (MyTextView)findViewById(R.id.time);
        time_tv = (MyTextView)findViewById(R.id.time_tv);

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

        tv_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation_in = AnimationUtils.loadAnimation(Practical_work_tenses3lvl.this, R.anim.alpha_in);
                animation_out = AnimationUtils.loadAnimation(Practical_work_tenses3lvl.this, R.anim.alpha_out);
                animation_in.setDuration(5000);
                animation_out.setDuration(1000);
                String sentence = "";
                for (int i = 0; i < parts.length - 1; i++) {
                    sentence += parts[i].textView.getText().toString();
                    sentence += " ";
                }
                sentence += parts[parts.length - 1].textView.getText().toString(); // чтобы на конце пробела не было
                sentence = sentence.trim();
                if (sentence.equals(array_sentences[word_index])) {
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

                    // Добавляем ошибку
                    Mistake mistake = new Mistake(makeSentenceBeautiful(tv_word_sentence.getText().toString()), makeSentenceBeautiful(sentence), makeSentenceBeautiful(array_sentences[word_index]));
                    list_mistakes.add(mistake);

                    timer.cancel();
                    countdownPeriod -= remove_time;
                    createAndStart(countdownPeriod, countDownInterval);
                }
                init();
            }
        });

        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current_index = -1;
                int current_number = -1;
                for (int i = parts.length-1; i >= 0; i--){
                    if (!parts[i].textView.getText().toString().equals(" ")){
                        current_number = parts[i].number;
                        current_index = i;
                        break;
                    }
                }
                if (current_index != -1){
                    String text = parts[current_index].textView.getText().toString();
                    parts[current_index].textView.setText(" ");
                    tv_buttons[current_number].setText(text);
                    check();
                }
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
                Intent i = new Intent(Practical_work_tenses3lvl.this, Mistakes.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                String topic_and_points = topic + "3lvl" + separator_for_topic_and_points + points;

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

    public void shuffleArray(String array[]){
        List<String> list = Arrays.asList(array);
        Collections.shuffle(list);
        array = list.toArray(array);
    }

    public void check(){
        for (int i=0; i<tv_buttons.length; i++){
            if (tv_buttons[i].getText().toString().equals(" ")){
                tv_buttons[i].setVisibility(View.INVISIBLE);
            }else{
                tv_buttons[i].setVisibility(View.VISIBLE);
            }
            if (parts[i].textView.getText().toString().equals(" ")){
                parts[i].textView.setVisibility(View.INVISIBLE);
            }else{
                parts[i].textView.setVisibility(View.VISIBLE);
            }
        }
    }

    public String[] collectSentences(String[] array_sentences, String[] grammar){
        String[] res_sentences = new String[array_sentences.length];
        for (int i = 0; i < array_sentences.length; i++){
            String sent = array_sentences[i];
            String gram = grammar[i];
            String tmp_gram[] = gram.split("/");
            String tmp_sent[] = sent.split(" ");
            String res_sent = "";
            int k = 0;
            for (int j = 0; j < tmp_sent.length; j++){
                if (tmp_sent[j].equals("...")){
                    tmp_sent[j] = tmp_gram[k].trim();
                    k++;
                }
                if (j == tmp_sent.length-1){
                    res_sent += tmp_sent[j];
                }else{
                    res_sent += tmp_sent[j] + " ";
                }
            }
            res_sentences[i] = res_sent;
        }
        return res_sentences;
    }

    // Далее следует вещь, которая делает предложение красивым и компактным, чтобы оно в будущем поместилось в "ошибках"
    private String makeSentenceBeautiful(String mysentence){
        String tmp_Arr[] = mysentence.split(" ");
        if (tmp_Arr.length > 2){
            if (tmp_Arr.length > 4){
                if (tmp_Arr.length > 6){
                    if (tmp_Arr.length > 8){
                        if (tmp_Arr.length > 10){
                            if (tmp_Arr.length > 12){
                                if (tmp_Arr.length > 14){
                                    tmp_Arr[1] += "\n";
                                    tmp_Arr[3] += "\n";
                                    tmp_Arr[5] += "\n";
                                    tmp_Arr[7] += "\n";
                                    tmp_Arr[9] += "\n";
                                    tmp_Arr[11] += "\n";
                                    tmp_Arr[13] += "\n";
                                }else{
                                    tmp_Arr[1] += "\n";
                                    tmp_Arr[3] += "\n";
                                    tmp_Arr[5] += "\n";
                                    tmp_Arr[7] += "\n";
                                    tmp_Arr[9] += "\n";
                                    tmp_Arr[11] += "\n";
                                }
                            }else {
                                tmp_Arr[1] += "\n";
                                tmp_Arr[3] += "\n";
                                tmp_Arr[5] += "\n";
                                tmp_Arr[7] += "\n";
                                tmp_Arr[9] += "\n";
                            }
                        }else{
                            tmp_Arr[1] += "\n";
                            tmp_Arr[3] += "\n";
                            tmp_Arr[5] += "\n";
                            tmp_Arr[7] += "\n";
                        }
                    }else{
                        tmp_Arr[1] += "\n";
                        tmp_Arr[3] += "\n";
                        tmp_Arr[5] += "\n";
                    }
                }else{
                    tmp_Arr[1] += "\n";
                    tmp_Arr[3] += "\n";
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

    class Part_of_sentence{
        public MyTextView textView;
        public int number;

        Part_of_sentence(){
            textView = null;
            number = -1;
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
