package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import grafos.GrafoNaoPonderado;
import uteis.Util;

public class TesteGrafoNaoPonderado{

    private static final String path = "C:\\Users\\Pablo Magalhães\\Documents\\GitHub\\projeto2-grupo-4\\codigo\\resources\\grafo1.txt";

    @Test
    public void testCarregamento(){

        Util.ImprimiMensagem("Teste 1: ");

        GrafoNaoPonderado grafo = retornaGrafo();

        boolean carregamento = grafo.carregarGrafo(path);
        
        grafo.imprimiGrafo();

        assertEquals(true, carregamento);

    }

    @Test
    public void testDeletaVertice(){

        Util.ImprimiMensagem("Teste 2: ");

        GrafoNaoPonderado grafo = retornaGrafo();

        grafo.carregarGrafo(path);

        Util.ImprimiMensagem("Grafo antes de deletar vértice: ");

        grafo.imprimiGrafo();

        //int antiga_ordem = grafo.getOrdem();
        //boolean deletado = false;

        grafo.deletaVertice(0);

        Util.ImprimiMensagem("Grafo depois de deletar vértice: ");

        grafo.imprimiGrafo();

        /*if(grafo.getOrdem() != antiga_ordem){

            deletado = true;

        } */

        //assertEquals(true, deletado);

    }

    private GrafoNaoPonderado retornaGrafo(){
        return new GrafoNaoPonderado();
    }


}