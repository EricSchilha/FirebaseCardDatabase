package com.example.firebasecarddatabase;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
/*  SOURCES
// Reading and Writing to a Firebase Realtime Database:                   https://firebase.google.com/docs/database/android/read-and-write
// Reading in a JSON Tree Structure and displaying cards in a list:       https://www.youtube.com/watch?v=jEmq1B1gveM
// Structuring Data:                                                      https://firebase.google.com/docs/database/android/structure-data
// Retrieving Specific Data from the Database:                            https://stackoverflow.com/questions/27187725/retrieving-specific-data-from-firebase-android
//
*/

public class MainActivity extends Activity {
    DatabaseReference dbCardReference;
    List<Card> lCards = new ArrayList<>();
    //ArrayAdapter<Card> aaCards;// = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, alCards);
    EditText editTextName, editTextPower, editTextToughness, editTextCMC;
    Spinner spinnerSets;
    Button btnAddCard;
    ListView lvCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbCardReference = FirebaseDatabase.getInstance().getReference("cards");
        editTextName = (EditText) findViewById(R.id.editText);
        editTextPower = (EditText) findViewById(R.id.editText2);
        editTextToughness = (EditText) findViewById(R.id.editText3);
        editTextCMC = (EditText) findViewById(R.id.editText4);
        btnAddCard = (Button) findViewById(R.id.buttonAddCard);
        lvCards = (ListView) findViewById(R.id.listViewCards);
        spinnerSets = (Spinner) findViewById(R.id.spinnerSets);
        //aaCards = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, alCards);


        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard(editTextName.getText().toString(), spinnerSets.getSelectedItem().toString(), Integer.parseInt(editTextCMC.getText().toString()), Integer.parseInt(editTextPower.getText().toString()), Integer.parseInt(editTextToughness.getText().toString()));
            }
        });

        //ADD CARDS
        addCard("Storm Crow", "7th Edition", 1, 2, 2);
        addCard("Alpha Tyrranax", "Scars of Mirrodin", 6, 5, 6);
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbCardReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lCards.clear();
                for (DataSnapshot cardSnapshot : dataSnapshot.getChildren()) {
                    Card tCard = cardSnapshot.getValue(Card.class);
                    lCards.add(tCard);
                    //editTextName.setText(lCards.get(0).sSet);
                }
                CardList clAdapter = new CardList(MainActivity.this, lCards);
                lvCards.setAdapter(clAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void addCard(String name, String set, int cmc, int power, int toughness) { //Named so it looks formal in the firebase console
        Card tCard = new Card(name, /*id,*/ set, cmc, power, toughness);
        lCards.add(tCard);
        dbCardReference.child(name).setValue(tCard);
    }

}
