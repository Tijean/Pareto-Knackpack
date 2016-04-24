/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack_pareto;

import java.util.HashSet;

/**
 *
 * @author Sebastien
 */
public class Knapsack_Pareto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashSet<Sac> list = Sac.genererSacPossible();
        for (Sac iterator : list) {
            //System.out.println(iterator.stat());
            System.out.println(iterator.toString());
        }
    }

}
