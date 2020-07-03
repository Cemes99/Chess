package com.example.chess.PvP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chess.Coordinates;
import com.example.chess.FirstPage;
import com.example.chess.Pieces.Bishop;
import com.example.chess.Pieces.King;
import com.example.chess.Pieces.Knight;
import com.example.chess.Pieces.Pawn;
import com.example.chess.Pieces.Piece;
import com.example.chess.Pieces.Queen;
import com.example.chess.Pieces.Rook;
import com.example.chess.Position;
import com.example.chess.R;

import java.util.ArrayList;
import java.util.Locale;

public class PvPActivity extends AppCompatActivity implements View.OnClickListener {
    // allow move of choices piece
    ArrayList<Coordinates> allowToMove = new ArrayList<>();

    // display pieces
    private TextView[][] squarePieces = new TextView[8][8];
    // display background
    private TextView[][] squareBackground = new TextView[8][8];

    private boolean WhiteTurn;

    // save previous board to undo
    private Position[][] Board = new Position[8][8];
    private Position[][] BoardPrevious = new Position[8][8];

    // on click position
    private Coordinates clickedPosition = new Coordinates();

    // layout ennoble
    private LinearLayout pawnChoices;
    private Button pawnQueen, pawnBishop, pawnKnight, pawnRock;

    private TextView gameOver;
    private boolean canUndo = false;

    // all pieces
    private Piece whiteKing, whiteQueen,
            whiteBishop1, whiteBishop2, whiteKnight1, whiteKnight2, whiteRook1, whiteRook2,
            whitePawn1, whitePawn2, whitePawn3, whitePawn4, whitePawn5, whitePawn6, whitePawn7, whitePawn8;
    private Piece blackKing, blackQueen,
            blackBishop1, blackBishop2, blackKnight1, blackKnight2, blackRook1, blackRook2,
            blackPawn1, blackPawn2, blackPawn3, blackPawn4, blackPawn5, blackPawn6, blackPawn7, blackPawn8;

    // previous choices
    private Piece previousPiece;
    private Coordinates previousCoordinate;

    // count down timer
    private TextView timeLeftWhite, timeLeftBlack;
    private long COUNT_DOWN_WHITE = 300000, COUNT_DOWN_BLACK = 300000;
    private CountDownTimer timerWhite, timerBlack;

