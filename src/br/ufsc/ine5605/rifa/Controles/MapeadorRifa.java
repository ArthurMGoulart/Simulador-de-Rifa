/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.rifa.Controles;

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
 * @author 05380317006
 */
public class MapeadorRifa implements Serializable{

    private HashMap<Integer, Rifa> cacheRifas;
    
    private static MapeadorRifa instancia;
    
    private final String filename;
    
    private MapeadorRifa(){
    
        cacheRifas = new HashMap();
        
        filename = "rifas.dat";
        
        load();
    
    }
    
    public Rifa getRifa(Integer codigo){
    
        return cacheRifas.get(codigo);
    
    }
    
    public void putRifa(Rifa rifa) throws IOException{
    
        cacheRifas.put(rifa.getCodigo(), rifa);
        
        persist();
    
    }
    
    public void removerRifa(Rifa rifa){
    
        cacheRifas.remove(rifa.getCodigo());
        
        persist();
    
    }
    
    public boolean temRifaComCodigo(int codigo){
    
        for(Rifa rifaTeste : this.cacheRifas.values()){
        
            if(rifaTeste.getCodigo() == codigo){
                
                return true;
                
            }    
        
        }
    
        return false;
        
    }
    
    public Rifa findRifaByCodigo(int codigo) throws Exception{
    
        if(cacheRifas.containsKey(codigo)){
        
            return (Rifa) cacheRifas.get(codigo);
        
        }else{
    
            throw new Exception("Nao Existe Rifa Com Esse Codigo");
        
        }
        
    }
    
    public void load(){
    
        try{
            
            FileInputStream fis = new FileInputStream(filename);
        
            ObjectInputStream ois = new ObjectInputStream(fis);
        
            this.cacheRifas = (HashMap<Integer, Rifa>) ois.readObject();
            
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
        
        oos.writeObject(cacheRifas);
        
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
    
        return new ArrayList(this.cacheRifas.values());
    
    }
    
    
    public static MapeadorRifa getInstancia(){
    
        if(instancia == null){
        
            instancia = new MapeadorRifa();
        
        }
        
        return instancia;
    
    }
    
}
