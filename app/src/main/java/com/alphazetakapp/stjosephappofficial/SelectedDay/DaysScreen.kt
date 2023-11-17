package com.alphazetakapp.stjosephappofficial.SelectedDay

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.datastore.StoreEndDay
import com.alphazetakapp.stjosephappofficial.model.MeditationCardDay
import com.alphazetakapp.stjosephappofficial.navigation.Routes
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun DaysScreen(navController: NavController) {
    val context = LocalContext.current
    val dataStore = StoreEndDay(context)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC2932A))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val backColor = colorResource(id = R.color.backgroundColorApp)
        val selectedDayNum = remember { mutableStateOf(0) }
        var isSelected by remember { mutableStateOf(false) }

        LazyColumn {
            items(getMeditationDays()) { meditationday ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedDayNum.value = meditationday.dayNum
                            navController.navigate(
                                Routes.MeditationScreen.createRoute(
                                    dayNum = meditationday.dayNum,
                                    meditationday.day,
                                    dailyRecord = meditationday.dailyRecord
                                )
                            )
                        },
                    border = BorderStroke(2.dp, Color.Gray),
                    colors = CardDefaults.cardColors(if (isSelected) Color.Gray else backColor)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(backColor),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = meditationday.photo),
                            contentDescription = "Image",
                            modifier = Modifier
                                .size(80.dp)
                                .padding(start = 8.dp),
                            alignment = Alignment.CenterStart
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = meditationday.day,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Cursive,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = meditationday.meditationDay,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 18.dp)
                                    .padding(bottom = 8.dp),
                                color = Color.White
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

