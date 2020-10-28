/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticAlgorithm;

/**
 * 
 * @author William Kirby
 */
public class IndividualCompetition {
    
    /**
     * calculate the fitness for the individuals by competing them against each other
     * @param individuals: individuals to compete
     */
    public static void competeIndividuals(CompIndividual[] individuals) {
        
        double[] runningTotals = new double[individuals.length];
        
        // calculate the average win rate for each individual
        // these loops run each player against all other players
        for(int x = 0;x < individuals.length;x++) {
            for(int y = x + 1;y < individuals.length;y++) {
                if(individuals[x].doesIndBeatInd(individuals[y])) {
                    runningTotals[x] = runningTotals[x] + 1;
                }
                else {
                    runningTotals[y] = runningTotals[y] + 1;
                }
            }
        }
        
        // make average win rate the fitness
        for(int x = 0;x < runningTotals.length;x++) {
            individuals[x].setFitness(runningTotals[x]/(individuals.length - 1));
        }
    }  
}