    // player name
    private TextView player1, player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvp);

        connectView();
        initBoard();
    }

    private void initBoard() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        player1.setText(bundle.getString("Name1"));
        player2.setText(bundle.getString("Name2"));

        whiteKing = new King(true);
        blackKing = new King(false);

        whiteQueen = new Queen(true);
        blackQueen = new Queen(false);

        whiteBishop1 = new Bishop(true);
        whiteBishop2 = new Bishop(true);
        blackBishop1 = new Bishop(false);
        blackBishop2 = new Bishop(false);

        whiteKnight1 = new Knight(true);
        whiteKnight2 = new Knight(true);
        blackKnight1 = new Knight(false);
        blackKnight2 = new Knight(false);

        whiteRook1 = new Rook(true);
        whiteRook2 = new Rook(true);
        blackRook1 = new Rook(false);
        blackRook2 = new Rook(false);

        whitePawn1 = new Pawn(true);
        whitePawn2 = new Pawn(true);
        whitePawn3 = new Pawn(true);
        whitePawn4 = new Pawn(true);
        whitePawn5 = new Pawn(true);
        whitePawn6 = new Pawn(true);
        whitePawn7 = new Pawn(true);
        whitePawn8 = new Pawn(true);

        blackPawn1 = new Pawn(false);
        blackPawn2 = new Pawn(false);
        blackPawn3 = new Pawn(false);
        blackPawn4 = new Pawn(false);
        blackPawn5 = new Pawn(false);
        blackPawn6 = new Pawn(false);
        blackPawn7 = new Pawn(false);
        blackPawn8 = new Pawn(false);

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Board[i][j] = new Position(null);
                BoardPrevious[i][j] = new Position(null);
            }
        }

        Board[7][0].setPiece(whiteRook1);
        Board[7][1].setPiece(whiteKnight1);
        Board[7][2].setPiece(whiteBishop1);
        Board[7][3].setPiece(whiteQueen);
        Board[7][4].setPiece(whiteKing);
        Board[7][5].setPiece(whiteBishop2);
        Board[7][6].setPiece(whiteKnight2);
        Board[7][7].setPiece(whiteRook2);

        Board[6][0].setPiece(whitePawn1);
        Board[6][1].setPiece(whitePawn2);
        Board[6][2].setPiece(whitePawn3);
        Board[6][3].setPiece(whitePawn4);
        Board[6][4].setPiece(whitePawn5);
        Board[6][5].setPiece(whitePawn6);
        Board[6][6].setPiece(whitePawn7);
        Board[6][7].setPiece(whitePawn8);

        Board[0][0].setPiece(blackRook1);
        Board[0][1].setPiece(blackKnight1);
        Board[0][2].setPiece(blackBishop1);
        Board[0][3].setPiece(blackQueen);
        Board[0][4].setPiece(blackKing);
        Board[0][5].setPiece(blackBishop2);
        Board[0][6].setPiece(blackKnight2);
        Board[0][7].setPiece(blackRook2);

        Board[1][0].setPiece(blackPawn1);
        Board[1][1].setPiece(blackPawn2);
        Board[1][2].setPiece(blackPawn3);
        Board[1][3].setPiece(blackPawn4);
        Board[1][4].setPiece(blackPawn5);
        Board[1][5].setPiece(blackPawn6);
        Board[1][6].setPiece(blackPawn7);
        Board[1][7].setPiece(blackPawn8);

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(Board[i][j] == null) BoardPrevious[i][j] = null;
                else BoardPrevious[i][j].setPiece(Board[i][j].getPiece());
            }
        }

        WhiteTurn = true;

        pawnChoices.setVisibility(View.INVISIBLE);
        gameOver.setVisibility(View.INVISIBLE);

        defaultBackground();
        displayPieces();
    }

    public void connectView() {
        player1 = findViewById(R.id.player_1);
        player2 = findViewById(R.id.player_2);

        squarePieces[0][0] = findViewById(R.id.S00);
        squareBackground[0][0] = findViewById(R.id.S000);
        squarePieces[0][1] = findViewById(R.id.S01);
        squareBackground[0][1] = findViewById(R.id.S001);
        squarePieces[0][2] = findViewById(R.id.S02);
        squareBackground[0][2] = findViewById(R.id.S002);
        squarePieces[0][3] = findViewById(R.id.S03);
        squareBackground[0][3] = findViewById(R.id.S003);
        squarePieces[0][4] = findViewById(R.id.S04);
        squareBackground[0][4] = findViewById(R.id.S004);
        squarePieces[0][5] = findViewById(R.id.S05);
        squareBackground[0][5] = findViewById(R.id.S005);
        squarePieces[0][6] = findViewById(R.id.S06);
        squareBackground[0][6] = findViewById(R.id.S006);
        squarePieces[0][7] = findViewById(R.id.S07);
        squareBackground[0][7] = findViewById(R.id.S007);

        squarePieces[1][0] = findViewById(R.id.S10);
        squareBackground[1][0] = findViewById(R.id.S010);
        squarePieces[1][1] = findViewById(R.id.S11);
        squareBackground[1][1] = findViewById(R.id.S011);
        squarePieces[1][2] = findViewById(R.id.S12);
        squareBackground[1][2] = findViewById(R.id.S012);
        squarePieces[1][3] = findViewById(R.id.S13);
        squareBackground[1][3] = findViewById(R.id.S013);
        squarePieces[1][4] = findViewById(R.id.S14);
        squareBackground[1][4] = findViewById(R.id.S014);
        squarePieces[1][5] = findViewById(R.id.S15);
        squareBackground[1][5] = findViewById(R.id.S015);
        squarePieces[1][6] = findViewById(R.id.S16);
        squareBackground[1][6] = findViewById(R.id.S016);
        squarePieces[1][7] = findViewById(R.id.S17);
        squareBackground[1][7] = findViewById(R.id.S017);

        squarePieces[2][0] = findViewById(R.id.S20);
        squareBackground[2][0] = findViewById(R.id.S020);
        squarePieces[2][1] = findViewById(R.id.S21);
        squareBackground[2][1] = findViewById(R.id.S021);
        squarePieces[2][2] = findViewById(R.id.S22);
        squareBackground[2][2] = findViewById(R.id.S022);
        squarePieces[2][3] = findViewById(R.id.S23);
        squareBackground[2][3] = findViewById(R.id.S023);
        squarePieces[2][4] = findViewById(R.id.S24);
        squareBackground[2][4] = findViewById(R.id.S024);
        squarePieces[2][5] = findViewById(R.id.S25);
        squareBackground[2][5] = findViewById(R.id.S025);
        squarePieces[2][6] = findViewById(R.id.S26);
        squareBackground[2][6] = findViewById(R.id.S026);
        squarePieces[2][7] = findViewById(R.id.S27);
        squareBackground[2][7] = findViewById(R.id.S027);

        squarePieces[3][0] = findViewById(R.id.S30);
        squareBackground[3][0] = findViewById(R.id.S030);
        squarePieces[3][1] = findViewById(R.id.S31);
        squareBackground[3][1] = findViewById(R.id.S031);
        squarePieces[3][2] = findViewById(R.id.S32);
        squareBackground[3][2] = findViewById(R.id.S032);
        squarePieces[3][3] = findViewById(R.id.S33);
        squareBackground[3][3] = findViewById(R.id.S033);
        squarePieces[3][4] = findViewById(R.id.S34);
        squareBackground[3][4] = findViewById(R.id.S034);
        squarePieces[3][5] = findViewById(R.id.S35);
        squareBackground[3][5] = findViewById(R.id.S035);
        squarePieces[3][6] = findViewById(R.id.S36);
        squareBackground[3][6] = findViewById(R.id.S036);
        squarePieces[3][7] = findViewById(R.id.S37);
        squareBackground[3][7] = findViewById(R.id.S037);

        squarePieces[4][0] = findViewById(R.id.S40);
        squareBackground[4][0] = findViewById(R.id.S040);
        squarePieces[4][1] = findViewById(R.id.S41);
        squareBackground[4][1] = findViewById(R.id.S041);
        squarePieces[4][2] = findViewById(R.id.S42);
        squareBackground[4][2] = findViewById(R.id.S042);
        squarePieces[4][3] = findViewById(R.id.S43);
        squareBackground[4][3] = findViewById(R.id.S043);
        squarePieces[4][4] = findViewById(R.id.S44);
        squareBackground[4][4] = findViewById(R.id.S044);
        squarePieces[4][5] = findViewById(R.id.S45);
        squareBackground[4][5] = findViewById(R.id.S045);
        squarePieces[4][6] = findViewById(R.id.S46);
        squareBackground[4][6] = findViewById(R.id.S046);
        squarePieces[4][7] = findViewById(R.id.S47);
        squareBackground[4][7] = findViewById(R.id.S047);

        squarePieces[5][0] = findViewById(R.id.S50);
        squareBackground[5][0] = findViewById(R.id.S050);
        squarePieces[5][1] = findViewById(R.id.S51);
        squareBackground[5][1] = findViewById(R.id.S051);
        squarePieces[5][2] = findViewById(R.id.S52);
        squareBackground[5][2] = findViewById(R.id.S052);
        squarePieces[5][3] = findViewById(R.id.S53);
        squareBackground[5][3] = findViewById(R.id.S053);
        squarePieces[5][4] = findViewById(R.id.S54);
        squareBackground[5][4] = findViewById(R.id.S054);
        squarePieces[5][5] = findViewById(R.id.S55);
        squareBackground[5][5] = findViewById(R.id.S055);
        squarePieces[5][6] = findViewById(R.id.S56);
        squareBackground[5][6] = findViewById(R.id.S056);
        squarePieces[5][7] = findViewById(R.id.S57);
        squareBackground[5][7] = findViewById(R.id.S057);

        squarePieces[6][0] = findViewById(R.id.S60);
        squareBackground[6][0] = findViewById(R.id.S060);
        squarePieces[6][1] = findViewById(R.id.S61);
        squareBackground[6][1] = findViewById(R.id.S061);
        squarePieces[6][2] = findViewById(R.id.S62);
        squareBackground[6][2] = findViewById(R.id.S062);
        squarePieces[6][3] = findViewById(R.id.S63);
        squareBackground[6][3] = findViewById(R.id.S063);
        squarePieces[6][4] = findViewById(R.id.S64);
        squareBackground[6][4] = findViewById(R.id.S064);
        squarePieces[6][5] = findViewById(R.id.S65);
        squareBackground[6][5] = findViewById(R.id.S065);
        squarePieces[6][6] = findViewById(R.id.S66);
        squareBackground[6][6] = findViewById(R.id.S066);
        squarePieces[6][7] = findViewById(R.id.S67);
        squareBackground[6][7] = findViewById(R.id.S067);

        squarePieces[7][0] = findViewById(R.id.S70);
        squareBackground[7][0] = findViewById(R.id.S070);
        squarePieces[7][1] = findViewById(R.id.S71);
        squareBackground[7][1] = findViewById(R.id.S071);
        squarePieces[7][2] = findViewById(R.id.S72);
        squareBackground[7][2] = findViewById(R.id.S072);
        squarePieces[7][3] = findViewById(R.id.S73);
        squareBackground[7][3] = findViewById(R.id.S073);
        squarePieces[7][4] = findViewById(R.id.S74);
        squareBackground[7][4] = findViewById(R.id.S074);
        squarePieces[7][5] = findViewById(R.id.S75);
        squareBackground[7][5] = findViewById(R.id.S075);
        squarePieces[7][6] = findViewById(R.id.S76);
        squareBackground[7][6] = findViewById(R.id.S076);
        squarePieces[7][7] = findViewById(R.id.S77);
        squareBackground[7][7] = findViewById(R.id.S077);

        gameOver = findViewById(R.id.game_over);

        pawnChoices = findViewById(R.id.pawn_choices);
        pawnQueen = findViewById(R.id.pawn_queen);
        pawnBishop = findViewById(R.id.pawn_bishop);
        pawnKnight = findViewById(R.id.pawn_knight);
        pawnRock = findViewById(R.id.pawn_rook);

        pawnQueen.setOnClickListener(this);
        pawnBishop.setOnClickListener(this);
        pawnKnight.setOnClickListener(this);
        pawnRock.setOnClickListener(this);

        timeLeftWhite = findViewById(R.id.white_timer);
        timeLeftBlack = findViewById(R.id.black_timer);
    }

    private void defaultBackground() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if((i + j) % 2 == 0) squareBackground[i][j].setBackgroundResource(R.color.colorBoardDark);
                else squareBackground[i][j].setBackgroundResource(R.color.colorBoardLight);
            }
        }
    }

    private void displayPieces() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Piece p = Board[i][j].getPiece();

                if(p != null) {
                    int x;

                    if(p instanceof King) x = 0;
                    else if(p instanceof Queen) x = 1;
                    else if(p instanceof Bishop) x = 2;
                    else if(p instanceof Knight) x = 3;
                    else if(p instanceof Rook) x = 4;
                    else x = 5;

                    switch (x) {
                        case 0: {
                            if(p.isWhite()) squarePieces[i][j].setBackgroundResource(R.drawable.white_king);
                            else squarePieces[i][j].setBackgroundResource(R.drawable.black_king_reverse);
                        } break;
                        case 1: {
                            if(p.isWhite()) squarePieces[i][j].setBackgroundResource(R.drawable.white_queen);
                            else squarePieces[i][j].setBackgroundResource(R.drawable.black_queen_reverse);
                        } break;
                        case 2: {
                            if(p.isWhite()) squarePieces[i][j].setBackgroundResource(R.drawable.white_bishop);
                            else squarePieces[i][j].setBackgroundResource(R.drawable.black_bishop_reverse);
                        } break;
                        case 3: {
                            if(p.isWhite()) squarePieces[i][j].setBackgroundResource(R.drawable.white_knight);
                            else squarePieces[i][j].setBackgroundResource(R.drawable.black_knight_reverse);
                        } break;
                        case 4: {
                            if(p.isWhite()) squarePieces[i][j].setBackgroundResource(R.drawable.white_rook);
                            else squarePieces[i][j].setBackgroundResource(R.drawable.black_rook_reverse);
                        } break;
                        case 5: {
                            if(p.isWhite()) squarePieces[i][j].setBackgroundResource(R.drawable.white_pawn);
                            else squarePieces[i][j].setBackgroundResource(R.drawable.black_pawn_reverse);
                        } break;
                    }
                } else {
                    squarePieces[i][j].setBackgroundResource(0);
                }
            }
        }
    }

    private void movePiece(Coordinates start, Coordinates target, Piece piece) {
        savePrevious();

        // check king move
        if(Board[start.getY()][start.getX()].getPiece() instanceof King) {
            ((King) Board[start.getY()][start.getX()].getPiece()).setMoved(true);
        }

        // check rook move
        if(Board[start.getY()][start.getX()].getPiece() instanceof Rook) {
            ((Rook) Board[start.getY()][start.getX()].getPiece()).setMoved(true);
        }

        // artificial castling
        if(Board[start.getY()][start.getX()].getPiece() instanceof King &&
                Math.abs(start.getX() - target.getX()) == 2) {

            Board[start.getY()][start.getX()] = new Position(null);
            Board[target.getY()][target.getX()] = new Position(piece);

            if(start.getX() - target.getX() == 2) {
                target.setX(start.getX() - 1);
                start.setX(0);
                movePiece(start, target, Board[start.getY()][0].getPiece());

            } else if(target.getX() - start.getX() == 2) {
                target.setX(start.getX() + 1);
                start.setX(7);
                movePiece(start, target, Board[start.getY()][7].getPiece());
            }

            canUndo = true;
            return ;
        }

        // check game over
        if(Board[target.getY()][target.getX()].getPiece() instanceof King) {
            Board[start.getY()][start.getX()] = new Position(null);
            Board[target.getY()][target.getX()] = new Position(piece);

            GameOver(!WhiteTurn);
        } else {
            Board[start.getY()][start.getX()] = new Position(null);
            Board[target.getY()][target.getX()] = new Position(piece);

            checkEnnoble();

            clearLastChoice();
            displayPieces();
            defaultBackground();

            canUndo = true;

            WhiteTurn = !WhiteTurn;
            startTimer();
        }
    }

    private void GameOver(boolean isWhiteLose) {
        clearLastChoice();
        displayPieces();
        defaultBackground();

        canUndo = false;
        if(timerWhite != null) timerWhite.cancel();
        if(timerBlack != null) timerBlack.cancel();

        if(isWhiteLose) gameOver.setText(player2.getText() + " Win !!");
        else gameOver.setText(player1.getText() + " Win !!");
        gameOver.setVisibility(View.VISIBLE);
    }

    private void showAllAllow(int y, int x) {
        Piece piece = Board[y][x].getPiece();
        allowToMove = piece.allowToMoves(new Coordinates(y, x), Board);

        for(Coordinates c : allowToMove) {
            squareBackground[c.getY()][c.getX()].setBackgroundResource(R.drawable.selected_background);
        }
    }

    private void clearLastChoice() {
        previousPiece = null;
        previousCoordinate = null;
        allowToMove.clear();
    }

    private void kingInDanger() {
        ArrayList<Coordinates> arrayList;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(Board[i][j].getPiece() != null) {
                    arrayList = Board[i][j].getPiece().allowToMoves(new Coordinates(i, j), Board);

                    for(Coordinates c : arrayList) {
                        if(Board[c.getY()][c.getX()].getPiece() instanceof King)
                            squareBackground[c.getY()][c.getX()].setBackgroundResource(R.color.colorKingInDanger);
                    }
                }
            }
        }
    }

    private void checkEnnoble() {
        for(int i = 0; i < 8; i++) {
            if(Board[0][i].getPiece() instanceof Pawn && Board[0][i].getPiece().isWhite()) {
                pawnChoices.setVisibility(View.VISIBLE);
                return ;
            }
        }

        for(int i = 0; i < 8; i++) {
            if(Board[7][i].getPiece() instanceof Pawn && !Board[0][i].getPiece().isWhite()) {
                pawnChoices.setVisibility(View.VISIBLE);
                return ;
            }
        }
    }

    private void undo() {
        if(canUndo) {
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    Board[i][j] = BoardPrevious[i][j];
                }
            }

            WhiteTurn = !WhiteTurn;
            clearLastChoice();
            defaultBackground();
            displayPieces();
        }
    }

    private void savePrevious() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                BoardPrevious[i][j] = Board[i][j];
            }
        }
    }

    private void startTimer() {
        if(WhiteTurn) {
            if(timerBlack != null) timerBlack.cancel();
            timerWhite = new CountDownTimer(COUNT_DOWN_WHITE, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    COUNT_DOWN_WHITE = millisUntilFinished;
                    updateCountDown();
                }

                @Override
                public void onFinish() {
                    GameOver(WhiteTurn);
                }
            }.start();
        } else {
            if(timerWhite != null) timerWhite.cancel();
            timerBlack = new CountDownTimer(COUNT_DOWN_BLACK, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    COUNT_DOWN_BLACK = millisUntilFinished;
                    updateCountDown();
                }

                @Override
                public void onFinish() {
                    GameOver(WhiteTurn);
                }
            }.start();
        }
    }

    private void updateCountDown() {
        if(WhiteTurn) {
            int minutes = (int) COUNT_DOWN_WHITE / 1000 / 60;
            int seconds = (int) COUNT_DOWN_WHITE / 1000 % 60;
            String timeLeft =
                    String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            timeLeftWhite.setText(timeLeft);
        } else {
            int minutes = (int) COUNT_DOWN_BLACK / 1000 / 60;
            int seconds = (int) COUNT_DOWN_BLACK / 1000 % 60;
            String timeLeft =
                    String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            timeLeftBlack.setText(timeLeft);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.S00: {
                clickedPosition = new Coordinates(0, 0);
            } break;
            case R.id.S01: {
                clickedPosition = new Coordinates(0, 1);
            } break;
            case R.id.S02: {
                clickedPosition = new Coordinates(0, 2);
            } break;
            case R.id.S03: {
                clickedPosition = new Coordinates(0, 3);
            } break;
            case R.id.S04: {
                clickedPosition = new Coordinates(0, 4);
            } break;
            case R.id.S05: {
                clickedPosition = new Coordinates(0, 5);
            } break;
            case R.id.S06: {
                clickedPosition = new Coordinates(0, 6);
            } break;
            case R.id.S07: {
                clickedPosition = new Coordinates(0, 7);
            } break;
            case R.id.S10: {
                clickedPosition = new Coordinates(1, 0);
            } break;
            case R.id.S11: {
                clickedPosition = new Coordinates(1, 1);
            } break;
            case R.id.S12: {
                clickedPosition = new Coordinates(1, 2);
            } break;
            case R.id.S13: {
                clickedPosition = new Coordinates(1, 3);;
            } break;
            case R.id.S14: {
                clickedPosition = new Coordinates(1, 4);
            } break;
            case R.id.S15: {
                clickedPosition = new Coordinates(1, 5);
            } break;
            case R.id.S16: {
                clickedPosition = new Coordinates(1, 6);
            } break;
            case R.id.S17: {
                clickedPosition = new Coordinates(1, 7);
            } break;
            case R.id.S20: {
                clickedPosition = new Coordinates(2, 0);
            } break;
            case R.id.S21: {
                clickedPosition = new Coordinates(2, 1);
            } break;
            case R.id.S22: {
                clickedPosition = new Coordinates(2, 2);
            } break;
            case R.id.S23: {
                clickedPosition = new Coordinates(2, 3);
            } break;
            case R.id.S24: {
                clickedPosition = new Coordinates(2, 4);
            } break;
            case R.id.S25: {
                clickedPosition = new Coordinates(2, 5);
            } break;
            case R.id.S26: {
                clickedPosition = new Coordinates(2, 6);
            } break;
            case R.id.S27: {
                clickedPosition = new Coordinates(2, 7);
            } break;
            case R.id.S30: {
                clickedPosition = new Coordinates(3, 0);
            } break;
            case R.id.S31: {
                clickedPosition = new Coordinates(3, 1);
            } break;
            case R.id.S32: {
                clickedPosition = new Coordinates(3, 2);
            } break;
            case R.id.S33: {
                clickedPosition = new Coordinates(3, 3);
            } break;
            case R.id.S34: {
                clickedPosition = new Coordinates(3, 4);
            } break;
            case R.id.S35: {
                clickedPosition = new Coordinates(3, 5);
            } break;
            case R.id.S36: {
                clickedPosition = new Coordinates(3, 6);
            } break;
            case R.id.S37: {
                clickedPosition = new Coordinates(3, 7);
            } break;
            case R.id.S40: {
                clickedPosition = new Coordinates(4, 0);
            } break;
            case R.id.S41: {
                clickedPosition = new Coordinates(4, 1);
            } break;
            case R.id.S42: {
                clickedPosition = new Coordinates(4, 2);
            } break;
            case R.id.S43: {
                clickedPosition = new Coordinates(4, 3);
            } break;
            case R.id.S44: {
                clickedPosition = new Coordinates(4, 4);
            } break;
            case R.id.S45: {
                clickedPosition = new Coordinates(4, 5);
            } break;
            case R.id.S46: {
                clickedPosition = new Coordinates(4, 6);
            } break;
            case R.id.S47: {
                clickedPosition = new Coordinates(4, 7);
            } break;
            case R.id.S50: {
                clickedPosition = new Coordinates(5, 0);
            } break;
            case R.id.S51: {
                clickedPosition = new Coordinates(5, 1);
            } break;
            case R.id.S52: {
                clickedPosition = new Coordinates(5, 2);
            } break;
            case R.id.S53: {
                clickedPosition = new Coordinates(5, 3);
            } break;
            case R.id.S54: {
                clickedPosition = new Coordinates(5, 4);
            } break;
            case R.id.S55: {
                clickedPosition = new Coordinates(5, 5);
            } break;
            case R.id.S56: {
                clickedPosition = new Coordinates(5, 6);
            } break;
            case R.id.S57: {
                clickedPosition = new Coordinates(5, 7);
            } break;
            case R.id.S60: {
                clickedPosition = new Coordinates(6, 0);
            } break;
            case R.id.S61: {
                clickedPosition = new Coordinates(6, 1);
            } break;
            case R.id.S62: {
                clickedPosition = new Coordinates(6, 2);
            } break;
            case R.id.S63: {
                clickedPosition = new Coordinates(6, 3);
            } break;
            case R.id.S64: {
                clickedPosition = new Coordinates(6, 4);
            } break;
            case R.id.S65: {
                clickedPosition = new Coordinates(6, 5);
            } break;
            case R.id.S66: {
                clickedPosition = new Coordinates(6, 6);
            } break;
            case R.id.S67: {
                clickedPosition = new Coordinates(6, 7);
            } break;
            case R.id.S70: {
                clickedPosition = new Coordinates(7, 0);
            } break;
            case R.id.S71: {
                clickedPosition = new Coordinates(7, 1);
            } break;
            case R.id.S72: {
                clickedPosition = new Coordinates(7, 2);
            } break;
            case R.id.S73: {
                clickedPosition = new Coordinates(7, 3);
            } break;
            case R.id.S74: {
                clickedPosition = new Coordinates(7, 4);
            } break;
            case R.id.S75: {
                clickedPosition = new Coordinates(7, 5);
            } break;
            case R.id.S76: {
                clickedPosition = new Coordinates(7, 6);
            } break;
            case R.id.S77: {
                clickedPosition = new Coordinates(7, 7);
            } break;

            case R.id.pawn_queen: {
                for(int i = 0; i < 8; i++) {
                    if(Board[0][i].getPiece() instanceof Pawn && Board[0][i].getPiece().isWhite()) {
                        Board[0][i].setPiece(new Queen(true));
                    }
                }

                for(int i = 0; i < 8; i++) {
                    if(Board[7][i].getPiece() instanceof Pawn && !Board[0][i].getPiece().isWhite()) {
                        Board[0][i].setPiece(new Queen(false));
                    }
                }

                pawnChoices.setVisibility(View.INVISIBLE);

                clearLastChoice();
                displayPieces();
                defaultBackground();
            } break;
            case R.id.pawn_bishop: {
                for(int i = 0; i < 8; i++) {
                    if(Board[0][i].getPiece() instanceof Pawn && Board[0][i].getPiece().isWhite()) {
                        Board[0][i].setPiece(new Bishop(true));
                    }
                }

                for(int i = 0; i < 8; i++) {
                    if(Board[7][i].getPiece() instanceof Pawn && !Board[0][i].getPiece().isWhite()) {
                        Board[0][i].setPiece(new Bishop(false));
                    }
                }

                pawnChoices.setVisibility(View.INVISIBLE);

                clearLastChoice();
                displayPieces();
                defaultBackground();
            } break;
            case R.id.pawn_knight: {
                for(int i = 0; i < 8; i++) {
                    if(Board[0][i].getPiece() instanceof Pawn && Board[0][i].getPiece().isWhite()) {
                        Board[0][i].setPiece(new Knight(true));
                    }
                }

                for(int i = 0; i < 8; i++) {
                    if(Board[7][i].getPiece() instanceof Pawn && !Board[0][i].getPiece().isWhite()) {
                        Board[0][i].setPiece(new Knight(false));
                    }
                }

                pawnChoices.setVisibility(View.INVISIBLE);

                clearLastChoice();
                displayPieces();
                defaultBackground();
            } break;
            case R.id.pawn_rook: {
                for(int i = 0; i < 8; i++) {
                    if(Board[0][i].getPiece() instanceof Rook && Board[0][i].getPiece().isWhite()) {
                        Board[0][i].setPiece(new Knight(true));
                    }
                }

                for(int i = 0; i < 8; i++) {
                    if(Board[7][i].getPiece() instanceof Rook && !Board[0][i].getPiece().isWhite()) {
                        Board[0][i].setPiece(new Knight(false));
                    }
                }

                pawnChoices.setVisibility(View.INVISIBLE);

                clearLastChoice();
                displayPieces();
                defaultBackground();
            } break;

            case R.id.undo_button: {
                undo();
                return ;
            }
            case R.id.home_button: {
                Intent intent = new Intent(PvPActivity.this, FirstPage.class);
                startActivity(intent);

                return ;
            }
            case R.id.reset_button: {
                initBoard();
                COUNT_DOWN_WHITE = 300000;
                COUNT_DOWN_BLACK = 300000;
                timeLeftWhite.setText("05:00");
                timeLeftBlack.setText("05:00");
                return ;
            }
        }

        int x = clickedPosition.getX();
        int y = clickedPosition.getY();

        defaultBackground();

        if(WhiteTurn) {
            if(Board[y][x].getPiece() != null) {
                if(Board[y][x].getPiece().isWhite()) {
                    showAllAllow(y, x);

                    previousPiece = Board[y][x].getPiece();
                    previousCoordinate = new Coordinates(y, x);
                    allowToMove = previousPiece.allowToMoves(previousCoordinate, Board);
                } else {
                    if(!allowToMove.isEmpty()) {
                        for(Coordinates c : allowToMove) {
                            if(y == c.getY() && x == c.getX()) {
                                movePiece(previousCoordinate, c, previousPiece);
                                break;
                            }
                        }
                    }
                }
            } else {
                if(!allowToMove.isEmpty()) {
                    for(Coordinates c : allowToMove) {
                        if(y == c.getY() && x == c.getX()) {
                            movePiece(previousCoordinate, c, previousPiece);
                            break;
                        }
                    }
                }
            }
        } else {
            if(Board[y][x].getPiece() != null) {
                if(!Board[y][x].getPiece().isWhite()) {
                    showAllAllow(y, x);

                    previousPiece = Board[y][x].getPiece();
                    previousCoordinate = new Coordinates(y, x);
                    allowToMove = previousPiece.allowToMoves(previousCoordinate, Board);
                } else {
                    if(!allowToMove.isEmpty()) {
                        for(Coordinates c : allowToMove) {
                            if(y == c.getY() && x == c.getX()) {
                                movePiece(previousCoordinate, c, previousPiece);
                                break;
                            }
                        }
                    }
                }
            } else {
                if(!allowToMove.isEmpty()) {
                    for(Coordinates c : allowToMove) {
                        if(y == c.getY() && x == c.getX()) {
                            movePiece(previousCoordinate, c, previousPiece);
                            break;
                        }
                    }
                }
            }
        }
        kingInDanger();
    }

    private void demoEnnoble() {

    }
}
