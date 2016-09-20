package com.absurd.hello.eneity;

import com.absurd.core.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/20.
 */
@Entity
@Table(name = "t_profit")
public class Profit extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Long fundkey;

    @Column(nullable = false)
    private String city;

    @Column(name="input_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inputTime;

    public Profit() {
        super();
    }

    public Profit(String name, Long fundkey, String city) {
        this.name = name;
        this.fundkey = fundkey;
        this.city = city;
        this.inputTime = new Date();
    }

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

    public Long getFundkey() {
        return fundkey;
    }

    public void setFundkey(Long fundkey) {
        this.fundkey = fundkey;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profit profit = (Profit) o;

        if (!name.equals(profit.name)) return false;
        if (!fundkey.equals(profit.fundkey)) return false;
        return city.equals(profit.city);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + fundkey.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
