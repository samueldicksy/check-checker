package com.example;

public class Tower {
	public static void main(String[] args) {
		
		
		int towers[] = {5,3,2,4,5};
		
		
		
		System.out.println(hitungJumlahGaris(towers));
		System.out.println(hitungJumlahGaris(towers));
		
	}
	
	public static int hitungJumlahGaris (int[] towers) {
		
		int numberOfLine = towers[0];
		
		for (int i  = 1; i< towers.length;i ++){
			if (towers[i-1] <= towers[i]){ 
				numberOfLine += towers[i] - towers[i-1];
			}
		}
		
		return numberOfLine;
	}
}
