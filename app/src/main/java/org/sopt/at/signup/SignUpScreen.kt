package org.sopt.at.signup

import android.util.Log
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val nickname = signUpViewModel.nickname.value
    val signUpStep = signUpViewModel.signUpStep
    val passwordVisibility = signUpViewModel.visibility.value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val signUpResult = signUpViewModel.signUpResult

    LaunchedEffect(signUpResult) {
        when (signUpResult) {
            is SignUpResult.Success -> navController.navigate(
                SignIn(
                    userId = id,
                    userPw = password
                )
            )

            is SignUpResult.Failure -> scope.launch {
                Log.i("스낵바", signUpResult.message ?: "")
                snackbarHostState.showSnackbar(signUpResult.message ?: "")
            }

            else -> {}
        }
        signUpViewModel.resetSignUpResult()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputScreen(
                    value = when (signUpStep) {
                        SignUpStep.Id -> id
                        SignUpStep.Password -> password
                        SignUpStep.Nickname -> nickname
                    },
                    signUpStep = signUpStep,
                    onValueChange = {
                        when (signUpStep) {
                            SignUpStep.Id -> {
                                signUpViewModel.updateId(it)
                            }

                            SignUpStep.Password -> {
                                signUpViewModel.updatePw(it)
                            }

                            SignUpStep.Nickname -> {
                                signUpViewModel.updateNickname(it)
                            }
                        }
                    },
                    onClickButton = {
                        when (signUpStep) {
                            SignUpStep.Nickname ->
                                signUpViewModel.requestSignUp()

                            else -> {
                                signUpViewModel.nextStep()
                            }
                        }
                    },
                    onClickBack = {
                        when (signUpStep) {
                            SignUpStep.Id ->
                                navController.navigate(SignIn("", ""))

                            else -> {
                                signUpViewModel.onPrevious()
                            }
                        }
                    },
                    visibility = passwordVisibility,
                    switchVisibility = { signUpViewModel.switchVisibility() }
                )
            }
        }
    )
}

@Composable
fun InputScreen(
    value: String,
    signUpStep: SignUpStep,
    onValueChange: (String) -> Unit,
    onClickButton: () -> Unit,
    onClickBack: () -> Unit,
    visibility: Boolean,
    switchVisibility: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        IconButton(
            onClick = onClickBack,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "뒤로가기",
                tint = Color.White
            )
        }
    }
    Column(
        modifier = Modifier
            .width(440.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text =
                when (signUpStep) {
                    SignUpStep.Id -> {
                        stringResource(R.string.sign_up_id_title)
                    }

                    SignUpStep.Password -> {
                        stringResource(R.string.sign_up_pw_title)
                    }

                    SignUpStep.Nickname -> {
                        stringResource(R.string.sign_up_nickname_title)
                    }
                },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 36.dp),
            color = Color.White
        )
        TvingCustomTextField(
            value = value,
            onValueChange = onValueChange,
            label = when (signUpStep) {
                SignUpStep.Id -> stringResource(R.string.id_text)
                SignUpStep.Password -> stringResource(R.string.pw_text)
                SignUpStep.Nickname -> stringResource(R.string.nickname_text)
            },
            focusedBorderColor = Color.LightGray,
            cursorColor = Color.White,
            roundedCornerShape = RoundedCornerShape(2.dp),
            isVisible = visibility,
            switchVisibility = {
                if (signUpStep == SignUpStep.Password) {
                    switchVisibility()
                }
            }
        )
        Text(
            text = when (signUpStep) {
                SignUpStep.Id -> stringResource(R.string.id_description)
                SignUpStep.Password -> stringResource(R.string.pw_description)
                SignUpStep.Nickname -> stringResource(R.string.nickname_description)
            },
            color = Color.Gray,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.weight(5f))
        Button(
            onClick = onClickButton,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(2.dp),
            enabled = value != "",
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Black,
                disabledContentColor = Color.White,
                containerColor = Color.White,
                contentColor = Color.Black,
            ),
            border = BorderStroke(0.5.dp, Color.Gray)
        ) {
            Text(
                text = when (signUpStep) {
                    SignUpStep.Id, SignUpStep.Password -> stringResource(R.string.next_button)
                    SignUpStep.Nickname -> stringResource(R.string.sign_up_text)
                }, fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}