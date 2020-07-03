package com.example.chess.ListLaw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chess.Pieces.Bishop;
import com.example.chess.Pieces.King;
import com.example.chess.Pieces.Knight;
import com.example.chess.Pieces.Pawn;
import com.example.chess.Pieces.Queen;
import com.example.chess.Pieces.Rook;
import com.example.chess.R;

import java.util.ArrayList;

public class LawAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Law> arrayList;

    public LawAdapter(Context context, ArrayList<Law> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Law getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.layout_law, parent, false);

        ImageView imageWhite = convertView.findViewById(R.id.imageWhite);
        ImageView imageBlack = convertView.findViewById(R.id.imageBlack);
        TextView textView = convertView.findViewById(R.id.text_law);

        if(arrayList.get(position).getPiece() instanceof King) {
            imageWhite.setImageResource(R.drawable.white_king);
            imageBlack.setImageResource(R.drawable.black_king);
            textView.setText(R.string.Law_Of_King);
        } else if(arrayList.get(position).getPiece() instanceof Queen) {
            imageWhite.setImageResource(R.drawable.white_queen);
            imageBlack.setImageResource(R.drawable.black_queen);
            textView.setText(R.string.Law_Of_Queen);
        } else if(arrayList.get(position).getPiece() instanceof Knight) {
            imageWhite.setImageResource(R.drawable.white_knight);
            imageBlack.setImageResource(R.drawable.black_knight);
            textView.setText(R.string.Law_Of_Knight);
        } else if(arrayList.get(position).getPiece() instanceof Bishop) {
            imageWhite.setImageResource(R.drawable.white_bishop);
            imageBlack.setImageResource(R.drawable.black_bishop);
            textView.setText(R.string.Law_Of_Bishop);
        } else if(arrayList.get(position).getPiece() instanceof Rook) {
            imageWhite.setImageResource(R.drawable.white_rook);
            imageBlack.setImageResource(R.drawable.black_rook);
            textView.setText(R.string.Law_Of_Rook);
        } else if(arrayList.get(position).getPiece() instanceof Pawn){
            imageWhite.setImageResource(R.drawable.white_pawn);
            imageBlack.setImageResource(R.drawable.black_pawn);
            textView.setText(R.string.Law_Of_Pawn);
        } else {
            textView.setText(R.string.Law_Of_Castle);
        }

        return convertView;
    }
}
