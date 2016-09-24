package jogos2d.projetos.com.br.jogos2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Edmilson Santana on 23/09/2016.
 */
public class EstudoView extends View {

    public EstudoView(Context context) {
        super(context);
    }

    public EstudoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawLine(x1, y1, x2, y2, paint);
        this.desenhaLinha( canvas );

        this.desenhaRetangulo(canvas);

        this.desenhaCirculo(canvas);

    }

    private void desenhaCirculo(Canvas canvas) {
        Paint paintCircle = new Paint();
        paintCircle.setStyle( Paint.Style.STROKE );

        canvas.drawCircle(100, 100, 100, paintCircle);

        Paint paint = new Paint();
        paint.setColor( Color.YELLOW );

        canvas.drawLine(0, 100, 100, 100, paint);
        canvas.drawLine(100, 100, 100, 0, paint);
    }

    private void desenhaLinha(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(0);
        paint.setColor(Color.RED);

        for ( int n = 1; n <= 20; n++) {
            paint.setStrokeWidth( n );
            canvas.drawLine(50, n*20, 100, n*20, paint);
        }
    }

    private void desenhaRetangulo(Canvas canvas) {
        canvas.drawRect(new Rect(200, 200, 300, 300), new Paint());
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawLine(200, 200, 300, 300, paint);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i <= 20; i++) {
            canvas.drawRect(300+(i*20*-1), 300+(i*20*-1),
                    400+(i*20), 400+(i*20), paint);
        }
    }
}
