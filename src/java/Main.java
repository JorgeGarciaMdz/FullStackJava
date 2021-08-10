/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Employee;
import Entity.Enum.EmployeeType;
import Entity.Enum.RoomType;
import Entity.Guest;
import Entity.Reservation;
import Entity.Room;
import java.util.Date;
import Service.EmployeeService;
import Service.GuestService;
import Service.ReservationService;
import Service.RoomService;
/**
 *
 * @author jorge
 */
public class Main {
    public static void main( String [] args){
        EmployeeService es = new EmployeeService();
        es.create(new Employee("jor123", "1234", EmployeeType.employee, 12345, "jorge", "garcia", new Date()));
        es.create(new Employee("admin", "admin", EmployeeType.administrator, 9870, "admin1", "admin 1", new Date()));
        Employee e = new Employee("juan", "juan", EmployeeType.employee, 345, "juan", "juan", new Date());
        e.setDischarge_date(new Date());
        es.create(e);
        
        GuestService gs = new GuestService();
        gs.create(new Guest("obrero", new Integer(123421), "guest 1", "guest 1", new Date(1990, 11, 24)));
        RoomService rs = new RoomService();
        rs.create(new Room( (short) 0, "0A", (short) 1, 139, RoomType.one_person));
        rs.create(new Room( (short) 0, "0B", (short) 1, 139, RoomType.one_person));
        rs.create(new Room((short) 0, "0C", (short) 2, 167, RoomType.two_person));
        rs.create(new Room((short) 0, "0D", (short) 2, 167, RoomType.two_person));
        rs.create(new Room((short) 0, "0E", (short) 3, 199, RoomType.three_person));
        rs.create(new Room((short) 0, "0F", (short) 3, 199, RoomType.three_person));
        rs.create(new Room((short) 0, "0G", (short) 4, 233, RoomType.multi_person));
        rs.create(new Room((short) 0, "0H", (short) 4, 233, RoomType.multi_person));
        
        ReservationService res = new ReservationService();
        res.create( new Reservation(new Date(new Date().getYear(), 7, 11), new Date(new Date().getYear(), 7, 18), null, null, rs.findById(5), gs.findById(4), es.findById(1)));
        res.create( new Reservation(new Date(new Date().getYear(), 6, 30), new Date(new Date().getYear(), 7, 3), null, null, rs.findById(5), gs.findById(4), es.findById(1)));
        res.create( new Reservation(new Date(new Date().getYear(), 7, 30), new Date(new Date().getYear(), 8, 3), null, null, rs.findById(5), gs.findById(4), es.findById(1)));
    }
    
}
