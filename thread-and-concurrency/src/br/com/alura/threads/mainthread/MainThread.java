package br.com.alura.threads.mainthread;

public class MainThread {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start of Thread MAIN (Execute jconsole para ver as threads em ação)");
		Thread.sleep(40000);
		System.out.println("End of Thread MAIN.");
	}
}
