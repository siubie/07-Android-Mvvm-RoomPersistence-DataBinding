package id.putraprima.roommvvmjava.screen.wordlist;

import android.graphics.Movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.putraprima.roommvvmjava.models.Word;

public class WordListViewModel extends ViewModel {
    private MutableLiveData<List<Word>> listWordMutableLivedata = new MutableLiveData<>();
    private List<Word> listWord = null;

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


}
