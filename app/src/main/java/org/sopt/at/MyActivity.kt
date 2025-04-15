package org.sopt.at

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId = intent.getStringExtra("userId").orEmpty()
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyView(
                        modifier = Modifier.padding(innerPadding),
                        userId = userId
                    )
                }
            }
        }
    }
}

@Composable
fun MyView(
    modifier: Modifier = Modifier,
    userId: String
) {
    Column(modifier = modifier) {
        Text(userId)
    }
}