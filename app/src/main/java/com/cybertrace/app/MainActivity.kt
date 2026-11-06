package com.cybertrace.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

private val Background = Color(0xFF080D14)
private val Panel = Color(0xFF111B27)
private val Green = Color(0xFF00E5A0)
private val White = Color(0xFFEAF2F8)
private val Grey = Color(0xFF91A0AF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CyberTraceApp()
        }
    }
}

@Composable
fun CyberTraceApp() {
    var consent by remember { mutableStateOf(false) }
    var activeTab by remember { mutableStateOf("Dashboard") }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                Text(
                    "CYBER TRACE",
                    color = Green,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    "ETHICAL SECURITY PLATFORM",
                    color = Grey,
                    fontSize = 11.sp
                )

                Spacer(Modifier.height(25.dp))

                Text(
                    "Security Dashboard",
                    color = White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Welcome to your security workspace.",
                    color = Grey,
                    fontSize = 13.sp
                )

                Spacer(Modifier.height(20.dp))

                InfoCard(
                    "SYSTEM STATUS",
                    "APPLICATION READY",
                    "Your local dashboard is running."
                )

                Spacer(Modifier.height(15.dp))

                Text(
                    "DEVICE DIAGNOSTICS",
                    color = Green,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))

                InfoCard(
                    "DEVICE MODEL",
                    Build.MODEL ?: "Unknown",
                    "Information from this Android device"
                )

                Spacer(Modifier.height(10.dp))

                InfoCard(
                    "ANDROID VERSION",
                    Build.VERSION.RELEASE ?: "Unknown",
                    "Operating system version"
                )

                Spacer(Modifier.height(10.dp))

                InfoCard(
                    "SDK VERSION",
                    Build.VERSION.SDK_INT.toString(),
                    "Android platform API level"
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    "CONSENT MANAGEMENT",
                    color = Green,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Panel
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            "Information sharing",
                            color = White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            "Allow a clearly disclosed test session to share information you approve.",
                            color = Grey,
                            fontSize = 13.sp
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                if (consent) "Sharing permission enabled locally"
                                else "Sharing permission disabled",
                                color = if (consent) Green else Grey,
                                modifier = Modifier.weight(1f),
                                fontSize = 12.sp
                            )

                            Switch(
                                checked = consent,
                                onCheckedChange = { consent = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Green,
                                    checkedTrackColor = Green.copy(alpha = 0.3f)
                                )
                            )
                        }

                        Text(
                            "This switch is a local demo setting. It does not transmit data.",
                            color = Grey,
                            fontSize = 11.sp
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                Text(
                    "REMOTE SUPPORT",
                    color = Green,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))

                InfoCard(
                    "SECURE SUPPORT",
                    "NOT CONNECTED",
                    "Remote sessions will require device-owner approval."
                )

                Spacer(Modifier.height(10.dp))

                InfoCard(
                    "SESSION HISTORY",
                    "NO SESSIONS",
                    "Connection history will appear here."
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    "MODULES",
                    color = Green,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))

                val modules = listOf(
                    "Dashboard",
                    "Device Diagnostics",
                    "Consent Management",
                    "Remote Support"
                )

                modules.forEach { module ->
                    Button(
                        onClick = { activeTab = module },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (activeTab == module)
                                Green.copy(alpha = 0.2f)
                            else Panel
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            module,
                            color = if (activeTab == module) Green else White
                        )
                    }
                }

                Spacer(Modifier.height(15.dp))

                Text(
                    "Selected module: $activeTab",
                    color = Grey,
                    fontSize = 12.sp
                )

                Spacer(Modifier.height(25.dp))

                Text(
                    "CYBER TRACE • VERSION 1.0",
                    color = Grey,
                    fontSize = 11.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun InfoCard(
    title: String,
    value: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Panel
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                title,
                color = Grey,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(6.dp))

            Text(
                value,
                color = Green,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(4.dp))

            Text(
                description,
                color = Grey,
                fontSize = 11.sp
            )
        }
    }
}
