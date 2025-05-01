package org.sopt.at.signin

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
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.R
import org.sopt.at.component.TvingCustomTextField

@Composable
fun SignInScreen(
    modifier: Modifier,
    onClickSignUp: () -> Unit,
    onClickSignIn: () -> Unit,
) {
    val signInViewModel: SignInViewModel = viewModel()
    val id = signInViewModel.id.value
    val password = signInViewModel.pw.value
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
            TvingCustomTextField(
                value = id,
                label = stringResource(R.string.id_text),
                onValueChange = { signInViewModel.updateId(it) },
                modifier = Modifier.padding(bottom = 12.dp),
                focusedBorderColor = Color.White,
                cursorColor = Color.White,
                roundedCornerShape = RoundedCornerShape(4.dp),
                isVisible = true,
            )
            TvingCustomTextField(
                value = password,
                label = stringResource(R.string.pw_text),
                onValueChange = { signInViewModel.updatePw(it) },
                modifier = Modifier.padding(bottom = 12.dp),
                focusedBorderColor = Color.White,
                cursorColor = Color.White,
                roundedCornerShape = RoundedCornerShape(4.dp),
                isVisible = signInViewModel.visibility.value,
                switchVisibility = { signInViewModel.switchVisibility() }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = { onClickSignIn() },
                enabled = id.isNotBlank() && password.isNotBlank(),
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
fun SignInBottom(onClickSignUp: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.find_id_text),
            color = Color.LightGray
        )
        VerticalDivider(thickness = 1.dp)
        Text(
            text = stringResource(R.string.find_pw_text),
            color = Color.LightGray
        )
        VerticalDivider(thickness = 1.dp)
        Text(
            text = stringResource(R.string.sign_up_text),
            Modifier.clickable(
                onClick = onClickSignUp
            ),
            color = Color.LightGray
        )
    }
}