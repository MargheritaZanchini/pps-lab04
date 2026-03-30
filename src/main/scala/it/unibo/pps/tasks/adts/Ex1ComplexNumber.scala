package it.unibo.pps.tasks.adts

/*  Exercise 1: 
 *  Complete the implementation of ComplexADT trait below, so that it passes
 *  the test in ComplexTest.
 */

object Ex1ComplexNumbers:

  trait ComplexADT:
    type Complex
    def complex(re: Double, im: Double): Complex
    extension (complex: Complex)
      def re(): Double
      def im(): Double
      def sum(other: Complex): Complex
      def subtract(other: Complex): Complex
      def asString(): String

  object BasicComplexADT extends ComplexADT:

    // Change assignment below: should probably define a case class and use it?
    case class complexClass(re: Double, im: Double)
    type Complex = complexClass
    def complex(re: Double, im: Double): Complex =
      complexClass(re, im)
    extension (complex: Complex)
      def re(): Double = complex.re
      def im(): Double = complex.im
      def sum(other: Complex): Complex = complexClass(other.re + complex.re, other.im + complex.im)
      def subtract(other: Complex): Complex = complexClass(complex.re - other.re, complex.im - other.im)
      def asString(): String = complex match {
        case Complex(r, i) if(i == 0) => "" + r
        case Complex(r, i) if(r == 0) => "" + i + "i"
        case Complex(r, i) if(i < 0) => "" + r + " - " + i.abs + "i"
        case Complex(r, i) => "" + r + " + " + i + "i"
      }

