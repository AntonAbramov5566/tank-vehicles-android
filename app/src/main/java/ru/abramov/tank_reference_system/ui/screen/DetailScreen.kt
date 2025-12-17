package ru.abramov.tank_reference_system.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.abramov.tank_reference_system.R
import ru.abramov.tank_reference_system.data.db.AppDatabase
import ru.abramov.tank_reference_system.data.db.entity.*
import ru.abramov.tank_reference_system.data.repository.TankRepository
import ru.abramov.tank_reference_system.ui.theme.*
import ru.abramov.tank_reference_system.viewmoodel.TankViewModel
import ru.abramov.tank_reference_system.viewmoodel.TankViewModelFactory

@SuppressLint("LocalContextResourcesRead", "DiscouragedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    tankId: Long,
    navController: NavController,
    viewModel: TankViewModel = viewModel(
        factory = TankViewModelFactory(
            TankRepository(AppDatabase.getDatabase(LocalContext.current))
        )
    )
) {
    val tank by viewModel.getTankById(tankId).collectAsStateWithLifecycle(null)
    val specs by viewModel.getSpecs(tankId).collectAsStateWithLifecycle(null)
    val photos by viewModel.getPhotos(tankId).collectAsStateWithLifecycle(emptyList())
    val history by viewModel.getHistory(tankId).collectAsStateWithLifecycle(emptyList())
    val modifications by viewModel.getModifications(tankId).collectAsStateWithLifecycle(emptyList())

    LaunchedEffect(Unit) { viewModel.loadDetails(tankId) }

    tank?.let { t ->
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(t.official_name ?: t.name, color = MyBlack) },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MyGreen1),
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back_24),
                            contentDescription = stringResource(id = R.string.back),
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {navController.popBackStack()}
                        )
                    }
                )
            }
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                val primaryPhoto = photos.firstOrNull { it.is_primary }
                val imageName = primaryPhoto?.filename?.substringBeforeLast(".")?.lowercase()?.replace("-", "_") ?: "placeholder"
                val context = LocalContext.current
                val resourceId = remember(imageName) {
                    context.resources.getIdentifier(imageName, "drawable", context.packageName)
                }
                Image(
                    painter = painterResource(id = if (resourceId != 0) resourceId else android.R.drawable.ic_menu_report_image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )

                var tabIndex by remember { mutableStateOf(0) }
                val tabs = listOf("ТТХ", "История", "Модификации", "Фото")

                TabRow(
                    selectedTabIndex = tabIndex,
                    containerColor = MyGreen2,
                    contentColor = MyBlack
                ) {
                    tabs.forEachIndexed { i, title ->
                        Tab(
                            selected = tabIndex == i,
                            onClick = { tabIndex = i },
                            text = { Text(title) }
                        )
                    }
                }

                when (tabIndex) {
                    0 -> TTHTab(specs)
                    1 -> HistoryTab(history)
                    2 -> ModificationsTab(modifications)
                    3 -> PhotosTab(photos)
                }
            }
        }
    } ?: Box(Modifier.fillMaxSize()) { CircularProgressIndicator(Modifier.align(Alignment.Center)) }
}

@Composable
private fun TTHTab(specs: Specifications?) {
    Column(Modifier.padding(16.dp)) {
        Text("Масса: ${specs?.weight_kg} кг", color = MyBlack)
        Text("Длина: ${specs?.length_m} м", color = MyBlack)
        Text("Ширина: ${specs?.width_m} м", color = MyBlack)
        Text("Высота: ${specs?.height_m} м", color = MyBlack)
        Text("Калибр: ${specs?.main_gun_caliber_mm} мм", color = MyBlack)
        Text("Мощность: ${specs?.engine_power_hp} л.с.", color = MyBlack)
        Text("Скорость: ${specs?.max_speed_kmh} км/ч", color = MyBlack)
    }
}

@Composable
private fun HistoryTab(history: List<History>) {
    Column(Modifier.padding(16.dp)) {
        history.forEach {
            Text(it.development_history, color = MyBlack)
            Spacer(Modifier.height(16.dp))
            Text(it.production_history, color = MyBlack)
            Spacer(Modifier.height(16.dp))
            Text(it.combat_use, color = MyBlack)
            Spacer(Modifier.height(16.dp))
            Text(it.notable_features, color = MyBlack)
            Spacer(Modifier.height(8.dp))
            Text(it.interesting_facts, color = MyBlack)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ModificationsTab(modifications: List<Modifications>) {
    Column(Modifier.padding(16.dp)) {
        modifications.forEach {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MyLightGray)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(it.modification_name, fontWeight = FontWeight.Bold, color = MyBlack)
                    Text(it.description, color = MyBlack)
                    Text("Годы: ${it.production_years}", color = MyBlack)
                    Text(it.changes, color = MyBlack)
                }
            }
        }
    }
}

@SuppressLint("DiscouragedApi", "LocalContextResourcesRead")
@Composable
private fun PhotosTab(photos: List<Photos>) {
    Column(Modifier.padding(16.dp)) {
        photos.forEach { photo ->
            if(!photo.is_primary){
                val imageName = photo.filename.substringBeforeLast(".").lowercase().replace("-", "_")
                val context = LocalContext.current
                val resourceId = remember(imageName) {
                    context.resources.getIdentifier(imageName, "drawable", context.packageName)
                }
                Image(
                    painter = painterResource(id = if (resourceId != 0) resourceId else android.R.drawable.ic_menu_report_image),
                    contentDescription = photo.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}