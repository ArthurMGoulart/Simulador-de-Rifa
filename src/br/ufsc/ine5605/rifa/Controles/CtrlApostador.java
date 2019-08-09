/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Controles;

import br.ufsc.ine5605.rifa.Entidades.Apostador;
import br.ufsc.ine5605.rifa.Entidades.EstadoApostador;
import br.ufsc.ine5605.rifa.Entidades.Rifa;
import br.ufsc.ine5605.rifa.Telas.AcoesBotao;
import br.ufsc.ine5605.rifa.Telas.TelaAcessarApostador;
import br.ufsc.ine5605.rifa.Telas.TelaApostadorComprando;
import br.ufsc.ine5605.rifa.Telas.TelaApostadorGanhador;
import br.ufsc.ine5605.rifa.Telas.TelaApostadorIniciado;
import br.ufsc.ine5605.rifa.Telas.TelaCriarApostador;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ListModel;

/**
 *
 * @author budi
 */
public class CtrlApostador implements Serializable{

    private Apostador apostadorControlado;
    
    private static CtrlPrincipal controlador = CtrlPrincipal.getInstancia();
    
    private static MapeadorApostador cache = MapeadorApostador.getInstancia();
    
    private static CtrlApostador instancia;
    
    private CtrlApostador(){
        
        
    }
    
    public static CtrlApostador getInstancia(){
        
        if(instancia == null){
        
            instancia = new CtrlApostador();
        
        }
        
        return instancia;
        
    }

    public Apostador getApostadorControlado() {
        
        return apostadorControlado;
        
    }
    
    public String getNomeApostador(){
    
        return apostadorControlado.getNome();
        
    }
    
    public MapeadorApostador getMapeadorApostador(){
    
        return cache;
    
    }
    
    public String[] getNumerosDisponiveisVendaRifa(){
    
        return apostadorControlado.getRifaAssociada().getRifa().getNumerosDisponiveis();
    
    }
    
    public String[] getNumerosComprados(){
    
        return apostadorControlado.getNumerosComprados();
    
    }
    
    public void setApostadorControlado(Apostador apostadorControlado) {
        
        this.apostadorControlado = apostadorControlado;
        
    }
    
    public void addNumero(int numero) throws Exception{
        
        this.apostadorControlado.comprarNumero(numero);
        
        this.apostadorControlado.getRifaAssociada().getRifa().venderNumero(numero);
        
        TelaApostadorComprando.getInstancia().desligarPainelComprar();
        
        TelaApostadorComprando.getInstancia().atualizarInformacoes();
        
        TelaApostadorComprando.getInstancia().ligarPainelMenu();
        
    }
    
    public void acessarApostador(int cpf) throws Exception{
        
        setApostadorControlado(MapeadorApostador.getInstancia().findApostadorByCpf(cpf));
        
        if(getApostadorControlado().getEstado() == EstadoApostador.Iniciado){
            
            TelaAcessarApostador.getInstancia().desligar();
            
            TelaApostadorIniciado.getInstancia().atualizarNomeApostador();
            
            TelaApostadorIniciado.getInstancia().ligarPainelMenu();
     
        }else if(getApostadorControlado().getEstado() == EstadoApostador.ComprandoNumeros){
        
            TelaAcessarApostador.getInstancia().desligar();
            
            TelaApostadorComprando.getInstancia().atualizarInformacoes();
            
            TelaApostadorComprando.getInstancia().ligarPainelMenu();
        
        }else if(getApostadorControlado().getEstado() == EstadoApostador.Ganhador){
        
            TelaAcessarApostador.getInstancia().desligar();
            
            TelaApostadorGanhador.getInstancia().atualizarInformacoes();
            
            TelaApostadorGanhador.getInstancia().ligarPainelMenu();
   
        }
      
    }

