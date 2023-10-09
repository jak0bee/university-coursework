import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GeneticAlgorithm {

    private static final String TARGET = "HELLO WORLD";
    private static final char[] ALPHABET;
    private double[] average = new double[0];
    private int mutations;
    private int popSize;
    private int allowedToEvolve;

    static {
        ALPHABET = new char[27];
        for (char c = 'A'; c <= 'Z'; c++) {
            ALPHABET[c - 'A'] = c;
        }
        ALPHABET[26] = ' ';
    }

    public void run() {
        initializeDefaultParameters();
        performGeneticAlgorithm();
        displayResults();
    }

    private void initializeDefaultParameters() {
        this.mutations = 2;
        this.popSize = 100;
        this.allowedToEvolve = 3;
    }

    private void performGeneticAlgorithm() {
        int mutations = 2;
        int popSize = 100;
        int allowedToEvolve = 3;
        elitist(allowedToEvolve, mutations, popSize);
    }

    private void displayResults() {
        JFrame frame = setupFrame();

        frame.add(createLabelPanel("The setting were: population size of " + popSize + ", " + mutations + " mutations per evolution, and  " + allowedToEvolve + " individuals allowed to evolve."), createConstraints(0));
        frame.add(createLabelPanel("The results are in!"), createConstraints(1));
        frame.add(createLabelPanel("It took " + average.length + " generations!"), createConstraints(2));
        frame.add(createLabelPanel("The average fitness of individuals throughout the whole process was: " + round(getAverageFitness(average), 4)), createConstraints(3));
        frame.add(createLabelPanel("The average fitness of the first generation was: " + round(average[0], 4)), createConstraints(4));
        frame.add(createLabelPanel("The average fitness of the last generation was: " + round(average[average.length - 1], 4)), createConstraints(5));

        frame.setVisible(true);
    }

    private JPanel createLabelPanel(String text) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(text));
        return panel;
    }

    private GridBagConstraints createConstraints(int gridY) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = gridY;
        return gridBagConstraints;
    }

    private JFrame setupFrame() {
        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The genetic algorithm");
        frame.setSize(1920, 1080);
        return frame;
    }

    private double getAverageFitness(double[] fitnessValues) {
        double sum = 0;
        for (double fitness : fitnessValues) {
            sum += fitness;
        }
        return sum / fitnessValues.length;
    }

    public void elitist(int howManyToEvolve, int mutations, int popSize) {
        Random generator = new Random(System.currentTimeMillis());
        Individual[] population = new Individual[popSize];

        for (int i = 0; i < popSize; i++) {
            char[] tempChromosome = new char[TARGET.length()];
            for (int j = 0; j < TARGET.length(); j++) {
                tempChromosome[j] = ALPHABET[generator.nextInt(ALPHABET.length)];
            }
            population[i] = new Individual(tempChromosome);
        }

        HeapSort.sort(population);

        for (int safety = 0; safety < 30000; safety++) {
            if (population[0].getFitness() == 1) {
                break;
            }
            Individual[] nextIteration = new Individual[popSize];
            for (int index = 0; index < popSize; index++) {
                nextIteration[index] = evolveIndividual(population[index % howManyToEvolve], population[(index % howManyToEvolve) + 1], mutations);
            }
            population = nextIteration.clone();
            HeapSort.sort(population);

            double[] tmp = new double[average.length + 1];
            for (Individual i : population) {
                tmp[average.length] += i.getFitness();
            }
            tmp[average.length] /= popSize;
            System.arraycopy(average, 0, tmp, 0, average.length);
            average = tmp.clone();
        }
    }

    private Individual evolveIndividual(Individual one, Individual two, int mutationRate) {
        char[] chromosome = new char[TARGET.length()];
        Random r = new Random();

        for (int i = 0; i < TARGET.length() / 2; i++) {
            chromosome[i] = one.getChromosome()[i];
        }
        for (int i = TARGET.length() / 2; i < TARGET.length(); i++) {
            chromosome[i] = two.getChromosome()[i];
        }

        for (int a = 0; a < mutationRate; a++) {
            chromosome[r.nextInt(TARGET.length())] = ALPHABET[r.nextInt(ALPHABET.length)];
        }

        return new Individual(chromosome);
    }

    private double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
