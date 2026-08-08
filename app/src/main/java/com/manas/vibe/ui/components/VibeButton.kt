package com.manas.vibe.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VibeButton(
    text:String,
    onClick:()-> Unit,
    modifier: Modifier,
    enabled: Boolean=true,
    isLoading: Boolean=false
){
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier
    ) {
        if (isLoading){
            CircularProgressIndicator(
                strokeWidth = 2.dp

            )
        }else{
            Text(
                text = text
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
private fun VibeButtonPreview(){
VibeButton(
    text = "Login",
    onClick ={},
    modifier = Modifier,

)

}