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
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @Column( name = "date_in", nullable = false)
    private Date date_in;
    
    @Temporal(TemporalType.DATE)
    @Column( name = "date_out", nullable = false)
    private Date date_out;
    
    @Temporal(TemporalType.DATE)
    @Column( name = "check_in")
    private Date check_in;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "check_out")
    private Date check_out;

    @ManyToOne()
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    
    @ManyToOne()
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;
    
    @ManyToOne()
    @JoinColumn( name = "employee_id", nullable = false)
    private Employee employee;
    
    public Reservation() {
    }

    public Reservation( Date date_in, Date date_out, Date check_in, Date check_out, Room room, Guest guest, Employee employee) {
        this.date_in = date_in;
        this.date_out = date_out;
        this.check_in = check_in;
        this.check_out = check_out;
        this.room = room;
        this.guest = guest;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
}
