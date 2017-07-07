package io.fuchsc.triceptional;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class foodPage extends AppCompatActivity {

    private EditText calNum;
    private EditText foodName;
    private EditText foodDiary;
    private EditText calTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            foodDiary.setText(savedInstanceState.getString("foodDiary"));
            calTotal.setText(savedInstanceState.getString("calTotal"));

        } else {
            // Probably initialize members with default values for a new instance
            foodDiary = (EditText) findViewById(R.id.foodDiary);
            calTotal = (EditText) findViewById(R.id.numAmount);
        }

        SharedPreferences sp =
                getSharedPreferences("Food",
                        Context.MODE_PRIVATE);
        if (sp.contains("foodDiary")) {
            foodDiary.setText(sp.getString("foodDiary", foodDiary.getText().toString()));
        }
        if (sp.contains("calTotal")) {
            calTotal.setText(sp.getString("calTotal",calTotal.getText().toString()));
        }

        calNum = (EditText) findViewById(R.id.calAmount);
        foodName = (EditText) findViewById(R.id.foodType);


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save all current
        savedInstanceState.putString("foodDiary", foodDiary.getText().toString());
        savedInstanceState.putString("calTotal", calTotal.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveData();
    }

    private void saveData() {
        SharedPreferences sp =
                getSharedPreferences("Food",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("foodDiary", foodDiary.getText().toString());
        editor.putString("calTotal", calTotal.getText().toString());
        editor.apply();
    }


    //update food diary when update button on nutrition page is pressed
    public void updateNutrition(View view) {
        int runningTotal = Integer.parseInt(calTotal.getText().toString());
        try {
            runningTotal = (Integer.parseInt(calNum.getText().toString())) + (Integer.parseInt(calTotal.getText().toString()));
        }
        catch (NumberFormatException e) {
            //do nothing
        }
        calTotal.setText(String.valueOf(runningTotal));
        foodDiary.setText(foodDiary.getText().toString() + "\n" + foodName.getText().toString() + " " +
                calNum.getText().toString() + "\n");
        saveData();
        calNum.setText(String.valueOf(0));
        foodName.setText("");
    }

    //when reset button is hit, voids all info in food diary
    public void resetAll(View view) {
        foodDiary.setText("");
        calTotal.setText(String.valueOf(0));
        saveData();
    }
}
