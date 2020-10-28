
package geneticAlgorithm;
import geneticAlgorithm.IndividualCompetition;

/**
 * @author William Kirby
 */
public class CompGeneticAlgorithm {
    
    protected final GenerationCreator generationCreator;
    protected CompIndividual[] individuals;
    protected int generationcounter;
    
    /**
    * use this to stop after a given generation
    * @return boolean for if it should end
    */
    protected boolean shouldEnd() {
        return this.generationcounter > 1000;
    }
    
    /**
    * use this to do anything for the current generation
    */
    protected void forCurrentGeneration() {
        this.generationcounter++;
    }
    
    /**
    * when all calculations have ended
    */   
    protected void atTheEnd() {
    
    }
    
    /**
    * before any calculations start
    */
    protected void atTheStart() {
    
    }
    
    // Constructor
    public CompGeneticAlgorithm(GenerationCreator generationCreator,CompIndividual[] individuals) {
        this.generationCreator = generationCreator;
        this.individuals = individuals;
    }
    
    /**
    * start computing the genetic algorithm
    */
    public final void calculate() {   
        this.atTheStart();
        while(true) {
            this.forCurrentGeneration();
            if(this.shouldEnd()) {
                break;
            }
            IndividualCompetition.competeIndividuals(individuals);
            this.individuals = this.generationCreator.getNextGeneration(this.individuals);
        }
        this.atTheEnd();
    }
}
