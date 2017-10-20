package br.com.threads.bathroomproblem;

public class TaskNumber1 implements Runnable {

	private Bathroom bathroom;
	
	public TaskNumber1(Bathroom bathroom) {
		super();
		this.bathroom = bathroom;
	}

	@Override
	public void run() {
		bathroom.doNumber1();
	}

}
