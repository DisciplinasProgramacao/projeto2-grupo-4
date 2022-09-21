package grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import uteis.Util;
import vertices.Vertice;

public abstract class GrafoMutavel extends Grafo {
    
    /* Construtores */
    
    public GrafoMutavel(){
        super();
    }

    /* Métodos abstratos */

    public abstract boolean addAresta(int origem, int destino);

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

}
