package org.sopt.at

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val signUp = { id: String, password: String ->
                        val intent = Intent().apply {
                            putExtra("id", id)
                            putExtra("password", password)
                        }
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                    SignUpView(modifier = Modifier.padding(innerPadding), signUp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUpView(modifier = Modifier, signUp = { _, _ -> })
}

@Composable
fun SignUpView(modifier: Modifier, signUp: (String, String) -> Unit) {
    var text by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var step by remember { mutableStateOf("아이디") }
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text(
                step + "를 입력해주세요.",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 36.dp)
            )
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("아이디") },
                singleLine = true
            )
            Button(
                onClick = {
                    if (step == "아이디") {
                        step = "비밀번호"
                        id = text
                        text = ""
                    } else {
                        password = text
                        signUp(id, password)
                    }
                },
                modifier = Modifier.width(480.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("다음", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}