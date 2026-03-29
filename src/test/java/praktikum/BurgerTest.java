package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    // проверяем что булка установилась
    @Test
    public void setBunsCorrectlyTest() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    // увеличение списка
    @Test
    public void addIngredientIncreasesIngredientsSizeTest() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
    }

    // добавленный ингридиент соответствует
    @Test
    public void addIngredientAddsCorrectIngredientTest() {
        burger.addIngredient(ingredient);
        assertSame(ingredient, burger.ingredients.get(0));
    }

    // проверка порядка
    @Test
    public void addMultipleIngredientsMaintainsOrderTest() {
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        assertEquals(2, burger.ingredients.size());
        assertSame(ingredient, burger.ingredients.get(0));
        assertSame(ingredient2, burger.ingredients.get(1));
    }

    @Test
    public void removeIngredientByIndexTest() {
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientForwardTest() {
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
        assertSame(ingredient2, burger.ingredients.get(0));
        assertSame(ingredient3, burger.ingredients.get(1));
        assertSame(ingredient, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientBackwardTest() {
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        burger.moveIngredient(2, 0);

        assertEquals(3, burger.ingredients.size());
        assertSame(ingredient3, burger.ingredients.get(0));
        assertSame(ingredient, burger.ingredients.get(1));
        assertSame(ingredient2, burger.ingredients.get(2));
    }
}

//    @Test
//    public void setBunsCorrectlyTest() {
//        Burger burger = new Burger();
//        Bun bun = new Bun("White Bun", 100f);
//        burger.setBuns(bun);
//        assertSame(bun, burger.bun);
//    }
//
//    @Test
//    public void addIngredientIncreasesIngredientsSizeTest() {
//        Burger burger = new Burger();
//        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
//        burger.addIngredient(ingredient);
//        assertEquals(1, burger.ingredients.size());
//    }
//
//    @Test
//    public void addIngredientAddsCorrectIngredientTest() {
//        Burger burger = new Burger();
//        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
//        burger.addIngredient(ingredient);
//        assertSame(ingredient, burger.ingredients.get(0));
//    }
//
//    @Test
//    public void addMultipleIngredientsMaintainsOrderTest() {
//        Burger burger = new Burger();
//        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
//        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "Cutlet", 100f);
//        burger.addIngredient(ingredient1);
//        burger.addIngredient(ingredient2);
//        assertEquals(2, burger.ingredients.size());
//        assertSame(ingredient1, burger.ingredients.get(0));
//        assertSame(ingredient2, burger.ingredients.get(1));
//    }
//
//    @Test
//    public void removeIngredientByIndexTest() {
//        Burger burger = new Burger();
//        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
//        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "Cutlet", 100f);
//        burger.addIngredient(ingredient1);
//        burger.addIngredient(ingredient2);
//        burger.removeIngredient(0);
//        assertEquals(1, burger.ingredients.size());
//        assertSame(ingredient2, burger.ingredients.get(0));
//    }
//
//    @Test
//    public void moveIngredientForwardTest() {
//        Burger burger = new Burger();
//        Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "S1", 10f);
//        Ingredient ing2 = new Ingredient(IngredientType.FILLING, "F1", 20f);
//        Ingredient ing3 = new Ingredient(IngredientType.SAUCE, "S2", 30f);
//        burger.addIngredient(ing1);
//        burger.addIngredient(ing2);
//        burger.addIngredient(ing3);
//        burger.moveIngredient(0, 2);
//        assertEquals(3, burger.ingredients.size());
//        assertSame(ing2, burger.ingredients.get(0));
//        assertSame(ing3, burger.ingredients.get(1));
//        assertSame(ing1, burger.ingredients.get(2));
//    }
//
//    @Test
//    public void moveIngredientBackwardTest() {
//        Burger burger = new Burger();
//        Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "S1", 10f);
//        Ingredient ing2 = new Ingredient(IngredientType.FILLING, "F1", 20f);
//        Ingredient ing3 = new Ingredient(IngredientType.SAUCE, "S2", 30f);
//        burger.addIngredient(ing1);
//        burger.addIngredient(ing2);
//        burger.addIngredient(ing3);
//        burger.moveIngredient(2, 0);
//        assertEquals(3, burger.ingredients.size());
//        assertSame(ing3, burger.ingredients.get(0));
//        assertSame(ing1, burger.ingredients.get(1));
//        assertSame(ing2, burger.ingredients.get(2));
//    }
//
//    @Test
//    public void getPriceWithNoIngredientsTest() {
//        Burger burger = new Burger();
//        Bun bun = new Bun("White Bun", 100f);
//        burger.setBuns(bun);
//        float expected = 100f * 2;
//        assertEquals(expected, burger.getPrice(), 0.0001);
//    }
//
//    @Test
//    public void getPriceWithOneIngredientTest() {
//        Burger burger = new Burger();
//        Bun bun = new Bun("White Bun", 100f);
//        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
//        burger.setBuns(bun);
//        burger.addIngredient(ingredient);
//        float expected = 100f * 2 + 50f;
//        assertEquals(expected, burger.getPrice(), 0.0001);
//    }
//
//    @Test
//    public void getPriceWithMultipleIngredientsTest() {
//        Burger burger = new Burger();
//        Bun bun = new Bun("White Bun", 100f);
//        Ingredient ing1 = new Ingredient(IngredientType.SAUCE, "S1", 50f);
//        Ingredient ing2 = new Ingredient(IngredientType.FILLING, "F1", 80f);
//        burger.setBuns(bun);
//        burger.addIngredient(ing1);
//        burger.addIngredient(ing2);
//        float expected = 100f * 2 + 50f + 80f;
//        assertEquals(expected, burger.getPrice(), 0.0001);
//    }
//    @Test
//    public void getReceiptWithNoIngredientsTest() {
//        Burger burger = new Burger();
//        Bun bun = new Bun("White Bun", 100f);
//        burger.setBuns(bun);
//        String receipt = burger.getReceipt();
//        assertTrue(receipt.contains("White Bun"));
//        assertTrue(receipt.contains("200"));
//    }
//
//    @Test
//    public void getReceiptWithOneIngredientTest() {
//        Burger burger = new Burger();
//        Bun bun = new Bun("White Bun", 100f);
//        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 50f);
//        burger.setBuns(bun);
//        burger.addIngredient(ingredient);
//        String receipt = burger.getReceipt();
//        assertTrue(receipt.contains("White Bun"));
//        assertTrue(receipt.contains("sauce"));
//        assertTrue(receipt.contains("Hot Sauce"));
//        assertTrue(receipt.contains("250"));
//    }
//
//    @Test
//    public void getReceiptWithMultipleIngredientsTest() {
//        Burger burger = new Burger();
//        Bun bun = new Bun("White Bun", 100f);
//        Ingredient ing1 = new Ingredient(IngredientType.FILLING, "Cutlet", 100f);
//        Ingredient ing2 = new Ingredient(IngredientType.SAUCE, "Cheese", 80f);
//        burger.setBuns(bun);
//        burger.addIngredient(ing1);
//        burger.addIngredient(ing2);
//        String receipt = burger.getReceipt();
//        assertTrue(receipt.contains("White Bun"));
//        assertTrue(receipt.contains("filling"));
//        assertTrue(receipt.contains("Cutlet"));
//        assertTrue(receipt.contains("sauce"));
//        assertTrue(receipt.contains("Cheese"));
//        assertTrue(receipt.contains("380"));;
//    }
