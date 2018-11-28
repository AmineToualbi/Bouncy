package com.myapps.toualbiamine.bouncy;

import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball implements Runnable {
    public static int bounceCounter = 1;
    public static int hitCounter = -1;
    public static int score = 0;
    public static int topScore = 0;
    public int acceleration = 5;
    int ballX = ((int) (Math.random() * ((double) BreakoutMain.WIDTH)));
    int ballY = 100;
    boolean bounceX;
    boolean bounceY = true;

    public void draw(Canvas g) {
        Paint paint = new Paint();
        paint.setColor(-1);
        g.drawCircle((float) this.ballX, (float) this.ballY, 15.0f, paint);
    }

    public void populate() {
        if (this.ballY >= BreakoutMain.paddle.f2y - 200 && this.ballY <= BreakoutMain.paddle.f2y - (200 - this.acceleration)) {
            BreakoutMain.ball[bounceCounter] = new Ball();
            BreakoutMain.threads[bounceCounter] = new Thread(BreakoutMain.ball[bounceCounter]);
            BreakoutMain.threads[bounceCounter].start();
            bounceCounter++;
        }
        if (this.ballY == 10 && hitCounter % 2 == 0) {
            BreakoutMain.ball[bounceCounter] = new Ball();
            BreakoutMain.threads[bounceCounter] = new Thread(BreakoutMain.ball[bounceCounter]);
            BreakoutMain.threads[bounceCounter].start();
            bounceCounter++;
        }
    }

    public void move() {
        hitEdges();
        hitPaddle();
        if (this.ballY == 10) {
            hitCounter++;
        }
        if (this.bounceX && this.bounceY) {
            this.ballX += this.acceleration;
            this.ballY += this.acceleration;
            BreakoutMain.board.postInvalidate();
        }
        if (!this.bounceX && this.bounceY) {
            this.ballX -= this.acceleration;
            this.ballY += this.acceleration;
            BreakoutMain.board.postInvalidate();
        }
        if (this.bounceX && !this.bounceY) {
            this.ballX += this.acceleration;
            this.ballY -= this.acceleration;
            BreakoutMain.board.postInvalidate();
        }
        if (!this.bounceX && !this.bounceY) {
            this.ballX -= this.acceleration;
            this.ballY -= this.acceleration;
            BreakoutMain.board.postInvalidate();
        }
    }

    public void run() {
        while (!MouseListener.over) {
            if (!MouseListener.start) {
                System.out.println(bounceCounter);
                if (bounceCounter < 3) {
                    populate();
                }
                move();
                topScoreUpdate();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void hitEdges() {
        if (this.ballX >= BreakoutMain.WIDTH - 20) {
            this.bounceX = false;
        }
        if (this.ballX <= 20) {
            this.bounceX = true;
        }
    }

    public void hitPaddle() {
        if (this.ballY >= BreakoutMain.paddle.f2y - 400 && this.ballY <= BreakoutMain.paddle.f2y - (380 - this.acceleration) && this.ballX >= BreakoutMain.paddle.myX - 2 && this.ballX <= BreakoutMain.paddle.myX + 102) {
            this.bounceY = false;
            BreakoutMain.mp1.start();
        }
        if (this.ballY == 10) {
            this.bounceY = true;
            this.acceleration += 2;
            score++;
        }
        if (this.ballY == BreakoutMain.HEIGHT - 100) {
            BreakoutMain.ball = null;
        }
        if (this.ballY >= BreakoutMain.paddle.f2y - 200 && this.ballY <= BreakoutMain.paddle.f2y - (200 - this.acceleration)) {
            BreakoutMain.ballbreak.start();
        }
    }

    private void topScoreUpdate() {
        topScore = BreakoutMain.settings.getInt("Top Score", topScore);
        if (score > topScore) {
            topScore = score;
            Editor editor = BreakoutMain.settings.edit();
            editor.putInt("Top Score", score);
            editor.commit();
        }
    }
}
