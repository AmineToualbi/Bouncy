package com.myapps.toualbiamine.bouncy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;

public class BreakoutMain extends Activity {

    public static Ball[] ball;
    public static MediaPlayer ballbreak;
    public static BoardComponent board;
    public static MediaPlayer gameover;
    public static int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static MouseListener mouse;
    public static MediaPlayer mp1;
    public static Paddle paddle;
    public static SharedPreferences settings;
    public static Thread[] threads;
    public static int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int HEIGHT = height;
    public static final int WIDTH = width;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp1 = MediaPlayer.create(this, R.raw.bouncesound);
        gameover = MediaPlayer.create(this, R.raw.gameover);
        ballbreak = MediaPlayer.create(this, R.raw.ballbreak);
        paddle = new Paddle();
        ball = new Ball[4];
        ball[0] = new Ball();
        threads = new Thread[4];
        board = new BoardComponent(this);
        new Thread(paddle).start();
        for (int i = 0; i < ball.length; i++) {
            threads[i] = new Thread(ball[i]);
            threads[i].start();
        }
        mouse = new MouseListener();
        board.setOnTouchListener(mouse);
        getWindow();
        requestWindowFeature(1);
        settings = getSharedPreferences("GAME_DATA", 0);
        setContentView(board);
    }

    public static void reset() {
        Ball.score = 0;
        Ball.bounceCounter = 1;
        Ball.hitCounter = -1;
        MouseListener.over = false;
        paddle = new Paddle();
        ball = new Ball[4];
        ball[0] = new Ball();
        threads = new Thread[4];
        new Thread(paddle).start();
        for (int i = 0; i < ball.length; i++) {
            threads[i] = new Thread(ball[i]);
            threads[i].start();
        }
    }
}
