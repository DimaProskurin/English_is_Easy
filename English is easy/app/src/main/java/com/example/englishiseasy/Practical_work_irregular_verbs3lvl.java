package com.example.englishiseasy;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Practical_work_irregular_verbs3lvl extends AppCompatActivity {

    String topic = "irregular_verbs";

    String words_base[], words_pastsimp[], words_pastsimp_2nd[], words_pastpart[], words_pastpart_2nd[], translations[], translations_2nd[];
    LinkedList<Mistake> list_mistakes;

    final String separator_for_topic_and_points = "/";

    ImageView imageRightAnswear, imageWrongAnswear;

    Animation animation_in, animation_out;

    private SoundPool soundPool;
    private int wrongSound, rightSound, Stream;

    public boolean array1st; // дляо определения из какого массива взять слово (чтобы был рандом)
    public int word_index;
    public int points = 0;

    CountDownTimer timer;
    long countDownInterval = 1000;
    long countdownPeriod = 60000;
    final long add_time = 5000;
    final long remove_time = 2000;

    private void init(){
        array1st = (int) (Math.random() * 2) == 1;
        word_index = (int)(Math.random()*words_base.length);

        if (array1st){
            tv_word.setText(translations[word_index]);
        }else{
            if (!translations_2nd[word_index].equals(".")){
                tv_word.setText(translations_2nd[word_index]);
            }else{
                tv_word.setText(translations[word_index]);
            }
        }

        et_base.requestFocus();

        et_base.setText("");
        et_base.setHint("1 форма");

        et_pastsimp.setText("");
        et_pastsimp.setHint("2 форма");

        et_pastpart.setText("");
        et_pastpart.setHint("3 форма");

    }

    MyTextView points_activity, points_tv, time_activity, time_tv, tv_go, tv_word;
    EditText et_base, et_pastsimp, et_pastpart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_work_irregular_verbs3lvl);

        list_mistakes = new LinkedList<>();

        words_base = new String[179];
        readFile(words_base, "words/irregular/irregular_base_words.txt");
        words_base[178] = "be";

        words_pastsimp = new String[179];
        readFile(words_pastsimp, "words/irregular/irregular_past_simple_words.txt");
        words_pastsimp[178] = "was / were";

        words_pastsimp_2nd = new String[179];
        readFile(words_pastsimp_2nd, "words/irregular/irregular_past_simple_words_2nd.txt");
        words_pastsimp_2nd[178] = ".";

        words_pastpart = new String[179];
        readFile(words_pastpart, "words/irregular/irregular_past_participle_words.txt");
        words_pastpart[178] = "been";

        words_pastpart_2nd = new String[179];
        readFile(words_pastpart_2nd, "words/irregular/irregular_past_participle_words_2nd.txt");
        words_pastpart_2nd[178] = ".";

        translations = new String[179];
        readFile(translations, "translations/irregular/irregular_translations.txt");
        translations[178] = "быть";

        translations_2nd = new String[179];
        readFile(translations_2nd, "translations/irregular/irregular_translations_2nd.txt");
        translations_2nd[178] = ".";


        tv_word = (MyTextView)findViewById(R.id.tv_word);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Baron.ttf");
        et_base = (EditText)findViewById(R.id.editText_base);
        et_base.setTypeface(typeface);

        et_pastsimp = (EditText)findViewById(R.id.editText_pastsimp);
        et_pastsimp.setTypeface(typeface);

        et_pastpart = (EditText)findViewById(R.id.editText_pastpart);
        et_pastpart.setTypeface(typeface);

        tv_go = (MyTextView)findViewById(R.id.tv_go);

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
                animation_in = AnimationUtils.loadAnimation(Practical_work_irregular_verbs3lvl.this, R.anim.alpha_in);
                animation_out = AnimationUtils.loadAnimation(Practical_work_irregular_verbs3lvl.this, R.anim.alpha_out);
                animation_in.setDuration(5000);
                animation_out.setDuration(1000);

                String text1, text2, text3;
                text1 = et_base.getText().toString().trim().toLowerCase();
                text2 = et_pastsimp.getText().toString().trim().toLowerCase();
                text3 = et_pastpart.getText().toString().trim().toLowerCase();

                if (word_index == 178){
                    if ((text1.equals(words_base[word_index].trim().toLowerCase())) &&
                            (text2.equals("was") || text2.equals("were") || text2.equals("was / were") || text2.equals("was were") || text2.equals("was, were")) &&
                            (text3.equals(words_pastpart[word_index].trim().toLowerCase()) || text3.equals(words_pastpart_2nd[word_index].trim().toLowerCase())) &&
                            (!text3.equals("."))){
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

                        Mistake mistake = new Mistake(tv_word.getText().toString(), text1+" "+text2+" "+text3, words_base[word_index] + " " + words_pastsimp[word_index] + " " + words_pastpart[word_index]);
                        list_mistakes.add(mistake);

                        timer.cancel();
                        countdownPeriod -= remove_time;
                        createAndStart(countdownPeriod, countDownInterval);
                    }
                }else{
                    if ((text1.equals(words_base[word_index].trim().toLowerCase())) &&
                            (text2.equals(words_pastsimp[word_index].trim().toLowerCase()) || text2.equals(words_pastsimp_2nd[word_index].trim().toLowerCase())) &&
                            (text3.equals(words_pastpart[word_index].trim().toLowerCase()) || text3.equals(words_pastpart_2nd[word_index].trim().toLowerCase())) &&
                            (!text2.equals(".")) && (!text3.equals("."))){
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

                        Mistake mistake = new Mistake(tv_word.getText().toString(), text1+" "+text2+" "+text3, words_base[word_index] + " " + words_pastsimp[word_index] + " " + words_pastpart[word_index]);
                        list_mistakes.add(mistake);

                        timer.cancel();
                        countdownPeriod -= remove_time;
                        createAndStart(countdownPeriod, countDownInterval);
                    }
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
                Intent i = new Intent(Practical_work_irregular_verbs3lvl.this, Mistakes.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                String topic_and_points = "irregular_verbs" + "3lvl" + separator_for_topic_and_points + points;

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
            while (i<array.length-1) {
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
