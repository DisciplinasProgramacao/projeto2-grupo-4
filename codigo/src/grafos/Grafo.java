package grafos;

import java.util.ArrayList;
import java.util.List;

import arestas.Aresta;
import uteis.Util;
import vertices.Vertice;

public abstract class Grafo {
    
    public final String nome = Util.getNomeGrafo();

    protected List<Vertice> vertices;

    private int tempo; 

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
     * Esta função só pussi o propósito de imprimir o grafo como uma função auxliar de teste
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

    /**
     * O grafo é euleriano sé possível realizar um ciclo passando por todas as arestas
     * @return true se o grafo for euleriano
     */
    public boolean euleriano(){
        //TEM q SER UM GRAFO CONEXO
        // TODOS OS GRAUS DE VERTICE PAR != 0

        if(ehConexo()){
            for(int i = 0; i < this.ordem();i++){
                if(!this.ehPar(this.vertices.get(i).getGrau()))
                    return false;
            }
        }else
            return false;

        return true;
    }

    /**
     * @return true -> retorna true se o grafo for conexo
     */
    private boolean ehConexo(){
        
        int passouPeloWhile = 0;

        for(int i = 0; i < this.ordem();i++){
            this.tempo = 0; 
            vertices.get(i).setTempoDescobrimento(0);
            vertices.get(i).setTempoTermino(0);
            vertices.get(i).setPai(0);
        }

        while(this.verticeNaoDescorbeto() != null){
            passouPeloWhile++;
            if(passouPeloWhile > 1){
                return false;
            }
            this.buscaProfundidade(this.verticeNaoDescorbeto());
        }

         
        
        
        return true;
    }

    /**
     * Realiza a busca de profundudidade do grafo a partir de um vértice raiz
     * @param v vertice raiz
     */
    private void buscaProfundidade(Vertice v) {
        int idVertice;
        this.tempo += 1;
        Vertice  verticeAuxiliar;
        v.setTempoDescobrimento(tempo);
        for(int i =0; i < v.getArestas().size() ;i++){
            idVertice = v.getArestas().get(i).getDestino();
            verticeAuxiliar = this.vertices.get(idVertice);
            if(verticeAuxiliar.getTempoDescobrimento() == 0){

                v.getArestas().get(i).visitarAresta();
                verticeAuxiliar.setPai(v.getID());
                buscaProfundidade(verticeAuxiliar);

            }else if(verticeAuxiliar.getTempoTermino() ==0 && verticeAuxiliar.getPai() != v.getID()){
                v.getArestas().get(i).visitarAresta();// aresta de retorno

            }

            this.tempo += 1;
            v.setTempoTermino(tempo);
        }
    }

    /**
     * 
     * @param nr -> número que será avaliado
     * @return -> retorna true se for par e false se for impar
     */
    private boolean ehPar(int nr){
        return nr%2 == 0;
    }

    
    /**
     * 
     * @return retorna true caso algum valor do vetor for igual a 0 -> null
     */
    private Vertice verticeNaoDescorbeto(){
        for(int i =0 ; i <this.ordem() ;i++){
            if(this.vertices.get(i).getTempoDescobrimento() == 0){
                return this.vertices.get(i);
            }

        }
        return null;
    }

    
}

