package com.example.messenger.View.FriendList;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.messenger.MainActivity;
import com.example.messenger.Model.FriendListItem;
import com.example.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<FriendListItem> friendListItemList;
    private List<FriendListItem> friendListItemListFull;
    private Context context;

    public FriendListAdapter(Context context, List<FriendListItem> friendListItemList) {
        this.context = context;
        this.friendListItemList = friendListItemList;
        friendListItemListFull = new ArrayList<>(friendListItemList);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friendlist, viewGroup, false);
        return new Item(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        FriendListItem friendListItem = this.friendListItemList.get(i);
        ((Item) viewHolder).bind(friendListItem);
        ((Item) viewHolder).parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.CURRENT_FRIEND_ID = ((Item) viewHolder).FriendID;
                MainActivity.CURRENT_FRIEND_NAME = ((Item)viewHolder).FriendName;
                Intent intent = new Intent(context, com.example.messenger.View.Messaging.View.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendListItemList.size();
    }

    @Override
    public Filter getFilter() {
        return friendListItemListFilter;
    }

    private Filter friendListItemListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<FriendListItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(friendListItemListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (FriendListItem item: friendListItemListFull){
                    if (item.getFriendName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            friendListItemList.clear();
            friendListItemList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class Item extends RecyclerView.ViewHolder {
        TextView textView;
        ConstraintLayout parentLayout;
        int FriendID;
        String FriendName;

        public Item(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tvItemFriendList);
            parentLayout = itemView.findViewById(R.id.constraintLayout);
        }

        void bind(FriendListItem friendListItem) {
            textView.setText(friendListItem.getFriendName());
            FriendID = friendListItem.getFriendID();
            FriendName = friendListItem.getFriendName();
        }
    }
}
