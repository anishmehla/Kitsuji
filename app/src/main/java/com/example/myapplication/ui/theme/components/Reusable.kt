package com.example.myapplication.ui.theme.components

import android.R.attr.icon
import android.R.id.icon
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun NavButtons(text: String,
               icon: ImageVector= Icons.Default.Add,
               contentDescription: String,
               onClick: () -> Unit,
               enabled: Boolean = true,
               modifier: Modifier = Modifier){
    OutlinedButton(onClick=onClick,
        shape= RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant, // light gray background
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant  // text/icon color
        ),
        enabled=enabled,
        modifier=modifier)  {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

    }
}

@Composable
fun NavItem(){
}
