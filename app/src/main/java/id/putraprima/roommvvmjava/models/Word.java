package id.putraprima.roommvvmjava.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Word implements Parcelable {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    protected Word(Parcel in) {
        word = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(word);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public String getWord() {
        return word;
    }
}
