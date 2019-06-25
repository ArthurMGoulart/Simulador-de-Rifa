/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Controles;

import br.ufsc.ine5605.rifa.Entidades.Apostador;
import br.ufsc.ine5605.rifa.Entidades.Rifa;
import br.ufsc.ine5605.rifa.Telas.TelaPrincipal;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author budi
 */
public class MapeadorApostador implements Serializable{
    
    private HashMap<Integer, Apostador> cacheApostadores;
    
    private static MapeadorApostador instancia;
    
    private final String filename;
    
    private MapeadorApostador(){
    
        cacheApostadores = new HashMap();
        
        filename = "apostadores.dat";
        
        load();
    
    }
    
    public Apostador getApostador(Integer cpf){
    
        return cacheApostadores.get(cpf);
    
    }
    
    public void putApostador(Apostador apostador) throws IOException{
    
        cacheApostadores.put(apostador.getCpf(), apostador);
        
        persist();
    
    }
    
    public void removerApostador(Apostador apostador){
    
        cacheApostadores.remove(apostador.getCpf());
        
        persist();
    
    }
    
    public boolean temApostadorComCpf(int cpf){
    
        for(Apostador apostadorTeste : this.cacheApostadores.values()){
        
            if(apostadorTeste.getCpf() == cpf){
                
                return true;
                
            }    
        
        }
    
        return false;
        
    }
    
    public Apostador findApostadorByCpf(int cpf) throws Exception{
    
        for(Apostador apostadorTeste : this.cacheApostadores.values()){
        
            if(apostadorTeste.getCpf() == cpf){
                
                return apostadorTeste;
                
            }    
        
        }
    
        throw new Exception("Nao Existe Apostador Com Esse CPF");
        
    }
    
    public void load(){
    
        try{
            
            FileInputStream fis = new FileInputStream(filename);
        
            ObjectInputStream ois = new ObjectInputStream(fis);
        
            this.cacheApostadores = (HashMap<Integer, Apostador>) ois.readObject();
            
            ois.close();
            
            fis.close();
        
        }catch(FileNotFoundException e){
        
            persist();
            
            TelaPrincipal.getInstancia().exibirExecao("Arquivo nao encontrado " + e.getMessage());
            
        }catch(ClassNotFoundException e){
        
            TelaPrincipal.getInstancia().exibirExecao("Classe nao encontrada " + e.getMessage());
            
        }catch(IOException e){
            
            TelaPrincipal.getInstancia().exibirExecao("Erro na entrada e saida  " + e.getMessage());

        }    
        
    }
    
    public void persist(){
    
        try{
        
        FileOutputStream fos = new FileOutputStream(filename);
        
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(cacheApostadores);
        
        oos.flush();
        
        fos.flush();
        
        oos.close();
        
        fos.close();
        
        }catch(FileNotFoundException e){
        
            TelaPrincipal.getInstancia().exibirExecao("Arquivo nao encontrado " + e.getMessage());
        
        }catch(IOException e){
            
            TelaPrincipal.getInstancia().exibirExecao("Erro na entrada e saida  " + e.getMessage());
        
        }
        
    }
    
    public ArrayList<Rifa> getList(){
    
        return new ArrayList(this.cacheApostadores.values());
    
    }
    
    
    public static MapeadorApostador getInstancia(){
    
        if(instancia == null){
        
            instancia = new MapeadorApostador();
        
        }
        
        return instancia;
    
    }
    
}  