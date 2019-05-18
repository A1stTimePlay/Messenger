package com.example.messenger.View.FriendList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.messenger.R;

import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] friendList;
    private Context context;

    public FriendListAdapter(Context context, String[] friendList){
        this.context = context;
        this.friendList = friendList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.item_friendlist, viewGroup, false);
        Item item = new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((Item)viewHolder).textView.setText(friendList[i]);
    }

    @Override
    public int getItemCount() {
        return this.friendList.length;
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView textView;

        public Item(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tvItemFriendlist);
        }
    }
}
