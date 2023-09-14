package com.example.lab3;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mButton = findViewById(R.id.button);
        Button mButton1 = findViewById(R.id.button1);
        TextView mTextView = findViewById(R.id.textView);
        mTextView.setText((String)getIntent().getSerializableExtra("message"));
        mButton.setOnClickListener(this);
        mButton1.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            mEditText = findViewById(R.id.editText);
            String message = mEditText.getText().toString();
            Intent intent = new Intent(getApplicationContext(), ActivityTwo.class);
            intent.putExtra("message",message);
            startActivity(intent);
        }
        if (view.getId() == R.id.button1) {
            Intent intent = new Intent(getApplicationContext(), ActivityThree.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}