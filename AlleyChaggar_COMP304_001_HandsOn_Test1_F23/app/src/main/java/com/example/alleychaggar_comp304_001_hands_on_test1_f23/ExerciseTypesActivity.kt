package com.example.alleychaggar_comp304_001_hands_on_test1_f23

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alleychaggar_comp304_001_hands_on_test1_f23.ui.theme.AlleyChaggar_COMP304_001_HandsOn_Test1_F23Theme

class ExerciseTypesActivity : ComponentActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val items = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
    private var checkedItems = BooleanArray(items.size)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_types)

        val checkBox1 = findViewById<CheckBox>(R.id.checkBox)
        val checkBox2 = findViewById<CheckBox>(R.id.checkBox2)
        val checkBox3 = findViewById<CheckBox>(R.id.checkBox3)
        val checkBox4 = findViewById<CheckBox>(R.id.checkBox4)

        val showCheckedItemsButton = findViewById<ImageButton>(R.id.showCheckedItemsButton)

        val checkBoxes = listOf(checkBox1, checkBox2, checkBox3, checkBox4)

        showCheckedItemsButton.setOnClickListener {
            val checkedItemsText = StringBuilder("\n")

            for (checkBox in checkBoxes) {
                if (checkBox.isChecked) {
                    checkedItemsText.append(checkBox.text).append("\n")
                }
            }

            if (checkedItemsText.isNotEmpty()) {
                // Remove the last newline character
                checkedItemsText.deleteCharAt(checkedItemsText.length - 1)

                Toast.makeText(this, checkedItemsText.toString(), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "No items are checked", Toast.LENGTH_SHORT).show()
            }        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    AlleyChaggar_COMP304_001_HandsOn_Test1_F23Theme {
        Greeting2("Android")
    }
}