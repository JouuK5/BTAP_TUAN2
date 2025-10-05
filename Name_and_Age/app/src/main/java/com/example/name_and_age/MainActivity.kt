package com.example.name_and_age

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.name_and_age.ui.theme.Name_and_AgeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Name_and_AgeTheme {
                CheckInput()
                }
            }
        }
    }

@Composable
fun CheckInput() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("")}
    var err_message by remember { mutableStateOf("")}

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "THỰC HÀNH 01",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Họ và tên",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.width(100.dp)
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .weight(1f)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Tuổi",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.width(100.dp)
                    )
                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if((age.isEmpty() || name.isEmpty())){
                        err_message = "Điền đủ thông tin dô!"
                    }else if(!age.isDigitsOnly()){
                        err_message = "Vui lòng nhập số!!"
                    }else{
                        var inp = age.toInt()
                        err_message = ""
                        if(inp < 2){
                           message = "Em bée"
                        }else if(inp in 2..5)
                            message = "Trẻ em"
                        else if(inp in 6..65)
                            message = "Người lớn ( đủ tuổi rồi he^^ )"
                        else
                            message = "Người già"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
                modifier = Modifier
                    .width(160.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Kiểm tra",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            if(err_message.isNotEmpty()){
                Text(text=err_message, color=Color.Red)
            }
            if(message.isNotEmpty()){
                Text(
                    text = message,
                    color = Color.Green
                )
            }
        }
    }
}


