package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class BurgerTest {
    @Test
    public void setBunsCorrectlyTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("White Bun", 100f);
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientIncreasesIngredientsSizeTest() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientAddsCorrectIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
        burger.addIngredient(ingredient);
        assertSame(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void addMultipleIngredientsMaintainsOrderTest() {
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "Cutlet", 100f);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(2, burger.ingredients.size());
        assertSame(ingredient1, burger.ingredients.get(0));
        assertSame(ingredient2, burger.ingredients.get(1));
    }

    @Test
    public void removeIngredientByIndexTest() {
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "Cutlet", 100f);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientForwardTest() {
        Burger burger = new Burger();
        Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "S1", 10f);
        Ingredient ing2 = new Ingredient(IngredientType.FILLING, "F1", 20f);
        Ingredient ing3 = new Ingredient(IngredientType.SAUCE, "S2", 30f);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        burger.addIngredient(ing3);
        burger.moveIngredient(0, 2);
        assertEquals(3, burger.ingredients.size());
        assertSame(ing2, burger.ingredients.get(0));
        assertSame(ing3, burger.ingredients.get(1));
        assertSame(ing1, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientBackwardTest() {
        Burger burger = new Burger();
        Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "S1", 10f);
        Ingredient ing2 = new Ingredient(IngredientType.FILLING, "F1", 20f);
        Ingredient ing3 = new Ingredient(IngredientType.SAUCE, "S2", 30f);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        burger.addIngredient(ing3);
        burger.moveIngredient(2, 0);
        assertEquals(3, burger.ingredients.size());
        assertSame(ing3, burger.ingredients.get(0));
        assertSame(ing1, burger.ingredients.get(1));
        assertSame(ing2, burger.ingredients.get(2));
    }

    @Test
    public void getPriceWithNoIngredientsTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("White Bun", 100f);
        burger.setBuns(bun);
        float expected = 100f * 2;
        assertEquals(expected, burger.getPrice(), 0.0001);
    }

    @Test
    public void getPriceWithOneIngredientTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("White Bun", 100f);
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        float expected = 100f * 2 + 50f;
        assertEquals(expected, burger.getPrice(), 0.0001);
    }

    @Test
    public void getPriceWithMultipleIngredientsTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("White Bun", 100f);
        Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "S1", 50f);
        Ingredient ing2 = new Ingredient(IngredientType.FILLING, "F1", 80f);
        burger.setBuns(bun);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        float expected = 100f * 2 + 50f + 80f;
        assertEquals(expected, burger.getPrice(), 0.0001);
    }

}
