package net.theelementguy.xkcdgenes.genes;

import java.util.Random;

public class GeneUtils {

	static double nextPowerLaw() {
		Random random = new Random();
		double y = random.nextDouble();
		double min = 0.5;
		double max = 20;
		double alpha = -2.5;
		return Math.pow(((Math.pow(max, alpha + 1) - Math.pow(min, alpha + 1)) * y + Math.pow(min, alpha + 1)), (1/(alpha+1)));
	}

	public static Gene randomGene() {
		Random random = new Random();
		if (random.nextInt() < 0.2) {
			return MultiplierGene.getRandom();
		} else {
			return BasicGene.getRandom();
		}
	}

}
