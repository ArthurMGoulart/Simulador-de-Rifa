/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Controles;

import br.ufsc.ine5605.rifa.Entidades.Apostador;
import br.ufsc.ine5605.rifa.Entidades.Rifa;
import br.ufsc.ine5605.rifa.Telas.AcoesBotao;
import br.ufsc.ine5605.rifa.Telas.TelaAcessarRifa;
import br.ufsc.ine5605.rifa.Telas.TelaCriarRifa;
import br.ufsc.ine5605.rifa.Telas.TelaListarApostadoresDaRifa;
import br.ufsc.ine5605.rifa.Telas.TelaProduto;
import br.ufsc.ine5605.rifa.Telas.TelaRifaFinalizada;
import br.ufsc.ine5605.rifa.Telas.TelaRifaNaoFinalizada;
import java.io.Serializable;
import javax.swing.ListModel;


/**
 *
 * @author budi
 */
public class CtrlRifa implements Serializable{
    
    private Rifa rifaControlada;

    private static CtrlRifa instancia;
    
    private CtrlRifa(){
        
    }
    
    public static CtrlRifa getInstancia(){
        
        if(instancia == null){
        
            instancia = new CtrlRifa();
        
        }
        
        return instancia;
        
    }

    public Rifa getRifaControlada() {
        
        return rifaControlada;
        
    }
    
    public MapeadorRifa getMapeadorRifa() {
     
        return MapeadorRifa.getInstancia();
        
    }

    public void setRifaControlada(Rifa rifaControlada) {
        
        this.rifaControlada = rifaControlada;
        
    }

    public void criarRifa(int codigo, int porcentagemDeLucro, int quantidadeDeNumerosParaVender) throws Exception{

        Rifa rifa = new Rifa(codigo, porcentagemDeLucro, quantidadeDeNumerosParaVender);
        
        if(MapeadorRifa.getInstancia().temRifaComCodigo(codigo)){
        
            throw new IllegalArgumentException("Rifa ja existente com esse codigo");
            
        }else if(porcentagemDeLucro < 0){
        
            throw new IllegalArgumentException("Porcentagem de Lucro menor que zero");
        
        }else if(quantidadeDeNumerosParaVender <= 0){
        
            throw new IllegalArgumentException("Quantidade De Numeros para Vender menor ou igual a zero");
        
        }
        
        MapeadorRifa.getInstancia().putRifa(rifa);
        
        TelaCriarRifa.getInstancia().desligar();
        
        CtrlPrincipal.getInstancia().ligarTela();

    }
    
    public void adicionarProduto(String nomeProduto, Integer precoProduto) throws IllegalArgumentException{
        
        if(precoProduto <= 0){
        
            throw new IllegalArgumentException("Preco produto menor ou igual a zero");
        
        }
        
        this.rifaControlada.adicionarProduto(nomeProduto, precoProduto);
         
    }

    public void finalizarRifa() throws Exception{
        
        this.rifaControlada.setFinalizada(true);
        
        this.rifaControlada.finalizar();
        
        MapeadorRifa.getInstancia().removerRifa(rifaControlada);
        
        MapeadorRifa.getInstancia().putRifa(rifaControlada);
            
        this.rifaControlada.finalizarApostadores();

        TelaRifaNaoFinalizada.getInstancia().desligar();
        
        CtrlPrincipal.getInstancia().ligarTela();
        
    }

    public String[] sortearProduto() throws Exception{
        
        String[] informacoes = this.rifaControlada.sortearUmProduto();
    
        return informacoes;
        
    }

    public void associarApostador(Rifa rifaParaAssociar, Apostador apostador) throws Exception{
        
        rifaParaAssociar.serAssociadaComApostador(apostador);
           
    }

    public void iniciarTelaCriarRifa() {
        
        TelaCriarRifa.getInstancia().ligar();
        
    } 
    
    public void iniciarTelaAcessarRifa() {
        
        TelaAcessarRifa.getInstancia().ligar();

    }
    
    public void iniciarTelaRifaNaoFinalizada(){
    
        TelaRifaNaoFinalizada.getInstancia().ligar();
    
    }
    
    public void desligarTelaCriarRifa(){
    
        TelaCriarRifa.getInstancia().desligar();
        
    }
    
    public void desligarTelaAcessarRifa(){
    
        TelaAcessarRifa.getInstancia().desligar();
        
    }
    
    public void desligarTelaRifaNaoFinalizada(){
    
        TelaRifaNaoFinalizada.getInstancia().desligar();
        
    }

    public void acessarRifa(int codigo) throws Exception{
        
        setRifaControlada(MapeadorRifa.getInstancia().findRifaByCodigo(codigo));
        
        if(!rifaControlada.isFinalizada()){
        
            TelaAcessarRifa.getInstancia().desligar();

            TelaRifaNaoFinalizada.getInstancia().atualizarCodigoDaRifa();

            iniciarTelaRifaNaoFinalizada();
      
        }else{
            
            TelaAcessarRifa.getInstancia().desligar();
            
            TelaRifaFinalizada.getInstancia().atualizarInformacoesRifa();
            
            TelaRifaFinalizada.getInstancia().ligar();

        }
        
    }
    
