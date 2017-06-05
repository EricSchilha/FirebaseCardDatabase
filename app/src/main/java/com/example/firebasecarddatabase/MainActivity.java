package com.example.firebasecarddatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/*  SOURCES
// Reading and Writing to a Firebase Realtime Database:                   https://firebase.google.com/docs/database/android/read-and-write
// Reading in a JSON Tree Structure and displaying cards in a list:       https://www.youtube.com/watch?v=jEmq1B1gveM
// Structuring Data:                                                      https://firebase.google.com/docs/database/android/structure-data
// Retrieving Specific Data from the Database:                            https://stackoverflow.com/questions/27187725/retrieving-specific-data-from-firebase-android
//
*/

public class MainActivity extends AppCompatActivity {
    DatabaseReference dbCardReference;
    ArrayList<Card> alCards = new ArrayList<>();
    EditText editText;
    Button btnAddCard;
    ListView lvCards;

    @Override
    protected void onStart() {
        super.onStart();

        dbCardReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alCards.clear();
                for (DataSnapshot cardSnapshot : dataSnapshot.getChildren()) {
                    Card tCard = cardSnapshot.getValue(Card.class);
                    alCards.add(tCard);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbCardReference = FirebaseDatabase.getInstance().getReference("cards");
        editText = (EditText) findViewById(R.id.editText);
        btnAddCard = (Button) findViewById(R.id.buttonAddCard);
        lvCards = (ListView) findViewById(R.id.listViewCards);
        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard(editText.getText().toString(), "", 0, 0, 0);
            }
        });

        //ADD CARDS
        addCard("Storm Crow", "7th Edition", 1, 2, 2);
        addCard("Alpha Tyrranax", "Scars of Mirrodin", 6, 5, 6);
    }


    public void addCard(String name, String set, int cmc, int power, int toughness) { //Named so it looks formal in the firebase console
        Card tCard = new Card(name, /*id,*/ set, cmc, power, toughness);
        alCards.add(tCard);
        dbCardReference.child(name).setValue(tCard);
    }

}
