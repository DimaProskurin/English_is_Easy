package com.example.englishiseasy;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class Mistakes extends AppCompatActivity {

    private View mChart;
    double mistakes_count, right_count;

    ListView listView;
    ArrayList<Mistake> list;
    MyTextView button_next, your_result_tv;

    String topic;
    final String separator_for_topic_and_points = "/";

    ArrayList<HashMap<String, Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mistakes);



        Bundle bundle = getIntent().getExtras();
        topic = bundle.getString("topic_and_points");
        list = bundle.getParcelableArrayList("list_mistakes");
        right_count = bundle.getInt("right_count");
        mistakes_count = bundle.getInt("mistakes_count");

        addPie();
        your_result_tv = (MyTextView)findViewById(R.id.your_result);
        initYourResult();

        listView = (ListView)findViewById(R.id.listView);
        button_next = (MyTextView) findViewById(R.id.next_inMistakeClass);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mistakes.this, Practical_work_score.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("topic_and_points",topic);
                startActivity(i);
                overridePendingTransition(R.anim.right_to_left, R.anim.top_out);
            }
        });

        data = new ArrayList<>(list.size());
        updateData();
        String from[] = {"word","your_translation","right_translation"};
        int to[] = {R.id.word_adapter, R.id.your_translation_adapter, R.id.right_translation_adapter};
        final SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.adapter_item_mistakes, from, to);
        listView.setAdapter(adapter);
    }

    private void updateData(){
        data.clear();
        HashMap<String, Object> map;
        for (int i = 0; i < list.size(); i++) {
            map = new HashMap<>();
            map.put("word", list.get(i).getWord());
            map.put("your_translation", list.get(i).getYour_translation());
            map.put("right_translation", list.get(i).getRight_translation());
            data.add(map);
        }
    }

    public void onBackPressed(){
        Intent i = new Intent(this, ChooseDifficulty.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int index = topic.indexOf(separator_for_topic_and_points);
        topic = topic.substring(0, index); // убрали очки
        topic = topic.substring(0,topic.length()-4); // убираем уровень 1лвл 2 лвл или 3лвл
        i.putExtra("topic",topic);
        startActivity(i);
        overridePendingTransition(R.anim.left_to_right, R.anim.top_in);
    }

    private void addPie() {
        // Имена секций
        String[] code = new String[] { "Ошибки", "Правильно" };

        // Значения секций
        double[] distribution = { mistakes_count, right_count };

        // Цвета секций
        int[] colors = { Color.RED, Color.GREEN };

        // Инициилизируем каждый кусок
        CategorySeries distributionSeries = new CategorySeries("");
        for (int i = 0; i < distribution.length; i++) {
            distributionSeries.add(code[i], distribution[i]);
        }

        // Рендер
        DefaultRenderer defaultRenderer = new DefaultRenderer();
        for (int i = 0; i < distribution.length; i++) {
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(false);

            // Рендер для куска
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        defaultRenderer.setLabelsColor(Color.BLACK);
        defaultRenderer.setChartTitleTextSize(20);
        defaultRenderer.setLabelsTextSize(13.f);
        defaultRenderer.setShowLegend(false); // выключаем легенду
        defaultRenderer.setZoomEnabled(false); // вырубаем зум
        defaultRenderer.setPanEnabled(false); // вырубаем движение

        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
        chartContainer.removeAllViews(); // очищаем
        // рисуем
        mChart = ChartFactory.getPieChartView(getBaseContext(), distributionSeries, defaultRenderer);
        chartContainer.addView(mChart);

    }

    public void initYourResult(){
        int all = (int)(mistakes_count + right_count);

        String your_result_str; // делаем склонение "раз" или "раза"
        if ((int)mistakes_count != 12 && (int)mistakes_count != 13 && (int)mistakes_count != 14){
            if ((int)mistakes_count % 10 == 2 || (int)mistakes_count % 10 == 3 || (int)mistakes_count % 10 == 4){
                your_result_str = "Из "+all+" вопросов вы правильно ответили на "+(int)right_count+" из них и ошиблись "+(int)mistakes_count+" раза.\n \n";
            }else{
                your_result_str = "Из "+all+" вопросов вы правильно ответили на "+(int)right_count+" из них и ошиблись "+(int)mistakes_count+" раз.\n \n";
            }
        }else{
            your_result_str = "Из "+all+" вопросов вы правильно ответили на "+(int)right_count+" из них и ошиблись "+(int)mistakes_count+" раз.\n \n";
        }

        double relation_right = (int)right_count / Double.valueOf(all);

        if (relation_right > 0.95){
            your_result_str += "Вердикт: Удивительно! Вы знаете эту тему практически в совершенстве! Носители языков вам завидуют!";
        }else{
            if (relation_right > 0.75){
                your_result_str += "Вердикт: Молодец! Вы знаете эту тему и можете легко использовать эту лексику в своей речи.";
            }else{
                if (relation_right > 0.5){
                    your_result_str += "Вердикт: Хорошо. Эта тема вам более менее знакома, но есть к чему стремиться.";
                }else{
                    if (relation_right > 0.35){
                        your_result_str += "Вердикт: Плохо. Вам следует повторить эту тему и пройти урок ещё раз.";
                    }else{
                        your_result_str += "Вердикт: :( Выучите материал и возвращайтесь.";
                    }
                }
            }
        }

        your_result_tv.setText(your_result_str);
    }

}
