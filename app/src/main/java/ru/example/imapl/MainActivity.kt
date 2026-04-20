package ru.example.imapl

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.example.imapl.ui.screens.home.HomeScreen
import ru.example.imapl.ui.screens.home.HomeViewModel
import ru.example.imapl.ui.theme.ImApLTheme

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImApLTheme {

                val viewModel = hiltViewModel<HomeViewModel>()
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                ) { paddingValue ->
                    HomeScreen(
                        modifier = Modifier.padding(paddingValues = paddingValue),
                        scrollBehavior = scrollBehavior,
                        images = viewModel.images,
                        onImageClick = {},
                        onSettingsClick = {},
                        onFabClick = {},
                    )
                }

            }
        }
    }
}
