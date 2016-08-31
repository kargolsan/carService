package Modules.Repairs.Models;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 24.08.2016
 * Time: 11:48
 */
@Entity
@Table(name = "SERVICES", uniqueConstraints = { @UniqueConstraint(columnNames = "SERVICE_ID") })
public class Service {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "SERVICE_ID")
    private Long id;

    @Column(name = "NAME", length = 1000)
    private String name;

    @Column(name = "MECHANIC", length = 100)
    private String mechanic;

    @Column(name = "QUANTITY", length = 8)
    private Double quantity;

    @Column(name = "PRICE_WITHOUT_TAX", precision=10, scale=2)
    private BigDecimal priceWithoutTax;

    @Column(name = "TAX_PERCENTAGE", precision=10, scale=2)
    private Double taxPercentage;

    @Column(name = "NOTE", length = 4000)
    private String note;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceWithoutTax() {
        return priceWithoutTax;
    }

    public void setPriceWithoutTax(BigDecimal priceWithoutTax) {
        this.priceWithoutTax = priceWithoutTax;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
