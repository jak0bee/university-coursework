import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class UserPlaying {
    private final int mutations;
    private final int popSize;
    private final int allowedToEvolve;
    static final String TARGET = "HELLO WORLD";
    private static double[] average = new double[0];
    static char[] alphabet = new char[27];
    public UserPlaying(int mutations, int popSize, int allowedToEvolve) {
        this.mutations = mutations;
        this.popSize = popSize;
        this.allowedToEvolve = allowedToEvolve;
    }
    public void run (){
        elitist(allowedToEvolve,mutations,popSize);
        StringBuilder avr= new StringBuilder();
        for (int i=0; i<average.length; i++){
            String line=("average for generation "+(i+1) + " is "+ round(average[i],3 )+"\n");
            avr.append(line);
        }
        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The genetic algorithm");
        frame.setSize(1920, 1080);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        //setting panel
        JPanel settingsPanel = new JPanel();
        JLabel settingsLabel = new JLabel("The setting were: population size of " + popSize + ", "+ mutations+ " mutations per evolution, and  " + allowedToEvolve + " individuals allowed to evolve.");
        settingsPanel.add(settingsLabel);
        gridBagConstraints.gridy = 0;
        frame.add(settingsPanel, gridBagConstraints);
        //intro panel
        JPanel introPanel = new JPanel();
        JLabel intro = new JLabel("The results are in!");
        introPanel.add(intro);
        gridBagConstraints.gridy=1;
        frame.add(introPanel,gridBagConstraints);
        //tries
        String tries =("It took "+ average.length+ " generations!");
        JLabel triesLabel = new JLabel(tries);
        JPanel triesPanel = new JPanel();
        triesPanel.add(triesLabel);
        gridBagConstraints.gridy=2;
        frame.add(triesPanel, gridBagConstraints);
        //averages
        double allAverages=0;
        for (double number : average){
            allAverages+=number;
        }
        allAverages/=average.length;
        JPanel averagesOne= new JPanel();
        JLabel averagesLabel = new JLabel("The average fitness of individuals throughout the whole proces was: "+round(allAverages,4));
        averagesOne.add(averagesLabel);
        gridBagConstraints.gridy=3;
        frame.add(averagesOne,gridBagConstraints);
        //second averages
        JPanel second = new JPanel();
        JLabel secondLabel = new JLabel("The average fitness of the first generation was: "+ round(average[0], 4));
        second.add(secondLabel);
        gridBagConstraints.gridy=4;
        frame.add(second,gridBagConstraints);
        //third averages
        JPanel third = new JPanel();
        JLabel thirdLabel = new JLabel("The average fitness of the last generation was: "+ round(average[average.length-1], 4));
        third.add(thirdLabel);
        gridBagConstraints.gridy=5;
        frame.add(third,gridBagConstraints);
        frame.setVisible(true);
    }

    public static void elitist(int howManyToEvolve, int mutations, int popSize) {
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
        //for the average fitness
        //starting the GA
        for (int safety = 0; safety < 30000; safety++) {
            if (population[0].getFitness() == 1) {
                System.out.println("this is the: " + safety + " iteration, the best is our target: ");
                System.out.println(population[0].genoToPhenotype());
                System.out.println("done");
                break;
            }
            Individual[] nextIteration = new Individual[popSize];
            for (int index = 0; index < popSize; index++) {
                //System.out.println("using index: "+ (index%HowManyToEvolve)+" and: "+((index%HowManyToEvolve)+1) );
                nextIteration[index] = Evolve(population[index % howManyToEvolve], population[(index % howManyToEvolve) + 1],mutations);
            }
            population = nextIteration.clone();
            HeapSort.sort(population);
            double[] tmp = new double[average.length+1];
            for (Individual i: population){
                tmp[average.length]+=i.getFitness();
            }
            tmp[average.length]/=popSize;
            System.arraycopy(average, 0, tmp, 0, average.length);
            average=tmp.clone();
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
