package com.example.firebasecarddatabase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CardList extends ArrayAdapter<Card>{

    private Activity context;
    private List<Card> cardList;

    public CardList(Activity context, List<Card> cardList){
        super(context, R.layout.list_layout, cardList);
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lvItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView tvCardName = (TextView) lvItem.findViewById(R.id.tvCardName);
        TextView tvCardPower = (TextView) lvItem.findViewById(R.id.tvCardPower);

        Card card = cardList.get(position);

        tvCardName.setText(card.getName());
        tvCardPower.setText(card.getPower());

        return lvItem;
    }
}
