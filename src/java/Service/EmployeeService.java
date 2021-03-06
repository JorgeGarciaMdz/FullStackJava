/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author jorge
 */
import Persistence.EmployeeJpaController;
import Persistence.FabricaEMF;
import Entity.Employee;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jorge
 */
public class EmployeeService {
    private final EmployeeJpaController employeeJpaController;

    public EmployeeService() {
        employeeJpaController = new EmployeeJpaController(FabricaEMF.getInstance().getEMF("HotelPU"));
    }
    
    public Employee findByUserPass( String user, String pass){
        return employeeJpaController.findEmployeeByUserPass(user, pass);
    }
    
    public List<Employee> findAll(){
        return employeeJpaController.findAllEmployeeEntities();
    }
    
    public Employee findById( int id ){
        return employeeJpaController.findEmployee( new Long(id));
    }
    
    public boolean update( Employee e){
        boolean state_transaccion = false;
        try {
            employeeJpaController.edit(e);
            state_transaccion = true;
        } catch (Exception ex) {
            System.out.println("EmployeeService error: " + ex.getMessage());
        }
        return state_transaccion;
    }
    
    public void create(Employee e){
        try {
            e.setAdmission_date(new Date());
            employeeJpaController.create(e);
        } catch (Exception ex) {
            System.out.println("EmployeeService error: " + ex.getMessage());
        }
        System.out.println(e.toString());
    }
    
    public void delete(int id){
        try {
            Employee e = findById( id );
            e.setDischarge_date(new Date());
            employeeJpaController.edit(e);
        } catch (Exception ex) {
            System.out.println("EmployeeService error: " + ex.getMessage());
        }
    }
}
