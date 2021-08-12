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
import javax.persistence.Table;

/**
 *
 * @author jorge
 */
@Entity
@Table(name="guest")
public class Guest extends Person implements Serializable{
       
    @NotNull
    @Column(name="profession", length = 150)
    private String profession;

    public Guest() {
    }

    public Guest(String profession, Integer dni, String name, String lastname, Date birthday) {
        super(dni, name, lastname, birthday);
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Guest{" + "profession=" + profession + '}' + super.toString();
    }
    
}