fun getMeditationDays(): List<MeditationCardDay> {
    return listOf(
        MeditationCardDay(
            1,
            "Dia 1",
            "APROVECHA ESTE DÍA PARA QUE ENTREGUES A JESÚS TUS CARGAS, TUS PROBLEMAS Y TUS ENFERMEDADES",
            R.drawable.day1, R.raw.listenmed1
        ),
        MeditationCardDay(
            2,
            "Dia 2",
            "EN ESTE DÍA ENTRÉGAME TUS AFLICCIONES, QUIERO SER CONSUELO EN TUS PENAS",
            R.drawable.day2, R.raw.listenmed2
        ),
        MeditationCardDay(
            3,
            "Dia 3",
            "EN ESTE DÍA QUIERO DECIRTE QUE EN MI CARPINTERÍA SIEMPRE ENCONTRARÁS LA PAZ",
            R.drawable.day3, R.raw.listenmed3
        ),
        MeditationCardDay(
            4,
            "Dia 4",
            "EN ESTE DÍA ENTRÉGAME TUS AFLICCIONES, ALGO HARE POR TI",
            R.drawable.day4, R.raw.listenmed4
        ),
        MeditationCardDay(
            5,
            "Dia 5",
            "EN ESTE DÍA ENTRÉGAME TUS DIFICULTADES",
            R.drawable.day5,
            R.raw.listenmed5
        ),
        MeditationCardDay(
            6,
            "Dia 6",
            "EN ESTE DÍA ENTRÉGAME TODAS LAS SITUACIONES QUE TE INTRAQUILIZAN",
            R.drawable.day6, R.raw.listenmed6
        ),
        MeditationCardDay(
            7,
            "Dia 7",
            "EN ESTE DÍA ENTRÉGAME DE NUEVO LAS INTENCIONES DE TU CORAZÓN",
            R.drawable.day7, R.raw.listenmed7
        ),
        MeditationCardDay(
            8,
            "Dia 8",
            "ESPERO QUE EN ESTE DÍA SIENTAS TU CARGA MÁS LIVIANA",
            R.drawable.day8, R.raw.listenmed8
        ),
        MeditationCardDay(
            9,
            "Dia 9",
            "EN ESTE DÍA HAS VENIDO A MI TALLER CON LA ILUSIÓN DE ENCONTRAR UNA SALIDA RÁPIDA A TUS PROBLEMAS",
            R.drawable.day9, R.raw.listenmed9
        ),
        MeditationCardDay(
            10,
            "Dia 10",
            "EN ESTE DÍA QUIERO QUE MIS PALABRAS TE SIRVAN COMO ANTÍDOTO A TODOS TUS MALES",
            R.drawable.day10, R.raw.listenmed10
        ),
        MeditationCardDay(
            11,
            "Dia 11",
            "CADA VEZ QUE VENGAS A MI CARPINTERÍA TE HARÉ VIVIR EXPERIENCIAS MARAVILLOSAS",
            R.drawable.day11, R.raw.listenmed11
        ),
        MeditationCardDay(
            12,
            "Dia 12",
            "HOY TE LLAMO A PONER TU CONFIANZA SOLO EN DIOS",
            R.drawable.day12, R.raw.listenmed12
        ),
        MeditationCardDay(
            13,
            "Dia 13",
            "NO DUDES EN VENIR A MI HUMILDE CARPINTERÍA",
            R.drawable.day13, R.raw.listenmed13
        ),
        MeditationCardDay(
            14,
            "Dia 14",
            "¿QUE FAVOR ESPERAS A TRAVÉS DE MI INTERCESIÓN",
            R.drawable.day14, R.raw.listenmed14
        ),
        MeditationCardDay(
            15,
            "Dia 15",
            "ESPERO QUE CADA DÍA SIENTAS MÁS LA NECESIDAD DE ENCONTRARTE CONMIGO EN MI CARPINTERÍA",
            R.drawable.day15, R.raw.listenmed15
        ),
        MeditationCardDay(
            16,
            "Dia 16",
            "EN ESTE DÍA PROPONTE VENCERTE A TI MISMO",
            R.drawable.day16, R.raw.listenmed16
        ),
        MeditationCardDay(
            17,
            "Dia 17",
            "AQUÍ EN MI CARPINTERÍA, TOMAD LA DECISIÓN DE VIVIR EN DIOS Y PARA DIOS",
            R.drawable.day17, R.raw.listenmed17
        ),
        MeditationCardDay(
            18,
            "Dia 18",
            "EN ESTE DÍA TOMO EN MIS MANOS LAS INTENCIONES DE TU ORACIÓN",
            R.drawable.day18, R.raw.listenmed18
        ),
        MeditationCardDay(
            19,
            "Dia 19",
            "HOY CAMINA EN LA CERTEZA DE QUE TU ORACIÓN HA SIDO ESCUCHADA POR EL SEÑOR",
            R.drawable.day19, R.raw.listenmed19
        ),
        MeditationCardDay(
            20,
            "Dia 20",
            "MI TALLER DEBE SER PARA TI LUGAR DE CONSOLACIÓN",
            R.drawable.day20, R.raw.listenmed20
        ),
        MeditationCardDay(
            21,
            "Dia 21",
            "COMO PATRONO DE LA VIDA INTERIOR CUIDARÉ SIEMPRE DE TI",
            R.drawable.day21, R.raw.listenmed21
        ),
        MeditationCardDay(
            22,
            "Dia 22",
            "EN MI TALLER PODRÁS VER REALIZADOS GRANDES MILAGROS",
            R.drawable.day22, R.raw.listenmed22
        ),
        MeditationCardDay(
            23,
            "Dia 23",
            "SIEMPRE ASISTO A MIS DEVOTOS, NO LOS DEJO SOLOS EN SUS ANGUSTIAS",
            R.drawable.day23, R.raw.listenmed23
        ),
        MeditationCardDay(
            24,
            "Dia 24",
            "EN ESTE DÍA QUIERO DEJAR EN TI UN BELLO RECUERDO",
            R.drawable.day24, R.raw.listenmed24
        ),
        MeditationCardDay(
            25,
            "Dia 25",
            "¿QUÉ PUEDO HACER POR TI EN ESTE DÍA",
            R.drawable.day25,
            R.raw.listenmed25
        ),
        MeditationCardDay(
            26,
            "Dia 26",
            "NO TE DEJES PERTURBAR NI PIENSES MÁS EN LA DERROTA",
            R.drawable.day26, R.raw.listenmed26
        ),
        MeditationCardDay(
            27,
            "Dia 27",
            "HOY DIME QUE SIENTES LA NECESIDAD DE CAMBIAR EN TU VIDA",
            R.drawable.day27, R.raw.listenmed27
        ),
        MeditationCardDay(
            28,
            "Dia 28",
            "¿QUÉ GRACIA ESPECIAL ESPERAS OBTENER EN ESTA VISITA?",
            R.drawable.day28, R.raw.listenmed28
        ),
        MeditationCardDay(
            29,
            "Dia 29",
            "MI CARPINTERÍA ESTÁ ABIERTA LAS 24 HORAS DEL DIA",
            R.drawable.day29, R.raw.listenmed29
        ),
        MeditationCardDay(
            30,
            "Dia 30",
            "EN ESTA TREINTENA EL SOL SE MANTENDRÁ SOBRE TI",
            R.drawable.day30, R.raw.listenmed30
        ),
        MeditationCardDay(
            dayNum = 31,
            day = "Consagración",
            meditationDay = "FINALIZADOS LOS 30 DÍAS DE ORACIÓN, CONSÁGRATE A San José",
            R.drawable.day1, R.raw.listenmed1
        )
    )

}