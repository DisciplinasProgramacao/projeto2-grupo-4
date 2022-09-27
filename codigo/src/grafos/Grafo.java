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
     * Método para retornar uma lista na ordem do caminho euleriano
     * @return -> Um List<Vertices>, caso o grafo nao seja euleriano, o método devolde null
     */
    public List<Vertice> caminhoEuleriano(){
        int index;
        int destino;
        int numeroArestas = this.tamanho() - this.ordem();

        List<Vertice> verticesAuxiliares = new ArrayList<Vertice>(this.vertices);
        List<Vertice> caminhoEuleriano = new ArrayList<Vertice>();
        
        Vertice verticeAtual = verticesAuxiliares.get(0);
        Aresta arestaAtual;

        if(this.euleriano()){
            while(numeroArestas != 0){
                caminhoEuleriano.add(verticeAtual);

                if(verticeAtual.getGrau() > 1){
                    index = 0;
                    arestaAtual = verticeAtual.getArestas().get(index);
                    while(this.ehPonte(verticesAuxiliares, verticeAtual.getID(), arestaAtual)){
                        index++;
                        arestaAtual = verticeAtual.getArestas().get(index);
                    }
                    
                }else{
                    arestaAtual = verticeAtual.getArestas().get(0);
                }
                destino = arestaAtual.getDestino();
                verticeAtual.removeAresta(arestaAtual.getDestino(),false);
                verticeAtual = verticesAuxiliares.get(destino);
            }
        }else{
            return null;
        }

        
        return caminhoEuleriano;
    }

    /**
     * @param vertices lista dos vértices do grafo
     * @param idVertice Id do vertice
     * @param aresta aresta a ser testa
     * @return true se aresta nao for ponte e false se aresta for ponte 
     */
    private boolean ehPonte(List<Vertice> vertices, int idVertice, Aresta aresta){

        Vertice verticeAuxiliar =  vertices.get(idVertice);
        verticeAuxiliar.getArestas().remove(aresta);
       
        
        return  this.ehConexo(vertices); 
    }

    /**
     * O grafo é euleriano sé possível realizar um ciclo passando por todas as arestas
     * @return true se o grafo for euleriano
     */
    public boolean euleriano(){
        //TEM q SER UM GRAFO CONEXO
        // TODOS OS GRAUS DE VERTICE PAR != 0

        if(ehConexo(this.vertices)){
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
    private boolean ehConexo(List<Vertice> vertices){
        this.limparVisitasArestas(this.vertices);
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

    /**
     * Limpa as visitas de todos as arestas
     * @param vertices - > Lista de vertices que terão as visitas das arestas apagadas
     */
    private void limparVisitasArestas(List<Vertice> vertices){
        int numeroArestas;
        Aresta aresta;
        for( int i = 0; i < this.ordem();i++){
            numeroArestas = this.vertices.get(i).getArestas().size();
            for(int j = 0; j < numeroArestas ;j++){
               aresta =  this.vertices.get(i).getArestas().get(j);
               aresta.limparVisita();
            }
        }
    }

    

    /**
     *
     * @return -> retorna verdadeiro se todos os vértices do grafo forem adjacentes (grafo completo)
     * e falso caso não sejam
     */
    public boolean completo() {
        for (int i = 0; i < this.vertices.size(); i++) {
            for (int j = 0; j < this.vertices.size(); j++) {
                if (!existeAresta(this.vertices.get(i).getID(), this.vertices.get(j).getID()) && this.vertices.get(i).getID()!=this.vertices.get(j).getID()) {
                    return false;
                }
            }
        }
        return true;
    }

}

