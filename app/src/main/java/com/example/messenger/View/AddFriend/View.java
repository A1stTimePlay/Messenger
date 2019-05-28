package com.example.messenger.View.AddFriend;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.messenger.MainActivity;
import com.example.messenger.Model.Account;
import com.example.messenger.Model.FriendListItem;
import com.example.messenger.Presenter.AddFriend.Presenter;
import com.example.messenger.R;
import com.example.messenger.Utils.ItemOffsetDecoration;
import com.example.messenger.View.FriendList.FriendListAdapter;

import java.util.ArrayList;
import java.util.List;

public class View extends MainActivity implements IView {

    private RecyclerView recyclerView;
    private List<Account> accountList;
    private AccountListAdapter adapter;
    private Toolbar toolbar;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        initVariable();
    }

    public void initVariable(){
        recyclerView = findViewById(R.id.recyclerview_account_list);
        accountList = new ArrayList<>();

        toolbar = findViewById(R.id.toolbar_friendlist);
        setSupportActionBar(toolbar);

        presenter = new Presenter(this);
        presenter.loadAccountList();
    }

    public void fillRecycleView(List<Account> accountList){
        this.accountList = accountList;
        adapter = new AccountListAdapter(this, this.accountList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_friend, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search_add_friend);
        android.support.v7.widget.SearchView searchView  = (android.support.v7.widget.SearchView) searchItem.getActionView();
        searchView.setQueryHint("Find something?");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }
}
