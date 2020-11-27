package id.putraprima.roommvvmjava.screen.wordadd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import id.putraprima.roommvvmjava.R;
import id.putraprima.roommvvmjava.databinding.FragmentWordAddBinding;
import id.putraprima.roommvvmjava.models.Word;
import id.putraprima.roommvvmjava.screen.wordlist.ListWordFragmentDirections;
import id.putraprima.roommvvmjava.screen.wordlist.WordListViewModel;
import id.putraprima.roommvvmjava.screen.wordlist.WordListViewModelFactory;

public class WordAddFragment extends Fragment {

    FragmentWordAddBinding binding;
    WordListViewModel viewModel;
    public WordAddFragment() {
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_word_add,container,false);
        viewModel = new ViewModelProvider(this).get(WordListViewModel.class);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.wordLiveData().observe(getViewLifecycleOwner(), new Observer<Word>() {
            @Override
            public void onChanged(Word word) {
                if(word!=null){
                    NavDirections action = WordAddFragmentDirections.actionWordAddFragmentToListWordFragment();
                    Navigation.findNavController(requireView()).navigate(action);
                    viewModel.onAddFragmentNavigated();
                }
            }
        });
    }
}