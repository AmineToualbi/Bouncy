package com.myapps.toualbiamine.bouncy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by toualbiamine on 12/21/17.
 */

public class BoardComponent extends View {

    int color;

    public BoardComponent(Context context, AttributeSet attrs){
        super(context, attrs);

        color = Color.BLACK;
    }

    protected void onDraw(Canvas g){

        Paint paint = new Paint();
        paint.setColor(color);

        //background
        g.drawRect(0, 0, BouncyMain.WIDTH, BouncyMain.HEIGHT, paint);





    }


}
