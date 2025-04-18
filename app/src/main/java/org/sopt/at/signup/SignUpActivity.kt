package org.sopt.at.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import kotlin.math.sign

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                val signUpViewModel: SignUpViewModel = viewModel()
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    val id = signUpViewModel.id.value
                    val pw = signUpViewModel.pw.value
                    val step = signUpViewModel.step.value
                    val idErrorMessage = stringResource(R.string.sign_up_id_error_msg)
                    val pwErrorMessage = stringResource(R.string.sign_up_pw_error_msg)
                    val onNext: () -> Unit = {
                        if (signUpViewModel.isValidId(id)) {
                            signUpViewModel.setIsError(isError = false)
                            signUpViewModel.onNext()
                        } else {
                            signUpViewModel.setIsError(isError = true)
                            scope.launch {
                                snackbarHostState.showSnackbar(idErrorMessage)
                            }
                        }
                    }
                    val signUp: () -> Unit = {
                        if (signUpViewModel.isValidPw(pw)) {
                            val intent = Intent().apply {
                                putExtra("id", id)
                                putExtra("pw", pw)
                            }
                            setResult(RESULT_OK, intent)
                            finish()
                        } else {
                            signUpViewModel.setIsError(isError = true)
                            scope.launch {
                                snackbarHostState.showSnackbar(pwErrorMessage)
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
                                if (step == 2) {
                                    signUpViewModel.setIsError(isError = false)
                                    signUpViewModel.onPrevious()
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
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNext = onNext,
                        signUp = signUp,
                        step = step,
                        signUpViewModel = signUpViewModel
                    )
                }
            }
        }
    }
}
