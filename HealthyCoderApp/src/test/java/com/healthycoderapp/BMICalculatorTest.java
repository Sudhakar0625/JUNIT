package com.healthycoderapp;

//import org.junit.Test;
import org.junit.jupiter.api.*;


import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class BMICalculatorTest {

    private String env="prod";
    @BeforeAll
    static void beforAll(){
        System.out.println("Before all unit tests.");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("After all unit tests");
    }
    @Nested
    class IsDietRecommndedTests
    {
        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv",numLinesToSkip = 1)
        public void should_ReturnTrue_When_DietRecommended(Double coderWeight,Double coderHeight){

            double weight=coderWeight;
            double height=coderHeight;

            boolean recommended=BMICalculator.isDietRecommended(weight,height);
            assertTrue(recommended);

        }

        @Test
        @DisplayName("Sample Method")
        @Disabled
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

    @Test
    public void should_ReturnNullWorstBMI_When_CoderListNotEmpty(){
        List<Coder> coders=new ArrayList<>();


        Coder coderWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);

        assertNull(coderWorstBMI);


    }
    @Test
    public void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty(){
        List<Coder> coders=new ArrayList<>();

        coders.add(new Coder(1.80,60.0));
        coders.add(new Coder(1.82,98.0));
        coders.add(new Coder(1.82,64.7));
        double[] expected={18.52,29.59,19.53};

        double[] bms=BMICalculator.getBMIScores(coders);
        assertArrayEquals(bms,expected);


    }
   @Test
    public void should_ReturnCoderWithInMs_when_CodersListHas1000Elements(){
        assumeTrue(this.env.equals("prod"));
        List<Coder> coders=new ArrayList<>();
        for(int i=0;i<1000;i++){
            coders.add(new Coder(1.0+i,10.0+i));
        }

        Executable executable=()->BMICalculator.findCoderWithWorstBMI(coders);
        assertTimeout(Duration.ofMillis(5000),executable);
    }

}