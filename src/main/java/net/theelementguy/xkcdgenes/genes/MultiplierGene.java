package net.theelementguy.xkcdgenes.genes;

import java.util.Random;

public class MultiplierGene implements Gene {

	private final int multiplier;

	public MultiplierGene(int multiplier) {
		this.multiplier = multiplier;
	}

	public int getMultiplier() {
		return multiplier;
	}

	@Override
	public int combine(Gene gene) {
		if (gene instanceof BasicGene basicGene) {
			return getMultiplier() * basicGene.getValue();
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return "x" + Integer.toString(multiplier);
	}

	public static MultiplierGene getRandom() {
		Random random = new Random();
		double trait = GeneUtils.nextPowerLaw();
		int rounded = Math.toIntExact(Math.round(trait));
		if (rounded < 2) {
			rounded = 2;
		} else if (rounded > 20) {
			rounded = 20;
		}
		return new MultiplierGene(rounded);
	}
}
