package org.d3if0006.chatapp

import androidx.compose.ui.tooling.preview.Preview
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AppointmentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppointmentScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen() {
    // State untuk mengelola waktu yang dipilih
    var selectedTime by remember { mutableStateOf<String?>(null) }

    // Daftar jadwal (true = tersedia, false = tidak tersedia)
    val schedules = listOf(
        "08:00" to false,
        "09:00" to false,
        "10:00" to true,
        "11:00" to false,
        "12:00" to false,
        "13:00" to true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFCDD2)) // Warna latar belakang
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "Teling",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Informasi Dosen
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0E0)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Username", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "STATUS", fontSize = 16.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Whatsapp : ", fontSize = 16.sp)
                Text(text = "Instagram : ", fontSize = 16.sp)
                Text(text = "LinkedIn : ", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Judul Jadwal
        Text(
            text = "JADWAL",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Daftar waktu
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            schedules.chunked(3).forEach { rowSchedules ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowSchedules.forEach { (time, available) ->
                        Button(
                            onClick = { if (available) selectedTime = time },
                            enabled = available,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedTime == time) Color.Green else if (available) Color.White else Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(8.dp)
                                .width(80.dp)
                                .height(40.dp)
                        ) {
                            Text(text = time, fontSize = 14.sp, color = if (available) Color.Black else Color.White)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol Invite
        Button(
            onClick = {
                if (selectedTime != null) {
                    println("Mengirim undangan untuk waktu $selectedTime")
                }
            },
            enabled = selectedTime != null,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedTime != null) Color.Black else Color.Gray
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Invite",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppointmentScreen() {
    AppointmentScreen()
}
