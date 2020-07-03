package com.example.chess.ListLaw;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chess.Pieces.Bishop;
import com.example.chess.Pieces.King;
import com.example.chess.Pieces.Knight;
import com.example.chess.Pieces.Pawn;
import com.example.chess.Pieces.Queen;
import com.example.chess.Pieces.Rook;
import com.example.chess.R;

import java.util.ArrayList;

public class LawActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Law> arrayList = new ArrayList<>();
    LawAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);
        listView = findViewById(R.id.list_law);

        initData();
    }

    private void initData() {
        arrayList.add(new Law(new King(true)));
        arrayList.add(new Law(new Queen(true)));
        arrayList.add(new Law(new Knight(true)));
        arrayList.add(new Law(new Bishop(true)));
        arrayList.add(new Law(new Rook(true)));
        arrayList.add(new Law(new Pawn(true)));
        arrayList.add(new Law(null));

        adapter = new LawAdapter(this, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
