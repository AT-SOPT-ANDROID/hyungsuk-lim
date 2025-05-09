package org.sopt.at.my

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import org.sopt.at.MainViewModel
import org.sopt.at.utils.getUserIdFromPrefs

@Serializable
data object My

@Composable
fun MyScreen(
    paddingValues: PaddingValues,
    navigateToSignIn: (id: String, password: String) -> Unit,
    viewModel: MyViewModel = viewModel()
) {
    val activity = LocalActivity.current
    val context = LocalContext.current
    val mainViewModel: MainViewModel =
        viewModel(viewModelStoreOwner = activity as ViewModelStoreOwner)

    val nickname = viewModel.nickname

    val userId = remember {
        getUserIdFromPrefs(context = context)
    }

    LaunchedEffect(Unit) {
        viewModel.getMyNickname(userId = userId)
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = nickname.value)
        Button(
            onClick = {
                mainViewModel.logout()
                navigateToSignIn("", "")
            },
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ) {
            Text("로그아웃")
        }
    }
}