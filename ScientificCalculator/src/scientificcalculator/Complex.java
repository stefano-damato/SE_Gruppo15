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
        this.real = Math.round(real*1000.0)/1000.0;
        this.imaginary = Math.round(imaginary*1000.0)/1000.0;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }
    
    public String getComplex(){
        return this.toString();
    }
    
    @Override
    public String toString() {
        if(imaginary>=0)
            return String.valueOf(real) + " + j" + String.valueOf(imaginary);
        else
            return String.valueOf(real) + " - j" + String.valueOf(-imaginary);
    }

 

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
        if (Double.doubleToLongBits(this.real) != Double.doubleToLongBits(other.real)) {
            return false;
        }
        if (Double.doubleToLongBits(this.imaginary) != Double.doubleToLongBits(other.imaginary)) {
            return false;
        }
        return true;
    }
    
    
    
    public Complex add(Complex a) {
        double realSum = real + a.getReal();
        double imaginarySum = imaginary + a.getImaginary();
        Complex sum = new Complex(realSum,imaginarySum);
        return sum;
    }
    
    public Complex sub(Complex a){
        double realSub = real - a.getReal();
        double imaginarySub = imaginary - a.getImaginary();
        Complex sub = new Complex(realSub,imaginarySub);
        return sub;
    }
    
    public Complex multiply(Complex a) {
        double realMultiply = real * a.getReal() + ((-1) * imaginary * a.getImaginary());
        double imaginaryMultiply = real * a.getImaginary() + imaginary * a.getReal();
        Complex multiply = new Complex(realMultiply,imaginaryMultiply);
        return multiply;
    }
    
    public Complex conjugate(){
        return new Complex(real, -imaginary);
    }
    
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
    
    public Complex square(){
        double X = Math.abs(Math.sqrt((real + Math.sqrt(real * real + imaginary * imaginary)) / 2));
        double Y = imaginary / (2 * X);
        return new Complex(X, Y);
    }
    
    public Complex invert(){
        return new Complex((real==0)? 0: -real,(imaginary==0)? 0:  -imaginary);
    }
}
