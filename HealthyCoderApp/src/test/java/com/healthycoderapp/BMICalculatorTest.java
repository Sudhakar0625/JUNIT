package com.healthycoderapp;

import org.junit.Test;

import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BMICalculatorTest {

    @Test
    public void should_ReturnTrue_When_DietRecommende(){

        double weight=81.2;
        double height=1.65;

        boolean recommended=BMICalculator.isDietRecommended(weight,height);

        assertTrue(recommended);

    }

    @Test
    public void should_ReturnFalse_When_DietNotRecommende(){

        double weight=50.0;
        double height=1.92;

        boolean recommended=BMICalculator.isDietRecommended(weight,height);

        assertFalse(recommended);

    }
    @Test
    public void should_ThrowArithematicExeception_When_HeightZero(){

        double weight=50.0;
        double height=0;

     //   Executable executable=()->BMICalculator.isDietRecommended(weight,height);
        Executable executable=()->{BMICalculator.isDietRecommended(weight,height);};

        assertThrows(ArithmeticException.class,executable);

    }
    @Test
    public void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty(){
        List<Coder> coders=new ArrayList<>();

        coders.add(new Coder(1.80,60.0));
        coders.add(new Coder(1.82,98.0));
        coders.add(new Coder(1.82,64.7));

        Coder coderWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);

        assertAll(
                ()->assertEquals(1.82,coderWorstBMI.getHeight()),
                ()->assertEquals(98.0,coderWorstBMI.getWeight()));


    }

}