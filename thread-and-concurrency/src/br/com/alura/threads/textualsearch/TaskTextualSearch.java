package br.com.alura.threads.textualsearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskTextualSearch implements Runnable {

	private String file;
	private String name;
	
	public TaskTextualSearch(String file, String name) {
		super();
		this.file = file;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			Scanner scanner = new Scanner(new File("resources/"+this.file));
			int occurrences = 0, lineCount = 0;
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				lineCount++;				

				if (line.toLowerCase().contains(name.toLowerCase())) {
					System.out.println(file + " - " + line + " - at line " + lineCount);
					occurrences++;
					//Simulates some sort of processing
					Thread.sleep(100);
				}
				
			}
			Thread threadAtual = Thread.currentThread();
			long id = threadAtual.getId();
			
			System.out.println("\n(Thread " + id + ") " + file + " - " + occurrences + " line(s) found.");
			scanner.close();
			
		} catch (FileNotFoundException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
