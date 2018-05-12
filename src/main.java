import java.util.Arrays;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		System.out.println("Enter the number of the problem to test (1 or 2)");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		switch(input) {
			case "1":
				ej1();
				break;
			case "2":
				ej2();
				break;
		}
		scanner.close();	
	}	
		
	private static void ej1() {
		Scanner scanner = new Scanner(System.in);
		Boolean success = false;
		float ni = 0;
		int dp = 0;
		String number = null;
		while(!success) {
			try {
				System.out.print("Enter a number to test: ");
			    number = scanner.next();
			    System.out.println("Enter the number of decimal positions:");
			    String decpos = scanner.next();
			    ni = Float.parseFloat(number);
			    dp = Integer.parseInt(decpos);
			    success = true;
			    scanner.close();
			} catch(Exception e){
				System.out.println(e.getMessage());
				System.out.println("Error parsing input. Please, try again.");
			}			
		}
		double naux = ni * Math.pow(10., (float)dp);
		double fl = Math.floor(naux);
		double cl = Math.ceil(naux);
		double resfl = Math.abs(naux - fl);
		double rescl = Math.abs(naux - cl);
		char[] oD;
		char[] fD;
		double rounded;
		if(resfl < rescl) {
			rounded = fl / Math.pow(10., (float)dp);
		}else {
			rounded = cl / Math.pow(10., (float)dp);
		}
	    oD = number.toCharArray();
	    String aux = String.valueOf(rounded);
	    fD = aux.toCharArray();
	    
	    String numInicial = "[";
	    String tipoRedondeo = tipoRed(dp);
	    String numInicial2 = finalNum(oD);
	    String numFinal = finalNum(fD);;
	    for(int i = 0; i<oD.length; i++) {
	    	if(i != 0) numInicial = numInicial + ",";
	    	numInicial = numInicial + toPeano(oD[i]);
	    }
	    numInicial = numInicial + "]";
	    
	    String test = "redondearDecimal(" + numInicial+", " + tipoRedondeo + ", redondeo(" + tipoRedondeo; 
	    test = test + ", numeroOriginal(" + numInicial2 + "), numeroRedondeado(" + numFinal + "))).";
	    System.out.println(test);
	}
	
	private static void ej2() {
		String test = "esCuadradoFantasticoSecreto([[";
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many lines are there in the array?");
		int lines = scanner.nextInt();
		for (int i = 0; i < lines; i++) {
			boolean valid = false;
			while(!valid) {
				System.out.println("Enter line " + i + " without any separators");
				try {
					String input = scanner.next();
					char[] numbers = input.toCharArray();
					if(numbers.length != lines) {
						int a = 5/0;
					}else {
						for (int j = 0; j < numbers.length; j++) {
							if(j != 0) test = test + ",";
							test = test + toPeano(numbers[j]);
						}
						test = test + "]";
						if (i != lines - 1) test = test + ",[";
						valid = true;
					}
					
				}catch(Exception e) {
					System.out.println("Invalid format. Please, try again");
				}
			}
		}
		test = test +"],";
		System.out.println("What's the secret number?");
		int secretInt = scanner.nextInt();
		char secret = (char)(secretInt + '0');
		test = test + toPeano(secret) + ").";
		scanner.close();
		System.out.println(test);
	}
	
	
	private static String finalNum(char[] input) {
		String res = "',', [";
		while(input[input.length - 1] == '0') {
			input = Arrays.copyOf(input, input.length-1);
		}
		if(input.length == 0) return res + "], []";
		for (int i = 0; i < input.length ; i++) {
			if(input[i] == '.') {
				res = res + "], [";
			}else {
				if(i != 0 && input[i-1] != '.') res = res + ",";
				res = res + toPeano(input[i]);
			}			
		}
		return res + "]";
	}
	
	private static String toPeano(char input) {
		switch(input){
			case '0':
				return "0";
			case '1':
				return "s(0)";
			case '2':
				return "s(s(0))";
			case '3':
				return "s(s(s(0)))";				
			case '4':
				return "s(s(s(s(0))))";				
			case '5':
				return "s(s(s(s(s(0)))))";				
			case '6':
				return "s(s(s(s(s(s(0))))))";				
			case '7':
				return "s(s(s(s(s(s(s(0)))))))";				
			case '8':
				return "s(s(s(s(s(s(s(s(0))))))))";				
			case '9':
				return "s(s(s(s(s(s(s(s(s(0)))))))))";
			case '.':
				return "','";
			default:
				return null;
		}
	}
	
	private static String tipoRed(int numDec) {
		switch(numDec) {
		case 0: return "redondeoUnidad";
		case 1: return "redondeoDecima";
		case 2: return "redondeoCentesima";
		default: return null;
		}
		
	}
	

}
