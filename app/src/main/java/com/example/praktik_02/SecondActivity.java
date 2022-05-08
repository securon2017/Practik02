package com.example.praktik_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT_KEY = "extra_text_key";
    private TextView textView;
    private Button btn_Upper;
    private  Button btn_Lower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView_Second_activity);
        Intent intent = getIntent();
        String text = intent.getStringExtra(EXTRA_TEXT_KEY);
        textView.setText(text);
    }

    public void setUpper(View view) {
        String text = textView.getText().toString();
        String upperText = text.toUpperCase(Locale.ROOT);
        textView.setText(upperText);
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_DATA, upperText);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void setLower(View view) {
        String text = textView.getText().toString();
        textView.setText(text.toLowerCase(Locale.ROOT));
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_DATA, text);
        setResult(RESULT_OK, intent);
        finish();
    }
}