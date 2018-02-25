package imult;

import java.io.File;
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
//	  sub is identical to add, except for not setting the carry at the end (due to it always 0)
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

	  BigInt r = new BigInt();
	  
	  int n = Math.max(A.length(), B.length());
	  int mid = Math.floorDiv(n, 2);
	  
//	  obtain in reverse order due to representation
	  BigInt alphaZero 	= A.split(0, mid-1);
	  BigInt alphaOne 	= A.split(mid, n);
	  
	  BigInt betaZero 	= B.split(0, mid-1);
	  BigInt betaOne 	= B.split(mid, n);
	  
//	  base case - single multiplication
	  boolean cond = A.length() <= 1 || B.length() <= 1;
	  
	  if (cond) {
		  r = Arithmetic.schoolMul(alphaOne, betaOne);
	  } else {
		  
		  BigInt tZero	= koMul(alphaZero, betaZero);
		  
		  BigInt tTwo	= koMul(alphaOne, betaOne);
		  
		  BigInt tOne	= sub(
							  koMul((add(alphaZero, alphaOne)), (add(betaZero, betaOne))),
							  add(tZero, tTwo)
							  );
		  
//		  System.out.println(tZero.value());
//		  System.out.println(tOne.value());
//		  System.out.println(tTwo.value());
		  
		  r = add(r, tTwo);
		  r.lshift(mid);
		  r = add(r, tOne);
		  r.lshift(mid);
		  r = add(r, tZero);
		  
	  }
	  
	  return r;
	  
  }

  public static BigInt koMulOpt(BigInt A, BigInt B) {
			  
	  BigInt r = new BigInt();
	  
//	  control for Opt variant:
	  int min = Math.min(A.length(), B.length());
	  
	  if (min <= 10) {
		  
		  r = Arithmetic.schoolMul(A, B);
		  
	  } else {
		  
		  int n = Math.max(A.length(), B.length());
		  int mid = Math.floorDiv(n, 2);
		  
//		  obtain in reverse order due to representation
		  BigInt alphaZero 	= A.split(0, mid-1);
		  BigInt alphaOne 	= A.split(mid, n);
		  
		  BigInt betaZero 	= B.split(0, mid-1);
		  BigInt betaOne 	= B.split(mid, n);
		  
//		  base case - single multiplication
		  boolean cond = A.length() <= 1 || B.length() <= 1;
		  
		  if (cond) {
			  
			  r = Arithmetic.schoolMul(alphaOne, betaOne);
//			  
//			  DigitAndCarry dc = Arithmetic.mulDigits(alphaOne.getDigit(0), betaOne.getDigit(0));
//			  
//			  r.setDigit(0, dc.getDigit());
//			  r.setDigit(1, dc.getCarry());
			  
		  } else {
			  
			  BigInt tZero	= koMulOpt(alphaZero, betaZero);
			  
			  BigInt tTwo	= koMulOpt(alphaOne, betaOne);
			  
			  BigInt tOne	= sub(
					  koMulOpt((add(alphaZero, alphaOne)), (add(betaZero, betaOne))),
					  add(tZero, tTwo)
					  );
			  
//		  System.out.println(tZero.value());
//		  System.out.println(tOne.value());
//		  System.out.println(tTwo.value());
			  
//			  tTwo.lshift(2 * mid);
//			  tOne.lshift(mid);
//			  
//			  r = add(add(tZero, tOne), tTwo);
			  
			  r = add(r, tTwo);
			  r.lshift(mid);
			  r = add(r, tOne);
			  r.lshift(mid);
			  r = add(r, tZero);
			  
		  }
		  
	  }
	  
	  return r;
	  
  }

  public static void main(String argv[]) throws java.io.FileNotFoundException {
	  
//	  BigInt a = new BigInt("5 4321");
//	  BigInt b = new BigInt("9 8760");
	  
//	  BigIntMul.mulTest(new Unsigned(1000), new Unsigned(1));
	  
//	  koMul(new BigInt("1000 0000"), new BigInt("1000 0000")).print();
//	  
////	  a.print();
////	  
////	  System.out.println(a.length());
////	  
////	  a.split(0, 1).print();
//	  
//	  BigInt c = koMul(a, b);
//	  c.print();
//	  
//	  System.out.println(BigIntMul.mulVerify(a, b, koMul(a, b)));
//	  
//	  
//	  add(a, b).print();
//	  sub(b, a).print();
	  
//	  BigIntMul.getRunTimes(
//			  new Unsigned(1),
//			  new Unsigned(10),
//			  new Unsigned(90),
//			  new File("koMulOptTimes"),
//			  true);
//	  
//	  BigIntMul.getRatios(
//			  new Unsigned(1),
//			  new Unsigned(10),
//			  new Unsigned(90),
//			  new File("koMulOptRatios"),
//			  new Unsigned(100));
	  
	  
//	  BigIntMul.plotRunTimes(
//			  0.0018645571121443064,
//			  0.0016374178205805039,
//			  new File("koMulOptTimes"));
//	  
  }
} //end StudentCode class
