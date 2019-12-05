package com.example.ass4;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText mealPrice;
    SeekBar seekbar;
    int quantity;
    TextView seekbarText;
    RadioGroup radioGroup;
    EditText totalPrice;
    ImageView imageView;
    CheckBox checkbox;
    Button btn;
    int meal_price;
    Double tip;
    Double tax;
    Double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        mealPrice = findViewById(R.id.mealPrice);
        seekbar = findViewById(R.id.seekbar);
        seekbarText = findViewById(R.id.seekbarText);
        radioGroup = findViewById(R.id.radioGroup);
        totalPrice = findViewById(R.id.totalPrice);
        imageView = findViewById(R.id.imageView);
        btn = findViewById(R.id.btn);
        checkbox = findViewById(R.id.checkbox);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(spinner.getSelectedItem().toString()){

                    case "Menu": mealPrice.setText("");
                                 break;
                    case "Burger": mealPrice.setText("10");
                                   imageView.setImageResource(R.drawable.burger);
                                   break;
                    case "Pizza": mealPrice.setText("20");
                                  imageView.setImageResource(R.drawable.pizza);
                                  break;
                    case "Cake": mealPrice.setText("30");
                                 imageView.setImageResource(R.drawable.cake);
                                 break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                quantity = progress;
                seekbarText.setText(String.valueOf(quantity));

                if(!mealPrice.getText().toString().isEmpty()){


                    meal_price = Integer.valueOf(mealPrice.getText().toString()) * quantity;
                    tax = 0.13 * (meal_price);
                    total = meal_price + tax;

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId){

                    case R.id.radio1: tip = 0.1 * meal_price;
                                      totalPrice.setText(String.valueOf(total + tip));
                                      break;

                    case R.id.radio2: tip = 0.15 * meal_price;
                        totalPrice.setText(String.valueOf(total + tip));
                        break;

                    case R.id.radio3: tip = 0.2 * meal_price;
                        totalPrice.setText(String.valueOf(total + tip));
                        break;

                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!totalPrice.getText().toString().isEmpty() && checkbox.isChecked())
                {
                    Toast.makeText(MainActivity.this, "Your order is successfully placed", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(MainActivity.this, "fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
