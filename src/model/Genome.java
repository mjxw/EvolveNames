// Matthew Wu
// HW 2 Evolve Names 
// TCSS 342

package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/** 
 * This class represents a genome. A genome has letters in it to represent genes.
 * A gene operates based off of a mutation rate that is specified. A gene can 
 * mutate, crossover with another genome, and has a fitness according to how close 
 * the genome sequence is to the target. 
 */
public class Genome implements Comparator<Genome> {

	private static String myTarget = "CHRISTOPHER PAUL MARRIOTT";
	
	public StringBuilder myGenome;  // public for testing
	private double myMutationRate;
	private Random rand;
	private char[] alphabet; 
	
	/**
	 * Constructor for a genome that takes in a mutation rate
	 * and sets up the default "A" gene.
	 * @param mutationRate is the mutation rate 
	 */
	public Genome(double mutationRate) {
		myGenome = new StringBuilder();
		myMutationRate = mutationRate; 
		setList();
		myGenome.append("A");
	}
	
	/**
	 * Constructor used for copying another gene.
	 * @param gene is the gene to be copied
	 */
	public Genome(Genome gene) {
		myGenome = new StringBuilder(gene.myGenome.toString());
		myMutationRate = gene.myMutationRate;
		setList();
	}
	
	/**
	 * Constructor used for testing. Does not have the default "A" gene.
	 * @param string is the gene sequence to be constructed 
	 * @param mutationRate is the mutation rate 
	 */
	public Genome(String string, double mutationRate) {
		myGenome = new StringBuilder();
		myGenome.append(string);
		myMutationRate = mutationRate;
	}
	
	/**
	 * Basic genome constructor
	 */
	public Genome() {
		
	}
	
	/**
	 * This method mutates the genome as randomly as possible. 
	 */
	public void mutate() {
		rand = new Random();		
		double randomDouble = Math.random(); // gives you a percent basically 
		
		if (myMutationRate > randomDouble) { // adding random gene 
			int randomIndex = rand.nextInt(alphabet.length);
			char randomCharIndex = alphabet[randomIndex];
			randomIndex = rand.nextInt(myGenome.length() + 1);
			myGenome.insert(randomIndex, randomCharIndex);	
		}
		randomDouble = Math.random();
		
		if (myMutationRate > randomDouble) { // deleting randomly 
			if (myGenome.length() >= 2) {
				int randomIndex = rand.nextInt(myGenome.length());
				myGenome.deleteCharAt(randomIndex);
			}
		}
	
		for (int i = 0; i < myGenome.length(); i++) { // updating randomly 
			randomDouble= Math.random();
			if (myMutationRate > randomDouble) {
				int randomIndex = rand.nextInt(alphabet.length);
				char randomCharIndex = alphabet[randomIndex];
				myGenome.setCharAt(i, randomCharIndex);
			}
		}
	}
	
	/**
	 * This method crosses one genome with another genome. 
	 * @param other is the other genome 
	 */
	public void crossover(Genome other) {
		int mySize = myGenome.length();
		int otherSize = other.myGenome.length();
		StringBuilder newGenome = new StringBuilder();

		int max = Math.max(otherSize, mySize);
		int min = Math.min(otherSize, mySize);
		rand = new Random();	
		
		for (int i = 0; i <= max; i++) {
			int optionSelect = rand.nextInt(2);
			if (optionSelect == 1) { // cross from other 
				if (i >= otherSize) {
					break;
				} else {
					newGenome.insert(i, other.myGenome.charAt(i)); 
				}
			} else { // cross from original 
				if (i >= mySize) {
					break; 
				} else { 
					newGenome.insert(i, myGenome.charAt(i)); 
				}
			}	
		}
		myGenome = newGenome;
	}	
		
	/**
	 * This method sets up the possible genes.
	 */
	private void setList() {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ-' ".toCharArray();
	}
	
	/**
	 * This method calculates a fitness number based off how close/far the genome sequence 
	 * is to the target sequence.
	 * @return an int representing the fitness
	 */
	public int fitness() {
		int n = myGenome.length(); // myGenome 
		int m = myTarget.length(); // target  
		int l = Math.min(m, n);
		int f = Math.abs(m - n);
		
		if (m == n || m > n ) { // target is equal or greater than genome
			for ( int i = 0; i < l; i++) {
				if (myTarget.charAt(i) != myGenome.charAt(i)) {
					f++;
				}
			}
			int diff = m - n; 
			f = f + diff; 
		} else { // target is shorter than genome 
			for (int i = 0; i < myTarget.length(); i++) {
				if (myTarget.charAt(i) != myGenome.charAt(i)) {
					f++; // add count of differences within index bounds
				}
			}
			int diff = n - m;
			f = f + diff; // add the remaining difference in size to f 
		}
		return f;
	} 
	
// 		EXTRA CREDIT 
//	public Integer fitness() {
//			int n = myGenome.length();
//			int m = myTarget.length();
//			int[][] matrix = new int[n + 1][m + 1];
//			
//			for(int i = 0; i < m + 1; i++) {
//				matrix[0][i] = i;
//			}
//	
//			for(int i = 0; i < n + 1; i++) {
//				matrix[i][0] = i;
//			}
//	
//			for(int i = 1; i <= n; i++) {
//				for(int j = 1; j <= m; j++) {	
//					if(myGenome.charAt(i - 1) == myTarget.charAt(j - 1)) {
//						matrix [i][j]= matrix [i - 1][j - 1];
//					} else {
//						matrix [i][j] = Math.min(Math.min(matrix [i - 1][j] + 1 , matrix[i][j - 1 ] + 1 ) , matrix[i - 1][j - 1 ]+ 1);
//					}
//				}
//			}
//			return matrix[n][m] + (int)(Math.abs(n - m) + 1) / 2;
//}
	
	/**
	 * This is the to string method for the genome. 
	 */
    public String toString() {
        return "(\"" + myGenome + "\", " + this.fitness() + ")";
    }

	@Override
	public int compare(Genome o1, Genome o2) { // for comparison between genomes
		return o1.fitness() - o2.fitness();
	}
}




