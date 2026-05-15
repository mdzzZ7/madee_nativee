package com.example.registrasiseminarcompose

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class HasilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nama = intent.getStringExtra("NAMA") ?: ""
        val nim = intent.getStringExtra("NIM") ?: ""
        val prodi = intent.getStringExtra("PRODI") ?: ""
        val email = intent.getStringExtra("EMAIL") ?: ""
        val semester = intent.getStringExtra("SEMESTER") ?: ""
        val telepon = intent.getStringExtra("TELEPON") ?: ""

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HasilRegistrasiScreen(
                        nama = nama,
                        nim = nim,
                        prodi = prodi,
                        email = email,
                        semester = semester,
                        telepon = telepon,
                        onOpenWebsite = {
                            val browserIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://lms.ummi.ac.id/")
                            )
                            startActivity(browserIntent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HasilRegistrasiScreen(
    nama: String,
    nim: String,
    prodi: String,
    email: String,
    semester: String,
    telepon: String,
    onOpenWebsite: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Hasil Registrasi Seminar",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Nama     : $nama")
        Text(text = "NIM      : $nim")
        Text(text = "Prodi    : $prodi")
        Text(text = "Email    : $email")
        Text(text = "Semester : $semester")
        Text(text = "Telepon  : $telepon")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onOpenWebsite,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buka Website Seminar")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewHasilregistrasiSreen() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HasilRegistrasiScreen(
                nama = "Zakwan Abdurrahman",
                nim = "2430511043",
                prodi = "Teknik Informatika",
                email = "zakwan@gmail.com",
                semester = "3",
                telepon = "081234567890",
                onOpenWebsite = {}
            )
        }
    }
}