    public void associarComRifa(int codigo) throws Exception{
        
        Rifa rifaParaAssociar = MapeadorRifa.getInstancia().findRifaByCodigo(codigo);
        
        if(rifaParaAssociar.isFinalizada()){
        
            throw new Exception("Rifa ja finalizada nao e possivel se associar");
        
        }
        
        this.apostadorControlado.associarRifa(rifaParaAssociar);
        
        CtrlPrincipal.getInstancia().getControladorRifa().associarApostador(rifaParaAssociar, this.apostadorControlado);
        
        TelaApostadorIniciado.getInstancia().desligarPainelAssociar();
        
        TelaApostadorIniciado.getInstancia().ligarPainelMenu();
        
    }

    public void criarApostador(String nome, int cpf) throws IllegalArgumentException, IOException{
        
        if(nome == null){
        
            throw new IllegalArgumentException("Nome nulo");
            
        }else if(cpf <= 0){
        
            throw new IllegalArgumentException("Cpf menor que zero");
        
        }
        
        Apostador apostador = new Apostador(nome, cpf);
        
        if(this.cache.temApostadorComCpf(cpf)){
        
            throw new IllegalArgumentException("Apostador ja existente com esse CPF");
         
        }else{
        
            this.cache.putApostador(apostador);
            
        }
        
        TelaCriarApostador.getInstancia().desligar();
            
        CtrlPrincipal.getInstancia().ligarTela();
        
    }

    public void deletarApostador() {
        
        if(this.apostadorControlado.getRifaAssociada() != null){
        
            this.apostadorControlado.getRifaAssociada().getRifa().deletarApostador(this.apostadorControlado);
        
        }
        
        this.cache.removerApostador(this.apostadorControlado);
        
        TelaApostadorIniciado.getInstancia().desligarPainelDeletar();
        
        CtrlPrincipal.getInstancia().ligarTela();
        
    }

    public void iniciarTelaCriarApostador() {
         
        TelaCriarApostador.getInstancia().ligar();
        
    }

    public void realizarAcaoCriarApostador(AcoesBotao acao) {
        
        if(acao.equals(AcoesBotao.CriarApostadorTela)){
        
            TelaCriarApostador.getInstancia().iniciarCriarApostador();
            
        
        }else if(acao.equals(AcoesBotao.VoltarTelaCriarApostador)){
        
            TelaCriarApostador.getInstancia().desligar();
            
            CtrlPrincipal.getInstancia().ligarTela();
        
        }
        
    }

    public void iniciarTelaAcessarApostador() {
        
        TelaAcessarApostador.getInstancia().ligar();
        
    }

    public void realizarAcaoAcessarApostador(AcoesBotao acao){
        
        if(acao.equals(AcoesBotao.AcessarApostadorTela)){
        
            TelaAcessarApostador.getInstancia().iniciarAcessarApostador();
        
        }else if(acao.equals(AcoesBotao.VoltarTelaAcessarApostador)){
            
            TelaAcessarApostador.getInstancia().desligar();
            
            CtrlPrincipal.getInstancia().ligarTela();
        
        }
        
    }
    
    public void realizaAcaoApostadorIniciado(AcoesBotao acao){
        
        if(acao.equals(AcoesBotao.AssociarApostadorIniciadoMenu)){
        
            TelaApostadorIniciado.getInstancia().desligarPainelMenu();
            
            TelaApostadorIniciado.getInstancia().ligarPainelAssociar();
        
        }else if(acao.equals(AcoesBotao.DeletarApostadorIniciadoMenu)){
        
            TelaApostadorIniciado.getInstancia().desligarPainelMenu();
            
            TelaApostadorIniciado.getInstancia().ligarPainelDeletar();
        
        }else if(acao.equals(AcoesBotao.VoltarApostadorIniciadoMenu)){
        
            TelaApostadorIniciado.getInstancia().desligarPainelMenu();
                        
            CtrlPrincipal.getInstancia().ligarTela();
        
        }else if(acao.equals(AcoesBotao.AssociarApostadorPainel)){
        
            TelaApostadorIniciado.getInstancia().iniciarAssociarApostador();

        }else if(acao.equals(AcoesBotao.VoltarApostadorIniciadoAssociar)){
        
            TelaApostadorIniciado.getInstancia().desligarPainelAssociar();
                        
            TelaApostadorIniciado.getInstancia().ligarPainelMenu();
        
        }else if(acao.equals(AcoesBotao.DeletarApostadorPainel)){
        
            TelaApostadorIniciado.getInstancia().iniciarDeletarApostador();
        
        }else if(acao.equals(AcoesBotao.VoltarApostadorIniciadoDeletar)){
        
            TelaApostadorIniciado.getInstancia().desligarPainelDeletar();
            
            TelaApostadorIniciado.getInstancia().ligarPainelMenu();

        }
    
    
    }

