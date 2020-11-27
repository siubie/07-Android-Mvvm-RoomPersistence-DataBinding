package id.putraprima.roommvvmjava.screen.worddetail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.putraprima.roommvvmjava.R;
import id.putraprima.roommvvmjava.databinding.FragmentWordDetailBinding;
import id.putraprima.roommvvmjava.models.Word;

public class WordDetailFragment extends Fragment {

    FragmentWordDetailBinding binding;

    public WordDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_word_detail,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments()!=null;
        Word word = WordDetailFragmentArgs.fromBundle(getArguments()).getWord();
        binding.txtWord.setText(word.getWord());
    }
}