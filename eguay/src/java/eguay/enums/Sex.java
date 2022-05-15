package eguay.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */
public enum Sex {
    HOMBRE(0, "Hombre"),
    MUJER(1, "Mujer"),
    OTRO(2, "No especificar");
    
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    private Sex(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public static Sex fromId(int id) {
        return Stream.of(values()).filter(s -> s.id == id).findFirst().orElse(OTRO);
    }
}
