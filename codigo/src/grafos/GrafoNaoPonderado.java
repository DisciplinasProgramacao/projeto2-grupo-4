package grafos;

import uteis.Util;

public class GrafoNaoPonderado extends GrafoMutavel {
    
    public GrafoNaoPonderado(){
        super();
    }

    /**
     * @param origem -> recebe em qual grafo será a origem da aresta
     * @param destino -> recebe em qual grafo será o destino da aresta
     * @return -> retorna true para caso não exista uma aresta com origem e destino igual dos paramêtros
     * e true, para caso a aresta seja adicionada com sucesso
     */
    @Override
    public boolean addAresta(int origem, int destino){

        if(this.vertices.size() == 0){
            Util.ImprimiErro("Grafo não possui vértices suficientes");
            return false;
        }

        for(int i = 0; i < this.vertices.size(); i++){
            
            if(!existeAresta(origem, destino)){

                if(this.vertices.get(i).getID() == origem){
                    this.vertices.get(i).addAresta(destino);
                    return true;
                }

            }

           

        }

        return false;

    }

    @Override
    public void carregarGrafo(String path) {
        super.carregarGrafo(path);
    }

    
}
