package mx.iteso.automata;

import java.util.ArrayList;
import java.util.TreeSet;

public class Automata {
	
	
	private String inputString = null;
    private ArrayList<Character> alphabet = new ArrayList<>();
    private int initialState = 0;
    private TreeSet<Integer> finalStates = new TreeSet<>();
    private ArrayList<ArrayList<Integer>> transitionMatrix = new ArrayList<>();
    
	public Automata() {};

	public String getInputString() {
		return inputString;
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}

	public ArrayList<Character> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String line) {
		for (String s : line.split(";")) {
			this.alphabet.add(s.charAt(0));
	    }
	}

	public int getInitialState() {
		return initialState;
	}

	public void setInitialState(int initialState) {
		this.initialState = initialState;
	}

	public TreeSet<Integer> getFinalStates() {
		return finalStates;
	}

	public void setFinalStates(String line) {
		for (String s : line.split(";")) {
			this.finalStates.add(Integer.parseInt(s));
	    }
	}

	public ArrayList<ArrayList<Integer>> getTransitionMatrix() {
		return transitionMatrix;
	}

	public void setTransitionMatrix(int row, String line) {
		this.transitionMatrix.add(new ArrayList<Integer>());
        for (String s : line.split(";")) {
          this.transitionMatrix.get(row).add(Integer.parseInt(s));
        }
		
	}
}
