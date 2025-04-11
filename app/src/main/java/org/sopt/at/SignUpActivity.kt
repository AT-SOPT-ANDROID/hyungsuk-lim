package org.sopt.at

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    var step by remember { mutableStateOf("아이디") }
                    var isError by remember { mutableStateOf(false) }
                    val isValidId = { id: String ->
                        id.matches(Regex("^[a-z0-9]{6,12}$")) &&
                                id.contains(Regex("[a-z]"))
                    }
                    val isValidPassword = { password: String ->
                        password.length in 8..15 &&
                                password.contains(Regex("[A-Za-z]")) &&
                                password.contains(Regex("[0-9]")) &&
                                password.contains(Regex("[^A-Za-z0-9]"))
                    }
                    val onNext: (String) -> Unit = { id: String ->
                        if (isValidId(id)) {
                            isError = false
                            step = "비밀번호"
                        } else {
                            isError = true
                            scope.launch {
                                snackbarHostState.showSnackbar("아이디는 영문 소문자 또는 영문 소문자, 숫자 조합 6~12자리입니다.")
                            }
                        }
                    }
                    val signUp: (String, String) -> Unit = { id: String, password: String ->
                        if (isValidPassword(password)) {
                            val intent = Intent().apply {
                                putExtra("id", id)
                                putExtra("password", password)
                            }
                            setResult(RESULT_OK, intent)
                            finish()
                        } else {
                            isError = true
                            scope.launch {
                                snackbarHostState.showSnackbar("비밀번호는 영문, 숫자, 특수문자(~!@#\$&^&*) 조합 8~15자리입니다.")
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        IconButton(
                            onClick = {
                                if (step == "비밀번호") {
                                    isError = false
                                    step = "아이디"
                                } else {
                                    finish()
                                }
                            },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "뒤로가기",
                                tint = Color.White
                            )
                        }
                    }
                    SignUpView(
                        modifier = Modifier.padding(innerPadding),
                        onNext = onNext,
                        signUp = signUp,
                        step = step,
                        isError = isError
                    )
                }
            }
        }
    }
}

@Composable
fun SignUpView(
    modifier: Modifier,
    onNext: (String) -> Unit,
    signUp: (String, String) -> Unit,
    step: String,
    isError: Boolean
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (step == "아이디") {
            IdInputView(
                text = id,
                onValueChange = { id = it },
                onNext = { onNext(id) },
                isError = isError
            )
        } else if (step == "비밀번호") {
            PasswordInputView(
                text = password,
                onValueChange = { password = it },
                onSignUp = { signUp(id, password) },
                isError = isError
            )
        }
    }
}

@Composable
fun IdInputView(
    text: String,
    onValueChange: (String) -> Unit,
    onNext: () -> Unit,
    isError: Boolean
) {
    Column(
        modifier = Modifier
            .width(480.dp)
            .padding(horizontal = 4.dp)
    ) {
        Text(
            "아이디를 입력해주세요.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 36.dp),
            color = Color.White
        )
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = { Text("아이디", color = Color.LightGray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(2.dp))
                .background(color = Color.DarkGray)
                .height(52.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.White,
            ),
            shape = RoundedCornerShape(2.dp),
            textStyle = TextStyle(color = Color.White)
        )
        Text(
            "영문 소문자 또는 영문 소문자, 숫자 조합 6~12자리",
            color = if (isError) {
                Color.Red
            } else {
                Color.Gray
            },
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.weight(5f))
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(2.dp))
                .height(52.dp),
            shape = RoundedCornerShape(2.dp),
            enabled = text != "",
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Black,
                disabledContentColor = Color.White,
                containerColor = Color.White,
                contentColor = Color.Black,
            ),
            border = BorderStroke(0.5.dp, Color.Gray)
        ) {
            Text("다음", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun PasswordInputView(
    text: String,
    onValueChange: (String) -> Unit,
    onSignUp: () -> Unit,
    isError: Boolean
) {
    var isVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .width(440.dp)
            .padding(horizontal = 4.dp)
    ) {
        Text(
            "비밀번호를 입력해주세요.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 36.dp),
            color = Color.White
        )
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = { Text("비밀번호", color = Color.LightGray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(2.dp))
                .background(color = Color.DarkGray)
                .height(52.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.White,
            ),
            shape = RoundedCornerShape(2.dp),
            textStyle = TextStyle(color = Color.White),
            trailingIcon =
                {
                    IconButton(onClick = { isVisible = !isVisible }) {
                        Icon(
                            painter = painterResource(
                                if (isVisible) R.drawable.baseline_remove_red_eye_24
                                else R.drawable.baseline_visibility_off_24
                            ),
                            contentDescription = "",
                            tint = Color.LightGray
                        )
                    }

                },
            visualTransformation = if (!isVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
        )
        Text(
            "영문, 숫자, 특수문자(~!@#$&^&*) 조합 8~15자리",
            color = if (isError) {
                Color.Red
            } else {
                Color.Gray
            },
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.weight(5f))
        Button(
            onClick = onSignUp,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(2.dp),
            enabled = text != "",
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Black,
                disabledContentColor = Color.White,
                containerColor = Color.White,
                contentColor = Color.Black,
            ),
            border = BorderStroke(0.5.dp, Color.Gray)
        ) {
            Text("다음", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}