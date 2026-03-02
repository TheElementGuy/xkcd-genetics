package net.theelementguy.xkcdgenes;

import net.theelementguy.xkcdgenes.trait.Trait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Population {

	public static final int LARGE_POPULATION_CUTOFF = 1000;

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
		int size = people.size();
		Set<Person> children = new HashSet<>();
		Random random = new Random();
		for (int i = 0; i < repeats; i++) {
			Person husband = getRandom();
			people.remove(husband);
			Person wife = getRandom();
			people.remove(wife);
			children.add(husband.combine(wife));
			children.add(husband.combine(wife));
			if (random.nextDouble() < 0.2) {
				children.add(husband.combine(wife));
			}
			if (size > LARGE_POPULATION_CUTOFF && i % (repeats / 10) == 0) {
				IO.print(Math.toIntExact(Math.round(((double) i * 100) / (repeats))));
				IO.println("% done");
			}
		}
		IO.println("New population: " + children.size());
		people.clear();
		people.addAll(children);
	}

	public void killDefective() {
		Random random = new Random();
		HashSet<Person> copy = new HashSet<>(people);
		double dead = 0;
		for (Person p : people) {
			double deathChance = Math.pow(Math.E, -p.getBloodCurdling().getValue());
			if (random.nextDouble() < deathChance) {
				if (people.size() < LARGE_POPULATION_CUTOFF) {
					System.out.println("Someone died. Stat: " + p.getBloodCurdling().toString());
				}
				dead++;
				copy.remove(p);
			}
		}
		people.clear();
		people.addAll(copy);
		if (people.size() > LARGE_POPULATION_CUTOFF) {
			IO.print(dead * 100 / people.size());
			IO.println("% died");
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

	public static Population fromFile() {

		List<String> lines;
		Set<Person> toAdd = new HashSet<>();
		try {
			lines = Files.lines(Path.of("src/main/resources/people.txt")).toList();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		for (String s : lines) {

			String[] data = s.split(" ");

			toAdd.add(new Person(Trait.parse(data[0], data[1])));

		}

		return new Population(toAdd);

	}

	public static Population random(int population) {

		Set<Person> toAdd = new HashSet<>();

		for (int i = 0; i < population; i++) {
			toAdd.add(Person.generateRandom());
		}

		return new Population(toAdd);

	}

}
