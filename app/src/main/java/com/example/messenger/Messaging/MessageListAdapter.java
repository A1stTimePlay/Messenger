package com.example.messenger.Messaging;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.messenger.Model.Message;
import com.example.messenger.R;

import java.util.ArrayList;
import java.util.List;

public class MessageListAdapter extends  RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    private List<String> Content = new ArrayList<>();
    private List<String> DateTime = new ArrayList<>();

    public MessageListAdapter(List<String> content, List<String> dateTime) {
        Content = content;
        DateTime = dateTime;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_message_sent, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textMessageBody.setText(Content.get(i));
        viewHolder.textMessageTime.setText(DateTime.get(i));
    }

    @Override
    public int getItemCount() {
        return Content.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textMessageBody;
        TextView textMessageTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textMessageBody= itemView.findViewById(R.id.text_message_body);
            textMessageTime= itemView.findViewById(R.id.text_message_time);
        }
    }
}
