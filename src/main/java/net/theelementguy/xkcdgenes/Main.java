package net.theelementguy.xkcdgenes;

import net.theelementguy.xkcdgenes.genes.MultiplierGene;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class Main {

	static void main() {

		Population population = Population.random(100);

		DefaultCategoryDataset data = new DefaultCategoryDataset();

		//String goes = IO.readln("Number of generations: ");

		//int parsedGoes = Integer.parseInt(goes);
		int parsedGoes = 100;

		for (int i = 0; i < parsedGoes; i++) {

			IO.println("Generation: " + i);
			IO.println(population.getBloodCurdlingStat());

			data.addValue(population.getBloodCurdlingStat(), "Generation", Integer.toString(i + 1));

			population.haveBabies();
			population.killDefective();

		}

		JFreeChart chart = ChartFactory.createLineChart("Inbreeding", "Generation", "Blood Curdling", data);

		ChartPanel panel = new ChartPanel(chart);

		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setContentPane(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
