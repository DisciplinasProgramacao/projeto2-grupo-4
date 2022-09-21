
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Grafo {
    
    public final String nome = Util.getNomeGrafo();

    private List<Vertice> vertices;

    /* Construtores */

    private void init(List<Vertice> vertices){
        this.vertices = vertices;
    }

    Grafo(){
        List<Vertice> vertices = new ArrayList<>();
        init(vertices);
    }

    Grafo(List<Vertice> vertices){
        init(vertices);
    }

    /* Métodos */


    /**
     * @param path -> o método recebe o caminho do arquivo, localizado em resources.
     * A função principal do método é que atraves de uma matriz de adjacência é obtido os dados referente
     *  ao grafo assim, carregando suas informações para a classe.
     */
    public void carregarGrafo(String path){

        File arquivo = new File(path);

        try(FileReader fileReader = new FileReader(arquivo)){

            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                
                String linha;
                int linha_referencia = 0;

                linha = bufferedReader.readLine();

                String divisao_matriz[] = linha.split("-");

                for(int i = 0; i < divisao_matriz.length; i++){
                    
                    this.addVertice(new Vertice());

                }


                while(linha != null){

                    divisao_matriz = linha.split("-");

                    for(int i = 0; i < divisao_matriz.length; i++){

                        if(divisao_matriz[i].equals("1")){

                            this.addAresta(i, linha_referencia);

                        }
    
                    }
                    
                    linha_referencia++;
                    linha = bufferedReader.readLine();

                }


                bufferedReader.close();

            } catch (Exception e) {
                
            }

            fileReader.close();

        }catch(Exception e){

        }

    } 


    /**
     * @param novo -> recebe o vértice a ser adicionado
     * @return -> retorna falso caso o vértice já exista no grafo e true para caso não exista
     */
    public boolean addVertice(Vertice novo){

        for(Vertice vertice_analisar : this.vertices){

            if(vertice_analisar.equals(novo)){
                Util.ImprimiErro("Vértice já existe em grafo");
                return false;
            }

        }

        this.vertices.add(novo);
        return true;

    } 


    /**
     * @param origem -> recebe em qual grafo será a origem da aresta
     * @param destino -> recebe em qual grafo será o destino da aresta
     * @return -> retorna true para caso não exista uma aresta com origem e destino igual dos paramêtros
     * e true, para caso a aresta seja adicionada com sucesso
     */
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

    /**
     * Esta função só pussi o proósito de imprimir o grafo como uma função auxliar de teste
     */
    public void imprimiGrafo(){

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
}
