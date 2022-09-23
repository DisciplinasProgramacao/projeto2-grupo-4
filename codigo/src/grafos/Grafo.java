package grafos;

import java.util.ArrayList;
import java.util.List;

import uteis.Util;
import vertices.Vertice;

public abstract class Grafo {
    
    public final String nome = Util.getNomeGrafo();

    protected List<Vertice> vertices;

    /* Construtores */

    private void init(List<Vertice> vertices){
        this.vertices = vertices;
    }

    public Grafo(){
        init(new ArrayList<>());
    }
   
    /* Gettres e Setters */

    public void setVertices(List<Vertice> vertices){
        this.vertices = vertices;
    }

    /* Métodos abstratos */


    /**
     * @param vertices -> através de uma lista de vértices é criado o sub-grafo
     * @return -> retorna o sub-grafo criado caso seja possível
     */
    public abstract Grafo geraSubGrafo(List<Vertice> vertices);

    /* Métodos */

    /**
     * Esta função só pussi o proósito de imprimir o grafo como uma função auxliar de teste
     */
    public void imprimiGrafoNaoPonderado(){

        for (Vertice vertice : this.vertices) {
            
            vertice.imprimiVertice();

        }

        if(this.vertices.size() == 0){
            Util.ImprimiErro("NULL");
        }

    }


    /**
     * @param destino -> recebe em qual grafo será a origem da aresta analisada
     * @param origem -> recebe em qual grafo será o destino da aresta analisada
     * @return -> retorna true para caso a aresta e exista e false caso não exista
     */
    public boolean existeAresta(int destino, int origem){
        
        for(int i = 0; i < this.vertices.size(); i++){

            if(this.vertices.get(i).getID() == origem){

                if (this.vertices.get(i).existeAresta(destino) != null){
                    return true;
                }else{
                    return false;
                }

            }

        }

        return false;


    }

    /**
     * @param id_vertice -> recebe o id do vértice a ser analisado
     * @return -> retorna true caso o vértice exista e false caso não exista
     */
    public boolean existeVertice(int id_vertice){

        for(Vertice vertice : this.vertices){

            if(vertice.getID() == id_vertice){
                return true;
            }

        }

        return false;

    }

    /**
     * @return -> retorna o número de vértices
     */
    public int ordem(){
        return this.vertices.size();
    }

    /**
     * 
     * @return -> retorna a soma do total de vétices com o total de arestass
     */
    public int tamanho(){
        int nrVertice = this.ordem();
        int nrAresta = 0;

        for(int i =0; i < nrVertice; i++){
            nrAresta += this.vertices.get(i).getGrau();
        }
        return nrVertice + nrAresta;
    }

    
}

