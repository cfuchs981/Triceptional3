package io.fuchsc.triceptional;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;


public class MainActivity extends AppCompatActivity {


    private EditText A;
    private EditText L;
    private EditText S;
    private EditText C;
    private EditText B;

    private TabHost tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tab = (TabHost) findViewById(R.id.tabHost);
        tab.setup();
        TabHost.TabSpec ts = tab.newTabSpec("A");
        ts.setContent(R.id.A);
        ts.setIndicator("A");
        tab.addTab(ts);
        ts = tab.newTabSpec("L");
        ts.setContent(R.id.L);
        ts.setIndicator("L");
        tab.addTab(ts);
        ts = tab.newTabSpec("S");
        ts.setContent(R.id.S);
        ts.setIndicator("S");
        tab.addTab(ts);
        ts = tab.newTabSpec("C");
        ts.setContent(R.id.C);
        ts.setIndicator("C");
        tab.addTab(ts);
        ts = tab.newTabSpec("B");
        ts.setContent(R.id.B);
        ts.setIndicator("B");
        tab.addTab(ts);


        if (savedInstanceState != null) {
            // Restore value of members from saved state
            A.setText(savedInstanceState.getString("arms"));
            L.setText(savedInstanceState.getString("legs"));
            S.setText(savedInstanceState.getString("shoulders"));
            B.setText(savedInstanceState.getString("back"));
            C.setText(savedInstanceState.getString("chest"));

        } else {
            // Probably initialize members with default values for a new instance
            A= (EditText) findViewById(R.id.firstA);
            L= (EditText) findViewById(R.id.secondL);
            B= (EditText) findViewById(R.id.fifthB);
            C= (EditText) findViewById(R.id.thirdC);
            S= (EditText) findViewById(R.id.fourthS);
        }

        SharedPreferences sp =
                getSharedPreferences("Data",
                        Context.MODE_PRIVATE);
        if (sp.contains("arms")) {
            A.setText(sp.getString("arms", A.getText().toString()));
        }
        if (sp.contains("legs")) {
            L.setText(sp.getString("legs", L.getText().toString()));
        }
        if (sp.contains("back")) {
            B.setText(sp.getString("back", B.getText().toString()));
        }
        if (sp.contains("chest")) {
            C.setText(sp.getString("chest", C.getText().toString()));
        }
        if (sp.contains("shoulders")) {
            S.setText(sp.getString("shoulders", S.getText().toString()));
        }

    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save all current
        savedInstanceState.putString("arms", A.getText().toString());
        savedInstanceState.putString("legs", L.getText().toString());
        savedInstanceState.putString("shoulders", S.getText().toString());
        savedInstanceState.putString("back", B.getText().toString());
        savedInstanceState.putString("chest", C.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveData();
    }

    private void saveData() {
        SharedPreferences sp =
                getSharedPreferences("Data",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("arms", A.getText().toString());
        editor.putString("legs", L.getText().toString());
        editor.putString("shoulders", S.getText().toString());
        editor.putString("back", B.getText().toString());
        editor.putString("chest", C.getText().toString());
        editor.apply();
    }

    //when the Nutrition button is hit from the main activity, redirect to food diary
    public void viewNutrition(View view) {
        Intent intent = new Intent(this, foodPage.class);
        startActivity(intent);
    }

    //when View Progress button is hit in main, redirect to progress page
    public void progressScreen(View view) {
        Intent intent = new Intent(this, ProgressChart.class);
        startActivity(intent);
    }

   public void saveMyChanges(View view) {
       A= (EditText) findViewById(R.id.firstA);
       L= (EditText) findViewById(R.id.secondL);
        B= (EditText) findViewById(R.id.fifthB);
        C= (EditText) findViewById(R.id.thirdC);
        S= (EditText) findViewById(R.id.fourthS);
        saveData();
    }

}
