package br.com.alura.threads.buscatextual;

public class TextualSearchMain {

	public static void main(String[] args) {
		String name = "da";
		
		Thread threadAssinaturas1 = new Thread(new TaskTextualSearch("assinaturas1.txt", name));
		Thread threadAssinaturas2 = new Thread(new TaskTextualSearch("assinaturas2.txt", name));
		Thread threadAutores = new Thread(new TaskTextualSearch("autores.txt", name));
		threadAssinaturas1.start();
		threadAssinaturas2.start();
		threadAutores.start();
	}

}
