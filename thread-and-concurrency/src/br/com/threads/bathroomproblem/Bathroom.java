package br.com.threads.bathroomproblem;

public class Bathroom {

	public void doNumber1() {
		System.out.println(getGuest() + " - Entering the bathroom...");
		System.out.println(getGuest() + " - Doing number 1...");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(getGuest() + " - Discharging...");
		System.out.println(getGuest() + " - Washing the hands...");
		System.out.println(getGuest() + " - Leaving the bathroom.");
	}
	
	public void doNumber2() {
		System.out.println(getGuest() + " - Entering the bathroom...");
		System.out.println(getGuest() + " - Doing number 2...");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(getGuest() + " - Discharging...");
		System.out.println(getGuest() + " - Washing the hands...");
		System.out.println(getGuest() + " - Leaving the bathroom.");
	}
	
	private String getGuest() {
		return Thread.currentThread().getName();
	}
}
