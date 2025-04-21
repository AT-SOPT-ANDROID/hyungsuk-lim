package org.sopt.at.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.my.MyActivity
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
                    pwState.value = result.data?.getStringExtra("pw") ?: ""
                }
            }
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                val mismatchId = stringResource(R.string.sign_in_id_snackbar)
                val mismatchPw = stringResource(R.string.sign_in_pw_snackbar)
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    val signInViewModel = ViewModelProvider(this).get(SingInViewModel::class.java)
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
                    SignInScreen(
                        Modifier.padding(innerPadding),
                        onClickSignUp,
                        onClickSignIn,
                    )
                }
            }
        }
    }
}

