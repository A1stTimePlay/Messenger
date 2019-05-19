package com.example.messenger.View.FriendList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.messenger.Model.FriendListItem;
import com.example.messenger.R;

import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FriendListItem> friendListItemList;
    private Context context;

    public FriendListAdapter(Context context, List<FriendListItem> friendListItemList){
        this.context = context;
        this.friendListItemList = friendListItemList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friendlist, viewGroup, false);
        return new Item(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FriendListItem friendListItem = this.friendListItemList.get(i);
        ((Item) viewHolder).bind(friendListItem);
    }

    @Override
    public int getItemCount() {
        return friendListItemList.size();
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView textView;

        public Item(View itemView){
            super(itemView);

            textView = itemView.findViewById(R.id.tvItemFriendList);
        }

        void bind(FriendListItem friendListItem){
            textView.setText(Integer.toString(friendListItem.getFriendID()));
        }
    }
}
