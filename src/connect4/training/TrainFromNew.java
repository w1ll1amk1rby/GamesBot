/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4.training;

import geneticAlgorithm.CompGeneticAlgorithm;
import geneticAlgorithm.GenerationCreator;
import geneticAlgorithm.CompIndividual;

/**
 *
 * @author William Kirby
 */
public class TrainFromNew {
    
    public static final double MUTATION_CHANCE = 0.05;
    public static final double CROSSOVER_CHANCE = 0;
    public static final int NUMB_INDIVIDUALS = 5;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GenerationCreator generationCreator = 
                new GenerationCreator(MUTATION_CHANCE,CROSSOVER_CHANCE);
        CompIndividual[] individuals = new CompIndividual[NUMB_INDIVIDUALS]; 
        for(int x = 0;x < individuals.length;x++) {
            individuals[x] = new Connect4Individual();
        }
        CompGeneticAlgorithm algor = new CompGeneticAlgorithm(generationCreator,individuals);
        algor.calculate();
    }
    
}
