/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaxbteste.easymockfirst;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class IncomeCalculatorTest {

    private ICalMethod calcMethod;
    private IncomeCalculator calc;

    @Before
    public void setUp() throws Exception {
    // NiceMocks return default values for
        // unimplemented methods
        calcMethod = createNiceMock(ICalMethod.class);
        
        calc = new IncomeCalculator();
    }

    @Test
    public void testCalc1() {
        int r = 2*2;
        assert(r>0 && r<5);
        // Setting up the expected value of the method call calc
        expect(calcMethod.calc(Position.BOSS)).andReturn(70000.0).times(2);
        expect(calcMethod.calc(Position.PROGRAMMER)).andReturn(50000.0);
        // Setup is finished need to activate the mock        
        
        replay(calcMethod);

        calc.setCalMethod(calcMethod);
        try {
            calc.calc();
            fail("Exception did not occur");
        } catch (RuntimeException e) {

        }
        calc.setPosition(Position.BOSS);
        assertEquals(70000.0, calc.calc(), 0);
        assertEquals(70000.0, calc.calc(), 0);
        calc.setPosition(Position.PROGRAMMER);
        assertEquals(50000.0, calc.calc(), 0);
        calc.setPosition(Position.SURFER);
        verify(calcMethod);
    }

    @Test(expected = RuntimeException.class)
    public void testNoCalc() {
        calc.setPosition(Position.SURFER);
        calc.calc();
    }

    @Test(expected = Exception.class)
    public void testNoPosition() {
        expect(calcMethod.calc(Position.BOSS)).andReturn(70000.0);
        replay(calcMethod);
        calc.setCalMethod(calcMethod);
        calc.calc();
    }

    @Test(expected = RuntimeException.class)
    public void testCalc2() {
        // Setting up the expected value of the method call calc
        expect(calcMethod.calc(Position.SURFER)).andThrow(new RuntimeException("Don't know this guy")).times(1);

        // Setup is finished need to activate the mock
        replay(calcMethod);
        calc.setPosition(Position.SURFER);
        calc.setCalMethod(calcMethod);
        calc.calc();
    }

}
