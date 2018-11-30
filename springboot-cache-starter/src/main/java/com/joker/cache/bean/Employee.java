package com.joker.cache.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Joker
 * @Description:
 * @Date: Created in 2018/11/30 15:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//DefaultSerializer requires a Serializable payload but received an object of type [com.joker.cache.bean.Employee]
public class Employee implements Serializable {

    private Integer id;

    private String lastName;

    private String email;

    private Integer gender;

    private Integer d_id;

    public Employee(Integer id, String lastName) {
        this.id = id;
        this.lastName = lastName;
    }

    public Employee(Integer id, String lastName, String email, Integer gender) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

}