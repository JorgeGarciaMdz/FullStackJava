/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import java.util.Date;

/**
 *
 * @author jorge
 */
public class DTO_Reservation {
    private int id;
    private Date date_in;
    private Date date_out;
    private int room_id;
    private int employee_id;
    private String name;
    private String lastname;
    private int dni;
    private Date birthday;
    private String profession;

    public DTO_Reservation() {
    }

    public DTO_Reservation(int id, Date date_in, Date date_out, int room_id, int employee_id, String name, String lastname, int dni, Date birthday, String profession) {
        this.id = id;
        this.date_in = date_in;
        this.date_out = date_out;
        this.room_id = room_id;
        this.employee_id = employee_id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.birthday = birthday;
        this.profession = profession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public Date getDate_in() {
        return date_in;
    }

    public void setDate_in(Date date_in) {
        this.date_in = date_in;
    }

    public Date getDate_out() {
        return date_out;
    }

    public void setDate_out(Date date_out) {
        this.date_out = date_out;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "DTO_Reservation{" + "id=" + id + ", date_in=" + date_in + ", date_out=" + date_out + ", room_id=" + room_id + ", employee_id=" + employee_id + ", name=" + name + ", lastname=" + lastname + ", dni=" + dni + ", birthday=" + birthday + ", profession=" + profession + '}';
    }
    
}
