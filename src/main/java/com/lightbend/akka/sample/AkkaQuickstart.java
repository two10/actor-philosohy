package com.lightbend.akka.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.lightbend.akka.sample.Greeter.Greet;
import com.lightbend.akka.sample.Greeter.WhoToGreet;

import java.io.IOException;

public class AkkaQuickstart {
  public static void main(String[] args) {
    // This is the system name of the actor
    final ActorSystem system = ActorSystem.create("nameOfTheActorSystem");
    try {
      //we have two actors here, one for printing and one for greeting
      final ActorRef printerActor1 =
        system.actorOf(Printer.props(), "nameSpaceOfPrintingActor");
      final ActorRef greetActor1 =
        system.actorOf(Greeter.props("messageStoredInInstance", printerActor1), "nameSpaceOfGreeterActor");

      //#main-send-messages
      greetActor1.tell(new WhoToGreet("messageInvokedGreet"), ActorRef.noSender());
      greetActor1.tell(new Greet(), ActorRef.noSender());

      System.out.println(">>> Press ENTER to exit <<<");
      System.in.read();
    } catch (IOException ioe) {
    } finally {
      system.terminate();
    }
  }
}
