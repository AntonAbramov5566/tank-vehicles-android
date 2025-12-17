package ru.abramov.tank_reference_system.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.abramov.tank_reference_system.R
import ru.abramov.tank_reference_system.data.db.AppDatabase
import ru.abramov.tank_reference_system.data.repository.TankRepository
import ru.abramov.tank_reference_system.ui.components.FilterBottomSheet
import ru.abramov.tank_reference_system.ui.components.TankCard
import ru.abramov.tank_reference_system.ui.theme.MyApplicationTheme
import ru.abramov.tank_reference_system.ui.theme.MyBlack
import ru.abramov.tank_reference_system.ui.theme.MyGreen1
import ru.abramov.tank_reference_system.ui.theme.MyGreen2
import ru.abramov.tank_reference_system.ui.theme.MyLightGray
import ru.abramov.tank_reference_system.viewmoodel.TankViewModel
import ru.abramov.tank_reference_system.viewmoodel.TankViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                val loginResult = remember { mutableStateOf<Pair<String, String>?>(null) }

                val viewModel: TankViewModel = viewModel(
                    factory = TankViewModelFactory(
                        TankRepository(AppDatabase.getDatabase(LocalContext.current))
                    )
                )

                loginResult.value?.let { (user, pass) ->
                    LaunchedEffect(user, pass) {
                        viewModel.login(user, pass)
                        loginResult.value = null
                    }
                }

                val onLogin: (String, String) -> Unit = { user, pass ->
                    loginResult.value = user to pass
                    navController.navigate("login") {
                        launchSingleTop = true
                        navController.popBackStack()
                    }
                }

                NavHost(navController = navController, startDestination = "catalog") {
                    composable("catalog") {
                        CatalogScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                    composable("login") {
                        LoginScreen(
                            onLogin = onLogin,
                            navController = navController
                        )
                    }
                    composable(
                        "detail/{tankId}",
                        arguments = listOf(navArgument("tankId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val tankId = backStackEntry.arguments?.getLong("tankId") ?: 0L
                        DetailScreen(
                            tankId = tankId,
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    navController: NavController,
    viewModel: TankViewModel,
) {
    var searchQuery by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    val isFilterEnabled by viewModel.isFilterEnabled.collectAsStateWithLifecycle()
    val filteredTanks by viewModel.filteredTanks.collectAsStateWithLifecycle(emptyList())

    if (showBottomSheet) {
        FilterBottomSheet(
            onDismiss = {   showBottomSheet = false },
            onApply = { caliber, classId ->
                viewModel.setFilterClass(classId)
                viewModel.setFilterCaliber(caliber?.toInt())
            },
            viewModel = viewModel
        )
    }

    LaunchedEffect(Unit) {
        viewModel.loadTanks()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text =stringResource(id = R.string.app_name), fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MyBlack) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MyGreen1
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    //viewModel.searchTanks(it)
                    viewModel.setSearchQuery(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MyLightGray, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text(
                        modifier = Modifier.height(36.dp),
                        text = stringResource(id = R.string.search),
                        color = Color.Gray,
                        fontSize = 22.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_search_24),
                        contentDescription = stringResource(id = R.string.search),
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {}
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        Icon(
                            painter = painterResource(id = R.drawable.close_24),
                            contentDescription = stringResource(id = R.string.search),
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    searchQuery = ""
                                    //viewModel.searchTanks(searchQuery)
                                    viewModel.setSearchQuery(searchQuery)
                                }
                        )
                    }
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MyLightGray,
                    unfocusedContainerColor = MyLightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = MyGreen1,
                    focusedLeadingIconColor = Color.Gray,
                    unfocusedLeadingIconColor = Color.Gray,
                    focusedTrailingIconColor = Color.Gray,
                    unfocusedTrailingIconColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            FilterAndProfileRow(
                onFiltersClick = { showBottomSheet = true },
                onProfileClick = { navController.navigate("login") },
                isFilterEnabled = isFilterEnabled
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(filteredTanks) { tank ->
                    val photos by viewModel.getPhotos(tank.id).collectAsStateWithLifecycle(emptyList())
                    TankCard(tank = tank, photos = photos, navController = navController)
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}
@Composable
fun FilterAndProfileRow(
    onFiltersClick: () -> Unit,
    onProfileClick: () -> Unit,
    isFilterEnabled: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isFilterEnabled) {
            FilterChip(
                selected = false,
                modifier = Modifier.height(36.dp),
                onClick = onFiltersClick,
                label = { Text(stringResource(id = R.string.filter)) },
                colors = SelectableChipColors(
                    containerColor = MyGreen2,
                    labelColor = MyBlack,
                    selectedContainerColor = MyGreen2,
                    disabledSelectedContainerColor = MyGreen2,
                    selectedLabelColor = MyBlack,
                    selectedLeadingIconColor = MyGreen2,
                    selectedTrailingIconColor = MyGreen2,
                    disabledContainerColor = MyGreen2,
                    disabledLabelColor = MyBlack,
                    disabledLeadingIconColor = MyGreen2,
                    disabledTrailingIconColor = MyGreen2,
                    leadingIconColor = MyGreen2,
                    trailingIconColor = MyGreen2
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        FilledTonalButton(
            onClick = onProfileClick,
            modifier = Modifier.height(36.dp),
            colors = ButtonColors(
                MyGreen2,
                contentColor = MyBlack,
                disabledContainerColor = MyGreen2,
                disabledContentColor = MyGreen2
            )
        ) {
            Icon(painterResource(id = R.drawable.account_24), contentDescription = null)
            Spacer(Modifier.width(4.dp))
            Text(stringResource(id = R.string.profile))
        }
    }
}