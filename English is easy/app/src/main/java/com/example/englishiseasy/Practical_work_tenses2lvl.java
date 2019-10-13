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

public class Practical_work_tenses2lvl extends AppCompatActivity {

    String topic = "tenses";

    Sentence[] sentences;
    String tenses[] = {"Present Simple", "Present Progressive", "Present Perfect", "Present Perfect Progressive",
            "Past Simple", "Past Progressive", "Past Perfect", "Past Perfect Progressive",
            "Future Simple", "Future Progressive", "Future Perfect", "Future Perfect Progressive"};

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

    int sentences_size;

    LinkedList<Mistake> list_mistakes;

    final String separator_for_topic_and_points = "/";

    ImageView imageRightAnswear, imageWrongAnswear;

    Animation animation_in, animation_out;

    private SoundPool soundPool;
    private int wrongSound, rightSound, Stream;

    public int sentence_index;
    public String tense; // время, которое загадано в предложении
    public int points = 0;

    CountDownTimer timer;
    long countDownInterval = 1000;
    long countdownPeriod = 60000;
    final long add_time = 1000;
    final long remove_time = 4000;

    MyTextView sentence, points_activity, points_tv, time_activity, time_tv;
    MyTextView textViews[] = new MyTextView[8];
    boolean true_textViews[] = new boolean[8];

