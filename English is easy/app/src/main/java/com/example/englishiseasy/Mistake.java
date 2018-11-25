package com.example.englishiseasy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Дмитрий on 29.04.2016.
 */
public class Mistake implements Parcelable {
    private String word;
    private String your_translation;
    private String right_translation;

    public Mistake(Parcel in){
        //распаковка из parcel
        String[] array = new String[3];
        in.readStringArray(array);
        word = array[0];
        your_translation = array[1];
        right_translation = array[2];
    }

    public Mistake(String word, String your_translation, String right_translation){
        this.word = word;
        this.your_translation = your_translation;
        this.right_translation = right_translation;
    }

    public static final Creator<Mistake> CREATOR = new Creator<Mistake>() {
        @Override
        public Mistake createFromParcel(Parcel in) {
            return new Mistake(in);
        }

        @Override
        public Mistake[] newArray(int size) {
            return new Mistake[size];
        }
    };

    public String getRight_translation() {
        return right_translation;
    }

    public void setRight_translation(String right_translation) {
        this.right_translation = right_translation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getYour_translation() {
        return your_translation;
    }

    public void setYour_translation(String your_translation) {
        this.your_translation = your_translation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //упаковка в parcel
        dest.writeStringArray(new String[] {word, your_translation, right_translation});
    }
}
