package com.example.alphainternproject.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.alphainternproject.presentation.event.BinEvent
import com.example.alphainternproject.presentation.viewmodel.BinViewModel

@Composable
fun BinInfoScreen(viewModel: BinViewModel) {
    val state by viewModel.state.collectAsState()

    var binNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = binNumber,
            onValueChange = { binNumber = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text("Введите BIN") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                viewModel.onEvent(BinEvent.GetBin(binNumber))
            }),
            singleLine = true
        )

        Button(
            onClick = {
                viewModel.onEvent(BinEvent.GetBin(binNumber))
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Получить информацию")
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            state.binInfo?.let { binInfo ->
                Text("Страна: ${binInfo.country?.name ?: "N/A"}")
                Text("Координаты: ${binInfo.country?.latitude ?: "N/A"}, ${binInfo.country?.longitude ?: "N/A"}")
                Text("Тип карты: ${binInfo.scheme ?: "N/A"}")
                Text("Информация о банке: " + listOfNotNull(
                    binInfo.bank?.name,
                    binInfo.bank?.city,
                    binInfo.bank?.url,
                    binInfo.bank?.phone
                ).joinToString(separator = ", ") { it })
            }

            state.error?.let { error ->
                Text(
                    text = error
                )
            }
        }
    }
}