    private void init(){
        for (int i=0; i<true_textViews.length; i++){
            true_textViews[i] = false;
        }

        sentence_index = (int)(Math.random()*sentences.length);
        sentence.setText(sentences[sentence_index].getText());

        tense = sentences[sentence_index].getTime() + " " + sentences[sentence_index].getGroup();

        int random_index;
        int random_button;
        boolean check = false;

        for (int i = 0; i < textViews.length; i++){
            random_index = (int)(Math.random()*tenses.length);
            if (tenses[random_index].equals(tense)){
                true_textViews[i] = true;
            }
            textViews[i].setText(tenses[random_index]);
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
            textViews[random_button].setText(tense);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_work_tenses2lvl);

        presentGroupsSize = presentSimpleSize + presentProgressiveSize + presentPerfectSize + presentPerfectProgressiveSize;
        pastGroupsSize = pastSimpleSize + pastProgressiveSize + pastPerfectSize + pastPerfectProgressiveSize;
        futureGroupsSize = futureSimpleSize + futureProgressiveSize + futurePerfectSize + futurePerfectProgressiveSize;

        sentences_size = presentGroupsSize + pastGroupsSize + futureGroupsSize;

        Sentence presentSimple[] = new Sentence[presentSimpleSize];
        readFileTenses(presentSimple, "translations/tenses/PresentSimple_translations.txt", "Simple", "Present");

        Sentence presentProgressive[] = new Sentence[presentProgressiveSize];
        readFileTenses(presentProgressive, "translations/tenses/PresentProgressive_translations.txt", "Progressive", "Present");

        Sentence presentPerfect[] = new Sentence[presentPerfectSize];
        readFileTenses(presentPerfect, "translations/tenses/PresentPerfect_translations.txt", "Perfect", "Present");

        Sentence presentPerfectProgressive[] = new Sentence[presentPerfectProgressiveSize];
        readFileTenses(presentPerfectProgressive, "translations/tenses/PresentPerfectProgressive_translations.txt", "Perfect Progressive", "Present");

        Sentence pastSimple[] = new Sentence[pastSimpleSize];
        readFileTenses(pastSimple, "translations/tenses/PastSimple_translations.txt", "Simple", "Past");

        Sentence pastProgressive[] = new Sentence[pastProgressiveSize];
        readFileTenses(pastProgressive, "translations/tenses/PastProgressive_translations.txt", "Progressive", "Past");

        Sentence pastPerfect[] = new Sentence[pastPerfectSize];
        readFileTenses(pastPerfect, "translations/tenses/PastPerfect_translations.txt", "Perfect", "Past");

        Sentence pastPerfectProgressive[] = new Sentence[pastPerfectProgressiveSize];
        readFileTenses(pastPerfectProgressive, "translations/tenses/PastPerfectProgressive_translations.txt", "Perfect Progressive", "Past");

        Sentence futureSimple[] = new Sentence[futureSimpleSize];
        readFileTenses(futureSimple, "translations/tenses/FutureSimple_translations.txt", "Simple", "Future");

        Sentence futureProgressive[] = new Sentence[futureProgressiveSize];
        readFileTenses(futureProgressive, "translations/tenses/FutureProgressive_translations.txt", "Progressive", "Future");

        Sentence futurePerfect[] = new Sentence[futurePerfectSize];
        readFileTenses(futurePerfect, "translations/tenses/FuturePerfect_translations.txt", "Perfect", "Future");

        Sentence futurePerfectProgressive[] = new Sentence[futurePerfectProgressiveSize];
        readFileTenses(futurePerfectProgressive, "translations/tenses/FuturePerfectProgressive_translations.txt", "Perfect Progressive", "Future");

        // Далее следует объединение
        Sentence presentSentences[] = new Sentence[presentGroupsSize];
        System.arraycopy(presentSimple, 0, presentSentences, 0, presentSimple.length);
        System.arraycopy(presentProgressive, 0, presentSentences, presentSimple.length, presentProgressive.length);
        System.arraycopy(presentPerfect, 0, presentSentences, presentSimple.length + presentProgressive.length, presentPerfect.length);
        System.arraycopy(presentPerfectProgressive, 0, presentSentences, presentSimple.length + presentProgressive.length + presentPerfect.length, presentPerfectProgressive.length);

        Sentence pastSentences[] = new Sentence[pastGroupsSize];
        System.arraycopy(pastSimple, 0, pastSentences, 0, pastSimple.length);
        System.arraycopy(pastProgressive, 0, pastSentences, pastSimple.length, pastProgressive.length);
        System.arraycopy(pastPerfect, 0, pastSentences, pastSimple.length + pastProgressive.length, pastPerfect.length);
        System.arraycopy(pastPerfectProgressive, 0, pastSentences, pastSimple.length + pastProgressive.length + pastPerfect.length, pastPerfectProgressive.length);

        Sentence futureSentences[] = new Sentence[futureGroupsSize];
        System.arraycopy(futureSimple, 0, futureSentences, 0, futureSimple.length);
        System.arraycopy(futureProgressive, 0, futureSentences, futureSimple.length, futureProgressive.length);
        System.arraycopy(futurePerfect, 0, futureSentences, futureSimple.length + futureProgressive.length, futurePerfect.length);
        System.arraycopy(futurePerfectProgressive, 0, futureSentences, futureSimple.length + futureProgressive.length + futurePerfect.length, futurePerfectProgressive.length);

        sentences = new Sentence[sentences_size];
        System.arraycopy(presentSentences, 0, sentences, 0, presentSentences.length);
        System.arraycopy(pastSentences, 0, sentences, presentSentences.length, pastSentences.length);
        System.arraycopy(futureSentences, 0, sentences, presentSentences.length + pastSentences.length, futureSentences.length);

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
                    animation_in = AnimationUtils.loadAnimation(Practical_work_tenses2lvl.this, R.anim.alpha_in);
                    animation_out = AnimationUtils.loadAnimation(Practical_work_tenses2lvl.this, R.anim.alpha_out);
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
                        Mistake mistake = new Mistake(makeSentenceBeautiful(sentence.getText().toString()), textViews[finalI].getText().toString(), tense);
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
                Intent i = new Intent(Practical_work_tenses2lvl.this, Mistakes.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                String topic_and_points = topic + "2lvl" + separator_for_topic_and_points + points;

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

    public void readFileTenses(Sentence[] array, String filename, String group, String time){
        try {
            AssetManager assetManager = this.getAssets();
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(filename), "windows-1251");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int i = 0;
            while (i<array.length) {
                array[i] = new Sentence(bufferedReader.readLine(), group, time);
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
