package id.putraprima.roommvvmjava.screen.wordlist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import id.putraprima.roommvvmjava.models.Word;

public class WordListViewModelFactory implements ViewModelProvider.Factory {
    private List<Word> wordList;

    public WordListViewModelFactory(List<Word> wordList) {
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(WordListViewModel.class)){
            return (T) new WordListViewModel(wordList);
        }
        throw new IllegalArgumentException("Error Viewmodel");
    }
}
