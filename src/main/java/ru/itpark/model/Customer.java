package ru.itpark.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
    private int id;
    private String name;
    private String login;
    private int order_id;

    @Override
    public String toString() {
        return "Customer (id " + this.id + ", login "
                + this.login + ", name " + name
                + ", order_id " + order_id + "\n"; //size: " + sizeOf();
    }

//    public int sizeOf() {
//        return id + name.length() + login.length() + order_id;
//    }
}
