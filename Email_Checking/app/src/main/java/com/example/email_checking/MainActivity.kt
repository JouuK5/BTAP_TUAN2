package com.example.email_checking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.email_checking.ui.theme.Email_checkingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Email_checkingTheme {
                var email by remember { mutableStateOf("") }
                var message by remember { mutableStateOf("")}
                var err_message by remember { mutableStateOf("")}

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Kiểm tra Email",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = {email = it},
                            label = { Text("Nhập địa chỉ")},
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(30.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        if(err_message.isNotEmpty()){
                            Text(
                                text = err_message,
                                color = Color.Red
                            )
                        }
                        if(message.isNotEmpty()){
                            Text(
                                text = message,
                                color = Color.Green
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = {
                                if(email.isEmpty() || email==null){
                                    err_message = "Email sai. Nhập lại đi!"
                                    message = ""
                                }else if(!check_email(email)){
                                    err_message = "K0' k4i' m34l kug~ nh4p s4i. Đo^' ngo^'k!!"
                                    message = ""
                                }else{
                                    message= "Email hợp lệ. Chúc mừng!!"
                                    err_message = ""
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor =Color(color = 0xFF5BA9FF),
                                contentColor = Color(color = 0xFFFFFFFF)
                            )
                        ){
                            Text(
                                text = "Kiểm tra",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }
                    }
                }
            }
        }
    }
}

fun check_email(email: String): Boolean {
    var foundAt = false
    for (c in email) {
        if (c == '@') {
            foundAt = true
        } else if (foundAt && c == '.') {
            return true
        }
    }
    return false
}

