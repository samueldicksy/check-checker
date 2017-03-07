package com.example;

public class Table {

	public static void main(String[] args) {
		String biodata[][] = {{"Nama", "Alamat", "No Telp"},
				  {"Samuel Dicksy", "Jln. Jend. S. Parman", "087740040016"},
				  {"Diaz Marifatata Ismono", "Jln. Jend. S. Parman kav 63", "087740040016"},
				  {"Samuel Dicksy", "Jln. Jend. S. Parman", "087740040016"},
				  };
		
		int colNumber = 3;
		
		int optimalWitdh[] = new int[colNumber];
		for (int i = 0; i< biodata.length;i++){
			for (int j = 0; j< colNumber;j++){
				if (optimalWitdh[j] < biodata[i][j].length())
					optimalWitdh[j] =  biodata[i][j].length(); 
			} 
		}
		
		String separator = "";
		
		for (int j = 0; j< 3;j++){
			separator += "+";
			for (int k = 0; k< optimalWitdh[j] + 2;k++){
				separator += "-";
			} 
		} 
		separator += "+";
		
		for (int i = 0; i< biodata.length;i++){
			System.out.println(separator); 
			String row = "| ";
			for (int j = 0; j< 3;j++){
				row += biodata[i][j];
				
				//===== adding spaces =======
				int numOfSpaces = optimalWitdh[j] - biodata[i][j].length();
				for (int k = 0 ;k < numOfSpaces;k++){
					row += " ";
				}
				
				row += " | ";
			}   
			System.out.println(row);
		}
		System.out.println(separator); 
	}

}
