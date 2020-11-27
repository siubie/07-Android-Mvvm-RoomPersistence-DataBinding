package id.putraprima.roommvvmjava.screen.wordlist;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.putraprima.roommvvmjava.R;
import id.putraprima.roommvvmjava.databinding.FragmentListWordBinding;
import id.putraprima.roommvvmjava.models.Word;
import id.putraprima.roommvvmjava.screen.worddetail.WordDetailFragmentArgs;

public class ListWordFragment extends Fragment {

    private FragmentListWordBinding binding;
    private WordListViewModel viewModel;
    AlertDialog.Builder dialog;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_word, container, false);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(WordListViewModel.class);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRvWord();
        binding.btnAddOneWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        dialog = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_add_word, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Add New Word");
        EditText edtWordInput;
        edtWordInput = dialogView.findViewById(R.id.edtWordInput);

        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                viewModel.onBtnAddOneWordClicked(edtWordInput.getText().toString());
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    private void setupRvWord() {
        RecyclerView rv = binding.rvWord;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        WordListAdapter adapter = new WordListAdapter(new OnItemWordListener() {
            @Override
            public void onWordClicked(Word word) {
                if (word != null) {
                    NavDirections action = ListWordFragmentDirections.actionListWordFragmentToWordDetailFragment(word);
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


    }


}