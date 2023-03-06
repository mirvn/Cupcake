package uiTest

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.R
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.theme.CupcakeTheme
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {

    @get:Rule
    val composableTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And sub total
        val subTotal = "$100"

        composableTestRule.setContent {
            CupcakeTheme {
                SelectOptionScreen(
                    subtotal = subTotal,
                    options = flavours
                )
            }
        }
        flavours.forEach { flavour ->
            composableTestRule.onNodeWithText(flavour).assertIsDisplayed()
        }
        composableTestRule.onNodeWithText(
            composableTestRule.activity.getString(R.string.subtotal_price, subTotal)
        ).assertIsDisplayed()
        // And then the next button is disabled
        composableTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun selectOptionScreen_buttonNextEnabled() {
        // Given list of options
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And sub total
        val subTotal = "$100"
        composableTestRule.setContent {
            CupcakeTheme {
                SelectOptionScreen(
                    subtotal = subTotal,
                    options = flavours
                )
            }
        }
        composableTestRule.onNodeWithText(flavours[0]).performClick()
        composableTestRule.onNodeWithStringId(R.string.next).assertIsDisplayed()
    }
}
