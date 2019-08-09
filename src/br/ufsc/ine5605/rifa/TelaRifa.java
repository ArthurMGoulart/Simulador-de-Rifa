/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa;

import java.util.Scanner;

/**
 *
 * @author budi
 */
public class TelaRifa extends Tela{
    
    private CtrlRifa controlador;
    
    public TelaRifa(CtrlRifa controlador){
    
        super();
        
        this.controlador = controlador;
    
    
    }
     
    public void inicia(){
        
        int opcao;
        
        if(!this.controlador.getRifaControlada().isFinalizada()){
            
            do{
                    
            System.out.println("--------- RIFA " + this.controlador.getRifaControlada().getCodigo() + "---------");

            System.out.println("Opcoes: ");

            System.out.println("1 - Incluir Produto");

            System.out.println("2 - Listar Apostadores");

            System.out.println("3 - Listar Produtos");
            
            System.out.println("4 - Finalizar Rifa");

            System.out.println("0 - Voltar para Menu");

            System.out.println("---------********--------");

            System.out.println("opcao: ");

            opcao = entradaDeDados.nextInt();

            entradaDeDados.nextLine();

            switch(opcao){
                    
                case 1:
                    adicionarProduto();
                    break;

                case 2:
                    listaApostadores();
                    break;
                case 3:

                    listaProdutos();
                    break;
                    
                case 4:
                    rifaFinalizada();
                    break;
               
                }
           
        } while(opcao != 0);
          
        }else{
            
            do{

                System.out.println("--------- Rifa Finalizada " + this.controlador.getRifaControlada().getCodigo() + " ---------");

                System.out.println("Opcoes: ");

                System.out.println("1 - Sortear Produto");

                System.out.println("2 - Listar Apostadores Ganhadores");
                
                System.out.println("3 - Lucro da Rifa");

                System.out.println("0 - Voltar Ao Menu");

                System.out.println("---------********--------");

                System.out.println("opcao: ");

                opcao = entradaDeDados.nextInt();

                entradaDeDados.nextLine();

                switch(opcao){

                    case 1:
                        sortearProdutos();
                        break;

                    case 2:
                        listarApostadoresGanhadores();
                        break;
                        
                    case 3:
                        mostrarLucro();
                        break;

                    }

            } while(opcao != 0);
             
        }
        
    }
    
    public void criarRifa(){
    
        try{
        
            System.out.println("----------- Criando Nova Rifa -----------");

            System.out.println("Codigo Da Rifa: ");

            int codigo = receberValorInteiro();

            System.out.println("Qual a Porcentagem de Lucro Desejada: ");

            int porcentagemDeLucro = receberValorInteiro();
            
            System.out.println("Qual a quantidade De Numeros Para Vender: ");
            
            int quantidadeDeNumerosParaVender = receberValorInteiro();

            this.controlador.criarRifa(codigo, porcentagemDeLucro, quantidadeDeNumerosParaVender);
        
        }catch(NumberFormatException e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            this.controlador.getControlador().getTela().inicia();
        
        }catch(Exception e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            this.controlador.getControlador().getTela().inicia();
        
        }
        
        System.out.println("Criada Com Sucesso");
    
    }
    
    private void adicionarProduto(){
        
        try{
        
            System.out.println("Nome: ");

            String nomeProduto = receberTexto();

            System.out.println("Preco: ");

            Integer precoProduto = receberValorInteiro();

            this.controlador.adicionarProduto(nomeProduto, precoProduto);
    
        }catch(NumberFormatException e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            adicionarProduto();
        
        }
        
        System.out.println("Adicionado Com Sucesso");
        
    }

    private void listaApostadores() {
        
        System.out.println("----------- Listando Apostadores -----------");
        
        for(AssocRifaApostador apostador : this.controlador.getRifaControlada().getApostadores()){
        
            System.out.println("Apostador: " + apostador.getApostador().getNome());

        }
        
    }

    private void listaProdutos() {
        
        System.out.println("----------- Listando Produtos -----------");
        
        for(Produto produto : this.controlador.getRifaControlada().getProdutos()){
        
            System.out.println("Produto: " + produto.getNome());

        }
        
    }   

    private void rifaFinalizada() {
         
        int numeroConfirmacao = 0;
        
        System.out.println("------- Digite 123 para finalizar a rifa ---------");
        
        try{
        
            numeroConfirmacao = receberValorInteiro();
        
        }catch(NumberFormatException e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            this.rifaFinalizada();
        
        }
        if(numeroConfirmacao == 123){
            
            try{
               
                this.controlador.finalizarRifa();
                
            }catch(Exception e){
            
                System.out.println(e.getMessage());
                
                mensagemEnter();
                
                rifaFinalizada();
            
            }
      
            System.out.println("Custo da rifa: " + this.controlador.getRifaControlada().getCusto());
        
            System.out.println("Preco por numero: " + this.controlador.getRifaControlada().getPrecoPorNumero());
            
            System.out.println("Rifa Finalizada");
        
        }else{
        
            System.out.println("Nao digitou 123");
            
            inicia();
        
        }
        
        System.out.println("Finalizada Com Sucesso");
        
        this.controlador.getControlador().getTela().inicia();
        
    }

    private void sortearProdutos() {
        
        System.out.println("--------- Sorteando Produto ------------");
        
        try{
        
            this.controlador.getRifaControlada().sortearUmProduto();
        
        }catch(Exception e){
        
            System.out.println(e.getMessage());
            
            mensagemEnter();
            
            inicia();
        
        }
        
    }

    private void listarApostadoresGanhadores(){
        
        System.out.println("----------- Listando Apostadores Ganhadores");
        
        for(AssocRifaApostador apostador : this.controlador.getRifaControlada().getApostadores()){
            
            if(apostador.getApostador().eGanhador()){
                
                System.out.println("Apostador: " + apostador.getApostador().getNome());
                
            }
        }
        
    }

    public void sortearUmProduto(int numeroSorteado, String nomeProduto, int precoProduto, String nomeApostador, int cpfApostador) {
        
        System.out.println("Produto Sorteado: " + nomeProduto + " Preco dele: " + precoProduto);
        
        System.out.println("Numero Sorteado: " + numeroSorteado);
        
        System.out.println("Apostador Ganhador do Produto: " + nomeApostador + " Cpf dele: " + cpfApostador);
        
    }

    private void mostrarLucro() {
        
        double lucro = this.controlador.getRifaControlada().calcularLucro();
        
        System.out.println("Lucro da Rifa: " + lucro);
        
    }

}