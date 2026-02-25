package net.theelementguy.xkcdgenes;

import net.theelementguy.xkcdgenes.trait.Trait;

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

}
