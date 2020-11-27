package id.putraprima.roommvvmjava.screen.wordlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.putraprima.roommvvmjava.databinding.ItemWordBinding;
import id.putraprima.roommvvmjava.models.Word;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {
    private List<Word> wordList;
    private OnItemWordListener onItemWordListener;

    public WordListAdapter() {
    }

    public WordListAdapter(List<Word> wordList) {
        this.wordList = wordList;
    }

    public WordListAdapter(OnItemWordListener onItemWordListener) {
        this.onItemWordListener = onItemWordListener;
    }

    public WordListAdapter(List<Word> wordList, OnItemWordListener onItemWordListener) {
        this.wordList = wordList;
        this.onItemWordListener = onItemWordListener;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemWordBinding itemWordBinding = ItemWordBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(itemWordBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word word = wordList.get(position);
        holder.bind(word,onItemWordListener);
    }

    @Override
    public int getItemCount() {
        if(wordList!=null){
            return wordList.size();
        }else {
           return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemWordBinding itemWordBinding;

        public ViewHolder(ItemWordBinding itemWordBinding) {
            super(itemWordBinding.getRoot());
            this.itemWordBinding = itemWordBinding;
        }

        public void bind(Word word, OnItemWordListener itemWordListener){
            itemWordBinding.setWord(word);
            itemWordBinding.setClickListener(itemWordListener);
            itemWordBinding.executePendingBindings();
        }
    }
}
