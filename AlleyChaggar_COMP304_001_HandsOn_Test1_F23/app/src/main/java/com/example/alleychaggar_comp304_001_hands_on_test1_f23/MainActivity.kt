package com.example.alleychaggar_comp304_001_hands_on_test1_f23

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alleychaggar_comp304_001_hands_on_test1_f23.ui.theme.AlleyChaggar_COMP304_001_HandsOn_Test1_F23Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun enterBtnClicked(view: View?) {
        if (view == null) return
        val intent = Intent(this, ExerciseTypesActivity::class.java)
        startActivity(intent)
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlleyChaggar_COMP304_001_HandsOn_Test1_F23Theme {
        Greeting("Android")
    }
}