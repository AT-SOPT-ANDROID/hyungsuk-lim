package org.sopt.at.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.my.MyActivity
import org.sopt.at.R
import org.sopt.at.signup.SignUpActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignInActivity : ComponentActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val idState = mutableStateOf("")
    private val pwState = mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    idState.value = result.data?.getStringExtra("id") ?: ""
                    pwState.value = result.data?.getStringExtra("password") ?: ""
                }
            }
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                val mismatchId= stringResource(R.string.sign_in_id_snackbar)
                val mismatchPw= stringResource(R.string.sign_in_pw_snackbar)
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    val context = LocalContext.current
                    val onClickSignUp = {
                        resultLauncher.launch(Intent(this, SignUpActivity::class.java))
                    }
                    val onClickSignIn: (String, String) -> Unit = { id, password ->
                        if (id != idState.value) {
                            scope.launch {
                                snackbarHostState.showSnackbar(mismatchId)
                            }
                        } else if (password != pwState.value) {
                            scope.launch {
                                snackbarHostState.showSnackbar(mismatchPw)
                            }
                        } else {
                            val intent = Intent(context, MyActivity::class.java).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            intent.putExtra("userId", id)
                            context.startActivity(intent)
                            finish()
                        }
                    }
                    SignInView(
                        Modifier.padding(innerPadding),
                        onClickSignUp,
                        onClickSignIn,
                    )
                }
            }
        }
    }
}

@Composable
fun SignInView(
    modifier: Modifier,
    onClickSignUp: () -> Unit,
    onClickSignIn: (String, String) -> Unit,
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .width(440.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.sing_in_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp),
                color = Color.White
            )
            SignInInput(id, "아이디", { id = it }, Modifier.padding(bottom = 12.dp))
            SignInInput(password, "비밀번호", { password = it }, Modifier.padding(bottom = 12.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = { onClickSignIn(id, password) },
                enabled = id != "" && password != "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = Color.Gray,
                    containerColor = Color.Red
                )
            ) {
                Text(
                    text = stringResource(R.string.sign_in_button),
                    color = Color.LightGray, fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            SignInBottom(onClickSignUp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.term_description),
            fontSize = 12.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SignInInput(text: String, label: String, onValueChange: (String) -> Unit, modifier: Modifier) {
    var isVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(color = Color.DarkGray)
            .height(52.dp),
        placeholder = { Text(label, color = Color.LightGray) },
        textStyle = TextStyle(color = Color.White),
        trailingIcon = if (label == "비밀번호") {
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
            }
        } else null,
        visualTransformation = if (label == "비밀번호" && !isVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color.White,
        ),
        shape = RoundedCornerShape(4.dp),
    )
}

@Composable
fun SignInBottom(onClickSignUp: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.find_id_text),
            color = Color.LightGray
        )
        Text("|", color = Color.Gray)
        Text(
            text = stringResource(R.string.find_pw_text),
            color = Color.LightGray
        )
        Text("|", color = Color.Gray)
        Text(
            text = stringResource(R.string.sign_up_text),
            Modifier.clickable(
                onClick = onClickSignUp
            ),
            color = Color.LightGray
        )
    }
}