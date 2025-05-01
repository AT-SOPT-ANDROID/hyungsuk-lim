package org.sopt.at.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.R
import org.sopt.at.home.HomeActivity
import org.sopt.at.signup.SignUpActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme


class SignInActivity : ComponentActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                val viewModel: SignInViewModel = viewModel()
                val snackbarHostState = remember { SnackbarHostState() }
                val mismatchId = stringResource(R.string.sign_in_id_snackbar)
                val mismatchPw = stringResource(R.string.sign_in_pw_snackbar)
                val signInResult = viewModel.signInResult
                val context = LocalContext.current
                resultLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == RESULT_OK) {
                        val id = result.data?.getStringExtra("id") ?: ""
                        val pw = result.data?.getStringExtra("pw") ?: ""
                        viewModel.setUserInfo(id, pw)
                    }
                }

                LaunchedEffect(signInResult) {
                    when (signInResult) {
                        is SignInResult.InvalidId -> snackbarHostState.showSnackbar(mismatchId)
                        is SignInResult.InvalidPw -> snackbarHostState.showSnackbar(mismatchPw)
                        is SignInResult.Success -> {
                            val intent = Intent(context, HomeActivity::class.java).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            intent.putExtra("userId", signInResult.userId)
                            context.startActivity(intent)
                            finish()
                        }

                        else -> {}
                    }
                    viewModel.resetSignInResult()
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    val onClickSignUp = {
                        resultLauncher.launch(Intent(this, SignUpActivity::class.java))
                    }
                    SignInScreen(
                        modifier = Modifier.padding(innerPadding),
                        onClickSignUp = onClickSignUp,
                        onClickSignIn = { viewModel.signIn() },
                    )
                }
            }
        }
    }
}

