package com.example.jrm533.it214_sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity
        extends AppCompatActivity {

    private static final String FIRST_VISIT = "firstVisitToTheSite";
    private static final String MAIN_PREFS_FILE = "mainActivityPrefsFile";
    private static final String INTERNAL_NAME_FILE = "mainActivityNameFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BlankFragment.addFragment(getSupportFragmentManager());
        TextView ratingView = (TextView) findViewById(R.id.ratingTextView);

        String rating = getIntent().getStringExtra("NextPageItem");
        String rating2 = getIntent().getStringExtra("OtherPageItem");

        ratingView.setText(rating + " : " + rating2);

        displayMessage();
        loadSharedPrefs();

        try {
            String previouslyEnteredName = readInternalNameFile();

            if (previouslyEnteredName != null) {
                TextView nameView = (TextView) findViewById(R.id.textView2);
                nameView.setText(previouslyEnteredName);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        Button genericBtn = findViewById(R.id.button);
        genericBtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.editText);
                TextView nameView = (TextView) findViewById(R.id.textView2);

                String yourName = name.getText().toString();
                String somethignToOutput = "You Entered: " + yourName;
                nameView.setText(somethignToOutput);


                try (FileOutputStream fos = openFileOutput(INTERNAL_NAME_FILE, Context.MODE_PRIVATE)) {
                    fos.write(yourName.getBytes());
                } catch (Exception e) {

                }
            }
        });
    }

    private void loadSharedPrefs() {
        SharedPreferences prefs = getSharedPreferences(MAIN_PREFS_FILE, MODE_PRIVATE);
        boolean firstVisit = prefs.getBoolean(FIRST_VISIT, true);

        TextView welcomeTxtView = findViewById(R.id.welcomeTxtView);

        if (firstVisit) {
            welcomeTxtView.setText("Welcome!");
            prefs.edit().putBoolean(FIRST_VISIT, false).commit();
        } else {
            welcomeTxtView.setText("Welcome Back");
        }
    }


//    private void writeInternalFile (String data) throws IOException {
//        try (FileOutputStream fos = openFileOutput(INTERNAL_NAME_FILE, Context.MODE_PRIVATE)) {
//            fos.write(data.getBytes());
//        }
//    }

    private String readInternalNameFile () throws Exception {
        byte[] data = new byte[50];
        try (FileInputStream fis = openFileInput(INTERNAL_NAME_FILE)) {
            fis.read(data);
        }
        return new String(data);
    }

    private void displayMessage() {
        String message = "please update your phone";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            message = "thanks!";
        }
        TextView thankYouTextView = findViewById(R.id.thankYouTextView);
        thankYouTextView.setText(message);

    }

    private void setOrientationMessage() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

    }

    public void nextPage(View view) {
        String rating = getIntent().getStringExtra("NextPageItem");
        String rating2 = getIntent().getStringExtra("OtherPageItem");


        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("NextPageItem", rating);
        intent.putExtra("OtherPageItem", rating2);
        startActivity(intent);
    }

    public void otherPage(View view) {
        String rating = getIntent().getStringExtra("NextPageItem");
        String rating2 = getIntent().getStringExtra("OtherPageItem");

        Intent intent = new Intent(this, AnotherActivity.class);
        intent.putExtra("NextPageItem", rating);
        intent.putExtra("OtherPageItem", rating2);
        startActivity(intent);
    }

}
