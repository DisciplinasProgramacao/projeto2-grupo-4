package grafos;

import java.util.List;

import uteis.Util;
import vertices.Vertice;

public class GrafoCompleto extends Grafo {

    @Override
    public Grafo geraSubGrafo(List<Vertice> vertices_sub_grafo) {
       
        GrafoCompleto sub_grafo = new GrafoCompleto();
        
        if(vertices_sub_grafo.size() == 0){

            Util.ImprimiErro("Não possui vértices");
            return null;
        
        }else{

            for(Vertice vertice : vertices_sub_grafo){

                sub_grafo.vertices.add(vertice);

            }

            return sub_grafo;
        }
    }
    
}
