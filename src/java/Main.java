/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Employee;
import Entity.Enum.EmployeeType;
import Entity.Enum.RoomType;
import Entity.Room;
import java.util.Date;
import Service.EmployeeService;
import Service.RoomService;
/**
 *
 * @author jorge
 */
public class Main {
    public static void main( String [] args){
        EmployeeService es = new EmployeeService();
        es.initEmployee();
        RoomService rs = new RoomService();
        rs.create(new Room( (short) 0, "0A", (short) 1, 139, RoomType.one_person));
        rs.create(new Room((short) 0, "0B", (short) 2, 167, RoomType.two_person));
        rs.create(new Room((short) 0, "0C", (short) 3, 199, RoomType.three_person));
        rs.create(new Room((short) 0, "0D", (short) 4, 233, RoomType.multi_person));
    }
    
}
