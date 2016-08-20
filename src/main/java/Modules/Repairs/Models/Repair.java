package Modules.Repairs.Models;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 12:21
 */
@Entity
@Table(name = "REPAIRS", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class Repair {

    public Repair() {

    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE_START")
    @Type(type = "timestamp")
    private Date dateStart;

    @Column(name = "DATE_END")
    @Type(type = "timestamp")
    private Date dateEnd;

    @Column(name = "NOTE", length = 4000)
    private String note;

    @Column(name = "CAR_ID")
    private Long carId;

    @Column(name = "CREATED_AT")
    @Type(type = "timestamp")
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Type(type = "timestamp")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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
