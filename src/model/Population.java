// Matthew Wu
// HW 2 Evolve Names 
// TCSS 342
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class represents a population. A population is a group of genomes 
 * that behave a random way at the end of the day. The most fit gene is kept 
 * track of. 
 *
 */
public class Population {
	
	public static final Random RAND = new Random();
	public Genome myMostFit;
	private List<Genome> myGenomePool;
	private int myNumGenomes;
	private Random rand;

	/**
	 * Constructor for a population with a set number of genomes and a mutation rate. 
	 * @param numGenomes is the number of genomes 
	 * @param mutationRate is the mutation rate 
	 */
	public Population(Integer numGenomes, Double mutationRate) {
		myNumGenomes = numGenomes;
		myGenomePool = new ArrayList<Genome>();
		for (int i = 0; i < myNumGenomes; i++) {
			Genome gene = new Genome(mutationRate);
			myGenomePool.add(gene);
		}
		myMostFit = myGenomePool.get(0); // grab arbitrary most fit for now
	}
	
	/**
	 * This method specifies the behavior of the population at the end of a day. 
	 * The population is randomly altered by various means, and restored. The most fit 
	 * gene is also updated. 
	 */
	public void day() {
		Collections.sort(myGenomePool, new Genome());
		myMostFit = myGenomePool.get(0); // update most fit 
		myGenomePool = myGenomePool.subList(0, myGenomePool.size() / 2); // delete least fit half
		restore(); // restore new half 
		Collections.sort(myGenomePool, new Genome()); // sort the genome pool again 
		myMostFit = myGenomePool.get(0); // update most fit again 
	}
	
	/**
	 * This method restores a half of the genome population by random variations of 
	 * the mutate and crossover procedures. 
	 */
	private void restore() {
		rand = new Random();
		int optionSelect = rand.nextInt(2);
		List<Genome> newGeneration = new ArrayList<Genome>();
		int cloneIndex = 0;
		
		for(int i = 0; i < myGenomePool.size(); i++) {
			int randomIndex = rand.nextInt(myGenomePool.size());
			Genome newGenome1 = myGenomePool.get(randomIndex);
			Genome clone = new Genome(newGenome1);
			cloneIndex = randomIndex;
			
			if (optionSelect == 0) {
				clone.mutate();
			} else {
				randomIndex = rand.nextInt(myGenomePool.size());
				while(randomIndex == cloneIndex) { // can't crossover with same genome 
					randomIndex = rand.nextInt(myGenomePool.size());
				}
				Genome newGenome2 = myGenomePool.get(randomIndex);
				clone.crossover(newGenome2);
				clone.mutate();
			}
			newGeneration.add(clone);
		}
		myGenomePool.addAll(newGeneration);
	}
	
	/**
	 * Helper method to get the most fit genome.  
	 * @return a genome that is the most fit
	 */
	public Genome getMostFit() {
		return myMostFit;
	}
	
	/**
	 * Helper method used for adding a gene when testing. 
	 * @param genome is the genome to be added. 
	 */
	public void add(Genome genome) {
		myGenomePool.add(genome);
	}
	
	/**
	 * To string method for the population.
	 */
	public String toString() {
        return myGenomePool.toString();
	}
}
