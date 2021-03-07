/**
 * 
 */
package com.lightbend.akka.sample.fault.tolerant;

import java.util.concurrent.TimeUnit;

import com.lightbend.akka.sample.fault.tolerant.DivisionActor.DivisionOperation;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

/**
 * Math operation Actor which supervise its child actors by handling various exception conditions.
 * @author Rakesh
 *
 */
public class MathOperation extends AbstractActor {
	ActorRef divActorRef = this.context().actorOf(Props.create(DivisionActor.class),"DivisionActor");
	/*
	 * Strategy to handle the exceptions occure inside the Division actor.
	 */
	SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.create(1,TimeUnit.SECONDS),new Function<Throwable, Directive>(){

		public Directive apply(Throwable throwable) throws Exception {
			if(throwable instanceof ArithmeticException || throwable instanceof NumberFormatException){
				System.out.println("Resume called");
				return SupervisorStrategy.resume();
			}else{
				System.out.println("Stop called");
				return SupervisorStrategy.stop();
			}
			
		}
		
	});
	
	/**
	 * Set the supervisor strategy.
	 */
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	};
	
	
	public static class PeformMathOperation {
		
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
        .match(PeformMathOperation.class, po -> {
        	DivisionOperation divisionOperationCorrect = new DivisionActor.DivisionOperation(10l, 10l);
        	DivisionOperation divisionOperationInCorrect = new DivisionActor.DivisionOperation(10l, 0l);
        	divActorRef.tell(divisionOperationCorrect, getSelf());
        	divActorRef.tell(divisionOperationInCorrect, getSelf());
        	divActorRef.tell(divisionOperationCorrect, getSelf());
          })
          
          .build();
	}

}
