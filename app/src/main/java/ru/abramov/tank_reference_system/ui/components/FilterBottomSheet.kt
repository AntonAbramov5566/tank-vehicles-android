package ru.abramov.tank_reference_system.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.abramov.tank_reference_system.R
import ru.abramov.tank_reference_system.viewmoodel.TankViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    onDismiss: () -> Unit,
    onApply: (caliber: Long?, classId: Long?) -> Unit,
    viewModel: TankViewModel
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val classes by viewModel.getAllClasses().collectAsStateWithLifecycle(emptyList())

    var selectedCaliber by remember { mutableStateOf<Int?>(null) }
    var selectedClassId by remember { mutableStateOf<Int?>(null) }
    var selectedYear by remember { mutableStateOf<Int?>(null) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Text(stringResource(R.string.filter), style = MaterialTheme.typography.headlineSmall) }

            item { Text(stringResource(R.string.caliber)) }
            item {
                Slider(
                    value = (selectedCaliber ?: 76).toFloat(),
                    onValueChange = { selectedCaliber = it.toInt() },
                    valueRange = 76f..152f,
                    steps = 76
                )
            }
            item { Text(text = "${selectedCaliber ?: 76}") }

            item { Text(stringResource(R.string.tank_class)) }
            items(classes) { cls ->
                FilterChip(
                    selected = selectedClassId == cls.id,
                    onClick = { selectedClassId = if (selectedClassId == cls.id) null else cls.id },
                    label = { Text(cls.name) },
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            item { Text(stringResource(R.string.start_year)) }
            item {
                Slider(
                    value = (selectedYear ?: 1940).toFloat(),
                    onValueChange = { selectedYear = it.toInt() },
                    valueRange = 1940f..2025f,
                    steps = 85
                )
            }
            item { Text(text = "${selectedYear ?: 1940}") }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = {
                        onApply(selectedCaliber?.toLong(), selectedClassId?.toLong())
                        onDismiss()
                    }) { Text(stringResource(R.string.apply)) }
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}