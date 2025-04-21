package org.sopt.at.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.sopt.at.R


@Composable
fun TvingCustomTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    focusedBorderColor: Color,
    cursorColor: Color,
    roundedCornerShape: RoundedCornerShape,
    isVisible: Boolean,
    switchVisibility: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .clip(roundedCornerShape)
            .background(color = Color.DarkGray)
            .height(52.dp),
        placeholder = {
            Text(
                text = label,
                color = Color.LightGray
            )
        },
        textStyle = TextStyle(color = Color.White),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = cursorColor,
        ),
        shape = roundedCornerShape,
        trailingIcon = if (label == stringResource(R.string.pw_text)) {
            {
                IconButton(onClick = { switchVisibility() }) {
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
        visualTransformation = if (label == stringResource(R.string.pw_text) && !isVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
    )
}
