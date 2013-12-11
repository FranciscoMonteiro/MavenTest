/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jaxbteste.easymockfirst;

/**
 *
 * @author User
 */
public class IncomeCalculator {
    private ICalMethod calMethod;
    private Position position;

    public ICalMethod getCalMethod() {
        return calMethod;
    }

    public void setCalMethod(ICalMethod calMethod) {
        this.calMethod = calMethod;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
    public double calc(){
      if (calMethod == null) {
            throw new RuntimeException("CalcMethod not yet maintained");
        }
        if (position == null) {
            throw new RuntimeException("Position not yet maintained");
        }
        return calMethod.calc(position);
    } 
}
