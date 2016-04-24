/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack_pareto;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Sebastien
 */
public class Sac {
    /*CONSTANTE*/

    static int[] nbItems = new int[]{5, 2, 6, 7}; // NUMBER of items for each type
    int[] weights = new int[]{5, 8, 7, 8}; // weight of an item for each type
    int[] lipides = new int[]{5, 9, 8, 1}; // quantity of lipids of the item
    int[] glucoses = new int[]{9, 5, 7, 32}; // quantity of glucose of the item
    int WEIGHT_MAX = 80;
    double ALPHA_LIPIDE = 1;
    double ALPHA_GLUCOSE = 1;
    int LIPID_MAX = 200;
    int GLUCOSE_MAX = 200;
    final static int NUMBER = 4;
    String[] NAMES = {"Strawberry jam", "Bananas", "Apple", "Honney"};

    int[] contenus = new int[NUMBER];

    boolean valide() {
        int weight = 0;
        for (int i = 0; i < NUMBER; i++) {
            weight += contenus[i] * weights[i];
            if (weight > WEIGHT_MAX) {
                return false;
            }
        }
        return true;
    }
    /*Prend en consédération les 3 paramètre*/
    boolean validePoid(){
        int weight = 0;
        int lipide=0;
        int glucose=0;
        for (int i = 0; i < NUMBER; i++) {
            weight += contenus[i] * weights[i];
            glucose += glucoses[i] * contenus[i];
            lipide += lipides[i] * contenus[i];
            if (weight > WEIGHT_MAX || glucose>GLUCOSE_MAX || lipide >LIPID_MAX) {
                return false;
            }
        }
        //System.out.println(getGlucose()+" "+getLipide()+" "+getWeight());
        return true;
    
    }

    @Override
    public Sac clone() {
        Sac clone = new Sac();
        for (int i = 0; i < NUMBER; i++) {
            clone.contenus[i] = contenus[i];
        }
        return clone;
    }

    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < NUMBER; i++) {
            text += NAMES[i] + " : " + contenus[i];
            text += System.getProperty("line.separator");
        }
        text+= "Glucose : " + getGlucose() +System.getProperty("line.separator");        
        text+= "Lipide : " + getLipide()+System.getProperty("line.separator");
        text+= "Poid : " + getWeight() +System.getProperty("line.separator");
        return text;
    }
    public String stat(){
        return getGlucose()+" "+getLipide()+" "+getWeight();
    }

    public int getGlucose() {
        int glucose = 0;
        for (int i = 0; i < NUMBER; i++) {
            glucose += glucoses[i] * contenus[i];
        }
        return glucose;
    }

    public int getLipide() {
        int lipide = 0;
        for (int i = 0; i < NUMBER; i++) {
            lipide += lipides[i] * contenus[i];
        }
        return lipide;
    }
    public int getWeight(){
        int weight = 0;
        for (int i = 0; i < NUMBER; i++) {
            weight += weights[i] * contenus[i];
        }
        return weight;
    }
    
    @Override
    public boolean equals(Object obj){
        Sac sac = (Sac) obj;
        for (int i = 0; i < NUMBER; i++) {
            if(contenus[i] !=sac.contenus[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Arrays.hashCode(this.contenus);
        return hash;
    }

    public static HashSet<Sac> genererSacPossible() {
        HashSet<Sac> liste = genererRecursif(0, new Sac());
        //System.out.println(liste.size());
        /*
         for (Sac iterator : liste) {
         System.out.println(iterator.toString());
         }
         */
        return liste;
    }

    private static HashSet<Sac> genererRecursif(int index, Sac input) {
        //System.out.println(index);
        if (index > NUMBER-1) {
            return new HashSet<>();
        }
        HashSet<Sac> liste = new HashSet<>();
        
        for (int i = 0; i <= nbItems[index]; i++) {
            Sac sac = input.clone();
            sac.contenus[index] = i;
            //System.out.println(sac);
            HashSet<Sac> listTemp = genererRecursif(index + 1, sac);
            liste.addAll(listTemp);
            if (sac.valide() && !liste.contains(sac)) {
                //System.out.println(sac.stat());
                //System.out.println(sac);
                liste.add(sac);
            }
        }
        return liste;
    }
    
    public boolean dominant(Sac sac){
        if(getGlucose()>sac.getGlucose() && getLipide()>=sac.getLipide()){
            return true;
        }
        if(getGlucose()>=sac.getGlucose() && getLipide()>sac.getLipide()){
            return true;
        }
        return false;
    }
    public boolean dominantPoid(Sac sac){
        if(getGlucose()<sac.getGlucose() || getLipide()<sac.getLipide() || getWeight()<sac.getWeight()){
            return false;
        } else if (getGlucose()==sac.getGlucose() && getLipide()==sac.getLipide() || getWeight()==sac.getWeight() ){
            return false;
        }
        return false;
    }
}
