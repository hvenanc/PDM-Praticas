package pdm.weatherapp

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pdm.weatherapp.ui.theme.WeatherAppTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterPage()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterPage(modifier: Modifier = Modifier) {
    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var confirmaSenha by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Crie sua Conta",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = nome,
            label = { Text(text = "Digite seu nome")},
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {nome = it}
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail")},
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {email = it}
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = senha,
            label = { Text(text = "Digite sua senha")},
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {senha = it},
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = confirmaSenha,
            label = { Text(text = "Confirme sua senha")},
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {confirmaSenha = it},
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.size(24.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            Toast.makeText(activity, "Conta Criada", Toast.LENGTH_LONG).show()
            activity?.startActivity(
                Intent(activity, MainActivity::class.java).setFlags(
                    FLAG_ACTIVITY_SINGLE_TOP
                )
            )
        },
            enabled = nome.isNotEmpty() && email.isNotEmpty() && senha == confirmaSenha
        ) {
            Text(text = "Criar Conta")
        }
        Spacer(modifier = Modifier.size(12.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                nome = ""; email = ""; senha = "" ; confirmaSenha = ""
            }
        ) {
            Text(text = "Limpar")
        }

    }
}