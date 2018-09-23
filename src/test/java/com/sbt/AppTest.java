package com.sbt;

import com.sbt.model.CalculationImpl;
import com.sbt.model.NotCorrectInputException;
import com.sbt.model.RPN;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testSimpleAddition() throws Exception {//1
        assertEquals("3", new CalculationImpl().calculate("1+2"));
    }

    @Test
    public void testSimpleSubtraction() throws Exception {//2
        assertEquals("1", new CalculationImpl().calculate("4-3"));
    }

    @Test
    public void testSimpleMultiplication() throws Exception {//3
        assertEquals("30", new CalculationImpl().calculate("5*6"));
    }

    @Test
    public void testSimpleDivision() throws Exception {//4
        assertEquals("3", new CalculationImpl().calculate("9/3"));
    }

    @Test
    public void testDivisionWithRemainder() throws Exception {//5
        assertEquals("0.5", new CalculationImpl().calculate("1/2"));
    }

    @Test
    public void testSimpleZeroDivision() throws Exception {//6
        assertEquals("∞", new CalculationImpl().calculate("1/(1-1)"));
    }

    @Test
    public void testBracket() throws Exception {//7
        assertEquals("0", new CalculationImpl().calculate("-(1-1)*2"));
    }

    @Test
    public void testBracket2() throws Exception {//8
        assertEquals("6", new CalculationImpl().calculate("(6+6)/(4-2)"));
    }

    @Test
    public void testBracket3() throws Exception {//9
        assertEquals("-72.8", new CalculationImpl().calculate("-((7+2.1)*8)"));
    }

    @Test
    public void testBracket4() throws Exception {//10
        assertEquals("2.4", new CalculationImpl().calculate("((3.2+3.4)/3-(4-3))*(3-1)"));
    }

    @Test
    public void testInvalidSymbol() throws Exception {//11
        try {
            new RPN("kjdakga").getResult();
        } catch (NotCorrectInputException e) {
            assertEquals(1, 1);
            return;
        }
        assertEquals(1, 0);
    }

    @Test(expected = NotCorrectInputException.class)
    public void testInvalidOpenBracket() throws Exception {//12
        new RPN("((5+3").getResult();
    }

    @Test(expected = NotCorrectInputException.class)
    public void testInvalidCloseBracket() throws Exception {//13
        new RPN("(5+3))").getResult();
    }

    @Test(expected = NotCorrectInputException.class)
    public void testInvalidOperator() throws Exception {//14
        new RPN("643-").getResult();
    }

    @Test(expected = NotCorrectInputException.class)
    public void testInvalidOperator2() throws Exception {//15
        new CalculationImpl().calculate("2+643*/");
    }

    @Test
    public void testNegativeNumders() throws Exception {//16
        assertEquals("-4", new CalculationImpl().calculate("-5+1"));
    }

    @Test
    public void testBigNumbers() throws Exception {//17
        assertEquals("25670864234658680", new CalculationImpl().calculate("10*2567086423465868"));
    }

    @Test
    public void testComplex() throws Exception {//18
        assertEquals("7", new CalculationImpl().calculate("((14.068+15.78)/(1.875+0.175))/((0.325+0.195)*4)"));
    }


    @Test
    public void testComplex2() throws Exception {//19
        assertEquals("200", new CalculationImpl().calculate("(57.24*3.55+430.728)/(2.7*1.88-1.336)+(127.18*4.35+14.067)/(18+2.1492/3.582)"));
    }

    @Test
    public void testComplex3() throws Exception {//20
        assertEquals("2.98895", new CalculationImpl().calculate("3.006-0.3417/34-0.875/125"));
    }

    @Test
    public void testComplex4() throws Exception {//21
        assertEquals("40.5", new CalculationImpl().calculate("5.7*16.2/20.52+127.68*0.5/4.56+34.68*15.4/(6.8*3.57)"));
    }

    @Test
    public void testComplex5() throws Exception {//22
        assertEquals("0", new CalculationImpl().calculate("(4.561+5.439)*0.1/((7.01-5.01)/0.5)-((4.45-2.2)/0.3)/((0.823+0.177)*30)"));
    }

    @Test
    public void testComplex6() throws Exception {//23
        assertEquals("23.8", new CalculationImpl().calculate("4.635/0.5+14.95/1.3+2.121/0.7"));
    }

    @Test
    public void testComplex7() throws Exception {//24
        assertEquals("34.256675", new CalculationImpl().calculate("589.72/16-18.305/7+0.0567/4"));
    }

    @Test
    public void testComplex8() throws Exception {//25
        assertEquals("1.6", new CalculationImpl().calculate("(0.008+0.992)*(5*0.6-1.4)"));
    }

    @Test
    public void testComplex9() throws Exception {//26
        assertEquals("900", new CalculationImpl().calculate("(50000-1397.3)/(20.4+33.603)"));
    }

    @Test
    public void testComplex10() throws Exception {//27
        assertEquals("1.12", new CalculationImpl().calculate("1.35/2.7+6.02-5.9+0.4/2.5*(4.2-1.075)"));
    }

    @Test
    public void testComplex11() throws Exception {//28
        assertEquals("0", new CalculationImpl().calculate("52/(6/(0.4-0.2)/(2.5*(0.8+1.2))+(34.06-33.81)*4/(6.84/(28.57-25.15)))-8"));
    }

    @Test
    public void testComplex12() throws Exception {//29
        assertEquals("839.45", new CalculationImpl().calculate("22.5/3.75+208.45+2.5/0.004"));
    }

    @Test
    public void testComplex13() throws Exception {//30
        assertEquals("4.5", new CalculationImpl().calculate("(0.1955+0.187)/0.085"));
    }

    @Test
    public void testComplex14() throws Exception {//31
        assertEquals("0.11", new CalculationImpl().calculate("15.76267/(100.6+42.697)"));
    }

    @Test
    public void testComplex15() throws Exception {//32
        assertEquals("15", new CalculationImpl().calculate("(86.9+667.6)/(37.1+13.2)"));
    }

    @Test
    public void testComplex16() throws Exception {//33
        assertEquals("-0.81", new CalculationImpl().calculate("-(9.09-9.0252)*(25.007-12.507)"));
    }

    @Test
    public void testComplex17() throws Exception {//34
        assertEquals("8", new CalculationImpl().calculate("(0.93+0.07)/(0.93-0.805)"));
    }

    @Test
    public void testComplex18() throws Exception {//35
        assertEquals("2701", new CalculationImpl().calculate("(2779.6+8024.4)/(1.98+2.02)"));
    }

    @Test
    public void testComplex19() throws Exception {//36
        assertEquals("1.4", new CalculationImpl().calculate("4.3-3.5+1.44/3.6+3.6/1.44*(0.1-0.02)"));
    }

    @Test
    public void testComplex20() throws Exception {//37
        assertEquals("0.41", new CalculationImpl().calculate("(0.578+0.172)*(0.823+0.117)-1.711/(4.418+1.382)"));
    }

    @Test
    public void testComplex21() throws Exception {//38
        assertEquals("-5.82", new CalculationImpl().calculate("-(32.52-((6+9.728/3.2)*2.5-1.6)*1.2-0.015/0.01)"));
    }

    @Test
    public void testComplex22() throws Exception {//39
        assertEquals("10.6", new CalculationImpl().calculate("50.32-((20+9.744/2.4)*0.5-1.63)/0.25+0.0752/0.04"));
    }

    @Test
    public void testComplex23() throws Exception {//40
        assertEquals("6.4", new CalculationImpl().calculate("(4.06*0.0058+3.3044895-(0.7584/2.37+0.0003/8))/(0.03625*80-2.43)"));
    }

    @Test
    public void testComplex24() throws Exception {//41
        assertEquals("0.08999765", new CalculationImpl().calculate("(2.045*0.033+10.518395-0.4647774/0.0562)/(0.003092/0.0001-5.188)"));
    }

    @Test
    public void testComplex25() throws Exception {//42
        assertEquals("-29", new CalculationImpl().calculate("(-30)-(-17)+(-6)-12+2"));
    }

    @Test
    public void testComplex26() throws Exception {//43
        assertEquals("-14", new CalculationImpl().calculate("1/3*2*(-6)*(-7)*(-1/2)"));
    }

    @Test
    public void testComplex27 () throws Exception {//44
        assertEquals("7", new CalculationImpl().calculate("(-1/3)*(2)*(-3)*(7)*1/2"));
    }

    @Test
    public void testComplex28() throws Exception {//45
        assertEquals("-9471.77147573", new CalculationImpl().calculate("52.6+(3425-1)/32535.1*(342-314104)-4+235*100"));
    }

    @Test(expected = NotCorrectInputException.class)
    public void testInvalidEqual() throws Exception {//46
        new CalculationImpl().calculate("5=5");
    }

    @Test(expected = Exception.class)
    public void testInvalidMinus() throws Exception {//47
            new RPN("9-7-").getResult();
    }

    @Test(expected = NotCorrectInputException.class)
    public void testInvalidPoint() throws Exception {//48
            new CalculationImpl().calculate(".4/6");
    }

    @Test(expected = NotCorrectInputException.class)
    public void testInvalidPoint2() throws Exception {//49
        new CalculationImpl().calculate("57.2-.1");
    }

    @Test
    public void testZero() throws Exception {//50
        assertEquals("-1", new CalculationImpl().calculate("00000000000-1"));
    }


}
