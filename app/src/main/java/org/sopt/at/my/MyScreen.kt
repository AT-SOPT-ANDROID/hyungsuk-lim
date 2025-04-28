package org.sopt.at.my

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import org.sopt.at.signin.SignInActivity

@Serializable
data class My(
    val userId: String
)

@Composable
fun MyScreen(
    paddingValues: PaddingValues,
) {
    val myViewModel: MyViewModel = viewModel()
    val userId = myViewModel.profile.userId
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(userId)
        Button(
            onClick = {
                val intent = Intent(context, SignInActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            },
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ) {
            Text("로그아웃")
        }
    }
}