package com.demo.entity.mapmapping;

import javax.persistence.*;
import java.util.Map;

@Table
@Entity(name = "stockholder")
public class StockHolder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ElementCollection
    @CollectionTable(name = "stocks")
    @MapKeyColumn(name = "company_code")
    @Column(name = "company_name")
    private Map<String, String> assets;

    public StockHolder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, String> getAssets() {
        return assets;
    }

    public void setAssets(Map<String, String> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "StockHolder{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", assets=" + assets +
                '}';
    }
}
