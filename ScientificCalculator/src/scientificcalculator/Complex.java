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
    private double immaginary;

    public Complex(double real, double immaginary) {
        this.real = real;
        this.immaginary = immaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImmaginary() {
        return immaginary;
    }
    
    @Override
    public String toString() {
        return "Complex{" + "real=" + real + ", immaginary=" + immaginary + '}';
    }
    
    public Complex add(Complex a) {
        double realSum = real + a.getReal();
        double immaginarySum = immaginary + a.getImmaginary();
        Complex sum = new Complex(realSum,immaginarySum);
        return sum;
    }
    
    public Complex sub(Complex a){
        double realSub = real - a.getReal();
        double immaginarySub = immaginary - a.getImmaginary();
        Complex sub = new Complex(realSub,immaginarySub);
        return sub;
    }
    
    public Complex multiply(Complex a) {
        double realMultiply = real * a.getReal() + ((-1) * immaginary * a.getImmaginary());
        double immaginaryMultiply = real * a.getImmaginary() + immaginary * a.getReal();
        Complex multiply = new Complex(realMultiply,immaginaryMultiply);
        return multiply;
    }
}
