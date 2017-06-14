package com.example.firebasecarddatabase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 2017-06-07.
 */

public class CardList extends ArrayAdapter<Card> {
    private Activity context;
    private List<Card> lCards;
    String sName;

    public CardList(Activity context, List<Card> lCards){
        super(context, R.layout.list_layout, lCards);
        this.context = context;
        this.lCards = lCards;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lvItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView tvName = (TextView) lvItem.findViewById(R.id.textViewName);
        TextView tvSet = (TextView) lvItem.findViewById(R.id.textViewSet);

        Card tCard = lCards.get(position);

        tvName.setText(tCard.sName);
        tvSet.setText(tCard.sSet);
        return lvItem;
    }
}
