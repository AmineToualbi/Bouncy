package com.myapps.toualbiamine.bouncy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.View;

public class BoardComponent extends View {
    int buttonColor = -3355444;
    int color = -16777216;

    public BoardComponent(Context context) {
        super(context);
    }

    public void onDraw(Canvas g) {
        Paint paint = new Paint();
        paint.setColor(this.color);
        g.drawRect(0.0f, 0.0f, (float) BreakoutMain.WIDTH, (float) BreakoutMain.HEIGHT, paint);
        if (MouseListener.start) {
            drawStart(g);
        }
        if (!(MouseListener.start || MouseListener.over)) {
            BreakoutMain.paddle.draw(g);
            for (int i = 0; i < BreakoutMain.ball.length; i++) {
                if (BreakoutMain.ball[i] != null) {
                    BreakoutMain.ball[i].draw(g);
                }
            }
            paint.setColor(-1);
            paint.setTextSize(80.0f);
            paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            g.drawText(BuildConfig.FLAVOR + Ball.score, (float) (BreakoutMain.WIDTH - 120), (float) ((BreakoutMain.HEIGHT / 2) - 780), paint);
        }
        if (MouseListener.over) {
            drawGameOver(g);
        }
    }

    private void drawGameOver(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setTextSize(80.0f);
        paint.setTextAlign(Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        canvas.drawText("GAME OVER", (float) ((BreakoutMain.WIDTH / 2) - 10), (float) ((BreakoutMain.HEIGHT / 2) - 400), paint);
        paint.setColor(-1);
        canvas.drawText("Score: " + Ball.score, (float) ((BreakoutMain.WIDTH / 2) - 10), (float) ((BreakoutMain.HEIGHT / 2) - 200), paint);
        canvas.drawText("Top score: " + Ball.topScore, (float) ((BreakoutMain.WIDTH / 2) - 10), (float) ((BreakoutMain.HEIGHT / 2) - 100), paint);
        BreakoutMain.gameover.start();
        paint.setStyle(Style.FILL);
        paint.setColor(this.buttonColor);
        canvas.drawCircle((float) (BreakoutMain.WIDTH / 2), (float) ((BreakoutMain.HEIGHT / 2) + 200), 150.0f, paint);
        paint.setStyle(Style.STROKE);
        paint.setColor(-1);
        canvas.drawCircle((float) (BreakoutMain.WIDTH / 2), (float) ((BreakoutMain.HEIGHT / 2) + 200), 150.0f, paint);
        paint.setColor(-16777216);
        paint.setTextSize(50.0f);
        paint.setTextAlign(Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        canvas.drawText("PLAY AGAIN", (float) (BreakoutMain.WIDTH / 2), (float) ((BreakoutMain.HEIGHT / 2) + 220), paint);
    }

    private void drawStart(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setTextSize(80.0f);
        paint.setTextAlign(Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        canvas.drawText("B O U N C Y", (float) ((BreakoutMain.WIDTH / 2) + 5), (float) ((BreakoutMain.HEIGHT / 2) - 500), paint);
        paint.setStyle(Style.FILL);
        paint.setColor(this.buttonColor);
        canvas.drawCircle((float) (BreakoutMain.WIDTH / 2), (float) (BreakoutMain.HEIGHT / 2), 150.0f, paint);
        paint.setStyle(Style.STROKE);
        paint.setColor(-1);
        canvas.drawCircle((float) (BreakoutMain.WIDTH / 2), (float) (BreakoutMain.HEIGHT / 2), 150.0f, paint);
        paint.setColor(-16777216);
        paint.setTextSize(80.0f);
        paint.setTextAlign(Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        canvas.drawText("START", (float) (BreakoutMain.WIDTH / 2), (float) ((BreakoutMain.HEIGHT / 2) + 35), paint);
    }
}
