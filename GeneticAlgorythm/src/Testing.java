import java.util.Random;

public class Testing {
    static final String TARGET = "HELLO WORLD";
    static char[] alphabet = new char[27];
    public static void main(String[] args) {
        testForAllowed(250);
        testingForSize();
        testingForMutation(0,7);
        testNoCrossover();

    }
    public static void testNoCrossover(){
        for (int i=0; i<10; i++){
            //creating a population
            int popSize=100;
            for (char c = 'A'; c <= 'Z'; c++) {
                alphabet[c - 'A'] = c;
            }
            alphabet[26] = ' ';
            Random generator = new Random(System.currentTimeMillis());
            Individual[] population = new Individual[popSize];
            // we initialize the population with random characters
            for (int p = 0; p < popSize; p++) {
                char[] tempChromosome = new char[TARGET.length()];
                for (int j = 0; j < TARGET.length(); j++) {
                    tempChromosome[j] = alphabet[generator.nextInt(alphabet.length)]; //choose a random letter in the alphabet
                }
                population[p] = new Individual(tempChromosome);
            }
            HeapSort.sort(population);
            System.out.println("for the population on "+popSize+" with no crossover, only mutations it takes "+elitistNoCross(population,2)+" generations");
        }
    }
    public static int elitistNoCross(Individual[] population, int howManyToEvolve) {
        int popSize = population.length;

        for (int safety = 0; safety < 30000; safety++) {
            if (population[0].getFitness() == 1) {
                return safety;
            }

            Individual[] nextIteration = new Individual[popSize];
            for (int index = 0; index < popSize; index++) {
                nextIteration[index] = EvolveNoCross(population[index % howManyToEvolve], 2);
            }
            population = nextIteration.clone();
            HeapSort.sort(population);


        }
        return -1;
    }
    public static Individual EvolveNoCross(Individual one, int mutationRate) {
        char[] chromosome = new char[TARGET.length()];
        for (int i = 0; i < TARGET.length(); i++) {
            chromosome[i] = one.getChromosome()[i];
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
    public static void testingForMutation(int start, int finish){
        int popSize=100;
        for (int i=start; i<finish; i++){
            //creating a population
            for (char c = 'A'; c <= 'Z'; c++) {
                alphabet[c - 'A'] = c;
            }
            alphabet[26] = ' ';
            Random generator = new Random(System.currentTimeMillis());
            Individual[] population = new Individual[popSize];
            // we initialize the population with random characters
            for (int p = 0; p < popSize; p++) {
                char[] tempChromosome = new char[TARGET.length()];
                for (int j = 0; j < TARGET.length(); j++) {
                    tempChromosome[j] = alphabet[generator.nextInt(alphabet.length)]; //choose a random letter in the alphabet
                }
                population[p] = new Individual(tempChromosome);
            }
            HeapSort.sort(population);
            System.out.println("for the mutation rate of "+i+" it needs "+ elitist(population,2,i)+" generations");
        }
    }
    public static void testingForSize() {
        System.out.println("--------------------------------------------");
        System.out.println("testing for size");
        for (int i = 1; i < 15; i++) {
            int popSize = i * 100;
            for (char c = 'A'; c <= 'Z'; c++) {
                alphabet[c - 'A'] = c;
            }
            alphabet[26] = ' ';
            Random generator = new Random(System.currentTimeMillis());
            @SuppressWarnings("MismatchedReadAndWriteOfArray") Individual[] population = new Individual[popSize];
            // we initialize the population with random characters
            for (int a = 0; a < popSize; a++) {
                char[] tempChromosome = new char[TARGET.length()];
                for (int j = 0; j < TARGET.length(); j++) {
                    tempChromosome[j] = alphabet[generator.nextInt(alphabet.length)]; //choose a random letter in the alphabet
                }
                population[i] = new Individual(tempChromosome);
            }
            testForBest(10, popSize);

        }


    }

    @SuppressWarnings("RedundantCast")
    public static void testForAllowed(int times) {
        int popSize = 100;
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
        System.out.println(("Testing for the popSize of ") + popSize + (", what is the best amount of individuals (between 1 and 25 ) that we should allow to evolve ( for the elitist selection ):"));
        for (int i = 1; i <= 25; i++) {
            boolean worked = true;
            int average = 0;
            for (int a = 0; a < times; a++) {
                int tries = elitist(population, i);
                if (tries == -1) {
                    worked = false;
                }
                average += tries;
            }
            average = (int) (average / times);
            if (worked) {
                System.out.println("for the top " + i + " individuals being allowed to evolve it took on average " + average + " generations");
            } else {
                System.out.println("for the top " + i + " individuals being allowed to evolve it sometimes didnt work, it is not worth trying ");
            }
        }
    }

    public static void testForBest(int times, int popSize) {
        int best = 9_999_999;
        int worst = 0;
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
        //System.out.println(("Testing for the popSize of ")+popSize+(", what is the best amount of individuals (between 1 and 25 ) that we should allow to evolve ( for the elitist selection ):"));
        for (int i = 1; i <= 25; i++) {
            boolean worked = true;
            for (int a = 0; a < times; a++) {
                int tries = elitist(population, i);
                if (tries == -1) {
                    worked = false;
                }
                if (tries < best) {
                    best = tries;
                }
                if(tries>worst){
                    worst=tries;
                }
            }
            if (!worked) {
                System.out.println("for the top " + i + " individuals being allowed to evolve it sometimes didnt work, it is not worth trying ");
            }
        }
        System.out.println("for the pop-size of " + popSize + " the best it can do is " + best + " generations");
        System.out.println("for the pop-size of " + popSize + " the worst it can do is " + worst + " generations");
    }

    public static int elitist(Individual[] population, int howManyToEvolve){
        return elitist(population,howManyToEvolve,2);
    }
    public static int elitist(Individual[] population, int howManyToEvolve, int mutationRate) {
        int popSize = population.length;

        for (int safety = 0; safety < 30000; safety++) {
            if (population[0].getFitness() == 1) {
                return safety;
            }

            Individual[] nextIteration = new Individual[popSize];
            for (int index = 0; index < popSize; index++) {
                nextIteration[index] = Evolve(population[index % howManyToEvolve], population[(index % howManyToEvolve) + 1],mutationRate);
            }
            population = nextIteration.clone();
            HeapSort.sort(population);


        }
        return -1;
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

}
