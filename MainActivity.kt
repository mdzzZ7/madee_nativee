package com.example.registrasiseminarcompose

import android.content.Intent
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormRegistrasiScreen(
                        onSubmit = { nama, nim, prodi, email, semester, telepon ->
                            val intent = Intent(this,
                                HasilActivity::class.java).apply {
                                putExtra("NAMA", nama)
                                putExtra("NIM", nim)
                                putExtra("PRODI", prodi)
                                putExtra("EMAIL", email)
                                putExtra("SEMESTER", semester)
                                putExtra("TELEPON", telepon)
                            }
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FormRegistrasiScreen(
    onSubmit: (String, String, String, String, String, String) -> Unit
) {
    var nama by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var prodi by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var semester by remember { mutableStateOf("") }
    var telepon by remember { mutableStateOf("") }

    var showWarningDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showResetConfirmDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Form Registrasi Seminar Mahasiswa",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text("Nama Mahasiswa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = nim,
            onValueChange = { nim = it },
            label = { Text("NIM") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = prodi,
            onValueChange = { prodi = it },
            label = { Text("Program Studi") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = semester,
            onValueChange = { semester = it },
            label = { Text("Semester") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = telepon,
            onValueChange = { telepon = it },
            label = { Text("Nomor Telepon") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (nama.isBlank() || nim.isBlank() || prodi.isBlank() ||
                    email.isBlank() || semester.isBlank() || telepon.isBlank()) {
                    showWarningDialog = true
                } else {
                    showConfirmDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Daftar Seminar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = {
                showResetConfirmDialog = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset")
        }
    }

    if (showResetConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showResetConfirmDialog = false },
            title = { Text("Konfirmasi Reset") },
            text = { Text("Apakah Anda yakin ingin menghapus seluruh data input?") },
            confirmButton = {
                TextButton(onClick = {
                    nama = ""
                    nim = ""
                    prodi = ""
                    email = ""
                    semester = ""
                    telepon = ""
                    showResetConfirmDialog = false
                }) {
                    Text("Hapus")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirmDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

    if (showWarningDialog) {
        AlertDialog(
            onDismissRequest = { showWarningDialog = false },
            title = { Text("Peringatan") },
            text = { Text("Semua data harus diisi terlebih dahulu.") },
            confirmButton = {
                TextButton(onClick = { showWarningDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Konfirmasi") },
            text = { Text("Apakah data registrasi seminar akan dikirim?")
            },
            confirmButton = {
                TextButton(onClick = {
                    showConfirmDialog = false
                    onSubmit(nama, nim, prodi, email, semester, telepon)
                }) {
                    Text("Ya")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewsformRegistrasiScreen() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FormRegistrasiScreen(onSubmit = { _, _, _, _, _, _ -> })
        }
    }
}