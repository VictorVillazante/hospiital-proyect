package com.example.monolitoproyect.StateDessignPattern.interfaces;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.monolitoproyect.StateDessignPattern.contexts.Context;

public interface State {
    public void doAction(Context context);

    public String buscarVentaPorID();

    public Object getVentas();

    public Object add();
 }