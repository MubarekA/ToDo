package com.example.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends  RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface  onClickListener {
        void onItemClicked(int position);
    }
    public interface  onLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    onLongClickListener longClickListerner;
    onClickListener clickListener;
    public ItemsAdapter(List<String> items, onLongClickListener longClickListerner,onClickListener clickListener) {
        this.items = items;
        this.longClickListerner = longClickListerner;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // User layout inflator to inflate the view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //grab the item at the position
    String item=items.get(position);
        //bind the item into the specified view holder
        holder.bind(item);
    }
//tells RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder {

        TextView tvItem;
//container to provide easy access to views that represent each row of the list
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
        // update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify listener which position was long pressed
                    longClickListerner.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
