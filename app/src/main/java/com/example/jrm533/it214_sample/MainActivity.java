package com.example.jrm533.it214_sample;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity
        extends AppCompatActivity {
    @Override
    public void onConfigurationChanged(Configuration newConfig)  {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    private void setOrientationMessage() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView ratingView = (TextView) findViewById(R.id.ratingTextView);

        String rating = getIntent().getStringExtra("NextPageItem");
        ratingView.setText(rating);

        displayMessage();
        setOrientationMessage();

        Button genericBtn = findViewById(R.id.button);
        genericBtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.editText);
                TextView nameView = (TextView) findViewById(R.id.textView2);

                String yourName = name.getText().toString();
                String somethignToOutput = "You Entered: " + yourName;
                nameView.setText(somethignToOutput);

            }
        });
    }


    private void displayMessage() {
        String message = "please update your phone";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            message = "thanks!";
        }
        TextView thankYouTextView = findViewById(R.id.thankYouTextView);
        thankYouTextView.setText(message);

    }

    public void nextPage(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}
