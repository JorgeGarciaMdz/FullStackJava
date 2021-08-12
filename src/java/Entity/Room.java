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

import Entity.Enum.RoomType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "room")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable = false)
    private Long id;
    
    @Column(name = "floor", nullable = false )
    private short floor;
    
    @Column(name = "door", nullable = false, length = 5)
    private String door;
    
    @Column(name = "capacity", nullable = false)
    private short capacity;
    
    @Column( name = "price", nullable = false)
    private float price;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false, length = 25)
    private RoomType type_room;    

    public Room() {
    }

    public Room(short floor, String door, short capacity, float price, RoomType type_room) {
        this.floor = floor;
        this.door = door;
        this.capacity = capacity;
        this.price = price;
        this.type_room = type_room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getFloor() {
        return floor;
    }

    public void setFloor(short floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public short getCapacity() {
        return capacity;
    }

    public void setCapacity(short capacity) {
        this.capacity = capacity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public RoomType getType_room() {
        return type_room;
    }

    public void setType_room(RoomType type_room) {
        this.type_room = type_room;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", floor=" + floor + ", door=" + door + ", capacity=" + capacity + ", price=" + price + ", type_room=" + type_room + '}';
    }    
}
