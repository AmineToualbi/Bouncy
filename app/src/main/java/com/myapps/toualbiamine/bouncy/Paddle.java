package com.myapps.toualbiamine.bouncy;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Paddle implements Runnable {
    int myX = ((BreakoutMain.WIDTH / 2) - 25);
    /* renamed from: y */
    int f2y = (BreakoutMain.HEIGHT - 50);

    public void draw(Canvas g) {
        Paint paint = new Paint();
        paint.setColor(-3355444);
        g.drawRect((float) this.myX, (float) (BreakoutMain.HEIGHT - 400), (float) (this.myX + 100), (float) (BreakoutMain.HEIGHT - 380), paint);
    }

    public void move() {
        if (MouseListener.f0x > this.myX) {
            this.myX += 40;
        }
        if (MouseListener.f0x < this.myX) {
            this.myX -= 40;
        }
        if (MouseListener.f0x == this.myX) {
            BreakoutMain.board.postInvalidate();
        } else {
            BreakoutMain.board.postInvalidate();
        }
    }

    public void run() {
        while (!MouseListener.over) {
            if (MouseListener.f0x != this.myX) {
                move();
                MouseListener.gameOver();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
