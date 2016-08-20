package Modules.Cars.Models;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 16:13
 */
@Entity
@Table(name = "CARS", uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
public class Car {

    /**
     * Constructor
     */
    public Car() {

    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private Long id;

    @Column(name = "REGISTRATION_NUMBER", length = 20)
    private String registrationNumber;

    @Column(name = "VIN", length = 30)
    private String vin;

    @Column(name = "NOTE", length = 4000)
    private String note;

    @Column(name = "CREATED_AT")
    @Type(type="timestamp")
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Type(type="timestamp")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
