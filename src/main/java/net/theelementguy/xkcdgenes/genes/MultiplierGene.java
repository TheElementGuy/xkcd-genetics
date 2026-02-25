package net.theelementguy.xkcdgenes.genes;

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
}
