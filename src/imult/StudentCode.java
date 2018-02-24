package imult;

import java.util.ArrayList;

/*
 * Class StudentCode: class for students to implement
 * the specific methods required by the assignment:
 * add()
 * sub()
 * koMul()
 * koMulOpt()
 * (See coursework handout for full method documentation)
 */

public class StudentCode {
  public static BigInt add(BigInt A, BigInt B) {
//	  create BigInt to store return value
	  BigInt r = new BigInt();

//	  hold carry for cross-checking in addition later
	  Unsigned carry = new Unsigned(0); 
	  
//	  calculate maximum length of the new BigInt
	  int newLength = Math.max(A.length(), B.length()) + 1;
	  
	  for (int i = 0; i < newLength; i++) {
		  DigitAndCarry dc = Arithmetic.addDigits(A.getDigit(i), B.getDigit(i), carry);
		  
		  r.setDigit(i, dc.getDigit());
		  
//		  store carry on outer scope for access next loop
		  carry = dc.getCarry();
	  }
	  
//	  prepend carry to result if one is produced after the last addition operation 
	  if (carry.intValue() > 0) {
		  r.setDigit(newLength, carry);
	  }
	  
	  return r;

  }

  public static BigInt sub(BigInt A, BigInt B) {
	  BigInt r = new BigInt();
	  
	  Unsigned carry = new Unsigned(0); 
	  
	  int newLength = Math.max(A.length(), B.length());
	  
	  for (int i = 0; i <= newLength; i++) {
		  DigitAndCarry dc = Arithmetic.subDigits(A.getDigit(i), B.getDigit(i), carry);
		  r.setDigit(i, dc.getDigit());
		  carry = dc.getCarry();
	  }
	  
	  return r;
  }

  public static BigInt koMul(BigInt A, BigInt B) {
	  return null;
	  
  }

  public static BigInt koMulOpt(BigInt A, BigInt B) {
	  return null;
  }

  public static void main(String argv[]) throws java.io.FileNotFoundException {
	  
	  BigInt a = new BigInt("1 2 3 4");
	  BigInt b = new BigInt("456");
	  
	  a.print();
	  
	  System.out.println(a.length());
	  
	  a.split(0, 1).print();
	  
	  
//	  
//	  add(a, b).print();
//	  sub(b, a).print();
  }
} //end StudentCode class
