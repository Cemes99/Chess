package com.example.chess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chess.ListLaw.LawActivity;
import com.example.chess.PvP.SignInPvPActivity;

public class FirstPage extends AppCompatActivity implements View.OnClickListener {
    private Button start, howToPlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        start = findViewById(R.id.start);
        start.setOnClickListener(this);

        howToPlay = findViewById(R.id.how_to_play);
        howToPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == start) {
            Intent intent = new Intent(FirstPage.this, SignInPvPActivity.class);
            startActivity(intent);
        }
        if(v == howToPlay) {
            Intent intent = new Intent(FirstPage.this, LawActivity.class);
            startActivity(intent);
        }
    }
}
