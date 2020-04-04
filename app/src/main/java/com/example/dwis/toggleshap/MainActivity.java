package com.example.dwis.toggleshap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnSelect;
    private ShapeSelectorView shapeSelector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shapeSelector = findViewById(R.id.shapeSelector);
        btnSelect = findViewById(R.id.button2);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You selected: " + shapeSelector.getSelectedShape(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
