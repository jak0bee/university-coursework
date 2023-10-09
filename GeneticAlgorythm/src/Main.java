import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The genetic algorithm");
        frame.setSize(1920, 1080);
        JLabel intro = new JLabel("The algorithm always uses the elitist selection but it does allow you to specify some of the attributes");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        frame.add(intro, gridBagConstraints);
        //empty space
        JLabel empty = new JLabel("  ");
        gridBagConstraints.gridy = 1;
        frame.add(empty, gridBagConstraints);
        //Population size panel
        String[] PopChoice = {"100", "200", "300", "400", "500", "600", "700"};
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        JPanel popPanel = new JPanel();
        JLabel popLabel = new JLabel("Please choose a size of you population");
        JComboBox<String> popBox = new JComboBox<>(PopChoice);
        popPanel.add(popLabel);
        popPanel.add(popBox);
        frame.add(popPanel, gridBagConstraints);
        //empty space
        gridBagConstraints.gridy = 3;
        JLabel empty2 = new JLabel("  ");
        frame.add(empty2, gridBagConstraints);
        //mutation choice
        JPanel mutationPanel = new JPanel();
        JLabel mutationLabel = new JLabel("Please choose a number of mutations of you population");
        String[] mutationChoice = {"1", "2", "3", "4"};
        JComboBox<String> mutationBox = new JComboBox<>(mutationChoice);
        mutationPanel.add(mutationLabel);
        mutationPanel.add(mutationBox);
        gridBagConstraints.gridy = 4;
        frame.add(mutationPanel, gridBagConstraints);
        //empty space
        gridBagConstraints.gridy = 5;
        JLabel empty3 = new JLabel("  ");
        frame.add(empty3, gridBagConstraints);
        frame.setVisible(true);
        //number of individuals allowed to evolve
        JPanel evolvePanel = new JPanel();
        JLabel evolveLabel = new JLabel("Please choose a number of individuals allowed to evolve in your population");
        String[] evolveChoice = {"1", "2", "3", "4", "5", "10", "15"};
        JComboBox<String> evolveBox = new JComboBox<>(evolveChoice);
        evolvePanel.add(evolveLabel);
        evolvePanel.add(evolveBox);
        gridBagConstraints.gridy = 6;
        frame.add(evolvePanel, gridBagConstraints);
        //empty space
        gridBagConstraints.gridy = 7;
        JLabel empty4 = new JLabel("  ");
        frame.add(empty4, gridBagConstraints);
        //buttons to choose
        JButton auto = new JButton("Start the algorith with default values");
        JButton user = new JButton("Start the algorithm with the values provided");
        JPanel choose = new JPanel();
        auto.addActionListener(e -> {
            GeneticAlgorithm one = new GeneticAlgorithm();
            frame.dispose();
            one.run();
        });
        user.addActionListener(e -> {
            int mutations = mutationBox.getSelectedIndex() + 1;
            int allowedToEvolve = evolveBox.getSelectedIndex();
            if (allowedToEvolve == 5) {
                allowedToEvolve = 10;
            } else if (allowedToEvolve == 6) {
                allowedToEvolve = 15;
            } else {
                allowedToEvolve += 1;
            }
            int popSize = (popBox.getSelectedIndex() + 1) * 100;
            UserPlaying one = new UserPlaying(mutations, popSize, allowedToEvolve);
            frame.dispose();
            one.run();
        });
        choose.add(user);
        choose.add(auto);
        gridBagConstraints.gridy = 8;
        frame.add(choose, gridBagConstraints);
        frame.setVisible(true);

    }

}
