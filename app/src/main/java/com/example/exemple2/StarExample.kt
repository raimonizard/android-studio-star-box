package com.example.exemple2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.exemple2.ui.theme.Exemple2Theme

class StarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Per tal de que la app ocupi fins a dalt a on hi ha l'hora
        // enableEdgeToEdge()
        setContent {
            Exemple2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Star(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * @author Raimon Izard
 * @since 03/10/2024
 * @param modifier Modifier
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Star(modifier: Modifier = Modifier) {
    /*
    Per usar el ConstraintLayout cal importar a dins del build.gradle.kts (Module :app):
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.constraintlayout.compose)
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
     */
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxYellow, boxGreen, boxCyan, boxMagenta) = createRefs()

        Box(modifier = Modifier
            .size(120.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                centerTo(parent) // 'centerTo(parent)' faria el mateix que les quatre linies de sota
                /*
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                 */
            })

        Box(modifier = Modifier
            .size(120.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(boxRed.start) // Changed to top
                bottom.linkTo(boxRed.top)
            })

        Box(modifier = Modifier
            .size(120.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                start.linkTo(boxRed.start)
                top.linkTo(boxRed.bottom)
            })

        Box(modifier = Modifier
            .size(120.dp)
            .background(Color.Cyan)
            .constrainAs(boxCyan) {
                top.linkTo(boxRed.top)
                end.linkTo(boxRed.start) // Changed to start
            })

        Box(modifier = Modifier
            .size(120.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                top.linkTo(boxRed.top)
                start.linkTo(boxRed.end)
            })
    }
}