package com.example.messenger.View.FriendList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.messenger.MainActivity;
import com.example.messenger.Model.FriendListItem;
import com.example.messenger.Presenter.FriendList.Presenter;
import com.example.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class View extends AppCompatActivity implements IView {

    private RecyclerView recyclerView;
    private List<FriendListItem> friendListItemList;
    private FriendListAdapter adapter;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        initVariable();
    }

    public void initVariable(){
        recyclerView = findViewById(R.id.recyclerView);
        friendListItemList = new ArrayList<>();
        presenter = new Presenter(this);

        presenter.loadFriendList(MainActivity.CURRENT_USER_ID);

    }

    public void fillRecycleView(List<FriendListItem> friendListItemList){
        this.friendListItemList = friendListItemList;
        adapter = new FriendListAdapter(this, this.friendListItemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView  = (android.support.v7.widget.SearchView) searchItem.getActionView();
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
