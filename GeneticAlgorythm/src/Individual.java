
public class Individual {

    char[] chromosome;
    double fitness;

    public Individual(char[] chromosome) {
        this.chromosome = chromosome;
        this.fitness = fitCheck();
    }

    public double fitCheck() {
        String TARGET = "HELLO WORLD";
        char[] splitTarget = {'H', 'E', 'L', 'L', 'O', ' ', 'W', 'O', 'R', 'L', 'D'};
        if (this.genoToPhenotype().equals("HELLO WORLD")) {
            return 1;
        }
        double fit = 0;
        if (this.chromosome[5] == ' ') {
            fit += 0.2;
        }
        for (int i = 0; i < TARGET.length(); i++) {
            if (splitTarget[i] == this.chromosome[i]) {
                fit += 0.08;
            }
        }
        return fit;
    }


    public char[] getChromosome() {
        return chromosome;
    }

    public double getFitness() {
        return fitness;
    }

    public String genoToPhenotype() {
        return String.valueOf(chromosome);
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Individual clone() {
        char[] chromClone = new char[chromosome.length];
        System.arraycopy(chromosome, 0, chromClone, 0, chromClone.length);
        return new Individual(chromClone);
    }


}
