/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Controles;

import br.ufsc.ine5605.rifa.Entidades.Apostador;
import br.ufsc.ine5605.rifa.Entidades.EstadoApostador;
import br.ufsc.ine5605.rifa.Entidades.Rifa;
import br.ufsc.ine5605.rifa.Telas.TelaAcessarApostador;
import br.ufsc.ine5605.rifa.Telas.TelaApostador;
import br.ufsc.ine5605.rifa.Telas.TelaApostadorIniciado;
import br.ufsc.ine5605.rifa.Telas.TelaCriarApostador;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author budi
 */
public class CtrlApostador implements Serializable{
    
    
    
    private TelaApostador tela;
    
    private Apostador apostadorControlado;
    
    private static CtrlPrincipal controlador = CtrlPrincipal.getInstancia();
    
    private static MapeadorApostador cache = MapeadorApostador.getInstancia();
    
    private static CtrlApostador instancia;
    
    private CtrlApostador(){
        
        this.tela = new TelaApostador("APOSTADOR"); 
        
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

    public TelaApostador getTela() {
        
        return tela;
        
    } 

    public CtrlPrincipal getControlador() {
        
        return controlador;
        
    }
    
    public void setApostadorControlado(Apostador apostadorControlado) {
        
        this.apostadorControlado = apostadorControlado;
        
    }
    
    public void addNumero(int numero) throws IllegalArgumentException{
        
        this.apostadorControlado.comprarNumero(numero);
        
    }
    
    public void acessarApostador(int cpf) throws Exception{
        
        setApostadorControlado(MapeadorApostador.getInstancia().findApostadorByCpf(cpf));
        
        if(getApostadorControlado().getEstado() == EstadoApostador.Iniciado){
            
            TelaAcessarApostador.getInstancia().desligar();
            
            TelaApostadorIniciado.getInstancia().ligarPainelMenu();
     
        }
      
    }

    public void associarComRifa(int codigo) throws Exception{
        
        Rifa rifaParaAssociar = MapeadorRifa.getInstancia().findRifaByCodigo(codigo);
        
        if(rifaParaAssociar.isFinalizada()){
        
            throw new Exception("Rifa ja finalizada nao e possivel se associar");
        
        }
        
        this.apostadorControlado.associarRifa(rifaParaAssociar);
        
        CtrlPrincipal.getInstancia().getControladorRifa().associarApostador(rifaParaAssociar, this.apostadorControlado);
        
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

    public void deletaApostador() {
        
        if(this.apostadorControlado.getRifaAssociada() != null){
        
            this.apostadorControlado.getRifaAssociada().getRifa().deletarApostador(this.apostadorControlado);
        
        }
        
        this.cache.removerApostador(this.apostadorControlado);
        
    }

    public void iniciarTelaCriarApostador() {
         
        TelaCriarApostador.getInstancia().ligar();
        
    }

    public void realizaAcaoCriarApostador(String acao) {
        
        if(acao.equals("Criar Apostador!")){
        
            TelaCriarApostador.getInstancia().iniciarCriarApostador();
            
        
        }else if(acao.equals("Voltar")){
        
            TelaCriarApostador.getInstancia().desligar();
            
            CtrlPrincipal.getInstancia().ligarTela();
        
        }
        
    }

    public void iniciarTelaAcessarApostador() {
        
        TelaAcessarApostador.getInstancia().ligar();
        
    }

    public void realizarAcaoAcessarApostador(String acao){
        
        if(acao.equals("Acessar Apostador")){
        
            TelaAcessarApostador.getInstancia().iniciarAcessarApostador();
        
        }else if(acao.equals("Voltar")){
            
            TelaAcessarApostador.getInstancia().desligar();
            
            CtrlPrincipal.getInstancia().ligarTela();
        
        }
        
    }
    
    public void realizaAcaoApostadorIniciado(String acao){
        
        if(acao.equals("Associar Com Rifa")){
        
            TelaApostadorIniciado.getInstancia().desligarPainelMenu();
            
            TelaApostadorIniciado.getInstancia().ligarPainelAssociar();
        
        }else if(acao.equals("Deletar Apostador")){
        
            TelaApostadorIniciado.getInstancia().desligarPainelMenu();
        
        }else if(acao.equals("Voltar")){
        
            TelaApostadorIniciado.getInstancia().desligarPainelMenu();
                        
            CtrlPrincipal.getInstancia().ligarTela();
        
        }else if(acao.equals("Voltar Painel Associar")){
        
            TelaApostadorIniciado.getInstancia().desligarPainelAssociar();
                        
            TelaApostadorIniciado.getInstancia().ligarPainelMenu();
        
        }
    
    
    }

}