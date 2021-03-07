package com.lightbend.akka.sample.fault.tolerant;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Main method to run the example
 * @author Rakesh
 *
 */
public class FaultToleranceMain {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create();
		ActorRef mathOp = system.actorOf(Props.create(MathOperation.class),"MathOperation");
		mathOp.tell(new MathOperation.PeformMathOperation(), ActorRef.noSender());
	}

}
