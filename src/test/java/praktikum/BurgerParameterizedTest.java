package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final String bunName;
    private final float bunPrice;
    private final List<IngredientData> ingredientsData;
    private final float expectedPrice;
    private final String expectedReceipt;

    private Burger burger;
    private Bun bun;

    private static class IngredientData {
        IngredientType type;
        String name;
        float price;

        IngredientData(IngredientType type, String name, float price) {
            this.type = type;
            this.name = name;
            this.price = price;
        }
    }

    public BurgerParameterizedTest(String bunName,
                                   float bunPrice,
                                   List<IngredientData> ingredientsData,
                                   float expectedPrice,
                                   String expectedReceipt) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientsData = ingredientsData;
        this.expectedPrice = expectedPrice;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters(name = "bun={0}, ingredients={2}, total={3}")
    public static Collection<Object[]> data() {
        String nl = System.lineSeparator();

        return Arrays.asList(new Object[][]{

                // --- без ингредиентов ---
                {
                        "White Bun",
                        100f,
                        Collections.emptyList(),
                        200f,
                        "(==== White Bun ====)" + nl +
                                "(==== White Bun ====)" + nl +
                                nl +
                                String.format(Locale.US, "Price: %f%n", 200f)
                },

                // --- один ингредиент ---
                {
                        "Brown Bun",
                        150f,
                        Arrays.asList(
                                new IngredientData(IngredientType.SAUCE, "Hot Sauce", 50f)
                        ),
                        350f,
                        "(==== Brown Bun ====)" + nl +
                                "= sauce Hot Sauce =" + nl +
                                "(==== Brown Bun ====)" + nl +
                                nl +
                                String.format(Locale.US, "Price: %f%n", 350f)
                },

                // --- несколько ингредиентов ---
                {
                        "White Bun",
                        200f,
                        Arrays.asList(
                                new IngredientData(IngredientType.FILLING, "Cutlet", 100f),
                                new IngredientData(IngredientType.SAUCE, "Cheese", 80f)
                        ),
                        580f,
                        "(==== White Bun ====)" + nl +
                                "= filling Cutlet =" + nl +
                                "= sauce Cheese =" + nl +
                                "(==== White Bun ====)" + nl +
                                nl +
                                String.format(Locale.US, "Price: %f%n", 580f)
                }
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);

        burger.setBuns(bun);

        for (IngredientData data : ingredientsData) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getType()).thenReturn(data.type);
            when(ingredient.getName()).thenReturn(data.name);
            when(ingredient.getPrice()).thenReturn(data.price);
            burger.addIngredient(ingredient);
        }
    }

    @Test
    public void getPriceShouldBeCorrect() {
        assertEquals(expectedPrice, burger.getPrice(), 0.0001);
    }

    @Test
    public void getReceiptShouldMatchExactly() {
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}