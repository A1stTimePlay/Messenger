package com.example.messenger.View.FriendList;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.messenger.MainActivity;
import com.example.messenger.Model.FriendListItem;
import com.example.messenger.Presenter.FriendList.Presenter;
import com.example.messenger.R;
import com.example.messenger.Utils.ItemOffsetDecoration;

import java.util.ArrayList;
import java.util.List;

public class View extends MainActivity implements IView {

    private RecyclerView recyclerView;
    private List<FriendListItem> friendListItemList;
    private FriendListAdapter adapter;
    private FloatingActionButton fabAddFriend;
    private Toolbar toolbar;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        initVariable();
    }

    public void initVariable(){
        fabAddFriend = findViewById(R.id.fabAddFriend);
        fabAddFriend.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(com.example.messenger.View.FriendList.View.this, com.example.messenger.View.AddFriend.View.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        friendListItemList = new ArrayList<>();

        toolbar = findViewById(R.id.toolbar_friendlist);
        setSupportActionBar(toolbar);

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

    // tạo thanh search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
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

    // User không thể quay lại màn hình login bằng nút back
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
