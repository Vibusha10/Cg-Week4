import java.util.*;

interface MealPlan {
    void showMeal();
    boolean isValid();
}

class VegetarianMeal implements MealPlan {
    private String dish;

    public VegetarianMeal(String dish) {
        this.dish = dish;
    }

    @Override
    public void showMeal() {
        System.out.println("Vegetarian Meal: " + dish);
    }

    @Override
    public boolean isValid() {
        return !dish.toLowerCase().contains("meat");
    }
}

class VeganMeal implements MealPlan {
    private String dish;

    public VeganMeal(String dish) {
        this.dish = dish;
    }

    @Override
    public void showMeal() {
        System.out.println("Vegan Meal: " + dish);
    }

    @Override
    public boolean isValid() {
        return !dish.toLowerCase().contains("meat") &&
               !dish.toLowerCase().contains("egg") &&
               !dish.toLowerCase().contains("milk");
    }
}

class KetoMeal implements MealPlan {
    private String dish;

    public KetoMeal(String dish) {
        this.dish = dish;
    }

    @Override
    public void showMeal() {
        System.out.println("Keto Meal: " + dish);
    }

    @Override
    public boolean isValid() {
        return !dish.toLowerCase().contains("rice") &&
               !dish.toLowerCase().contains("bread");
    }
}

class HighProteinMeal implements MealPlan {
    private String dish;

    public HighProteinMeal(String dish) {
        this.dish = dish;
    }

    @Override
    public void showMeal() {
        System.out.println("High-Protein Meal: " + dish);
    }

    @Override
    public boolean isValid() {
        return dish.toLowerCase().contains("chicken") ||
               dish.toLowerCase().contains("tofu") ||
               dish.toLowerCase().contains("beans");
    }
}

class Meal<T extends MealPlan> {
    private T mealPlan;

    public Meal(T mealPlan) {
        this.mealPlan = mealPlan;
    }

    public void display() {
        mealPlan.showMeal();
    }

    public boolean validate() {
        return mealPlan.isValid();
    }
}

class MealPlanGenerator {
    public static <T extends MealPlan> void generateMealPlan(Meal<T> meal) {
        if (meal.validate()) {
            System.out.print("Valid Meal Plan - ");
            meal.display();
        } else {
            System.out.println("Invalid meal plan. Please revise your choice.");
        }
    }
}

public class Program4 {
    public static void main(String[] args) {
        Meal<VegetarianMeal> vegMeal = new Meal<>(new VegetarianMeal("Paneer Curry"));
        Meal<VeganMeal> veganMeal = new Meal<>(new VeganMeal("Tofu Stir Fry"));
        Meal<KetoMeal> ketoMeal = new Meal<>(new KetoMeal("Grilled Chicken"));
        Meal<HighProteinMeal> proteinMeal = new Meal<>(new HighProteinMeal("Tofu Salad"));

        List<Meal<? extends MealPlan>> meals = Arrays.asList(vegMeal, veganMeal, ketoMeal, proteinMeal);

        for (Meal<? extends MealPlan> meal : meals) {
            MealPlanGenerator.generateMealPlan(meal);
            System.out.println();
        }
    }
}
