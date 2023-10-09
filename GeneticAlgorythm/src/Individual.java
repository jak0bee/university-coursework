
public class Individual {

    char[] chromosome;
    double fitness;

    public Individual(char[] chromosome) {
        this.chromosome = chromosome;
        this.fitness =fitCheck() ;
    }
    public double fitCheck() {
        String TARGET = "HELLO WORLD";
        char[] splitTarget = {'H', 'E', 'L', 'L', 'O', ' ', 'W', 'O', 'R', 'L', 'D'};
        if (this.genoToPhenotype().equals("HELLO WORLD")) {
            return 1;
        }
        double fit = 0;
        if (this.chromosome[5] ==' ') {
            fit += 0.2;
        }
        for (int i = 0; i < TARGET.length(); i++) {
            if (splitTarget[i] == this.chromosome[i] ) {
                fit += 0.08;
            }
        }
        return fit;
    }


    public char[] getChromosome() {
        return chromosome;
    }

    public void setChromosome(char[] chromosome) {
        this.chromosome = chromosome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public String genoToPhenotype() {
        StringBuilder builder = new StringBuilder();
        builder.append(chromosome);
        return builder.toString();
    }

    public Individual clone() {
        char[] chromClone = new char[chromosome.length];
        for (int i = 0; i < chromClone.length; i++) {
            chromClone[i] = chromosome[i];
        }
        return new Individual(chromClone);
    }


}
