/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author jorge
 */
public class FabricaEMF {
    
    private static EntityManagerFactory emf;
    
    private FabricaEMF() {
    }
    
    public static FabricaEMF getInstance() {
        return FabricaEMFHolder.INSTANCE;
    }
    
    private static class FabricaEMFHolder {

        private static final FabricaEMF INSTANCE = new FabricaEMF();
    }
    
    public EntityManagerFactory getEMF(String namePersistenceUnit){
        if( emf == null)
            emf = Persistence.createEntityManagerFactory(namePersistenceUnit);
        return emf;
    }
}
