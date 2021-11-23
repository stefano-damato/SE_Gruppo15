/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

/**
 *
 * @author nicol
 */
public class Complex {
    private double real;
    private double imaginary;
    
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getimaginary() {
        return imaginary;
    }
    
    @Override
    public String toString() {
        return real + " + j" + imaginary;
    }
    
    public Complex add(Complex a) {
        double realSum = real + a.getReal();
        double imaginarySum = imaginary + a.getimaginary();
        Complex sum = new Complex(realSum,imaginarySum);
        return sum;
    }
    
    public Complex sub(Complex a){
        double realSub = real - a.getReal();
        double imaginarySub = imaginary - a.getimaginary();
        Complex sub = new Complex(realSub,imaginarySub);
        return sub;
    }
    
    public Complex multiply(Complex a) {
        double realMultiply = real * a.getReal() + ((-1) * imaginary * a.getimaginary());
        double imaginaryMultiply = real * a.getimaginary() + imaginary * a.getReal();
        Complex multiply = new Complex(realMultiply,imaginaryMultiply);
        return multiply;
    }
    
    public Complex conjugate(){
        return new Complex(real, -imaginary);
    }
    
    public Complex divide(Complex a) throws DivisionException{
        if(a.getReal()==0 || a.getimaginary()==0)
            throw new DivisionException("Division not possible");
        
        double areal = StrictMath.pow(a.getReal(), 2.); 
        double aimag = StrictMath.pow(a.getimaginary(), 2.);

        double ac = this.real*a.getReal();
        double bd = this.imaginary*a.getimaginary();
        double bc = this.imaginary*a.getReal();
        double ad = this.real*a.getimaginary();

        return new Complex((ac+bd)/(areal+aimag),(bc-ad)/(areal+aimag));
    }
}
