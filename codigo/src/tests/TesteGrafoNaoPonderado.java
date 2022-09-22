package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import grafos.GrafoNaoPonderado;

public class TesteGrafoNaoPonderado{

    private static final String path = "C:\\Users\\Pablo Magalhães\\Documents\\GitHub\\projeto2-grupo-4\\codigo\\resources\\grafo1.txt";

    @Test
    public void testCarregamento(){

        GrafoNaoPonderado grafo = retornaGrafo();

        boolean carregamento = grafo.carregarGrafo(path);
        
        grafo.imprimiGrafo();

        assertEquals(true, carregamento);

    }

    @Test
    public void testDeletaVertice(){

        GrafoNaoPonderado grafo = retornaGrafo();

        grafo.carregarGrafo(path);

        grafo.imprimiGrafo();

        //int antiga_ordem = grafo.getOrdem();
        //boolean deletado = false;

        grafo.deletaVertice(0);

        grafo.imprimiGrafo();

        /*if(grafo.getOrdem() != antiga_ordem){

            deletado = true;

        } */

        //assertEquals(true, deletado);

    }

    public GrafoNaoPonderado retornaGrafo(){
        return new GrafoNaoPonderado();
    }


}