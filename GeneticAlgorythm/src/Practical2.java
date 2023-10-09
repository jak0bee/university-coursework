import java.util.Random;

/**
 * Some very basic stuff to get you started. It shows basically how each
 * chromosome is built.
 *
 * @author Jo Stevens
 * @author Alard Roebroeck
 * @version 1.1, 12 Dec 2012
 */

public class Practical2 {

    static final String TARGET = "HELLO WORLD";
    static char[] alphabet = new char[27];
    private static final int popSize = 100;

    public static void main(String[] args) {
        //creating a population
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet[c - 'A'] = c;
        }
        alphabet[26] = ' ';
        Random generator = new Random(System.currentTimeMillis());
        Individual[] population = new Individual[popSize];
        // we initialize the population with random characters
        for (int i = 0; i < popSize; i++) {
            char[] tempChromosome = new char[TARGET.length()];
            for (int j = 0; j < TARGET.length(); j++) {
                tempChromosome[j] = alphabet[generator.nextInt(alphabet.length)]; //choose a random letter in the alphabet
            }
            population[i] = new Individual(tempChromosome);
        }
        HeapSort.sort(population);
        //asking the user for their choice
        int howManyToEvolve = 2;
        elitist(population, howManyToEvolve);
    }

    public static void elitist(Individual[] population, int howManyToEvolve) {
        int popSize = population.length;
        for (int safety = 0; safety < 30000; safety++) {
            if (population[0].getFitness() == 1) {
                System.out.println("this is the: " + safety + " iteration, the best is our target: ");
                System.out.println(population[0].genoToPhenotype());
                System.out.println("the rest of the population with their fitness values:");
                System.out.println("done");
                break;
            }
            Individual[] nextIteration = new Individual[popSize];
            for (int index = 0; index < popSize; index++) {
                //System.out.println("using index: "+ (index%HowManyToEvolve)+" and: "+((index%HowManyToEvolve)+1) );
                nextIteration[index] = Evolve(population[index % howManyToEvolve], population[(index % howManyToEvolve) + 1]);
            }
            population = nextIteration.clone();
            HeapSort.sort(population);
        }
    }

    public static Individual Evolve(Individual one, Individual two, int mutationRate) {
        char[] chromosome = new char[TARGET.length()];
        for (int i = 0; i < 6; i++) {
            chromosome[i] = one.getChromosome()[i];
        }

        for (int i = 6; i < TARGET.length(); i++) {
            chromosome[i] = two.getChromosome()[i];
        }
        Random r = new Random();
        //random
        for (int a = 0; a < mutationRate; a++) {
            char random_char = alphabet[r.nextInt(alphabet.length)];
            int random_int = r.nextInt(TARGET.length());
            chromosome[random_int] = random_char;
        }

        return new Individual(chromosome);
    }

    public static Individual Evolve(Individual one, Individual two) {
        return Evolve(one, two, 2);
    }

}
