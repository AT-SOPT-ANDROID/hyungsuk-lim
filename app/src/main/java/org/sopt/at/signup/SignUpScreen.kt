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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.sopt.at.R
import org.sopt.at.component.TvingCustomTextField
import org.sopt.at.signin.SignIn

@Serializable
data object SignUp

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val signUpViewModel: SignUpViewModel = viewModel()
    val id = signUpViewModel.id.value
    val password = signUpViewModel.pw.value
    val isError = signUpViewModel.isError.value
    val step = signUpViewModel.step.intValue
    val idErrorMessage = stringResource(R.string.sign_up_id_error_msg)
    val pwErrorMessage = stringResource(R.string.sign_up_pw_error_msg)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            IconButton(
                onClick = {
                    if (step == 2) {
                        signUpViewModel.setIsError(isError = false)
                        signUpViewModel.onPrevious()
                    } else {
                        navController.navigate(SignIn("", ""))
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
        if (step == 1) {
            IdInputScreen(
                text = id,
                onValueChange = { signUpViewModel.updateId(it) },
                onNext = {
                    if (signUpViewModel.isValidId(id)) {
                        signUpViewModel.setIsError(isError = false)
                        signUpViewModel.nextStep()
                    } else {
                        signUpViewModel.setIsError(isError = true)
                        scope.launch {
                            snackbarHostState.showSnackbar(idErrorMessage)
                        }
                    }
                },
                isError = isError,
            )
        } else if (step == 2) {
            PasswordInputScreen(
                text = password,
                onValueChange = { signUpViewModel.updatePw(it) },
                onSignUp = {
                    if (signUpViewModel.isValidPw(password)) {
                        navController.navigate(SignIn(userId = id, userPw = password))
                    } else {
                        signUpViewModel.setIsError(isError = true)
                        scope.launch {
                            snackbarHostState.showSnackbar(pwErrorMessage)
                        }
                    }
                },
                isError = isError,
                signUpViewModel = signUpViewModel
            )
        }
    }
}

@Composable
fun IdInputScreen(
    text: String,
    onValueChange: (String) -> Unit,
    onNext: () -> Unit,
    isError: Boolean,
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
        TvingCustomTextField(
            value = text,
            label = stringResource(R.string.id_text),
            onValueChange = onValueChange,
            focusedBorderColor = Color.LightGray,
            cursorColor = Color.White,
            roundedCornerShape = RoundedCornerShape(2.dp),
            isVisible = true,
            switchVisibility = { }
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
            Text(text = stringResource(R.string.next_button), fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun PasswordInputScreen(
    text: String,
    onValueChange: (String) -> Unit,
    onSignUp: () -> Unit,
    isError: Boolean,
    signUpViewModel: SignUpViewModel
) {
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
        TvingCustomTextField(
            value = text,
            onValueChange = onValueChange,
            label = stringResource(R.string.pw_text),
            focusedBorderColor = Color.LightGray,
            cursorColor = Color.White,
            roundedCornerShape = RoundedCornerShape(2.dp),
            isVisible = signUpViewModel.visibility.value,
            switchVisibility = { signUpViewModel.switchVisibility() }
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
            Text(text = stringResource(R.string.next_button), fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}