    public void realizaAcaoApostadorComprando(AcoesBotao acao) {
        
        if(acao.equals(AcoesBotao.ComprarNumeroMenu)){
            
            TelaApostadorComprando.getInstancia().desligarPainelMenu();
            
            TelaApostadorComprando.getInstancia().ligarPainelComprar();
        
        }else if(acao.equals(AcoesBotao.ListarNumerosDisponiveis)){
            
            TelaApostadorComprando.getInstancia().desligarPainelMenu();
        
            TelaApostadorComprando.getInstancia().ligarPainelListarNumerosDisponiveis();
        
        }else if(acao.equals(AcoesBotao.ListarNumerosComprados)){
        
            TelaApostadorComprando.getInstancia().desligarPainelMenu();
            
            TelaApostadorComprando.getInstancia().ligarPainelListarNumerosComprados();
        
        }else if(acao.equals(AcoesBotao.ComprarNumeroPainel)){
        
            TelaApostadorComprando.getInstancia().iniciarComprarNumero();
            
            TelaApostadorComprando.getInstancia().atualizarInformacoes();
        
        }else if(acao.equals(AcoesBotao.VoltarApostadorComprandoMenu)){
        
            TelaApostadorComprando.getInstancia().desligarPainelMenu();
            
            CtrlPrincipal.getInstancia().ligarTela();
        
        }else if(acao.equals(AcoesBotao.VoltarApostadorComprandoNumero)){
            
            TelaApostadorComprando.getInstancia().desligarPainelComprar();
            
            TelaApostadorComprando.getInstancia().ligarPainelMenu();
        
        }else if(acao.equals(AcoesBotao.VoltarApostadorComprandoListandoDisponiveis)){
            
            TelaApostadorComprando.getInstancia().desligarPainelListarNumerosDisponiveis();
            
            TelaApostadorComprando.getInstancia().ligarPainelMenu();
            
        }else if(acao.equals(AcoesBotao.VoltarApostadorComprandoListandoComprados)){
            
            TelaApostadorComprando.getInstancia().desligarPainelListarNumerosComprados();
        
            TelaApostadorComprando.getInstancia().ligarPainelMenu();
        
        }
        
    }

    public void realizaAcaoApostadorGanhador(AcoesBotao acao) {

        if(acao.equals(AcoesBotao.ListarProdutosGanhos)){
        
            TelaApostadorGanhador.getInstancia().desligarPainelMenu();
            
            TelaApostadorGanhador.getInstancia().ligarPainelListagem();
        
        }else if(acao.equals(AcoesBotao.VoltarApostadorComprandoMenu)){
        
            TelaApostadorGanhador.getInstancia().desligarPainelMenu();
            
            CtrlPrincipal.getInstancia().ligarTela();
    
        }else if(acao.equals(AcoesBotao.VoltarListagemApostadorGanhador)){
        
            TelaApostadorGanhador.getInstancia().desligarPainelListagem();
            
            TelaApostadorGanhador.getInstancia().ligarPainelMenu();
   
        }
        
    }

    public String[] getProdutosGanhos() {
        
        return this.apostadorControlado.getNomeProdutosGanhos();
        
    }

}