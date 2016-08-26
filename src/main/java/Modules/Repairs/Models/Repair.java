package Modules.Repairs.Models;

import java.util.Date;
import java.util.Set;
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

    @Column(name = "PAID")
    @Type(type="true_false")
    private Boolean paid;

    @Column(name = "CREATED_AT")
    @Type(type = "timestamp")
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Type(type = "timestamp")
    private Date updatedAt;

    /** parts of repair */
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            joinColumns=@JoinColumn(name="ID"))
    private Set<Part> parts;

    /** services of repair */
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            joinColumns=@JoinColumn(name="ID"))
    private Set<Service> services;

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

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
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

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
