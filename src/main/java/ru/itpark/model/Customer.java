package ru.itpark.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Customer implements Serializable {
    private int id;
    private String name;
    private String login;
    private int order_id;

    @Override
    public String toString() {
        return "Customer (id " + this.id + ", login "
                + this.login + ", name " + name
                + ", order_id " + order_id + "\n";
    }
}
