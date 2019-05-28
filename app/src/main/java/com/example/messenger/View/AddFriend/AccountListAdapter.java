package com.example.messenger.View.AddFriend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.messenger.MainActivity;
import com.example.messenger.Model.Account;
import com.example.messenger.Model.FriendListItem;
import com.example.messenger.Presenter.AddFriend.Presenter;
import com.example.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class AccountListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<Account> accountList;
    private List<Account> accountListFull;
    private Context context;

    public AccountListAdapter(Context context, List<Account> accountList){
        this.context = context;
        this.accountList = accountList;
        accountListFull= new ArrayList<>(accountList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // xài chung item layout voi friendlist
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friendlist, viewGroup, false);
        return new Item(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        Account account = this.accountList.get(i);
        ((Item)viewHolder).bind(account);
        ((Item) viewHolder).parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendListItem friendListItem = new FriendListItem(MainActivity.CURRENT_USER_ID,((Item) viewHolder).AccountID,((Item) viewHolder).AccountName);
                Presenter presenter = new Presenter();
                presenter.addFriend(friendListItem);
                Toast.makeText(context, "Added "+friendListItem.getFriendName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    @Override
    public Filter getFilter() {
        return accountListFilter;
    }

    private Filter accountListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Account> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(accountListFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Account account : accountListFull){
                    if (account.getUsername().toLowerCase().contains(filterPattern)){
                        filteredList.add(account);
                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            accountList.clear();
            accountList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class Item extends RecyclerView.ViewHolder {
        TextView textView;
        ConstraintLayout parentLayout;
        int AccountID;
        String AccountName;

        public Item(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tvItemFriendList);
            parentLayout = itemView.findViewById(R.id.constraintLayout);
        }

        void bind(Account addFriendItem) {
            textView.setText(addFriendItem.getUsername());
            AccountID = addFriendItem.getAccountID();
            AccountName = addFriendItem.getUsername();
        }
    }
}
