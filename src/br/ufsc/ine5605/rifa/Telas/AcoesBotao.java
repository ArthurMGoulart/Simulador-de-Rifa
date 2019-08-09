/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Telas;

import java.io.Serializable;

/**
 *
 * @author budi
 */
public enum AcoesBotao implements Serializable{
    
    //** Enum de acoes que os botoes da TelaAcessarApostador executam */
    
    AcessarApostadorTela, VoltarTelaAcessarApostador,
    
    //** Enum de acoes que os botoes da TelaAcessarRifa executam */
    
    AcessarRifaTela, VoltarTelaAcessarRifa,
    
    //** Enum de acoes que os botoes da TelaCriarApostador executam */
    
    CriarApostadorTela, VoltarTelaCriarApostador,
    
    //** Enum de acoes que os botoes da TelaCriarRifa executam */
    
    CriarRifaTela, VoltarTelaCriarRifa,
    
    //** Enum de acoes que os botoes da TelaPrincipal executam */
    
    CriarRifaMenu, CriarApostadorMenu, AcessarRifaMenu, AcessarApostadorMenu, Encerrar,
    
    //** Enum de acoes que os botoes da TelaApostadorGanhador executam */
    
    ListarProdutosGanhos, VoltarApostadorGanhadorMenu, VoltarListagemApostadorGanhador,
    
    //** Enum de acoes que os botoes da TelaProduto executam */
    
    AdicionarProdutoPainel, VoltarTelaProdutoListando, VoltarTelaProdutoAdicionando, 
    
    //** Enum de acoes que os botoes da TelaApostadorComprando executam */
    
    ComprarNumeroMenu, ListarNumerosDisponiveis, ListarNumerosComprados, ComprarNumeroPainel,
    
    VoltarApostadorComprandoMenu, VoltarApostadorComprandoNumero,
    
    VoltarApostadorComprandoListandoDisponiveis, VoltarApostadorComprandoListandoComprados,
    
    //** Enum de acoes que os botoes da TelaListarApostadoresDaRifa executam */
    
    VoltarListagemApostadores, VoltarListagemApostadoresGanhadores,
    
    //** Enum de acoes que os botoes da TelaRifaFinalizada executam */
    
    SortearProduto, ListarApostadoresGanhadores, VoltarRifaFinalizada,
    
    //** Enum de acoes que os botoes da TelaRifaNaoFinalizada executam */
    
    AdicionarProdutoRifaNaoFinalizada, ListarProdutosRifaNaoFinalizada, ListarApostadoresRifaNaoFinalizada,
    
    FinalizarRifaNaoFinalizada, VoltarRifaNaoFinalizada,

    /** Enum de acoes que os botoes da TelaApostadorIniciado executam */
    
    AssociarApostadorIniciadoMenu, DeletarApostadorIniciadoMenu,
    
    VoltarApostadorIniciadoMenu, VoltarApostadorIniciadoAssociar, VoltarApostadorIniciadoDeletar, 
    
    AssociarApostadorPainel, DeletarApostadorPainel
     
}
