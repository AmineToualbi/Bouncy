package com.myapps.toualbiamine.bouncy;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MouseListener implements OnTouchListener {
    public static boolean over = false;
    public static boolean start = true;
    /* renamed from: x */
    public static int f0x;
    /* renamed from: y */
    public static int f1y;

    public boolean onTouch(View view, MotionEvent motionEvent) {
        f0x = (int) motionEvent.getX();
        f1y = (int) motionEvent.getY();
        switch (motionEvent.getActionMasked()) {
            case 0:
                break;
            case 11:
                if (over) {
                    BreakoutMain.reset();
                    break;
                }
                break;
        }
        if (start && f0x >= (BreakoutMain.WIDTH / 2) - 150 && f0x <= (BreakoutMain.WIDTH / 2) + 150 && f1y >= (BreakoutMain.HEIGHT / 2) - 150 && f1y <= (BreakoutMain.HEIGHT / 2) + 150) {
            start = false;
            BreakoutMain.board.postInvalidate();
        }
        if (over && f0x >= (BreakoutMain.WIDTH / 2) - 150 && f0x <= (BreakoutMain.WIDTH / 2) + 150 && f1y >= (BreakoutMain.HEIGHT / 2) + 50 && f1y <= (BreakoutMain.HEIGHT / 2) + 350) {
            BreakoutMain.reset();
        }
        BreakoutMain.board.postInvalidate();
        return true;
    }

    public static void gameOver() {
        if (Ball.bounceCounter == 1 && BreakoutMain.ball[0].ballY >= BreakoutMain.HEIGHT - 50) {
            over = true;
        }
        if (Ball.bounceCounter == 2 && BreakoutMain.ball[0].ballY >= BreakoutMain.HEIGHT - 50 && BreakoutMain.ball[1].ballY >= BreakoutMain.HEIGHT - 50) {
            over = true;
        }
        if (Ball.bounceCounter != 3) {
            over = false;
        } else if (BreakoutMain.ball[0].ballY >= BreakoutMain.HEIGHT - 50 && BreakoutMain.ball[1].ballY >= BreakoutMain.HEIGHT - 50 && BreakoutMain.ball[2].ballY >= BreakoutMain.HEIGHT - 50) {
            over = true;
        }
    }
}
