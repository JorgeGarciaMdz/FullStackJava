/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Enum.EmployeeType;

/**
 *
 * @author jorge
 */
public class DTO_Employee {
    private String user;
    private String password;
    private EmployeeType type;
    private int dni;
    private String name;
    private String lastname;
    private String birthday;

    public DTO_Employee() {
    }

    public DTO_Employee(String user, String password, EmployeeType type, int dni, String name, String lastname, String birthday) {
        this.user = user;
        this.password = password;
        this.type = type;
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
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

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "DTO_Employee{" + "user=" + user + ", password=" + password + ", type=" + type + ", dni=" + dni + ", name=" + name + ", lastname=" + lastname + ", birthday=" + birthday + '}';
    } 
    
}
