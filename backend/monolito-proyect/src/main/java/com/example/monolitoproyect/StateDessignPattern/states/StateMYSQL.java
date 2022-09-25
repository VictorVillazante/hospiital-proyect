package com.example.monolitoproyect.StateDessignPattern.states;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.monolitoproyect.StateDessignPattern.contexts.Context;
import com.example.monolitoproyect.StateDessignPattern.interfaces.State;
import com.example.monolitoproyect.StateDessignPattern.repository.PruebaEntityRepository;



@Service
public class StateMYSQL implements State {
   

   private PruebaEntityRepository pruebaEntityRepository;

   @Autowired
   public StateMYSQL(PruebaEntityRepository pruebaEntityRepository) {
       this.pruebaEntityRepository = pruebaEntityRepository;
   }


   // @Autowired
   // private BLPrueba pbl=new BLPrueba(per);

   //  @Autowired
   //  public StateMYSQL(BLPrueba pbl){
   //      //StateFirebase sf=new StateFirebase();
   //      this.pbl=pbl;
   //  }

   
   public void doAction(Context context) {
       System.out.println("usando mysql");
       context.setState(this);	
    }
 
    public String toString(){
       return "State MYSQL";
    }

   @Override
   public String buscarVentaPorID() {
      // TODO Auto-generated method stub
      return "buscarVentaPorID MYSQL";
   }
   
  

   @Override
   public Object getVentas() {
      // TODO Auto-generated method stub
      // per.findAll();
      //return new ResponseEntity(per.findAll(),HttpStatus.OK);
      // List<PruebaEntity> result = per.findAll();

      //return per.findAll();
      return this.pruebaEntityRepository.findAll();
      //return a.list();
   }
   @Override
   public ResponseEntity add() {
      // TODO Auto-generated method stub
      return null;
   }

 }