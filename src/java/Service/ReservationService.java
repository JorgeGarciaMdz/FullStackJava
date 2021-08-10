/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.Reservation;
import Persistence.FabricaEMF;
import Persistence.ReservationJpaController;
import java.util.List;

/**
 *
 * @author jorge
 */
public class ReservationService {
    private final ReservationJpaController reservationJpaController;
    private final RoomService roomService;

    public ReservationService() {
        reservationJpaController = new ReservationJpaController(FabricaEMF.getInstance().getEMF("HotelPU"));
        roomService = new RoomService();
    }
    
    public Reservation findById( int id){
        return reservationJpaController.findReservation(new Long(id));
    }
    
    public List<Reservation> findByRoomId( int id ){
        System.out.println("reservation room " + id);
        //return reservationJpaController.findByRoomId(roomService.findById( id ));
        return null;
    }
    
    public List<Reservation> findAll(){
        return reservationJpaController.findReservationEntities();
    }
    
    public void create( Reservation r){
        reservationJpaController.create(r);
    }
    
    public boolean update( Reservation r){
        boolean state_transaccion = false;
        try {
            reservationJpaController.edit(r);
            state_transaccion = true;
        } catch (Exception ex) {
            System.out.println("EmployeeService error: " + ex.getMessage());
        }
        return state_transaccion;
    }
    
    public void delete(int id){
        // hard delete is not implemented
    }
}
