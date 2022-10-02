package grafos;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import uteis.Util;
import vertices.Vertice;

public class GrafoPonderado extends GrafoMutavel {

    @Override
    public boolean addAresta(int origem, int destino) {
        
        return false;
    }
    public boolean addAresta(int origem, int destino, int peso) {
        
        return false;
    }
    public boolean carregarGrafo(String path){

        File arquivo = new File(path);

        return false;

    } 

    @Override
    public Grafo geraSubGrafo(List<Vertice> vertices_sub_grafo) {

        GrafoPonderado sub_grafo = new GrafoPonderado();
        
        if(vertices_sub_grafo.size() == 0){

            Util.ImprimiErro("Não possui vértices");
            return null;
        
        }else{

            for(Vertice vertice : vertices_sub_grafo){

                sub_grafo.addVertice(vertice);

            }

            return sub_grafo;
        }
    }
    

}
