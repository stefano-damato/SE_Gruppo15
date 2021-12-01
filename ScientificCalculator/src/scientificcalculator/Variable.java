/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

/**
 * The <em>Variable</em> class represents a variable characterized by a the name and a value which is a complex number.
 * @author benedettocirillo
 */
public class Variable implements Comparable<Variable>{
    /** contains the name of the variable*/
    private char name;
    /** contains the value of the variable*/
    private Complex value;
    
    
    /**
     * Initializes an object of class <em>Variable</em>.
     * @param name {@code char}
     * @param value {@code Complex}
     */
    public Variable(char name, Complex value) {
        this.name = name;
        this.value = value;
    }
    
    /**
     * This method return the name of the variable
     * @return name {@code char}
     */
    public char getName() {
        return name;
    }
    
    /**
     * This method return the value of the variable
     * @return value {@code Complex}
     */
    public Complex getValue() {
        return value;
    }
    
    /**
     * This method return string representation of the variable
     * @return Character.toString(name) + " = " + value.toString() {@code String}
     */
    public String getVariable(){
        return Character.toString(name) + " = " + value.toString();
    }

    /**
     * This method overrides the method {@link #equals(java.lang.Object)}.
     * <p>
     * Check if two variables have the same name.
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
        final Variable other = (Variable) obj;
        if (this.name != other.name) {
            return false;
        }
        return true;
    }
    
    /**
     * This method overrides the method {@link #compareTo(scientificcalculator.Variable) #equals(java.lang.Object)}.
     * <p>
     * Variables 
     * @param obj
     * @return boolean value, true if the two objects are equal, false otherwise.
     */
    @Override
    public int compareTo(Variable o) {
        return Character.compare(name, o.getName());
    }
    
    
}
