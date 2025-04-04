package com.example.lemonade

import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var currentResult by remember { mutableStateOf(1) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Yellow
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        )
        {
            when (currentResult) {
                1 -> LemonTextAndImage(
                    image = R.drawable.lemon_tree,
                    text = R.string.one,
                    contentDescription = R.string.one,
                    onImageClick = { currentResult = 2 }
                )

                2 -> LemonTextAndImage(
                    image = R.drawable.lemon_squeeze,
                    text = R.string.two,
                    contentDescription = R.string.two,
                    onImageClick = { currentResult = 3 }
                )

                3 -> LemonTextAndImage(
                    image = R.drawable.lemon_drink,
                    text = R.string.three,
                    contentDescription = R.string.three,
                    onImageClick = { currentResult = 4 }
                )

                4 -> LemonTextAndImage(
                    image = R.drawable.lemon_restart,
                    text = R.string.four,
                    contentDescription = R.string.four,
                    onImageClick = { currentResult = 1 }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    image: Int,
    text: Int,
    contentDescription: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(contentDescription),
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = Color.Cyan.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(16.dp),
                    )
                .clickable{ onImageClick() }
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(text)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun LemonadeAppPreview(){
    LemonadeApp()
}