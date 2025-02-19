package com.example.uasa1.ui.view.Tugas

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uasa1.ui.costumwidget.CostumeTopAppBar
import com.example.uasa1.ui.navigation.DestinasiNavigasi
import com.example.uasa1.ui.viewmodel.PenyediaViewModel
import com.example.uasa1.ui.viewmodel.UpdateTugasViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateTugas : DestinasiNavigasi {
    override val route = "Update_Tugas"
    override val titleRes = "Update Tugas"
    const val ID_TUGAS = "id_tugas"
    val routeWithArgs = "$route/{$ID_TUGAS}"
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTugasScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateTugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateTugas.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        TugasEntryBody(
            modifier = Modifier.padding(padding),
            tugasUiState = viewModel.tugasUpdateUiState,
            onTugasValueChange = viewModel::updateInsertTugasState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateTugas()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}
