
package geneticAlgorithm;


/**
 * @author William Kirby
 */
public abstract class CompIndividual {
    
    private double fitness;
    /*
    * create a new random CompIndividual 
    */
    public CompIndividual() {
        this.fitness = 0;
    }
    
    /**
     * apply the mutation chance to the individual
     * @param mutationChance: the chance of a mutation per index
    */
    public abstract void applyMutation(double mutationChance);
    
    /**
     * apply the mutation chance to the individual
     * @param other: the individual to cross over with
    */
    public abstract void crossOverIndividual(CompIndividual other);
    
    /**
     * create a child from the individual 
     * @return , new child
    */
    public abstract CompIndividual createChild();
    
    /**
     * compete the two individuals against each other
     * @param individual: individual to compete against
     * @return if the current individual won
     */
    public abstract boolean doesIndBeatInd(CompIndividual individual);

    public double getFitness() {
        return this.fitness;
    }
    
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
