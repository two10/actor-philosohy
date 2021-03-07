/**
 * 
 */
package com.lightbend.akka.sample.fault.tolerant;


import com.lightbend.akka.sample.Greeter.Greet;
import com.lightbend.akka.sample.Greeter.WhoToGreet;
import com.lightbend.akka.sample.Printer.Greeting;

import akka.actor.AbstractActor;
import akka.actor.UntypedActor;
import scala.Option;

/**
 * This actor handles the division math operation 
 * @author Rakesh
 *
 */
public class DivisionActor extends AbstractActor {
	
	/**
	 * Actor method call before restart of the actor.
	 */
	
	
	
	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		System.out.println("Pre restart called");
		super.preRestart(reason, message);
	}
	
	/**
	 * Method call before actor onReceive method call.
	 */
	@Override
	public void preStart() throws Exception {
		System.out.println("Pre start called");
		super.preStart();
	}
	
	public static class DivisionOperation{
		public Long op1;
		public Long op2;
		
		public DivisionOperation(Long op1 , Long op2) {
			this.op1 = op1;
			this.op2 = op2;
		}
	}
	
	


	@Override
	public Receive createReceive() {
		return receiveBuilder()
		        .match(DivisionOperation.class, (dOp) -> {
		        	System.out.println(dOp.op1/dOp.op2);
		        })
		        .build();
	}

}
