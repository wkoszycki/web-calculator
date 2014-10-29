package com.cinemacity.calculator.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Configuration class for Jersey, responsible for registering resources. Can be
 * automatically auto-scaned, for now manually added to save overhead
 *
 * @author Wojciech Koszycki <wojciech.koszycki@gmail.com>
 */
public class RestConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CalculatorResource.class);
        return classes;
    }
}
