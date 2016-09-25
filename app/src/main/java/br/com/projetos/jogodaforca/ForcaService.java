package br.com.projetos.jogodaforca;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Clarice on 24/09/2016.
 */
public class ForcaService {

    private String palavraParaAdvinhar;
    private Set<Character> letrasUsadas;
    private int qntErros = -1;

    public ForcaService(String palavra) {
        letrasUsadas = new HashSet<>();
        this.palavraParaAdvinhar = palavra;
    }

    public void joga(Character letra) {
        if ( letrasUsadas.contains(letra) ) {
            return;
        }
        letrasUsadas.add( letra );
        if (palavraParaAdvinhar.contains(letra.toString())) {
            return;
        }
        qntErros++;

    }

    public String getPalavraAteAgora() {
        String visualizacao = "";
        for ( char c : palavraParaAdvinhar.toCharArray()) {
            if ( letrasUsadas.contains( c )) {
                visualizacao += c;
            } else {
                visualizacao += " ";
            }
        }
        return visualizacao;
    }

    public Boolean isMorreu() {
        return getQntErros() == 5;
    }

    public Boolean isGanhou() {
        return !getPalavraAteAgora().contains(" ");
    }

    public Boolean isTerminou() {
        return isMorreu() || isGanhou();
    }
    public int getQntErros() {
        return qntErros;
    }

    public void setQntErros(int qntErros) {
        this.qntErros = qntErros;
    }
}
