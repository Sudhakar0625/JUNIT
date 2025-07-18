package com.healthycoderapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeEach
    void setup() {
        System.out.println("Testing is started");
        this.dietPlanner = new DietPlanner(20, 30, 50);
    }

    @AfterEach
    void afterEach()
    {
        System.out.println("Testing is completed");
    }

    @RepeatedTest(value = 10,name=RepeatedTest.LONG_DISPLAY_NAME)
    void should_ReturnCorrectDietPlan_When_CorrectCoder() {
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110, 73, 275);

        DietPlan actual = dietPlanner.calculateDiet(coder);

        assertAll(
                () -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()),
                () -> assertEquals(expected.getFat(), actual.getFat()),
                () -> assertEquals(expected.getProtein(), actual.getProtein())
        );
    }
}
