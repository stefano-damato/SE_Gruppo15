/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

/**
 *
 * @author benedettocirillo
 */
public class Variable implements Comparable<Variable>{
    private char name;
    private Complex value;

    public Variable(char name, Complex value) {
        this.name = name;
        this.value = value;
    }

    public char getName() {
        return name;
    }

    public Complex getValue() {
        return value;
    }
    
    public String getVariable(){
        return Character.toString(name) + " = " + value.toString();
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
        final Variable other = (Variable) obj;
        if (this.name != other.name) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Variable o) {
        return Character.compare(name, o.getName());
    }
    
    
}
