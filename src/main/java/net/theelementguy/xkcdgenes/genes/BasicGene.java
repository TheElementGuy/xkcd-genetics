package net.theelementguy.xkcdgenes.genes;

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

}
