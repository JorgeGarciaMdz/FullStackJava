/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.Reservation;
import Persistence.FabricaEMF;
import Persistence.ReservationJpaController;
import Servelet.DTO_Reservation;
import java.util.List;
import Entity.Guest;

/**
 *
 * @author jorge
 */
public class ReservationService {
    private final ReservationJpaController reservationJpaController;
    private final RoomService roomService;
    private final GuestService guestService;
    private final EmployeeService employeeService;

    public ReservationService() {
        reservationJpaController = new ReservationJpaController(FabricaEMF.getInstance().getEMF("HotelPU"));
        roomService = new RoomService();
        guestService = new GuestService();
        employeeService = new EmployeeService();
    }
    
    public Reservation findById( int id){
        return reservationJpaController.findReservation(new Long(id));
    }
    
    public List<Reservation> findByRoomId( int room_id ){
        System.out.println("reservation room " + room_id);
        return reservationJpaController.findByRoomId( new Long(room_id));        
    }
    
    public List<Reservation> findAll(){
        return reservationJpaController.findReservationEntities();
    }
    
    public void create( Reservation r){
        reservationJpaController.create(r);
    }
    
    public DTO_Reservation create( DTO_Reservation dto_r){
        Guest g = new Guest();
        g.setBirthday(dto_r.getBirthday());
        g.setDni(dto_r.getDni());
        g.setLastname(dto_r.getLastname());
        g.setName(dto_r.getName());
        g.setProfession(dto_r.getProfession());
        System.out.println(g.toString());
        guestService.create(g);
        System.out.println(g.toString());
        if( g.getId() != null){
            Reservation r = new Reservation();
            r.setDate_in(dto_r.getDate_in());
            r.setDate_out(dto_r.getDate_out());
            r.setGuest(g);
            r.setRoom(roomService.findById(dto_r.getRoom_id()));
            r.setEmployee(employeeService.findById(dto_r.getEmployee_id()));
            reservationJpaController.create(r);
            if( r.getId() != null ){
                dto_r.setId(Integer.parseInt(r.getId().toString()));
            }
        }
        return dto_r;
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
