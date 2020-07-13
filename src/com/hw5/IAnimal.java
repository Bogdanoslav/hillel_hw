package com.hw5;

public interface IAnimal {
    default public String say(){
        return ("Hello, ");
    };
}
