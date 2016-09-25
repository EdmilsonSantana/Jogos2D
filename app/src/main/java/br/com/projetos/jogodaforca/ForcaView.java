package br.com.projetos.jogodaforca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * Created by Edmilson Santana on 24/09/2016.
 */
public class ForcaView extends PlanoCartesianoView {

    /**
     * responsável por armazenar todas as figuras geométricas
     * a serem desenhadas na forca
     * **/
    private Path pathForca;
    private Paint paintForca;
    private ForcaService forcaService;

    private enum Membro{braco, perna}

    private enum Lado{direito, esquerdo}

    public ForcaView(Context context) {
        super(context);
    }

    public ForcaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPathForca( new Path() );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        plotaArmacacaoDaForca();
        if(getForcaService() != null) {
            switch(getForcaService().getQntErros()) {
                case 0:
                    plotaCabeca();
                    break;
                case 1:
                    plotaCorpo();
                    break;
                case 2:
                    plotaMembro(Membro.braco, Lado.direito);
                    break;
                case 3:
                    plotaMembro(Membro.braco, Lado.esquerdo);
                    break;
                case 4:
                    plotaMembro(Membro.perna, Lado.direito);
                    break;
                case 5:
                    plotaMembro(Membro.perna, Lado.esquerdo);

            }
            drawLetrasCorretas( canvas );
            plotaTracos();
        }

        canvas.drawPath(getPathForca(), getPaintForca());
    }

    private void plotaArmacacaoDaForca() {
        getPathForca().moveTo( toPixel(1), toPixel(10));
        getPathForca().lineTo( toPixel(3), toPixel(10));

        getPathForca().moveTo(toPixel(2), toPixel(10));
        getPathForca().lineTo( toPixel(2), toPixel(1));

        getPathForca().rLineTo( toPixel(5), 0);

        getPathForca().rLineTo(0, toPixel(1));
    }

    private void plotaCabeca() {
        getPathForca().addCircle( toPixel(7), toPixel(3), toPixel(1), Path.Direction.CW);
    }

    private void plotaCorpo() {
        getPathForca().moveTo( toPixel(7), toPixel(4) );
        getPathForca().lineTo( toPixel(7), toPixel(7));
    }

    private void plotaMembro(Membro membro, Lado lado) {
        final int posDoCorpo = 7; // posição do corpo no eixo x
        final int alturaDoBraco = 5; //def. o Y onde será desenhado o braço
        final int alturaDaPerna = 7; //def. o Y onde será desenhado a perna
        int alturaFinal;

        if ( membro.equals(Membro.braco) ) {
            getPathForca().moveTo( toPixel(posDoCorpo), toPixel(alturaDoBraco));
            alturaFinal = alturaDoBraco + 1;
        } else {
            getPathForca().moveTo( toPixel(posDoCorpo), toPixel(alturaDaPerna));
            alturaFinal = alturaDaPerna + 1;
        }

        if (lado.equals(Lado.direito)) {
            getPathForca().lineTo( toPixel(posDoCorpo+1), toPixel(alturaFinal));
        } else {
            getPathForca().lineTo( toPixel(posDoCorpo-1), toPixel(alturaFinal));
        }
    }

    public Path getPathForca() {
        return pathForca;
    }

    public void setPathForca(Path pathForca) {
        this.pathForca = pathForca;
    }

    public Paint getPaintForca() {

        paintForca = new Paint();
        paintForca.setColor( Color.BLACK );
        paintForca.setStyle( Paint.Style.STROKE );
        paintForca.setStrokeWidth( 8 );

        return paintForca;
    }

    private void plotaTracos() {
        int eixoX = toPixel(3);
        getPathForca().moveTo(eixoX+10, toPixel(10));
        if(getForcaService()==null) {
            return;
        }
        for(int i = 0; i <= getForcaService().getPalavraAteAgora().length()-1;i++) {
            getPathForca().rMoveTo(10, 0);
            getPathForca().rLineTo(toPixel(1), 0);
        }
    }
    private Paint getPaintTraco() {
        Paint paint = new Paint();
        paint.setColor( Color.BLACK );
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth( 2 );
        paint.setTextSize( 25 );

        return paint;
    }

    private void drawLetrasCorretas(Canvas canvas) {
        int eixoX = toPixel(3);
        getPathForca().moveTo( eixoX + 10, toPixel(10));
        eixoX += 35;

        if (getForcaService() == null ) {
            return;
        }

        for (int i = 0; i <= getForcaService().getPalavraAteAgora().length() - 1; i++) {
            char c = getForcaService().getPalavraAteAgora().charAt(i);
            canvas.drawText(c+"", eixoX+((toPixel(1) + 10) * i), toPixel(10)-15, getPaintTraco());
        }
    }


    public ForcaService getForcaService() {
        return forcaService;
    }


    public void setForcaService(ForcaService forcaService) {
        this.forcaService = forcaService;
    }
}


