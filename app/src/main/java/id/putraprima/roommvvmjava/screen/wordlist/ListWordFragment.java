package id.putraprima.roommvvmjava.screen.wordlist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.putraprima.roommvvmjava.R;
import id.putraprima.roommvvmjava.databinding.FragmentListWordBinding;
import id.putraprima.roommvvmjava.models.Word;

public class ListWordFragment extends Fragment {

    private FragmentListWordBinding binding;
    private WordListViewModel viewModel;

    public ListWordFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<Word> wordList = new ArrayList<>();
        wordList.add(new Word("Satu"));
        wordList.add(new Word("Dua"));
        wordList.add(new Word("Tiga"));
        WordListViewModelFactory viewModelFactory = new WordListViewModelFactory(wordList);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list_word, container, false);
        viewModel = new ViewModelProvider(this,viewModelFactory).get(WordListViewModel.class);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRvWord();
    }

    private void setupRvWord() {
        RecyclerView rv = binding.rvWord;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        WordListAdapter adapter = new WordListAdapter(new OnItemWordListener() {
            @Override
            public void onWordClicked(Word word) {
                if(word!=null){
                    NavDirections action = ListWordFragmentDirections.actionListWordFragmentToWordDetailFragment();
                    Navigation.findNavController(requireView()).navigate(action);
                    viewModel.onWordNavigated();
                }
            }
        });

        rv.setAdapter(adapter);

        viewModel.listMovieLiveData().observe(getViewLifecycleOwner(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setWordList(words);
            }
        });

        viewModel.navigateToAdd().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean!=null){
                    NavDirections action = ListWordFragmentDirections.actionListWordFragmentToWordAddFragment();
                    Navigation.findNavController(requireView()).navigate(action);
                    viewModel.onAddBtnNavigated();
                }
            }
        });

    }



}