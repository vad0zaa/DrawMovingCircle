package ee.sinchukov.drawmovingcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;


/**
 * Created by user_39 on 23.04.2015.
 */
public class DrawScene extends View {

    private Paint canvasPaint, circlePaint;

    // circle coordinates and radius
    private int x;
    private int y;
    private int circleRadius=50;
    private int screenWidth;
    private Handler h;

    // sift coordinates pixels by frame,  and frame rate
    private int dx = 15;
    private int dy = 15;
    private final int FRAME_RATE = 5;

    public int getter_X(){
        return x;
    }

    public int getter_Y(){
        return y;
    }

    public DrawScene(Context context, int start_x, int start_y){
        super(context);

        // assign circle position x,y
        x=start_x;
        y=start_y;

        canvasPaint = new Paint();
        circlePaint = new Paint();

        canvasPaint.setStyle(Paint.Style.FILL);
        canvasPaint.setColor(Color.GREEN);
        circlePaint.setColor(Color.YELLOW);

        h = new Handler();

    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();   //  redraw view object, i.e. start DrawScene method OnDraw
        }
    };

    @Override
    protected void onDraw(Canvas sceneCanvas) {
        super.onDraw(sceneCanvas);

        sceneCanvas.drawPaint(canvasPaint);
        sceneCanvas.drawCircle(x,y,circleRadius,circlePaint);

        // change coordinates
        x=x+dx;
        y+=dy;

        if(x>this.getWidth() -circleRadius || x<circleRadius){
            dx=dx*(-1);
        }

        if(y>this.getHeight()-circleRadius || y<circleRadius){
            dy=dy*(-1);
        }

        // handler will start next Runnable r, to redraw using invalidate()
        h.postDelayed(r,FRAME_RATE);
    }


}
