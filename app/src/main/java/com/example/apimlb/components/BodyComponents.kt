package com.example.apimlb.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextView(texto:String){
    Text(text = texto,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,)
}

@Composable
fun Space(espacio:Int){
    Spacer(modifier = Modifier.height(espacio.dp))
}
@Composable
fun MainButton(
    backColor: Color,
    color: Color,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    padding: Dp = 8.dp,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = color,
            containerColor = backColor
        ),
        modifier = modifier
            .padding(padding)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(cornerRadius)),
        shape = RoundedCornerShape(cornerRadius),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}
