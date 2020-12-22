import java.util.*;
import java.io.*;
import static java.lang.System.*;

class Fraction {
	int numerator, denominator;
	 // true if positive, false if negative
	 // i tried a bunch of stuff for this but a boolean turned out to be simplest
	boolean sign = true;
	
	// only for use when denominator is 1
	public Fraction (int numerator) {
		this.numerator = Math.abs(numerator);
		denominator = 1;
		
		if (numerator < 0) sign = false;
	}
	
	// overriding constructor for user convenience
	public Fraction (int numerator, int denominator) {
		this.numerator = Math.abs(numerator);
		this.denominator = Math.abs(denominator);
		
		sign = !(numerator < 0 ^ denominator < 0);
	}
	
	// setter methods for numerator, denominator, and sign (for subtract method)
	public  void setNumerator   (int numerator)   { this.numerator   = numerator;   }
	public  void setDenominator (int denominator) { this.denominator = denominator; }
	private void flipSign       ()                { this.sign = !this.sign; } // only ever need to flip it
	
	// basic simplification method
	public void simplify () {
		int commonFactor = GCD(numerator, denominator);
		
		numerator /= commonFactor;
		denominator /= commonFactor;
	}
	
	// add fractions, calls addFractions for simplicity
	public Fraction add (Fraction f2) { return addFractions (f2); }
	
	// subtract fractions, just distribute the negative to the second fraction and treat it like addition
	public Fraction subtract (Fraction f2) {
		f2.flipSign(); return addFractions (f2);
	}
	
	// adds the two fractions
	private Fraction addFractions (Fraction f2) {
		Fraction toReturn;
		
		if (denominator != f2.denominator) {
			int new_denominator = LCM(denominator, f2.denominator);
			int new_numerator = (sign ? 1 : -1) * numerator * (new_denominator / denominator) +
				(f2.sign ? 1 : -1) * f2.numerator * (new_denominator / f2.denominator);
			
			toReturn = new Fraction (new_numerator, new_denominator);
		}
		else
			toReturn = new Fraction ((sign ? 1 : -1) * numerator + (f2.sign ? 1 : -1) * f2.numerator,
				denominator);
		
		toReturn.simplify();
		return toReturn;
	}
	
	// multiply fractions, calls multiplyFractions for simplicity
	public Fraction multiply (Fraction f2) { return multiplyFractions (f2); }
	
	// divide fractions, similar to subtraction, flip fraction and treat as multiplication
	public Fraction divide (Fraction f2) {
		int temp = f2.numerator; f2.setNumerator(f2.denominator);
		f2.setDenominator(temp); return multiplyFractions (f2);
	}
	
	// multiplies the two fractions
	private Fraction multiplyFractions (Fraction f2) {
		Fraction toReturn;
		
		int new_numerator = numerator * f2.numerator;
		int new_denominator = denominator * f2.denominator;
		
		toReturn = new Fraction (new_numerator, new_denominator);
		toReturn.simplify();
		toReturn.sign = !(sign ^ f2.sign);
		
		return toReturn;
	}
	
	// basic euclidean algorithm for GCD
	private int GCD (int denominator_1, int denominator_1) {
		if (denominator_1 == 0) return denominator_2;
		return GCD(denominator_2 % denominator_1, denominator_1);
	}
	
	// LCM method based on the GCD formula
	private int LCM (int denominator_1, int denominator_2) {
		return (denominator_1 * denominator_2) / GCD(denominator_1, denominator_2);
	}
}
