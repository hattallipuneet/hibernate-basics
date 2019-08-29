package com.entity.sorted.mapmapping;

import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.*;

@Table
@Entity(name = "tourist")
public class Tourist {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ElementCollection
    @CollectionTable(name = "places")
    @Column(name = "description")
    @MapKeyColumn(name = "name")
//    @OrderBy("name DESC")
    @SortComparator(LengthComparator.class)
    private Map<String, String> places;

    public Tourist() {
    }

    public Tourist(String firstName, String lastName) {
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

    public Map<String, String> getPlaces() {
        return places;
    }

    public void setPlaces(Map<String, String> places) {
        this.places = places;
    }

    public static class LengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 == o2)? 0 : ((o1.length() > o2.length()) ? -1 : 1);
        }
    }
}
