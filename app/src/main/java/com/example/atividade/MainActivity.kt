package com.example.atividade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControleJogadores()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControleJogadores() {

    data class PlayerData(var nome: String, var level: Int = 0, var equipamento: Int = 0, var modificadores: Int = 0) {
        val poderTotal: Int
            get() = level + equipamento + modificadores
    }

    val jogadores = remember { mutableStateListOf(
        PlayerData(""),
        PlayerData(""),
        PlayerData(""),
        PlayerData(""),
        PlayerData(""),
        PlayerData("")
    ) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp)
    ) {
        items(jogadores.size) { index ->
            var nomeJogador by remember { mutableStateOf(jogadores[index].nome) }
            var levelJogador by remember { mutableStateOf(jogadores[index].level) }
            var equipamento by remember { mutableStateOf(jogadores[index].equipamento) }
            var modificadores by remember { mutableStateOf(jogadores[index].modificadores) }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = nomeJogador,
                    onValueChange = {
                        nomeJogador = it
                        jogadores[index] = jogadores[index].copy(nome = it)
                    },
                    label = { Text("Nome do Jogador") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Nível: $levelJogador")
                    Row(horizontalArrangement = Arrangement.Center) {
                        Button(onClick = { if (levelJogador > 0) {
                            levelJogador--
                            jogadores[index] = jogadores[index].copy(level = levelJogador)
                        }}) {
                            Text("-")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { if (levelJogador < 10) {
                            levelJogador++
                            jogadores[index] = jogadores[index].copy(level = levelJogador)
                        }}) {
                            Text("+")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Equipamento: $equipamento")
                    Row(horizontalArrangement = Arrangement.Center) {
                        Button(onClick = { if (equipamento > 0) {
                            equipamento--
                            jogadores[index] = jogadores[index].copy(equipamento = equipamento)
                        }}) {
                            Text("-")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { if (equipamento < 40) {
                            equipamento++
                            jogadores[index] = jogadores[index].copy(equipamento = equipamento)
                        }}) {
                            Text("+")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Modificadores: $modificadores")
                    Row(horizontalArrangement = Arrangement.Center) {
                        Button(onClick = { if (modificadores > -5) {
                            modificadores--
                            jogadores[index] = jogadores[index].copy(modificadores = modificadores)
                        }}) {
                            Text("-")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { if (modificadores < 10) {
                            modificadores++
                            jogadores[index] = jogadores[index].copy(modificadores = modificadores)
                        }}) {
                            Text("+")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Poder Total: ${jogadores[index].poderTotal}")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Preview() {
    ControleJogadores()
}
