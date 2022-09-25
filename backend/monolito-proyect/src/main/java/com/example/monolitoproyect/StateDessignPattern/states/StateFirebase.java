package com.example.monolitoproyect.StateDessignPattern.states;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.monolitoproyect.StateDessignPattern.contexts.Context;
import com.example.monolitoproyect.StateDessignPattern.interfaces.State;
import com.example.monolitoproyect.StateDessignPattern.service.impl.PostManagementServiceImpl;



public class StateFirebase implements State {
   // @Autowired
   // private PostManagementService service;
   private PostManagementServiceImpl pms=new PostManagementServiceImpl();
   
    public void doAction(Context context) {
       System.out.println("Usando firebase");
       context.setState(this);	
    }
 
    public String toString(){
       return "State firebase";
    }

   
   // @Override
   // public Object getVentas() {
   //    // TODO Auto-generated method stub
   //    return new ResponseEntity(pms.list(),HttpStatus.OK);
   //    //return null;
   // }
   public Object getVentas() {
      // TODO Auto-generated method stub
      return new ResponseEntity(pms.list(),HttpStatus.OK);
      //return null;
   }
   @Override
   public ResponseEntity add() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String buscarVentaPorID() {
      // TODO Auto-generated method stub
      return null;
   }
 }
