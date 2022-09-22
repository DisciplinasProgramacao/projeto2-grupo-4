package uteis;

import java.util.List;
import java.util.Scanner;

import arestas.Aresta;

public class Util {
    
    private static int ID_GRAFO = -1;
    public static int ID = -1;
    private static String nome_grafo = "GRAFO ";
    private static final Scanner teclado = new Scanner(System.in);

    public static int retornaInteiro(){
        return teclado.nextInt();
    }

    public static void ImprimiMensagemSemQuebraLinha(String mensagem){

        System.out.print(mensagem);

    }

    public static void ImprimiMensagem(String mensagem){

        System.out.println(mensagem);

    }

    public static void ImprimiErro(String erro){
        System.err.println(erro);
    }
    
    public static int retornaID(){

        ID++;
        
        return ID;

    }

    public static boolean IsListaArestasVazia(List<Aresta> arestas){

        if(arestas.size() == 0){
            return true;
        }else{
            return false;
        }

    }
   
    public static String getNomeGrafo(){

        ID_GRAFO++;
        return nome_grafo + ID_GRAFO;

    }

}
