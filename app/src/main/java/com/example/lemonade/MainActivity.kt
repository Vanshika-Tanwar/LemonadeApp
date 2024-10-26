    package com.example.lemonade
    
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.border
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.layout.wrapContentSize
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.Button
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import com.example.lemonade.ui.theme.LemonadeTheme
    
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                LemonadeTheme {
                    LemonadeApp()
                }
            }
        }
    }
    
    @Composable
    fun LemonadeActivity(modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    ) {
       var result by remember{ mutableStateOf(1) }
        val imageResource = when(result){
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }
        val descResource = when(result){
            1 -> R.string.lemon_tree
            2 -> R.string.lemon_squeeze
            3 -> R.string.lemon_drink
            else -> R.string.lemon_restart
        }
        val activityText = when(result){
            1 -> R.string.select_lemon
            2 -> R.string.squeeze
            3 -> R.string.drink
            else -> R.string.restart
        }
        var tapsReq by remember{ mutableStateOf(0)}
        var currTaps by remember{mutableStateOf(0)}
        val handleClick = {
            when (result) {
                1 -> {
                    // Select a lemon, move to the squeeze phase
                    tapsReq = (2..4).random() // Randomize the number of taps required for squeezing
                    currTaps = 0 // Reset current taps for the squeeze phase
                    result = 2
                }
                2 -> {
                    // Squeeze the lemon, count taps
                    if (currTaps < tapsReq - 1) {
                        currTaps++
                    } else {
                        // Once enough taps, move to the drink phase
                        result = 3
                    }
                }
                3 -> {
                    // Drink lemonade, move to restart
                    result = 4
                }
                else -> {
                    // Restart the process
                    result = 1
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color(0xfffff36d))
            .wrapContentSize(Alignment.Center)
        ){
            Text(
                text = "Lemonade",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
                )
        }
    
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            Button(
                onClick = { handleClick() },
                shape = RoundedCornerShape(30.dp)
            ) {
                Image(painter = painterResource(imageResource), contentDescription = stringResource(descResource))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(stringResource(activityText), fontSize = 18.sp)
        }
    }
    
    @Preview(showBackground = true)
    @Composable
    fun LemonadeApp() {
        LemonadeActivity()
    }