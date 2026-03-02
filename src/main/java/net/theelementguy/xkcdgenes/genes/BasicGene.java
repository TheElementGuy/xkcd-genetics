package net.theelementguy.xkcdgenes.genes;

import java.util.Random;

public class BasicGene implements Gene {

	private final int value;

	public BasicGene(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public int combine(Gene gene) {
		if (gene instanceof BasicGene basicGene) {
			return Math.max(basicGene.getValue(), getValue());
		} else if (gene instanceof MultiplierGene multiplierGene) {
			return getValue() * multiplierGene.getMultiplier();
		} else {
			return value;
		}
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

	public static BasicGene getRandom() {
		Random random = new Random();
		double trait = random.nextDouble() * 4 + 10;
		int rounded = Math.toIntExact(Math.round(trait));
		if (rounded < 1) {
			rounded = 1;
		} else if (rounded > 20) {
			rounded = 20;
		}
		return new BasicGene(rounded);
	}
}
