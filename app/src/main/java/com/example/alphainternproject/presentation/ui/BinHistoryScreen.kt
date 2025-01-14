package com.example.alphainternproject.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alphainternproject.presentation.viewmodel.BinViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import android.util.Log

@Composable
fun BinHistoryScreen(viewModel: BinViewModel) {
    val state by viewModel.state.collectAsState()

    Log.d("BinHistoryScreen", "Отображение истории: ${state.binHistory}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("История запросов:", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.binHistory) { bin ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("BIN Length: ${bin.number.length ?: "N/A"}")
                        Text("Страна: ${bin.country.name ?: "N/A"}")
                        Text("Тип карты: ${bin.scheme ?: "N/A"}")
                    }
                }
            }
        }
    }
}
