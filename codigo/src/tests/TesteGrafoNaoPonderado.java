package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import grafos.GrafoNaoPonderado;

public class TesteGrafoNaoPonderado{

    @Test
    public void testCarregamento(){

        GrafoNaoPonderado grafo = new GrafoNaoPonderado();

        boolean carregamento = grafo.carregarGrafo("C:\\Users\\Pablo Magalhães\\Documents\\GitHub\\projeto2-grupo-4\\codigo\\resources\\grafo1.txt");
        
        assertEquals(true, carregamento);

    }

}