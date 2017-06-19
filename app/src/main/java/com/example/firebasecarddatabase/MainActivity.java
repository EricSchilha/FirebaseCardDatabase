package com.example.firebasecarddatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*  SOURCES
// Reading and Writing to a Firebase Realtime Database:                   https://firebase.google.com/docs/database/android/read-and-write
// Reading in a JSON Tree Structure and displaying cards in a list:       https://www.youtube.com/watch?v=jEmq1B1gveM
// Structuring Data:                                                      https://firebase.google.com/docs/database/android/structure-data
// Retrieving Specific Data from the Database:                            https://stackoverflow.com/questions/27187725/retrieving-specific-data-from-firebase-android
// Checking for blank EditText fields:                                    https://stackoverflow.com/questions/20349522/android-check-if-edittext-is-empty-when-inputtype-is-set-on-number-phone
// Toast Message:                                                         https://developer.android.com/guide/topics/ui/notifiers/toasts.html
*/

public class MainActivity extends Activity {
    DatabaseReference dbCardReference;
    EditText editTextName, editTextPower, editTextToughness, editTextCMC;
    Spinner spinnerSets;
    Button btnAddCard, btnSwitch;
    Intent iIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iIntent = new Intent(this, DisplayCards.class);
        dbCardReference = FirebaseDatabase.getInstance().getReference("cards");
        editTextName = (EditText) findViewById(R.id.editText);
        editTextPower = (EditText) findViewById(R.id.editText2);
        editTextToughness = (EditText) findViewById(R.id.editText3);
        editTextCMC = (EditText) findViewById(R.id.editText4);
        btnAddCard = (Button) findViewById(R.id.buttonAddCard);
        btnSwitch = (Button) findViewById(R.id.buttonSwitch);
        spinnerSets = (Spinner) findViewById(R.id.spinnerSets);


        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((editTextName.getText().toString().trim().length() == 0) || (editTextPower.getText().toString().trim().length() == 0) || (editTextToughness.getText().toString().trim().length() == 0) || (editTextCMC.getText().toString().trim().length() == 0)) {
                    Toast.makeText(getApplicationContext(), "Please enter a value in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    addCard(editTextName.getText().toString(), spinnerSets.getSelectedItem().toString(), Integer.parseInt(editTextCMC.getText().toString()), Integer.parseInt(editTextPower.getText().toString()), Integer.parseInt(editTextToughness.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Card Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(iIntent);
            }
        });
        //ADD CARDS
        addCard("Storm Crow", "7th Edition", 1, 2, 2);
        addCard("Alpha Tyrranax", "Scars of Mirrodin", 6, 5, 6);
    }


    public void addCard(String name, String set, int cmc, int power, int toughness) {
        Card tCard = new Card(name, /*id,*/ set, cmc, power, toughness);
        dbCardReference.child(name).setValue(tCard);

    }

}
