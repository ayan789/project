package com.example.tlyanencrypt.controller;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableQazSecurity {

    String value() default "";
}
