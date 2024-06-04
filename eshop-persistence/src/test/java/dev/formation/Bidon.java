package dev.formation;

public class Bidon {

	public static void main(String[] args) {
		Application singleton = Application.getInstance();
		
		Application singletonBis = Application.getInstance();
		
		System.out.println(singleton);
		System.out.println(singletonBis);

	}

}
