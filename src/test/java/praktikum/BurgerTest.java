package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Mock
    private Ingredient ingredient3;

    @Before
    public void setUp() {
        burger = new Burger();

        when(bun.getName()).thenReturn("White Bun");
        when(bun.getPrice()).thenReturn(100f);
    }

    // проверяем что булка установилась
    @Test
    public void setBunsStoresReferenceTest() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    // добавленный ингридиент соответствует
    @Test
    public void addIngredientStoresCorrectIngredientTest() {
        burger.addIngredient(ingredient1);
        assertSame(ingredient1, burger.ingredients.get(0));
    }

    // увеличение списка
    @Test
    public void addIngredientIncreasesSizeTest() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
    }

    // проверка порядка
    @Test
    public void addIngredientMaintainsOrderTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        assertSame(ingredient1, burger.ingredients.get(0));
        assertSame(ingredient2, burger.ingredients.get(1));
    }

    // удаление элемента
    @Test
    public void removeIngredientDecreasesSizeTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShiftsElementsTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertSame(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientForwardChangesOrderTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        burger.moveIngredient(0, 2);

        List<Ingredient> list = burger.ingredients;
        assertSame(ingredient2, list.get(0));
        assertSame(ingredient3, list.get(1));
        assertSame(ingredient1, list.get(2));
    }

    @Test
    public void moveIngredientBackwardChangesOrderTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        burger.moveIngredient(2, 0);

        List<Ingredient> list = burger.ingredients;

        assertSame(ingredient3, list.get(0));
        assertSame(ingredient1, list.get(1));
        assertSame(ingredient2, list.get(2));
    }

    @Test
    public void getPriceWithIngredientsTest() {
        when(ingredient1.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        assertEquals(250f, burger.getPrice(), 0.0001);
    }

    @Test
    public void getReceiptContainsBunNameTest() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("White Bun"));
    }

    @Test
    public void getReceiptContainsIngredientDataTest() {
        when(ingredient1.getName()).thenReturn("Cutlet");
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient1.getPrice()).thenReturn(80f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Cutlet"));
        assertTrue(receipt.contains("filling"));
    }

    @Test
    public void getReceiptContainsCorrectPriceTest() {
        when(ingredient1.getPrice()).thenReturn(80f);
        when(ingredient1.getName()).thenReturn("Cutlet");
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("280"));
    }
}