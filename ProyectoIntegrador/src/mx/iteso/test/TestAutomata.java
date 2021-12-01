package mx.iteso.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import mx.iteso.automata.Automata;
import tools.Leer;

public class TestAutomata {

	public static void main(String[] args) {
		
		Automata automatom = new Automata();
		
		int action = 0;
		
		do {
			action = getAction();
			switch (action) {
				case 1: // File entry
					// Start of file open
				    JFileChooser chooser = new JFileChooser(new File("").getAbsolutePath());
				    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de texto del automata", "txt");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(null);
				    if (returnVal == JFileChooser.ERROR_OPTION || returnVal == JFileChooser.CANCEL_OPTION) {
				      System.out.println("Por favor seleccione un archivo.");
				      return;
				    }

				    File automaton = new File(chooser.getSelectedFile().getAbsolutePath());
				    BufferedReader bReader = null;
				    try {
				      bReader = new BufferedReader(new FileReader(automaton));
				    } catch (FileNotFoundException e) {
				      System.out.println(e);
				      return;
				    }
				    // End of file opening
				    
				    try {
					      String line;
					      
					      // Reading the input string
					      automatom.setInputString(bReader.readLine());

					      // Reading the alphabet symbols
					      line = bReader.readLine();
					      automatom.setAlphabet(line);

					      // Reading the initial state
					      automatom.setInitialState(Integer.parseInt(bReader.readLine()));

					      // Reading the final states
					      line = bReader.readLine();
					      automatom.setFinalStates(line);

					      // Reading the transition matrix
					      int row = 0;
					      while ((line = bReader.readLine()) != null) {
					        automatom.setTransitionMatrix(row, line);
					        row++;
					      }

					      bReader.close();
					    } catch (IOException e) {
					      System.out.println(e);
					      return;
					    }
				    
				    // Validating input with conditions
				    AFDValidator(automatom);
					break;
					
				case 2: // Manual entry
					Scanner reader = new Scanner(System.in);
					
					// Reading the input string
					System.out.println("Ingresa la cadena a procesas: ");
					automatom.setInputString(reader.next());
					
					// Reading the alphabet symbols
					System.out.println("Ingresa los simbolos del alfabeto separado por [;]: ");
					automatom.setAlphabet(reader.next());
					
					// Reading the initial state
					System.out.println("Ingresa el estado inicial: ");
					automatom.setInitialState(reader.nextInt());
					
					// Reading the final states
					System.out.println("Ingresa el/los estado/s final/es separado por [;]: ");
					automatom.setFinalStates(reader.next());
					
					// Reading the transition matrix
					System.out.println("Ingresa la cantidad de filas de la matriz de transiciones: ");
					int rows = reader.nextInt();
					int row = 0;
					System.out.println("Ingresa la matriz de transiciones: ");
					for(int i=0; i<rows; i++) {
						automatom.setTransitionMatrix(row, reader.next());
						row++;
					}
					
					// Validating input with conditions
					AFDValidator(automatom);
					
					break;
			}
		} while(action != 3);
		
		System.out.println("\n\tAdiós! :)");

	  }
	
	private static void printMenu() {
		System.out.println("\n\t\t\tMenú");
		System.out.println("\t1.- Abrir archivo de texto");
		System.out.println("\t2.- Escribir automata");
		System.out.println("\t3.- Salir\n");
	}
	
	private static int getAction() {
		int action = 0;
		do {
			printMenu();
			System.out.println("Selecciona una opción: ");
			action = Leer.datoInt();
		} while((action < 1) || (action > 9));
		return action;
	}
	  
	  public static void AFDValidator(Automata automatom) {

		    int currState = automatom.getInitialState();
		    String sequence = currState + "/";
		    for (int i = 0; i < automatom.getInputString().length(); i++) {
		      Character c = automatom.getInputString().charAt(i);
		      currState = automatom.getTransitionMatrix().get(currState).get(automatom.getAlphabet().indexOf(c));
		      sequence += currState + "/";
		    }
		    
		    System.out.printf("Cadena: %s\n", automatom.getInputString());

		    if (automatom.getFinalStates().contains(currState)) {
		      System.out.printf("La cadena SI es aceptada!\nEstado final: %s\n", currState);
		    } else {
		      System.out.printf("La cadena NO es aceptada!\nEstado final: %s\n", currState);
		    }

		    System.out.printf("Secuencia de estaods visitados:\n%s\n", sequence);
	  }

}
