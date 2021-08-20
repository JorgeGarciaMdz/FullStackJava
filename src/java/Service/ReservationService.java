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
import Entity.Room;
import Entity.Employee;

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

    public Reservation findById(int id) {
        return reservationJpaController.findReservation(new Long(id));
    }

    public List<Reservation> findByRoomId(int room_id) {
        System.out.println("reservation room " + room_id);
        return reservationJpaController.findByRoomId(new Long(room_id));
    }
    
    public List<Reservation> findByDate(String date_from, String date_to){
        System.out.println( date_from + "   " + date_to);
        return reservationJpaController.findByDate(date_from, date_to);
    }
    
    public List<Reservation> findByDateAndEmployee( String date_from, String date_to, String id_employee){
        return reservationJpaController.findByDateAndEmployee(date_from, date_to, id_employee);
    }

    public List<Reservation> findAll() {
        return reservationJpaController.findReservationEntities();
    }

    public void create(Reservation r) {
        reservationJpaController.create(r);
    }

    public DTO_Reservation create(DTO_Reservation dto_r) {
        Guest g = guestService.findByDni(dto_r.getDni());
        if (g == null) {
            g = new Guest();
            g.setBirthday(dto_r.getBirthday());
            g.setDni(dto_r.getDni());
            g.setLastname(dto_r.getLastname());
            g.setName(dto_r.getName());
            g.setProfession(dto_r.getProfession());
            guestService.create(g);
        }        
        if (g.getId() != null) {
            Reservation r = new Reservation();
            r.setDate_in(dto_r.getDate_in());
            r.setDate_out(dto_r.getDate_out());
            r.setGuest(g);
            
            Room room = roomService.findById(dto_r.getRoom_id());
            Employee employee = employeeService.findById(dto_r.getEmployee_id());
            r.setRoom(room);
            r.setEmployee(employee);
            reservationJpaController.create(r);
            if (r.getId() != null) {
                dto_r.setId(Integer.parseInt(r.getId().toString()));
            }
        }
        return dto_r;
    }

    public boolean update(Reservation r) {
        boolean state_transaccion = false;
        try {
            reservationJpaController.edit(r);
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
