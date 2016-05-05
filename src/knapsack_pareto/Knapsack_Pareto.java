/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack_pareto;

import java.util.ArrayList;
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
        algoTest(list);
        /*
        for (Sac iterator : list) {
            System.out.println(iterator.stat());
            //System.out.println(iterator.toString());
        }
        */
    }
    public static void testerDom(HashSet<Sac> list){
        Sac sac =null;
        for (Sac iterator : list) {
            if(sac == null){
                sac = iterator;
            } else{
                System.out.println(iterator.dominant(sac));
                System.out.println(iterator);
                System.out.println(sac);
                break;
            }
            
            //System.out.println(iterator.stat());
            ///System.out.println(iterator.toString());
        }        
    }
    public static void algoTest(HashSet<Sac> list){
        HashSet<Sac> output = (HashSet<Sac>) list.clone();
        HashSet<Sac> invalide = new HashSet<>();
       
        for (Sac i : list) {
            if(invalide.contains(i)) continue;
            for(Sac j : list){
                if(!i.equals(j)){
                    if(i.dominant(j)){
                        output.remove(j);
                        invalide.add(j);
                        
                    } else if(j.dominant(i)){
                        output.remove(i);
                        invalide.add(i);
                        break;
                    }
                }
            }
        }
        
        for(Sac it: output){
            //System.out.println(it.stat());
            System.out.println(it);
        }
        
    }
    

}
