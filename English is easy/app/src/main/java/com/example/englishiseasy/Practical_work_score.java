package com.example.englishiseasy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Practical_work_score extends AppCompatActivity {

    final String MY_SERVER = "http://myserver-project.herokuapp.com";

    EditText editT_name;
    ListView listView;
    LinkedList<Players> list;
    MyTextView tv_ok;
    int points;
    final String myKeyName = "admin";
    final String separator_for_topic_and_points = "/";

    String topic_and_points;
    String topic;

    Players mPlayer;
    SimpleAdapter adapter;

    ArrayList<HashMap<String, Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_work_score);

        //Чтобы сервер проснулся
        mPlayer = new Players(myKeyName, 0, "admin_topic");
        new HttpSaveUserInfoTask().execute(MY_SERVER + "/save");
        new HttpDeleteUserTask().execute(MY_SERVER + "/deletebyname/" + myKeyName);

        topic_and_points = getIntent().getStringExtra("topic_and_points");
        String tmp_arr[] = topic_and_points.split(separator_for_topic_and_points);
        topic = tmp_arr[0];
        points = Integer.valueOf(tmp_arr[1]);

        listView = (ListView)findViewById(R.id.listView);

        data = new ArrayList<>();
        new HttpGetAllUsersTask().execute(MY_SERVER + "/get/" + topic);

        String from[] = {"name","point"};
        int to[] = {R.id.name_adapter, R.id.points_adapter};
        adapter = new SimpleAdapter(this, data, R.layout.adapter_item, from, to);
        listView.setAdapter(adapter);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Baron.ttf");

        editT_name = (EditText)findViewById(R.id.name);
        editT_name.setTypeface(typeface);

        tv_ok = (MyTextView)findViewById(R.id.click_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editT_name.getText().toString();
                name = name.trim();
                if (name.equals(myKeyName)){
                    Toast.makeText(Practical_work_score.this, "Нельзя использовать '"+myKeyName+ "' в имени", Toast.LENGTH_LONG).show();
                }else{
                    mPlayer = new Players(name, points, topic);
                    new HttpSaveUserInfoTask().execute(MY_SERVER + "/save");
                    editT_name.setVisibility(View.INVISIBLE);
                    tv_ok.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private class HttpSaveUserInfoTask extends AsyncTask<String, Void, String> {

        private JSONObject json;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                json = new JSONObject();
                json.put("playerTopic", mPlayer.getPlayerTopic());
                json.put("playerName", mPlayer.getPlayerName());
                json.put("playerPoints", mPlayer.getPlayerPoints());
            } catch (JSONException e) {
                Log.e("error_json", e.getLocalizedMessage());
            }
        }

        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream;
            HttpURLConnection urlConnection;
            String result = "";
            String jsonToString = json.toString();
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("PUT");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
                urlConnection.connect();
                urlConnection.getOutputStream().write(jsonToString.getBytes());
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                String response = convertInputStreamToString(inputStream);
                urlConnection.disconnect();
                result = response;
            } catch (MalformedURLException e) {
                Log.e("error_malformed", e.getLocalizedMessage());
            } catch (IOException e) {
                Log.e("error_ioexception", e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (!result.equals("1")) {
                Toast.makeText(Practical_work_score.this, "При сохранении пользовательских данных на сервере произошла ошибка", Toast.LENGTH_LONG).show();
            }
            new HttpGetAllUsersTask().execute(MY_SERVER + "/get/" + topic);
        }
    }

    private class HttpGetAllUsersTask extends AsyncTask<String, Void, String>{

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Practical_work_score.this);
            dialog.setTitle("Загружаем информацию...");
            dialog.setCancelable(false);
            dialog.setIndeterminate(true);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream;
            HttpURLConnection urlConnection;
            String result = "";
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                String response = convertInputStreamToString(inputStream);
                urlConnection.disconnect();
                result = response;
            } catch (MalformedURLException e) {
                Log.e("error_malformed", e.getLocalizedMessage());
            } catch(IOException e){
                Log.e("error_ioexception", e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Players player = null;
            try {
                JSONArray array = new JSONArray(result);
                list = new LinkedList<>();
                for(int i=0; i<array.length(); i++){
                    player = new Players(array.getJSONObject(i).getString("playerName"), array.getJSONObject(i).getInt("playerPoints"), array.getJSONObject(i).getString("playerTopic"));
                    list.add(player);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
            if (list == null){
                Toast.makeText(Practical_work_score.this,"Проблема подключения к сети",Toast.LENGTH_LONG).show();
            }else{
                updateData();
                adapter.notifyDataSetChanged();
            }
        }
    }

    private class HttpDeleteUserTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream;
            HttpURLConnection urlConnection;
            String result = "";
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("DELETE");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
                urlConnection.connect();
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                String response = convertInputStreamToString(inputStream);
                urlConnection.disconnect();
                result = response;
            } catch (MalformedURLException e) {
                Log.e("error_malformed", e.getLocalizedMessage());
            } catch(IOException e){
                Log.e("error_ioexception", e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    private String convertInputStreamToString(InputStream inputStream)
            throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        if (inputStream != null) {
            inputStream.close();
        }
        return result;
    }

    private void updateData(){
        data.clear();
        HashMap<String, Object> map;
        for (int i = 0; i < list.size(); i++) {
                map = new HashMap<>();
                map.put("name", list.get(i).getPlayerName());
                map.put("point", list.get(i).getPlayerPoints());
                data.add(map);
        }
    }

    public void onBackPressed(){
        Intent i = new Intent(this, ChooseDifficulty.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        topic = topic.substring(0,topic.length()-4); // убираем уровень 1лвл 2 лвл или 3лвл
        i.putExtra("topic",topic);
        startActivity(i);
        overridePendingTransition(R.anim.left_to_right, R.anim.top_in);
    }
}
