/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author jorge
 */

import Entity.Enum.EmployeeType;
import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jorge
 */
@Entity
@Table(name="employee")
public class Employee extends Person implements Serializable{
    
    @NotNull
    @Column(name="user", length = 30, nullable = false, unique = true)
    private String user;
    
    @NotNull
    @Column(name="password", length = 255, nullable = false)
    private String password;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name="admission_date", nullable = false)
    private Date admission_date;
    
    @Temporal(TemporalType.DATE)
    @Column(name="discharge_date", nullable = true)
    private Date discharge_date;
    
    @Enumerated(EnumType.STRING)
    @Column(name="employe_type", nullable = false, length = 25)
    private EmployeeType type;

    public Employee() {
    }

    public Employee(String user, String password, EmployeeType type, Integer dni, String name, String lastname, Date birthday) {
        super(dni, name, lastname, birthday);
        this.user = user;
        this.password = password;
        this.admission_date = new Date();
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(Date admission_date) {
        this.admission_date = admission_date;
    }

    public Date getDischarge_date() {
        return discharge_date;
    }

    public void setDischarge_date(Date discharge_date) {
        this.discharge_date = discharge_date;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Employee{" + "user=" + user + ", password=" + password +
               ", admission_date=" + admission_date + ", discharge_date=" + discharge_date + 
               ", type=" + type + '}' + super.toString();
    }
    
}
