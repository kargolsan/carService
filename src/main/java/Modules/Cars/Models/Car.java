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

    @Column(name = "USER", length = 4000)
    private String user;

    @Column(name = "PHONES", length = 100)
    private String phones;

    @Column(name = "YEAR_PRODUCTION", length = 20)
    private String yearProduction;

    @Column(name = "BODY", length = 100)
    private String body;

    @Column(name = "ENGINE_CAPACITY", length = 100)
    private String engineCapacity;

    @Column(name = "ENGINE_MODEL", length = 100)
    private String engineModel;

    @Column(name = "ENGINE_POWER", length = 100)
    private String enginePower;

    @Column(name = "MANUFACTURER", length = 100)
    private String manufacturer;

    @Column(name = "MODEL", length = 100)
    private String model;

    @Column(name = "FUEL", length = 100)
    private String fuel;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getYearProduction() {
        return yearProduction;
    }

    public void setYearProduction(String yearProduction) {
        this.yearProduction = yearProduction;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
