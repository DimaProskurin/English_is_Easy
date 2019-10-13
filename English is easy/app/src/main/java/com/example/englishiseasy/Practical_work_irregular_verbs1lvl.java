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

public class Practical_work_irregular_verbs1lvl extends AppCompatActivity {

    String topic = "irregular_verbs";

    String words[];
    String translations[], translations_2nd[];
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
    long countdownPeriod = 60000;
    final long add_time = 3000;
    final long remove_time = 2000;

    private void init(){
        word_index = (int)(Math.random()*words.length);
        word.setText(words[word_index]);
        translation.setText("");
    }

    MyTextView word, points_activity, points_tv, time_activity, time_tv, tv_go;
    EditText translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_work_irregular_verbs1lvl);

        list_mistakes = new LinkedList<>();

        words = new String[179];
        readFile(words, "words/irregular/irregular_base_words.txt");
        words[178] = "be";

        translations = new String[179];
        readFile(translations, "translations/irregular/irregular_translations.txt");
        translations[178] = "быть";

        translations_2nd = new String[179];
        readFile(translations_2nd, "translations/irregular/irregular_translations_2nd.txt");
        translations_2nd[178] = ".";

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Baron.ttf");
        word = (MyTextView)findViewById(R.id.word);
        points_activity = (MyTextView)findViewById(R.id.points);
        points_tv = (MyTextView)findViewById(R.id.points_tv);
        time_activity = (MyTextView)findViewById(R.id.time);
        time_tv = (MyTextView)findViewById(R.id.time_tv);
        translation = (EditText)findViewById(R.id.translation);
        translation.setTypeface(typeface);
        tv_go = (MyTextView)findViewById(R.id.click_go);
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
                animation_in = AnimationUtils.loadAnimation(Practical_work_irregular_verbs1lvl.this, R.anim.alpha_in);
                animation_out = AnimationUtils.loadAnimation(Practical_work_irregular_verbs1lvl.this, R.anim.alpha_out);
                animation_in.setDuration(5000);
                animation_out.setDuration(1000);
                String text = translation.getText().toString().toLowerCase().trim();
                if (!text.equals(".")){ // защита от особо хитрых игроков
                    if (text.equals(translations[word_index].toLowerCase()) || text.equals(translations_2nd[word_index].trim().toLowerCase())) {
                        points += 1;
                        points_activity.setText(String.valueOf(points));
                        playSound(rightSound);
                        imageRightAnswear.setAnimation(animation_in);
                        imageRightAnswear.setAnimation(animation_out);
                        timer.cancel();
                        countdownPeriod += add_time;
                        createAndStart(countdownPeriod, countDownInterval);
                    }else {
                        playSound(wrongSound);
                        imageWrongAnswear.setAnimation(animation_in);
                        imageWrongAnswear.setAnimation(animation_out);

                        String trans_s = "";
                        if (!translations_2nd[word_index].equals(".")){
                            trans_s = "/" + translations_2nd[word_index];
                        }
                        Mistake mistake = new Mistake(words[word_index], translation.getText().toString(), translations[word_index] + trans_s);
                        list_mistakes.add(mistake);

                        timer.cancel();
                        countdownPeriod -= remove_time;
                        createAndStart(countdownPeriod, countDownInterval);
                    }
                }else {
                    playSound(wrongSound);
                    imageWrongAnswear.setAnimation(animation_in);
                    imageWrongAnswear.setAnimation(animation_out);

                    String trans_s = "";
                    if (!translations_2nd[word_index].equals(".")){
                        trans_s = "/" + translations_2nd[word_index];
                    }
                    Mistake mistake = new Mistake(words[word_index], ".", translations[word_index] + trans_s);
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
                Intent i = new Intent(Practical_work_irregular_verbs1lvl.this, Mistakes.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                String topic_and_points = "irregular_verbs" + "1lvl" + separator_for_topic_and_points + points;

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
