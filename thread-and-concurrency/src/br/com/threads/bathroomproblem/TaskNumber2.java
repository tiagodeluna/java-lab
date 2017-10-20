package br.com.threads.bathroomproblem;

public class TaskNumber2 implements Runnable {

	private Bathroom bathroom;
	
	public TaskNumber2(Bathroom bathroom) {
		super();
		this.bathroom = bathroom;
	}

	@Override
	public void run() {
		bathroom.doNumber2();
	}

}
