package com.example.englishiseasy;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Theory_irregular_verbs extends AppCompatActivity {

    ListView listView;

    ArrayList<HashMap<String, Object>> data;

    String[] firstForm, secondForm, thirdForm, translations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_irregular_verbs);

        firstForm = new String[179];
        firstForm[0] = "be";
        readFile(firstForm, "words/irregular/irregular_base_words.txt");

        secondForm = new String[179];
        secondForm[0] = "was / were";
        readFile(secondForm, "words/irregular/irregular_past_simple_words.txt");
        readFileFor2ndFiles(secondForm, "words/irregular/irregular_past_simple_words_2nd.txt");

        thirdForm = new String[179];
        thirdForm[0] = "been";
        readFile(thirdForm, "words/irregular/irregular_past_participle_words.txt");
        readFileFor2ndFiles(thirdForm, "words/irregular/irregular_past_participle_words_2nd.txt");

        translations = new String[179];
        translations[0] = "быть";
        readFile(translations, "translations/irregular/irregular_translations.txt");
        readFileFor2ndFiles(translations, "translations/irregular/irregular_translations_2nd.txt");

        listView = (ListView)findViewById(R.id.listView_theory_iv);

        data = new ArrayList<>(firstForm.length);

        HashMap<String, Object> map;
        for (int i = 0; i < firstForm.length; i++) {
            map = new HashMap<>();
            map.put("firstForm", firstForm[i]);
            map.put("secondForm", secondForm[i]);
            map.put("thirdForm",thirdForm[i]);
            map.put("translation", translations[i]);
            data.add(map);
        }

        String from[] = {"firstForm","secondForm","thirdForm","translation"};
        int to[] = {R.id.firstForm_adapter, R.id.secondForm_adapter, R.id.thirdForm_adapter, R.id.translation_adapter};
        final SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.adapter_item_irregular_verbs, from, to);
        listView.setAdapter(adapter);
    }

    public void readFile(String[] array, String filename){
        try {
            AssetManager assetManager = this.getAssets();
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(filename), "windows-1251");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int i = 1; // ВНИМАНИЕ: Потому что глагол to be добавляется отдельно
            while (i<array.length) {
                array[i] = bufferedReader.readLine();
                i++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFileFor2ndFiles(String[] array, String filename){
        try {
            AssetManager assetManager = this.getAssets();
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(filename), "windows-1251");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int i = 1; // ВНИМАНИЕ: Потому что глагол to be добавляется отдельно
            while (i<array.length) {
                String s = bufferedReader.readLine();
                if (!s.equals(".")){
                    array[i] += "/" + s;
                }
                i++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed(){
        Intent i = new Intent(this, Theory_choosing.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        overridePendingTransition(R.anim.left_to_right, R.anim.top_in);
    }
}
