package net.theelementguy.xkcdgenes;

import net.theelementguy.xkcdgenes.genes.Gene;
import net.theelementguy.xkcdgenes.genes.GeneUtils;
import net.theelementguy.xkcdgenes.trait.Trait;

import java.util.Random;

public class Person {

	private final Trait bloodCurdling;

	public Person(Trait bloodCurdling) {
		this.bloodCurdling = bloodCurdling;
	}

	public Person combine(Person partner) {
		return new Person(getBloodCurdling().combine(partner.getBloodCurdling()));
	}

	public Trait getBloodCurdling() {
		return bloodCurdling;
	}

	public static Person generateRandom() {

		return new Person(new Trait(GeneUtils.randomGene(), GeneUtils.randomGene()));

	}

}
