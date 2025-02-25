package com.alphazetakapp.stjosephappofficial.presentation.preparation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.presentation.common.ResponsiveLayout
import com.alphazetakapp.stjosephappofficial.presentation.navigation.Screen
import com.alphazetakapp.stjosephappofficial.ui.BannerApp
import com.google.android.gms.ads.MobileAds

@Composable
fun PrepScreen(
    navController: NavController,
    viewModel: PrepViewModel = hiltViewModel()
) {
    val backColor = colorResource(id = R.color.backgroundColorApp)
    val prepText = stringResource(id = R.string.preparation_screen_text)
    val title = stringResource(id = R.string.title_preparation_screen)

    ResponsiveLayout(
        content = {
            // Diseño para móviles
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backColor),
                containerColor = backColor
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    // Banner en la parte superior
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        BannerApp()
                    }

                    // Contenido principal
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Header
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Card con el contenido principal
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = prepText,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        lineHeight = 24.sp
                                    ),
                                    color = Color.White,
                                    modifier = Modifier
                                        .verticalScroll(rememberScrollState())
                                        .padding(bottom = 16.dp)
                                )
                            }
                        }

                        // Botones de navegación
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Botón Volver
                            Button(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(28.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.ArrowBack,
                                        contentDescription = "Volver"
                                    )
                                    Text(
                                        text = "Volver",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                            }

                            // Botón Continuar
                            Button(
                                onClick = { navController.navigate(Screen.Days.route) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFF2A71B),
                                    contentColor = Color.Black
                                ),
                                shape = RoundedCornerShape(28.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Continuar",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Icon(
                                        imageVector = Icons.Outlined.ArrowForward,
                                        contentDescription = "Continuar"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        tabletContent = {
            // Diseño para tablets
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backColor),
                containerColor = backColor
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    // Banner en la parte superior
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                    ) {
                        BannerApp()
                    }

                    // Contenido principal
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        horizontalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        // Panel izquierdo con título e imagen
                        Column(
                            modifier = Modifier
                                .weight(0.4f)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                ),
                                color = Color.White
                            )
                            
                            Spacer(modifier = Modifier.height(32.dp))
                            
                            Image(
                                painter = painterResource(id = R.drawable.loguitook3),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape)
                            )
                        }

                        // Panel derecho con contenido y botones
                        Column(
                            modifier = Modifier
                                .weight(0.6f)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            // Card con el contenido
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f)
                                ),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(24.dp)
                                ) {
                                    Text(
                                        text = prepText,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            lineHeight = 28.sp
                                        ),
                                        color = Color.White,
                                        modifier = Modifier.verticalScroll(rememberScrollState())
                                    )
                                }
                            }

                            // Botones de navegación
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(24.dp)
                            ) {
                                Button(
                                    onClick = { navController.popBackStack() },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(64.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                                        contentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(32.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.ArrowBack,
                                            contentDescription = "Volver",
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Text(
                                            text = "Volver",
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                    }
                                }

                                Button(
                                    onClick = { navController.navigate(Screen.Days.route) },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(64.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFF2A71B),
                                        contentColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(32.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Continuar",
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                        Icon(
                                            imageVector = Icons.Outlined.ArrowForward,
                                            contentDescription = "Continuar",
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}