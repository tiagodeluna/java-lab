package br.com.threads.bathroomproblem;

public class Main {

	public static void main(String[] args) {
		Bathroom bathroom = new Bathroom();
		
		Thread guest1 = new Thread(new TaskNumber1(bathroom), "John");
		Thread guest2 = new Thread(new TaskNumber2(bathroom), "Peter");
		Thread guest3 = new Thread(new TaskNumber1(bathroom), "Carina");
		
		guest1.start();
		guest2.start();
		guest3.start();
	}

}
