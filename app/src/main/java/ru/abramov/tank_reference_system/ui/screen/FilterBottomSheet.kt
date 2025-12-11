package ru.abramov.tank_reference_system.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import ru.abramov.tank_reference_system.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    onDismiss: () -> Unit,
    onApply: (String?, Int?) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selectedClass by remember { mutableStateOf<String?>(null) }
    var selectedYear by remember { mutableStateOf<Int?>(null) }
    val OBT = stringResource(id = R.string.OBT)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(stringResource(id = R.string.filter), style = MaterialTheme.typography.headlineSmall)

            Spacer(Modifier.height(12.dp))

            Text(stringResource(id = R.string.tank_class))
            FilterChip(
                selected = selectedClass == OBT,
                onClick = { selectedClass = if (selectedClass == OBT) null else OBT },
                label = { Text(OBT) }
            )

            Spacer(Modifier.height(12.dp))

            Text(stringResource(id = R.string.start_year))
            Slider(
                value = (selectedYear ?: 1940).toFloat(),
                onValueChange = { selectedYear = it.toInt() },
                valueRange = 1940f..2025f,
                steps = 85
            )
            Text(text = "${selectedYear ?: 1940}")

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss) { Text(stringResource(id = R.string.cancel)) }
                Spacer(Modifier.width(8.dp))
                Button(onClick = {
                    onApply(selectedClass, selectedYear)
                    onDismiss()
                }) { Text(stringResource(id = R.string.apply)) }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}