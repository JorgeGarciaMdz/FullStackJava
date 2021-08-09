/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Guest;
import Persistence.FabricaEMF;
import Persistence.GuestJpaController;
import java.util.List;

/**
 *
 * @author jorge
 */
public class GuestService {

    private final GuestJpaController guestJpaController;

    public GuestService() {
        guestJpaController = new GuestJpaController(FabricaEMF.getInstance().getEMF("HotelPU"));
    }

    public Guest findById(int id) {
        return guestJpaController.findGuest(new Long(id));
    }

    public List<Guest> findAll() {
        return guestJpaController.findGuestEntities();
    }

    public void create(Guest g) {
        guestJpaController.create(g);
    }

    public boolean update(Guest g) {
        boolean state_transaccion = false;
        try {
             guestJpaController.edit(g);
            state_transaccion = true;
        } catch (Exception ex) {
            System.out.println("EmployeeService error: " + ex.getMessage());
        }
        return state_transaccion;
    }

    public void delete(int id) {
        // hard delete is not implemented
    }
}
