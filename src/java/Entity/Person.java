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
import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jorge
 */
@Entity
@Table(name="person")
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name="id")
    protected Long id;
    
    @NotNull
    @Column(name="dni", nullable=false, unique=true)
    protected Integer dni;
    
    @NotNull
    @Column(name="name", nullable=false, length=45)
    protected String name;
    
    @NotNull
    @Column(name="lastname", nullable=false, length=45)
    protected String lastname;
    
    @NotNull
    @Column(name="birthday", nullable=false)
    @Temporal(TemporalType.DATE)
    protected Date birthday;

    public Person() {
    }

    public Person(Integer dni, String name, String lastname, Date birthday) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
    }
    

    public Person(Long id, Integer dni, String name, String lastname, Date birthday) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", dni=" + dni + ", name=" + name + ", lastname=" + lastname + ", birthday=" + birthday +  '}';
    }
}
