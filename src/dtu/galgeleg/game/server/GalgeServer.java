/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.galgeleg.game.server;

import java.rmi.Naming;

/**
 *
 * @author hamenpc
 */
public class GalgeServer {
    public static void main(String[] arg) throws Exception{
        java.rmi.registry.LocateRegistry.createRegistry(9912);
        GalgelegImpl g = new GalgelegImpl();
        Naming.rebind("rmi://localhost/galgelegnexus", g);
        System.out.println("GalgeServer spiller max");
    }
}
