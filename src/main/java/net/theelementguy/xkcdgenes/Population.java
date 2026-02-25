package net.theelementguy.xkcdgenes;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Population {

	private final Set<Person> people;

	public Population(Set<Person> people) {
		this.people = new HashSet<>(people);
	}

	public Population(List<Person> people) {
		this.people = new HashSet<>(people);
	}

	public Population() {
		this.people = new HashSet<>();
	}

	public Set<Person> getPeople() {
		return people;
	}

	public double getBloodCurdlingStat() {
		double runningTotal = 0;
		for (Person p : people) {
			runningTotal += p.getBloodCurdling().getValue();
		}
		return runningTotal / people.size();
	}

	public void haveBabies() {
		int repeats = people.size() / 2;
		Set<Person> children = new HashSet<>();
		for (int i = 0; i < repeats; i++) {
			Person husband = getRandom();
			people.remove(husband);
			Person wife = getRandom();
			people.remove(wife);
			children.add(husband.combine(wife));
			children.add(husband.combine(wife));
			children.add(husband.combine(wife));
		}
		people.clear();
		people.addAll(children);
	}

	public void killDefective() {
		Random random = new Random();
		for (Person p : people) {
			double deathChance = Math.pow(Math.E, -p.getBloodCurdling().getValue());
			if (random.nextDouble() < deathChance) {
				System.out.println("Someone died. Stat: " + p.getBloodCurdling().getValue());
				people.remove(p);
			}
		}
	}

	private Person getRandom() {
		int i = 0;
		int getIndex = new Random().nextInt(people.size());
		for (Person p : people) {
			if (i == getIndex) {
				return p;
			}
			i++;
		}
		throw new RuntimeException("Stack Overflow code didn't work :(");
	}

	//TODO: from file

}
