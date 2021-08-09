/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.Room;
import Persistence.FabricaEMF;
import Persistence.RoomJpaController;
import java.util.List;

/**
 *
 * @author jorge
 */
public class RoomService {
    private final RoomJpaController roomJpacontroller;

    public RoomService() {
        roomJpacontroller = new RoomJpaController(FabricaEMF.getInstance().getEMF("HotelPU"));
    }
    
    public Room findById( int id ){
        return roomJpacontroller.findRoom( new Long(id));
    }
    
    public List<Room> findByCapacity( int capacity ){
        return roomJpacontroller.findByCapacity(new Long(capacity));
    }
    
    public List<Room> findAll(){
        return roomJpacontroller.findRoomEntities();
    }
    
    public void create(Room r){
        roomJpacontroller.create(r);
    }
    
    public void delete( int id){
        // hard delete is not implemented
    }
    
    public boolean update( Room r){
        boolean state_transaccion = false;
        try {
            roomJpacontroller.edit(r);
            state_transaccion = true;
        } catch (Exception ex) {
            System.out.println("EmployeeService error: " + ex.getMessage());
        }
        return state_transaccion;
    }
}
