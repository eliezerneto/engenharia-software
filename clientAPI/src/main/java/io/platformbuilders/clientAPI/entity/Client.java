package io.platformbuilders.clientAPI.entity;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "phone", nullable = false)
    private String phone;

    private Integer age;

    public Client() {
    }

    public Client(Long id, String name, String email, Date birthDate, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public Client(String name, String email, Date birthDate, String phone) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        LocalDate now = LocalDate.now();

        if ((now != null) && (getBirthDate() != null)) {
            return Period.between(getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), now)
                    .getYears();
        } else {
            return 0;
        }

    }

}