package com.cybertrace.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF080D14)
private val Panel = Color(0xFF111B27)
private val Green = Color(0xFF00E5A0)
private val White = Color(0xFFEAF2F8)
private val Grey = Color(0xFF91A0AF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CyberTraceApp() }
    }
}

@Composable
fun CyberTraceApp() {
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

                Spacer(Modifier.height(20.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Dashboard", "Consent Management").forEach { tab ->
                        Button(
                            onClick = { activeTab = tab },
                            colors = ButtonDefaults.buttonColors(
                                containerColor =
                                    if (activeTab == tab) Green.copy(alpha = 0.2f)
                                    else Panel
                            )
                        ) {
                            Text(
                                tab,
                                color = if (activeTab == tab) Green else White
                            )
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))

                if (activeTab == "Dashboard") {
                    DashboardScreen()
                } else {
                    ConsentScreen()
                }

                Spacer(Modifier.height(25.dp))

                Text(
                    "CYBER TRACE • VERSION 1.1",
                    color = Grey,
                    fontSize = 11.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun DashboardScreen() {
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

    InfoCard(
        "REMOTE SUPPORT",
        "NOT CONNECTED",
        "Remote support is not implemented."
    )

    Spacer(Modifier.height(10.dp))

    InfoCard(
        "SESSION HISTORY",
        "NO SESSIONS",
        "No sessions have been created."
    )
}

@Composable
fun ConsentScreen() {
    var modelSelected by remember { mutableStateOf(true) }
    var androidSelected by remember { mutableStateOf(true) }
    var sdkSelected by remember { mutableStateOf(false) }
    var approved by remember { mutableStateOf(false) }
    var submitted by remember { mutableStateOf(false) }

    Column {
        Text(
            "CONSENT MANAGEMENT",
            color = White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "Choose exactly which device information you agree to share.",
            color = Grey,
            fontSize = 13.sp
        )

        Spacer(Modifier.height(20.dp))

        ConsentOption(
            "Device model",
            modelSelected
        ) {
            modelSelected = it
            submitted = false
        }

        ConsentOption(
            "Android version",
            androidSelected
        ) {
            androidSelected = it
            submitted = false
        }

        ConsentOption(
            "Android SDK version",
            sdkSelected
        ) {
            sdkSelected = it
            submitted = false
        }

        Spacer(Modifier.height(15.dp))

        HorizontalDivider(color = Grey.copy(alpha = 0.3f))

        Spacer(Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = approved,
                onCheckedChange = {
                    approved = it
                    submitted = false
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Green
                )
            )

            Text(
                "I understand and approve the selected information sharing.",
                color = White,
                fontSize = 12.sp
            )
        }

        Spacer(Modifier.height(15.dp))

        Button(
            onClick = { submitted = true },
            enabled = approved &&
                (modelSelected || androidSelected || sdkSelected),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green,
                contentColor = Background
            )
        ) {
            Text("SUBMIT CONSENT")
        }

        Spacer(Modifier.height(15.dp))

        if (submitted) {
            InfoCard(
                "CONSENT STATUS",
                "APPROVED LOCALLY",
                "Your choices were recorded only in this screen. No information was transmitted."
            )
        } else {
            InfoCard(
                "PRIVACY NOTICE",
                "NO DATA TRANSMISSION",
                "This is a local interface prototype. It does not create links, upload information, or contact a server."
            )
        }
    }
}

@Composable
fun ConsentOption(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                title,
                color = White,
                modifier = Modifier.weight(1f),
                fontSize = 14.sp
            )

            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Green
                )
            )
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
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
