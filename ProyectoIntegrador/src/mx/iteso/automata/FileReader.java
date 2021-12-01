package mx.iteso.automata;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileReader {
	
	public static void file (String[] args) {
		JFileChooser chooser = new JFileChooser(new File("").getAbsolutePath());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de texto del automata", "txt");
		chooser.setFileFilter(filter);
	}
}
