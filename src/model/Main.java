// Matthew Wu
// HW 2 Evolve Names 
// TCSS 342
package model;

import java.awt.EventQueue;

/**
 * This class is the controller for the simulation. Running the evolve names procedure 
 * and testing the genome and population classes. 
 */
public class Main {
    
	/**
	 * Main runs the evolving simulation and reports on run time statistics. 
	 * @param theArgs
	 */
	public static void main(final String[] theArgs) {
		Population population = new Population(100, 0.05);
		int gen = 0;
		long startTimer = System.currentTimeMillis();
		System.out.println(population.myMostFit);
		while (population.myMostFit.fitness() != 0) {
			population.day();
			System.out.println(population.getMostFit());
			gen++;
		}
		System.out.println("Generations: " + gen);
		long stopTimer = System.currentTimeMillis();
		long elapsedTime = stopTimer - startTimer;
		System.out.println("Run time: " + elapsedTime + "ms");
		
//		testGenome();
//		testPopulation();
    }
	
	/**
	 * This method runs tests on the genome class. 
	 */
	public static void testGenome() {
		Genome test1 = new Genome(0.5);
		test1.myGenome.append("AAA"); // A-AAA
		Genome test2 = new Genome(0.5);
		test2.myGenome.append("BCD");  // A-BCD

		test1.crossover(test2);
		System.out.println("Cross over 'AAAA' and 'ABCD: " + test1);
		System.out.println("");
		
		Genome test3 = new Genome(0.5);
		test3.mutate();
		System.out.println("Mutating Gene 'A' after 1 mutation: " + test3);
		test3.mutate();
		System.out.println("Mutating Gene 'A' after 2 mutations: " + test3);
		test3.mutate();
		System.out.println("Mutating Gene 'A' after 3 mutations: " + test3);
		test3.mutate();
		System.out.println("Mutating Gene 'A' after 4 mutations: " + test3);
		test3.mutate();
		System.out.println("Mutating Gene 'A' after 5 mutations: " + test3);
		System.out.println("");

		Genome cloneTest = new Genome(test3);
		System.out.println("Testing copy constructor on last mutation: " + cloneTest);
		System.out.println("");

		System.out.println("Calculate fitness on 'CHRISTOPHER PAUL MARRIOTT' "
				+ "and 'ACHRISTOPHER PAUL MARRIOTT'");;
		Genome test4 = new Genome("CHRISTOPHER PAUL MARRIOTT", 0.05);
		Genome test5 = new Genome(0.05);
		test5.myGenome.append("CHRISTOPHER PAUL MARRIOTT");
		System.out.println("Fitness expected: 0 || Actual: " + test4.fitness());
		System.out.println("Fitness expected: 25 || Actual: " + test5.fitness());
		
	}
	
	/**
	 * This method runs test on the population class. 
	 */
	public static void testPopulation() {
		Genome test1 = new Genome("AAA", 0.05);
		Genome test2 = new Genome("MATT", 0.05);
		Genome test3 = new Genome("LAB", 0.05);
		Genome test4 = new Genome("JAVASCRIPT", 0.05);
		
		Population pop = new Population(1, 0.05); // includes default genome "A"
		pop.add(test1);
		pop.add(test2);
		pop.add(test3);
		pop.add(test4);
		
		System.out.println("Population before day(): " + pop);
		System.out.println("");

		pop.day();
		
		System.out.println("Population after day(): " + pop);
	}
}

