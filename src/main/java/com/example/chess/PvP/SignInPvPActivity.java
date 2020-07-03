package com.example.chess.PvP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chess.R;

public class SignInPvPActivity extends AppCompatActivity {
    private EditText name1, name2;
    private Button save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_pvp);

        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInPvPActivity.this, PvPActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("Name1", name1.getText().toString());
                bundle.putString("Name2", name2.getText().toString());

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
