package com.example.praktik_02;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "EXTRA_DATA";
    public static final String TAG = "MainActivity";
    public static final int REQUEST_CODE = 1;

    private EditText editText;
    private Button process;

    private final ActivityResultContract<String, String> startActivityContract =
            new ActivityResultContract<String, String>() {
                @NonNull
                @Override
                public Intent createIntent(@NonNull Context context, String input) {
                    Intent intent = new Intent(context,SecondActivity.class);
                    intent.putExtra(SecondActivity.EXTRA_TEXT_KEY, input);
                    return  intent;
                }

                @Override
                public String parseResult(int resultCode, @Nullable Intent intent) {
                    if(resultCode == Activity.RESULT_OK) {
                        return intent != null ? intent.getStringExtra(MainActivity.EXTRA_DATA) : null;
                    }
                    return null;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.inputText);
        process = findViewById(R.id.btn_Process);

        final ActivityResultLauncher<String> activityResultLauncher =
                registerForActivityResult(startActivityContract, new ActivityResultCallback<String>() {
                    @Override
                    public void onActivityResult(String result) {
                        editText.setText(result);
                    }
                });
        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                activityResultLauncher.launch(text);
            }
        });
    }
}