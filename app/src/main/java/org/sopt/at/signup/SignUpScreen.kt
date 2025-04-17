package org.sopt.at.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R

@Composable
fun SignUpScreen(
    modifier: Modifier,
    onNext: () -> Unit,
    signUp: () -> Unit,
    step: Int,
    signUpViewModel: SignUpViewModel
) {
    val id = signUpViewModel.id.value
    val password = signUpViewModel.pw.value
    val isError = signUpViewModel.isError.value
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (step == 1) {
            IdInputScreen(
                text = id,
                onValueChange = { signUpViewModel.updateId(it) },
                onNext = { onNext() },
                isError = isError
            )
        } else if (step == 2) {
            PasswordInputScreen(
                text = password,
                onValueChange = { signUpViewModel.updatePw(it) },
                onSignUp = { signUp() },
                isError = isError
            )
        }
    }
}

@Composable
fun IdInputScreen(
    text: String,
    onValueChange: (String) -> Unit,
    onNext: () -> Unit,
    isError: Boolean
) {
    Column(
        modifier = Modifier
            .width(480.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.sign_up_id_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 36.dp),
            color = Color.White
        )
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.id_text),
                    color = Color.LightGray
                )
            },
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
            text = stringResource(R.string.id_description),
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
            Text(text = "다음", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun PasswordInputScreen(
    text: String,
    onValueChange: (String) -> Unit,
    onSignUp: () -> Unit,
    isError: Boolean
) {
    var isVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .width(440.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.sign_up_pw_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 36.dp),
            color = Color.White
        )
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.pw_text),
                    color = Color.LightGray
                )
            },
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
            text = stringResource(R.string.pw_description),
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