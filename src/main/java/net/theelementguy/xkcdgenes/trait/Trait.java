package net.theelementguy.xkcdgenes.trait;

import net.theelementguy.xkcdgenes.genes.Gene;

import java.util.Random;

public class Trait {

	private final Gene gene1;
	private final Gene gene2;

	public Trait(Gene gene1, Gene gene2) {
		this.gene1 = gene1;
		this.gene2 = gene2;
	}

	public int getValue() {
		return gene1.combine(gene2);
	}

	public Trait combine(Trait other) {
		return new Trait(getGene(), other.getGene());
	}

	public Gene getGene() {
		Random random = new Random();
		if (random.nextBoolean()) {
			return gene1;
		} else {
			return gene2;
		}
	}
}
