package ru.abramov.tank_reference_system.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.abramov.tank_reference_system.R
import ru.abramov.tank_reference_system.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit,
    navController: NavController
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginEmpty = login.isBlank()
    val passwordEmpty = password.isBlank()
    val canLogin = !loginEmpty && !passwordEmpty

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text =stringResource(id = R.string.app_name), fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MyBlack) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MyGreen1
                )
            )
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(190.dp))

            Text(
                text = stringResource(id = R.string.log_in_to_filter),
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )

            Spacer(Modifier.height(32.dp))

            Text(
                text = stringResource(id = R.string.login),
                fontSize = 12.sp,
                color = Color(0xFF4B5563),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            )
            OutlinedTextField(
                value = login,
                onValueChange = { login = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = if (loginEmpty) Color.Red else MyGreen2,
                    unfocusedBorderColor = if (loginEmpty) Color.Red else Color(0xFFD1D5DB),
                    cursorColor = MyGreen2
                ),
                singleLine = true
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.password),
                fontSize = 12.sp,
                color = Color(0xFF4B5563),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = if (passwordEmpty) Color.Red else MyGreen2,
                    unfocusedBorderColor = if (passwordEmpty) Color.Red else Color(0xFFD1D5DB),
                    cursorColor = MyGreen2
                ),
                singleLine = true
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    onLogin(login, password)
                    navController.popBackStack()
                },
                enabled = canLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MyGreen2,
                    contentColor = MyBlack,
                    disabledContainerColor = Color(0xFFB0BEC5)
                )
            ) {
                Text(text = stringResource(id = R.string.enter), fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(24.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = Color(0xFFE5E7EB)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.continue_guest),
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )

            Spacer(Modifier.height(16.dp))

            OutlinedButton(
                onClick = {navController.popBackStack()},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MyBlack,
                    containerColor = MyGreen2
                )
            ) {
                Text(text = stringResource(id = R.string.continue_without_logging))
            }
        }
    }
}