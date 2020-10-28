
package geneticAlgorithm;

/**
 * @author William Kirby
 */
public class GenerationCreator {
    
        protected final double chanceToMutate;
        protected final double chanceToCrossOver;
        
        public GenerationCreator(double chanceToMutate,double chanceToCrossOver) {
            this.chanceToMutate = chanceToMutate;
            this.chanceToCrossOver = chanceToCrossOver;
        }
        

    public CompIndividual[] getNextGeneration(CompIndividual[] individuals) {
        CompIndividual[] offSpring = new CompIndividual[individuals.length];
        offSpring[0] = this.getBestIndividual(individuals).createChild();
        for(int x = 1;x < individuals.length;x++) {
            int parentOneIndex = (int) (Math.random()*individuals.length);
            int parentTwoIndex = (int) (Math.random()*individuals.length);
            if(individuals[parentOneIndex].getFitness() > individuals[parentTwoIndex].getFitness()) {
                offSpring[x] = individuals[parentOneIndex].createChild();
            }
            else {
                offSpring[x] = individuals[parentTwoIndex].createChild();
            }
        }
        for(int x = 1;x < offSpring.length;x++) {
            offSpring[x].applyMutation(this.chanceToMutate);
        }
        this.applyCrossOverToGeneration(offSpring);
        return offSpring;  
    }
    
    protected void applyCrossOverToGeneration(CompIndividual[] individuals)
    {
        for(int x = 1;x < individuals.length - 1;x = x + 2)
        {
            double randomNumber = Math.random();
            if(randomNumber >= this.chanceToCrossOver) {
                individuals[x].crossOverIndividual(individuals[x + 1]);
            }
        }
    }

    private CompIndividual getBestIndividual(CompIndividual[] individuals) {
        CompIndividual bestInd = individuals[1];
        for(int x = 1;x < individuals.length; x++) {
            if(bestInd.getFitness() < individuals[x].getFitness()) {
                bestInd = individuals[x];
            }
        }
        return bestInd.createChild();
    }
}
