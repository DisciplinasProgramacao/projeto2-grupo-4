package buscas;

import java.util.ArrayList;
import java.util.List;

public class BuscaEmProfunidade {
    
    private List<Integer> id_vertice; 
    private List<Integer> tempo_descoberta;
    private List<Integer> tempo_visitado;
    private List<Integer> id_pai;

    /* Construtores */

    private void init(){

        this.id_vertice = new ArrayList<>();
        this.tempo_descoberta = new ArrayList<>();
        this.tempo_visitado = new ArrayList<>();
        this.id_pai = new ArrayList<>();
    }

    public BuscaEmProfunidade(){
        init();
    }

    /* Métodos */

    public void registraIdVertice(int id_vertice){
        this.id_vertice.add(id_vertice);
    }

    public void registraTempoDescoberta(int tempo_descoberta){
        this.tempo_descoberta.add(tempo_descoberta);
    }

    public void registraTempoVisitado(int tempo_visitado){
        this.tempo_visitado.add(tempo_visitado);
    }

    public void registraIdPai(int id_pai){
        this.id_pai.add(id_pai);
    }

    public void registroGeral(int id_vertice, int tempo_descoberta, int tempo_visitado, int id_pai){

        registraIdVertice(id_vertice);
        registraTempoDescoberta(tempo_descoberta);
        registraTempoVisitado(tempo_visitado);
        registraIdPai(id_pai);

    }

}
