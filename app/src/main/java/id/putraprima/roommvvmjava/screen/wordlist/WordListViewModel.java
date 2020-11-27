package id.putraprima.roommvvmjava.screen.wordlist;

import android.graphics.Movie;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import id.putraprima.roommvvmjava.models.Word;

public class WordListViewModel extends ViewModel {
    private MutableLiveData<List<Word>> listWordMutableLivedata = new MutableLiveData<>();
    private List<Word> listWord = new ArrayList<>();

    public WordListViewModel() {
    }

    public WordListViewModel(List<Word> listWord) {
        this.listWord = listWord;
        this.listWordMutableLivedata.setValue(listWord);
    }

     public LiveData<List<Word>> listMovieLiveData(){
        return listWordMutableLivedata;
     }


     private MutableLiveData<Word> _navigateToDetail = new MutableLiveData<>();
     public LiveData<Word> navigateToDetail(){
         return _navigateToDetail;
     }

     public void onWordClicked(Word word){
         _navigateToDetail.setValue(word);
     }

     public void onWordNavigated(){
         _navigateToDetail.setValue(null);
     }


    private MutableLiveData<Boolean> _navigateToAdd = new MutableLiveData<>();
    public LiveData<Boolean> navigateToAdd(){
        return _navigateToAdd;
    }

    public void onAddBtnClicked(){
        _navigateToAdd.setValue(true);
    }

    public void onAddBtnNavigated(){
        _navigateToAdd.setValue(null);
    }


    private MutableLiveData<Word> _wordMutableLiveData = new MutableLiveData<Word>();
    public LiveData<Word> wordLiveData(){
        return _wordMutableLiveData;
    }

    public void onBtnAddOneWordClicked(String word){
        Word newWord = new Word(word);
        listWord.add(newWord);
        _wordMutableLiveData.setValue(newWord);
        listWordMutableLivedata.setValue(listWord);
    }

    public void onAddFragmentNavigated(){
        _wordMutableLiveData.setValue(null);
    }
}
