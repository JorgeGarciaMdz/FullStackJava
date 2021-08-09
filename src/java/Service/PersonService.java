/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Persistence.FabricaEMF;
import Persistence.PersonJpaController;

/**
 *
 * @author jorge
 */
public class PersonService {
    private PersonJpaController personJpaController;

    public PersonService() {
        personJpaController = new PersonJpaController(FabricaEMF.getInstance().getEMF("HotelPU"));
    }    
}
