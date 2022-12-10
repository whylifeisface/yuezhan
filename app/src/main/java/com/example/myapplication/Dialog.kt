package com.example.myapplication

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

@Composable
fun MuDialog(
    title: @Composable (() -> Unit),
    text: @Composable (() -> Unit)? = null,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
) {
    AlertDialog(
        title = title,
        onDismissRequest = { /*TODO*/ },
        text = text,
        confirmButton = confirmButton,
        dismissButton = dismissButton
    )

}

@Composable
fun Me(title: @Composable () -> Unit, text: @Composable () -> Unit) {

    MuDialog(
        title = title,
        confirmButton = {
            Button(
                modifier = Modifier.border(0.dp, Color.White),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.outlinedButtonColors()
            ) {
                Text(text = stringResource(id = R.string.confirmButton))
            }
        },
        text = text,
        dismissButton = {
            Button(
                modifier = Modifier,
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.outlinedButtonColors()
            ) {
                Text(text = stringResource(id = R.string.dismissButton))
            }
        }
    )
}

@Composable
fun My(web: String? = "", name: String? = "",navController: NavController) {
    BackHandler {
        navController.popBackStack()
    }
    MuDialog(
        title = {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.add)
            )
        },
        confirmButton = {
            Button(
                modifier = Modifier.border(0.dp, Color.White),
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.outlinedButtonColors()
            ) {
                Text(text = stringResource(id = R.string.confirmButton))
            }
        },
        text = {
            if (web == null && name == null) {
                Constrain(web = "", name = "")
            }
        },

        dismissButton = {
            Button(
                modifier = Modifier,
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.outlinedButtonColors()
            ) {
                Text(text = stringResource(id = R.string.dismissButton))
            }
        }
    )
}

data class DialogConstrain(
    val web: String = "",
    val name: String = "",
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Constrain(web: String = "", name: String = "") {
    val keyboard = LocalSoftwareKeyboardController.current

    val webTextValue = remember {
        mutableStateOf(web)
    }
    val nameTextValue = remember {
        mutableStateOf(name)
    }
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .fillMaxWidth()
        .pointerInput(Unit) {
            detectTapGestures {
                keyboard?.hide()
            }
        }
    ) {
        val (webText, nameText, nameFor, webFor) = createRefs()
        createHorizontalChain(webFor, webText)
        createHorizontalChain(nameFor, nameText)
        createGuidelineFromAbsoluteLeft(200.dp)
        Text(modifier = Modifier.constrainAs(nameFor) {
            top.linkTo(nameText.top)
        }, text = "11")
        Text(modifier = Modifier.constrainAs(webFor) {
            top.linkTo(webText.top)
        }, text = "111")

        TextField(
            modifier = Modifier
                .clickable {
                    keyboard?.show()
                }
                .fillMaxWidth(0.4f)
                .height(20.dp)
                .constrainAs(nameText) {
                    top.linkTo(parent.top)
                },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = Color.Black,
                cursorColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
            ),
            value = webTextValue.value,
            onValueChange = {
                webTextValue.value = it
            }
        )

        TextField(
            modifier = Modifier
                .clickable {
                    keyboard?.show()
                }
                .fillMaxWidth(0.4f)
                .height(20.dp)
                .constrainAs(webText) {
                    top.linkTo(webText.top, 30.dp)

                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = Color.Black,
                cursorColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
            ),

            value = nameTextValue.value,
            onValueChange = { nameTextValue.value = it }
        )

    }
}