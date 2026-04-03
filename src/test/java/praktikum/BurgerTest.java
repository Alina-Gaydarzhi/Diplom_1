package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    @Mock
    private Ingredient thirdIngredient;

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
        burger.addIngredient(firstIngredient);
        assertSame(firstIngredient, burger.ingredients.get(0));
    }

    // увеличение списка
    @Test
    public void addIngredientIncreasesSizeTest() {
        burger.addIngredient(firstIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    // проверка порядка
    @Test
    public void addIngredientMaintainsOrderTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        assertSame(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void addIngredientMaintainsOrderSecondTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        assertSame(secondIngredient, burger.ingredients.get(1));
    }

    // удаление элемента
    @Test
    public void removeIngredientDecreasesSizeTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShiftsElementsTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.removeIngredient(0);

        assertSame(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientForwardFirstPositionTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        burger.moveIngredient(0, 2);
        assertSame(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientForwardSecondPositionTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        burger.moveIngredient(0, 2);
        assertSame(thirdIngredient, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientForwardThirdPositionTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        burger.moveIngredient(0, 2);
        assertSame(firstIngredient, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientBackwardFirstPositionTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        burger.moveIngredient(2, 0);
        assertSame(thirdIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientBackwardSecondPositionTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        burger.moveIngredient(2, 0);
        assertSame(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientBackwardThirdPositionTest() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        burger.moveIngredient(2, 0);
        assertSame(secondIngredient, burger.ingredients.get(2));
    }

    @Test
    public void getPriceWithIngredientsTest() {
        when(firstIngredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);

        assertEquals(250f, burger.getPrice(), 0.0001);
    }

    @Test
    public void getReceiptContainsBunNameTest() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("White Bun"));
    }

    @Test
    public void getReceiptContainsIngredientNameTest() {
        when(firstIngredient.getName()).thenReturn("Cutlet");
        when(firstIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(firstIngredient.getPrice()).thenReturn(80f);
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Cutlet"));
    }

    @Test
    public void getReceiptContainsIngredientTypeTest() {
        when(firstIngredient.getName()).thenReturn("Cutlet");
        when(firstIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(firstIngredient.getPrice()).thenReturn(80f);
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("filling"));
    }

    @Test
    public void getReceiptContainsCorrectPriceTest() {
        when(firstIngredient.getPrice()).thenReturn(80f);
        when(firstIngredient.getName()).thenReturn("Cutlet");
        when(firstIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("280"));
    }
}