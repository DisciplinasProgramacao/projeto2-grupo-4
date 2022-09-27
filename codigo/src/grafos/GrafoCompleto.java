package grafos;

import java.util.List;

import arestas.Aresta;
import uteis.Util;
import vertices.Vertice;

public class GrafoCompleto extends Grafo {

    GrafoCompleto(){
        super();
    }

    public void GerarGrafoCompleto(){
        for(int i = 0; i < this.vertices.size(); i++){
            for (int j = 0; j < this.vertices.size(); j++) {
                if(!existeAresta(this.vertices.get(i).getID(), this.vertices.get(j).getID())){
                        this.vertices.get(i).addAresta(this.vertices.get(j).getID());
                }
            }
        }
    }

    @Override
    public boolean existeVertice(int id_vertice){
            if(existeAresta(this.vertices.get(1).getID(), id_vertice)){
                return true;
            }
        return false;
    }

    @Override
    public boolean existeAresta(int verticeA, int verticeB) {
        if (completo() && verticeA != verticeB) {
            return true;
        } else {
            for (int i = 0; i < this.vertices.size(); i++) {
                if (this.vertices.get(i).getID() == verticeA) {
                    if (this.vertices.get(i).existeAresta(verticeB) != null) {
                        return true;
                    }

                }
            }
            return false;
        }
    }

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
