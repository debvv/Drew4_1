package com.example.drew4_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;

public class Draw2 extends Activity {

    Point size;
    int dL, dR;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display =
                getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        dL=300;dR=300;
        setContentView(new myview(this));
    }

    class myview extends View {

        Canvas canvas;
        double [] gxp=new double [] {2,9,10};
        double [] gyp=new double [] {5,13,2};

        public myview(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            this.canvas = canvas;

            Rectangle(gxp,gyp,0);

            Intent intent = getIntent();
            String choice = intent.getStringExtra("choice");

            DrawRectangles(choice);
        }

        private void DrawRectangles(String mode)
        {
            switch (mode)
            {
                case "Initial":
                    return;
                case "Horizontal":
                    double [] nxp = new double[] {gxp[0]+2,gxp[1]+2,gxp[2]+2};
                    Rectangle(nxp,gyp,0);
                    return;
                case "Vertical":
                    double [] nyp = new double[] {gyp[0]-2,gyp[1]-2,gyp[2]-2};
                    Rectangle(gxp,nyp,0);
                    return;
                case "Vertical+Horizontal":
                    nxp = new double[] {gxp[0]+2,gxp[1]+2,gxp[2]+2};
                    nyp = new double[] {gyp[0]-2,gyp[1]-2,gyp[2]-2};
                    Rectangle(nxp,nyp,0);
                    return;
                case "Rotate 30":
                    Rectangle(gxp,gyp,30);
                    return;
                case "Rotate 90":
                    Rectangle(gxp,gyp,90);
                    return;
                case "Rotate 180":
                    Rectangle(gxp,gyp,180);
                    return;
            }
        }

        private void Rectangle(double [] xp, double [] yp, int angl)
        {
            double d1,d2,d3,dmax,minx;

// distance bet vertex
            d1=dist2pp(xp[0],yp[0],xp[1],yp[1]);
            d2=dist2pp(xp[1],yp[1],xp[2],yp[2]);
            d3=dist2pp(xp[2],yp[2],xp[0],yp[0]);

// the max length
            dmax=-1E-7;
            if(dmax<d1) dmax=d1;
            if(dmax<d2) dmax=d2;
            if(dmax<d3) dmax=d3;

            minx=1E-7;
            if(minx>xp[0]) minx=xp[0];
            if(minx>xp[1]) minx=xp[1];
            if(minx>xp[2]) minx=xp[2];

            double udpx=(size.x-dL-dR)/(dmax);
            double udpy=udpx;
            double x0=-minx*udpx+dL-100;
            double y0=(dmax)*udpx/2+200+600;

            Paint paint = new Paint();

            //coordinate centrul
            double xc1 = (xp[0] + xp[1]) / 2;
            double yc1=(yp[0]+yp[1])/2;
            double xc2=(xp[2]+xp[1])/2;
            double yc2=(yp[2]+yp[1])/2;

            double k1=-1/((yp[1]-yp[0])/(xp[1]- xp[0]));
            double k2=-1/((yp[2]-yp[1])/(xp[2]- xp[1]));

            double xc = (yc2 - yc1 + k1 * xc1 - k2 * xc2) / (k1 - k2);

            double yc=yc1+k1*(xc-xc1);

            if (angl!=0)
            {
                double[] nxp = new double[] {0,0,0};
                double[] nyp = new double[] {0,0,0};

                double alf = (double)angl / 180.0 * 3.1415927;
                for (int i = 0; i < 3; i++) {
                    nxp[i] = xc + (xp[i] - xc) *
                            Math.cos(alf) - (yp[i] - yc) *
                            Math.sin(alf);
                    nyp[i] = yc + (xp[i] - xc) *
                            Math.sin(alf) + (yp[i] - yc) *
                            Math.cos(alf);
                }
                Rectangle(nxp,nyp,0);
                return;
            }

            //puncts
            for (int i = 0; i < 3; i++)
            { double _xv=x0 + (xp[i])* udpx;
                float _x1 =Float.parseFloat(String.valueOf(_xv));
                float _y1 = Float.parseFloat(String.valueOf(y0 + ( -yp[i]) * udpy));
                paint.setColor(Color.GREEN);
                canvas.drawCircle(_x1, _y1,17, paint);
            }

            //lines
            float x1,x2,x3,y1,y2,y3;
            udpy=udpx;
            double xv=x0 + (xp[0])* udpx;
            x1 =Float.parseFloat(String.valueOf(xv));
            y1 = Float.parseFloat(String.valueOf(y0 + (- yp[0]) * udpy));
            xv=x0 + (xp[1])* udpx;
            x2 =Float.parseFloat(String.valueOf(xv));
            y2 = Float.parseFloat(String.valueOf(y0 + (- yp[1]) * udpy));
            xv=x0 + (xp[2])* udpx;
            x3=Float.parseFloat(String.valueOf(xv));
            y3= Float.parseFloat(String.valueOf(y0 + ( - yp[2]) * udpy));

            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(7);
            canvas.drawLine(x1, y1, x2, y2, paint);
            canvas.drawLine(x2, y2, x3, y3, paint);
            canvas.drawLine(x3, y3, x1, y1, paint);

            //center
            paint.setColor(Color.YELLOW);
            canvas.drawCircle((float)(xc*udpx+x0), (float)(-yc*udpy+y0), 19, paint);
        }

        public double dist2pp(double x1,double y1,double x2,double y2)
        { return Math.sqrt((x1-x2)*(x1-x2)+(y1- y2)*(y1-y2)); }
    }
}
