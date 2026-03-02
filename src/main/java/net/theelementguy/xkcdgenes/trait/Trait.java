package net.theelementguy.xkcdgenes.trait;

import net.theelementguy.xkcdgenes.genes.BasicGene;
import net.theelementguy.xkcdgenes.genes.Gene;
import net.theelementguy.xkcdgenes.genes.MultiplierGene;

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

	public static Trait parse(String gene1, String gene2) {
		Gene first;
		Gene second;
		if (gene1.contains("x")) {
			first = new MultiplierGene(Integer.parseInt(gene1.replace("x", "")));
		} else {
			first = new BasicGene(Integer.parseInt(gene1));
		}
		if (gene2.contains("x")) {
			second = new MultiplierGene(Integer.parseInt(gene2.replace("x", "")));
		} else {
			second = new BasicGene(Integer.parseInt(gene2));
		}
		return new Trait(first, second);
	}

	@Override
	public String toString() {
		return gene1.toString() + gene2.toString();
	}
}
