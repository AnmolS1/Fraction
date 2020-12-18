import java.util.*;
import java.io.*;
import static java.lang.System.*;

class Fraction {
	int numerator, denominator;
	int sign = 1; // 1 if positive, -1 if negative
	
	public Fraction (int numerator) {
		this.numerator = Math.abs(numerator);
		denominator = 1;
		
		if (numerator < 0) sign *= -1;
	}
	
	public Fraction (int numerator, int denominator) {
		this.numerator = Math.abs(numerator);
		this.denominator = Math.abs(denominator);
		
		if (numerator < 0)   sign *= -1;
		if (denominator < 0) sign *= -1;
	}
	
	public void simplify () {
		
		
		int smaller = Math.min(numerator, denominator);
		
		for (int i = smaller; i >= 2; i--)
			if (numerator % i == 0 && denominator % i == 0) {
				numerator /= i;
				denominator /= i;
				break;
			}
	}
	
	public Fraction add (Fraction f2) {
		
	}
	
	public Fraction subtract (Fraction f2) {
		
	}
	
	public Fraction multiply (Fraction f2) {
		
	}
	
	public Fraction divide (Fraction f2) {
		
	}
}