package br.com.projetos.jogodaforca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Edmilson Santana on 24/09/2016.
 */
public class PlanoCartesianoView extends View {

    /** armazena o menor lado do display, caso
     * o display seja menor na vertical a variável
     * guardara este valor do contrário será guardado
     * o valor da dimensão da nossa tela na horizontal**/
    private int menorLadoDisplay;
    private int unidade;
    private Boolean desenhaPlanoCartesiano = Boolean.FALSE;

    public PlanoCartesianoView(Context context) {
        super(context);
    }

    public PlanoCartesianoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void drawPlanoCartesiano(Canvas canvas) {
        Path path = new Path();

        int max = toPixel(10);
        for ( int n = 0; n <= 10; n++) {
            // desenhando as linhas verticais
            path.moveTo(toPixel(n), 1);
            path.lineTo(toPixel(n), max);

            //desenhando as linhas na horizontal
            path.moveTo(1, toPixel(n));
            path.lineTo(max, toPixel(n));
        }

        Paint paint = new Paint();

        paint.setAntiAlias(true); // propriedade que define a suavidade da linha
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        canvas.drawPath( path, paint );
    }

    public int toPixel(int vezes) {
        return vezes * getUnidade();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if ( getHeight() > getWidth() ) {
            setMenorLadoDisplay( getWidth() );
        } else {
            setMenorLadoDisplay( getHeight() );
        }

        setUnidade( getMenorLadoDisplay() / 10);

        if( isDesenhaPlanoCartesiano() ) {
            drawPlanoCartesiano( canvas );
        }
    }


    public int getUnidade() {
        return unidade;
    }
    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    public int getMenorLadoDisplay() {
        return menorLadoDisplay;
    }
    public void setMenorLadoDisplay(int menorLadoDisplay) {
        this.menorLadoDisplay = menorLadoDisplay;
    }

    public Boolean isDesenhaPlanoCartesiano() {
        return desenhaPlanoCartesiano;
    }

    public void setDesenhaPlanoCartesiano(Boolean desenhaPlanoCartesiano) {
        this.desenhaPlanoCartesiano = desenhaPlanoCartesiano;
    }
}
