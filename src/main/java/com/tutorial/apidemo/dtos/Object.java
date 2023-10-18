package com.tutorial.apidemo.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Constructor;

@Data
//@Getter
//@Setter
public class Object {
    @NotEmpty(message = "Can not is empty")
    private   String name;
//    public  Object(String name){
//        this.name=name;
//    }
//
//    public Object(){
//
//    }
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

}
