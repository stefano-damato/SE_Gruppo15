/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.DivisionException;



/**
 * The <em>Complex</em> class represents a complex number characterized by a real part and an imaginary part.
 * 
 * @author group15
 */

public class Complex {
    /**Contains the real part of the complex number*/
    private double real;
    /**Contains the imaginary part of the complex number*/
    private double imaginary;
    
    private static final int decimals = 10^8;
    
    /**
     * Initializes an object of class <em>Complex</em>.
     * @param real {@code double}
     * @param imaginary {@code double}
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    
    /**
     * Returns the real part
     * @return real {@code double}
     */
    public double getReal() {
        return real;
    }
    
    /**
     * Returns the imaginary part
     * @return imaginary {@code double}
     */
    public double getImaginary() {
        return imaginary;
    }
    
    /**
     * Returns a string representation of the Complex object in Cartesian coordinate format.
     * @return a string representation of the Complex object {@code String}
     */
    public String getComplex(){
        return this.toString();
    }
    
    /**
     * Returns a string representation of the Complex object in Cartesian coordinate format.
     * @return a string representation of the Complex object {@code String}
     */
    @Override
    public String toString() {
        if(real==0){
            if(imaginary!=0)
                return (imaginary>0)? " + " + String.valueOf(imaginary)+"j": " - " + String.valueOf(-imaginary+"j");
            else return "0";
        }else{
            if(imaginary==0)
                return String.valueOf(real);
            else
                return (imaginary>0)? String.valueOf(real) + " + " + String.valueOf(imaginary)+"j": String.valueOf(real) + " - " + String.valueOf(-imaginary)+"j";
        }
    }
    
    /**
     * This method overrides the method {@link #equals(java.lang.Object)}.
     * <p>
     * Check if two complex numbers are equal. 
     * They are equal if their real part is the same and their imaginary part is the same, otherwise they are different.
     * @param obj
     * @return boolean value, true if the two objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Complex other = (Complex) obj;
        if ((Math.round(this.real*decimals)/decimals) != Math.round(other.real*decimals)/decimals) {
            return false;
        }
        if ((Math.round(this.imaginary*decimals)/decimals) != Math.round(other.imaginary*decimals)/decimals) {
            return false;
        }
        return true;
    }
     
    /**
     * This method adds respectively the real part and the imaginary part of the current Complex with the real and imaginary part of the Complex passed as a parameter.
     * @param a {@code Complex}
     * @return new object {@code Complex} that represents <code>a</code> added to <code>this</code>
     */
    public Complex add(Complex a) {
        double realSum = real + a.getReal();
        double imaginarySum = imaginary + a.getImaginary();
        Complex sum = new Complex(realSum,imaginarySum);
        return sum;
    }
    
    /**
     * This method subtracts respectively the real part and the imaginary part of the current Complex with the real and imaginary part of the Complex passed as a parameter.
     * @param a {@code Complex}
     * @return new object {@code Complex} that represents <code>a</code>  subtracted from <code>this</code>
     */
    public Complex sub(Complex a){
        double realSub = real - a.getReal();
        double imaginarySub = imaginary - a.getImaginary();
        Complex sub = new Complex(realSub,imaginarySub);
        return sub;
    }
    
    /**
     * This method multiplies the current Complex with Complex passed as a parameter.
     * @param a {@code Complex}
     * @return new object {@code Complex} that represents <code>a</code> multiplied to <code>this</code>
     */
    public Complex multiply(Complex a) {
        double realMultiply = real * a.getReal() + ((-1) * imaginary * a.getImaginary());
        double imaginaryMultiply = real * a.getImaginary() + imaginary * a.getReal();
        Complex multiply = new Complex(realMultiply,imaginaryMultiply);
        return multiply;
    }
    
    /**
     * This method divides the current Complex with Complex passed as a parameter.
     * @param a {@code Complex}
     * @return new object {@code Complex} that represents <code>this</code> divided by <code>a</code>
     * @throws DivisionException if the real part oand the imaginary part of parameter are both 0
     */
    public Complex divide(Complex a) throws DivisionException{
        if(a.getReal()==0 && a.getImaginary()==0)
            throw new DivisionException("Division not possible");
        
        double areal = StrictMath.pow(a.getReal(), 2.); 
        double aimag = StrictMath.pow(a.getImaginary(), 2.);

        double ac = this.real*a.getReal();
        double bd = this.imaginary*a.getImaginary();
        double bc = this.imaginary*a.getReal();
        double ad = this.real*a.getImaginary();
        
        return new Complex((ac+bd)/(areal+aimag),(bc-ad)/(areal+aimag));
    }
    
    /**
     * This method takes the square root of the current Complex.
     * Computes the principal branch of the square root, which 
     * is the value with 0 <= this < pi.
     * @return new object {@code Complex} that represents the square root of <code>this</code>
     */
    public Complex square(){
        double r = Math.sqrt(this.mod());
        double theta=Math.atan2(imaginary,real)/2;
        return new Complex(r*Math.cos(theta),r*Math.sin(theta));
    }
    
    /**
     * This method inverts the sign of the real part and the imaginary part of the current Complex.
     * @return new object {@code Complex} that represents <code>this</code> with inverted sign
     */
    public Complex invert(){
        return new Complex((real==0)? 0: -real,(imaginary==0)? 0:  -imaginary);
    }

    /**
        Modulus of this Complex number
        (the distance from the origin in polar coordinates).
        @return |z| where z is this Complex number.
    */
    public double mod() {
        if (real!=0 || imaginary!=0) {
            return Math.sqrt(real*real+imaginary*imaginary);
        } else {
            return 0;
        }
    }
    
}