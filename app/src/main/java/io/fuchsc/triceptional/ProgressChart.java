package io.fuchsc.triceptional;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import java.util.Calendar;


public class ProgressChart extends AppCompatActivity {

    EditText weight;
    EditText waist;
    EditText hips;
    EditText chest;
    EditText updateLog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_chart);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            waist.setText(savedInstanceState.getString("waist"));
            weight.setText(savedInstanceState.getString("weight"));
            hips.setText(savedInstanceState.getString("hips"));
            chest.setText(savedInstanceState.getString("chestP"));
            updateLog.setText(savedInstanceState.getString("log"));

        } else {
            // Probably initialize members with default values for a new instance
            waist = (EditText) findViewById(R.id.waist);
            hips = (EditText) findViewById(R.id.hips);
            chest = (EditText) findViewById(R.id.chest);
            weight = (EditText) findViewById(R.id.weight);
            updateLog = (EditText) findViewById(R.id.updateLog);

        }

        SharedPreferences sp =
                getSharedPreferences("Graph",
                        Context.MODE_PRIVATE);
        if (sp.contains("waist")) {
            waist.setText(sp.getString("waist", waist.getText().toString()));
        }
        if (sp.contains("weight")) {
            weight.setText(sp.getString("weight", weight.getText().toString()));
        }
        if (sp.contains("hips")) {
            hips.setText(sp.getString("hips", hips.getText().toString()));
        }
        if (sp.contains("chestP")) {
            chest.setText(sp.getString("chestP", chest.getText().toString()));
        }
        if (sp.contains("log")) {
            updateLog.setText(sp.getString("log", updateLog.getText().toString()));
        }

        updateLog.setMovementMethod(new ScrollingMovementMethod());


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save all current
        savedInstanceState.putString("waist", waist.getText().toString());
        savedInstanceState.putString("weight", weight.getText().toString());
        savedInstanceState.putString("hips", hips.getText().toString());
        savedInstanceState.putString("chestP", chest.getText().toString());
        savedInstanceState.putString("log", updateLog.getText().toString());


        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveData();
    }

    private void saveData() {
        SharedPreferences sp =
                getSharedPreferences("Graph",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("waist", waist.getText().toString());
        editor.putString("weight", weight.getText().toString());
        editor.putString("hips", hips.getText().toString());
        editor.putString("chest", chest.getText().toString());
        editor.putString("log", updateLog.getText().toString());


        editor.apply();
    }



    //When user is finished updating scores and hits button, will update chart/
    public void processUpdates(View view) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);

        String updateString = cal.getTime().toString();
        String stats = " WS : " + waist.getText().toString() + ", CH: " + chest.getText().toString() + ", HP: " +
                hips.getText().toString() + ", WT: " + weight.getText().toString();
        String oldLog = updateLog.getText().toString();
        updateLog.setText(oldLog + "\n" + updateString + "\n" + stats + "\n");
        saveData();

    }



}