    public void realizaAcaoCriarRifa(AcoesBotao acao) {
        
        if(acao.equals(AcoesBotao.CriarRifaTela)){
        
            TelaCriarRifa.getInstancia().iniciarCriarRifa();
        
        }else if(acao.equals(AcoesBotao.VoltarTelaCriarRifa)){
        
            TelaCriarRifa.getInstancia().desligar();
            
            CtrlPrincipal.getInstancia().ligarTela();
            
        }
        
    }

    public void realizarAcaoRifaNaoFinalizada(AcoesBotao acao) {
        
        if(acao.equals(AcoesBotao.AdicionarProdutoRifaNaoFinalizada)){
                      
            desligarTelaRifaNaoFinalizada();
                
            TelaProduto.getInstancia().ligarAdicionar();   
        
        }else if(acao.equals(AcoesBotao.ListarProdutosRifaNaoFinalizada)){

            desligarTelaRifaNaoFinalizada();
            
            TelaProduto.getInstancia().iniciarComponentesListar();
                
            TelaProduto.getInstancia().ligarListagem();

        }else if(acao.equals(AcoesBotao.ListarApostadoresRifaNaoFinalizada)){
        
            desligarTelaRifaNaoFinalizada();
            
            TelaListarApostadoresDaRifa.getInstancia().atualizarListagemApostadores();
            
            TelaListarApostadoresDaRifa.getInstancia().ligarListagemApostadores();
        
        }else if(acao.equals(AcoesBotao.FinalizarRifaNaoFinalizada)){
        
            TelaRifaNaoFinalizada.getInstancia().iniciarFinalizarRifa();
        
        }else if(acao.equals(AcoesBotao.VoltarRifaNaoFinalizada)){
        
            desligarTelaRifaNaoFinalizada();
            
            CtrlPrincipal.getInstancia().ligarTela();
            
        }
        
    }
    
    public String[] getNomesProdutoDaRifaControlada(){
    
        return this.rifaControlada.getNomeProdutos();
    
    }
    
    public String[] getNomesApostadoresDaRifa() {
        
        return this.rifaControlada.getNomeApostadores();
        
    }
    
    public String[] getNomesApostadoresGanhadoresDaRifa(){
    
        return this.rifaControlada.getNomeApostadoresGanhadores();
    
    }

    public void realizarAcaoTelaProduto(AcoesBotao acao) {
        
        if(acao.equals(AcoesBotao.AdicionarProdutoPainel)){
        
            TelaProduto.getInstancia().adicionarProduto();

        }else if(acao.equals(AcoesBotao.VoltarTelaProdutoAdicionando)){
            
            TelaProduto.getInstancia().desligarAdicionar();
            
            iniciarTelaRifaNaoFinalizada();
        
        }else if(acao.equals(AcoesBotao.VoltarTelaProdutoListando)){
        
            TelaProduto.getInstancia().desligarListagem();
            
            iniciarTelaRifaNaoFinalizada();
            
        }
        
    }

    public void realizarAcaoAcessarRifa(AcoesBotao acao) {
    
        if(acao.equals(AcoesBotao.AcessarRifaTela)){
        
            TelaAcessarRifa.getInstancia().iniciarAcessarRifa();
            
        }else if(acao.equals(AcoesBotao.VoltarTelaAcessarRifa)){
        
            TelaAcessarRifa.getInstancia().desligar();
            
            CtrlPrincipal.getInstancia().ligarTela();
        
        }
        
    }

    public void realizarAcaoRifaFinalizada(AcoesBotao acao) {
        
        if(acao.equals(AcoesBotao.SortearProduto)){
        
            TelaRifaFinalizada.getInstancia().iniciarSortearProduto();
        
        }else if(acao.equals(AcoesBotao.ListarApostadoresGanhadores)){
        
            TelaRifaFinalizada.getInstancia().desligar();
            
            TelaListarApostadoresDaRifa.getInstancia().ligarListagemApostadoresGanhadores();
        
        }else if(acao.equals(AcoesBotao.VoltarRifaFinalizada)){
        
            TelaRifaFinalizada.getInstancia().desligar();
            
            CtrlPrincipal.getInstancia().ligarTela();
  
        }
    
    }

    public void realizaAcaoTelaListarApostadores(AcoesBotao acao) {
        
        if(acao.equals(AcoesBotao.VoltarListagemApostadores)){
        
            TelaListarApostadoresDaRifa.getInstancia().desligarListagemApostadores();
            
            TelaRifaNaoFinalizada.getInstancia().ligar();
        
        }else if(acao.equals(AcoesBotao.VoltarListagemApostadoresGanhadores)){
        
            TelaListarApostadoresDaRifa.getInstancia().desligarListagemApostadoresGanhadores();
            
            TelaRifaFinalizada.getInstancia().ligar();
        
        }
        
    }

    public String[] getNomeApostadoresGanhadores() {
        
        return this.rifaControlada.getNomeApostadoresGanhadores();
        
    }

}