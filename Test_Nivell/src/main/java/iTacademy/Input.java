package iTacademy;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
	
	public static int llegirInt(String missatge) {

		Scanner sc = new Scanner(System.in);
		int num = 0;
		Boolean correcte = false;

		do {
			try {
				System.out.println(missatge);
				num = sc.nextInt();
				correcte = true;
			} catch (InputMismatchException e) {
				System.out.println("Format error please input a valid number!");
			} finally {
				sc.nextLine();
			}
		} while (!correcte);

		return num;
	}

}