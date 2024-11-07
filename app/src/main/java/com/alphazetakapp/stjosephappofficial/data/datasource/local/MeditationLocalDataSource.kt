package com.alphazetakapp.stjosephappofficial.data.datasource.local

import com.alphazetakapp.stjosephappofficial.R
import com.alphazetakapp.stjosephappofficial.data.model.MeditationDto
import javax.inject.Inject

class MeditationLocalDataSource @Inject constructor() {
    fun getMeditations(): List<MeditationDto> {
        return listOf(
            MeditationDto(
                dayNum = 1,
                day = "Dia 1",
                meditationDay = "APROVECHA ESTE DÍA PARA QUE ENTREGUES A JESÚS TUS CARGAS, TUS PROBLEMAS Y TUS ENFERMEDADES",
                imageResId = R.drawable.day1,
                audioResId = R.raw.listenmed1
            ),
            MeditationDto(
                dayNum = 2,
                day = "Dia 2",
                meditationDay = "EN ESTE DÍA ENTRÉGAME TUS AFLICCIONES, QUIERO SER CONSUELO EN TUS PENAS",
                imageResId = R.drawable.day2,
                audioResId = R.raw.listenmed2
            ),
            MeditationDto(
                dayNum = 3,
                day = "Dia 3",
                meditationDay = "EN ESTE DÍA QUIERO DECIRTE QUE EN MI CARPINTERÍA SIEMPRE ENCONTRARÁS LA PAZ",
                imageResId = R.drawable.day3,
                audioResId = R.raw.listenmed3
            ),
            MeditationDto(
                dayNum = 4,
                day = "Dia 4",
                meditationDay = "EN ESTE DÍA ENTRÉGAME TUS AFLICCIONES, ALGO HARE POR TI",
                imageResId = R.drawable.day4,
                audioResId = R.raw.listenmed4
            ),
            MeditationDto(
                dayNum = 5,
                day = "Dia 5",
                meditationDay = "EN ESTE DÍA ENTRÉGAME TUS DIFICULTADES",
                imageResId = R.drawable.day5,
                audioResId = R.raw.listenmed5
            ),
            MeditationDto(
                dayNum = 6,
                day = "Dia 6",
                meditationDay = "EN ESTE DÍA ENTRÉGAME TODAS LAS SITUACIONES QUE TE INTRAQUILIZAN",
                imageResId = R.drawable.day6,
                audioResId = R.raw.listenmed6
            ),
            MeditationDto(
                dayNum = 7,
                day = "Dia 7",
                meditationDay = "EN ESTE DÍA ENTRÉGAME DE NUEVO LAS INTENCIONES DE TU CORAZÓN",
                imageResId = R.drawable.day7,
                audioResId = R.raw.listenmed7
            ),
            MeditationDto(
                dayNum = 8,
                day = "Dia 8",
                meditationDay = "ESPERO QUE EN ESTE DÍA SIENTAS TU CARGA MÁS LIVIANA",
                imageResId = R.drawable.day8,
                audioResId = R.raw.listenmed8
            ),
            MeditationDto(
                dayNum = 9,
                day = "Dia 9",
                meditationDay = "EN ESTE DÍA HAS VENIDO A MI TALLER CON LA ILUSIÓN DE ENCONTRAR UNA SALIDA RÁPIDA A TUS PROBLEMAS",
                imageResId = R.drawable.day9,
                audioResId = R.raw.listenmed9
            ),
            MeditationDto(
                dayNum = 10,
                day = "Dia 10",
                meditationDay = "EN ESTE DÍA QUIERO QUE MIS PALABRAS TE SIRVAN COMO ANTÍDOTO A TODOS TUS MALES",
                imageResId = R.drawable.day10,
                audioResId = R.raw.listenmed10
            ),
            MeditationDto(
                dayNum = 11,
                day = "Dia 11",
                meditationDay = "CADA VEZ QUE VENGAS A MI CARPINTERÍA TE HARÉ VIVIR EXPERIENCIAS MARAVILLOSAS",
                imageResId = R.drawable.day11,
                audioResId = R.raw.listenmed11
            ),
            MeditationDto(
                dayNum = 12,
                day = "Dia 12",
                meditationDay = "HOY TE LLAMO A PONER TU CONFIANZA SOLO EN DIOS",
                imageResId = R.drawable.day12,
                audioResId = R.raw.listenmed12
            ),
            MeditationDto(
                dayNum = 13,
                day = "Dia 13",
                meditationDay = "NO DUDES EN VENIR A MI HUMILDE CARPINTERÍA",
                imageResId = R.drawable.day13,
                audioResId = R.raw.listenmed13
            ),
            MeditationDto(
                dayNum = 14,
                day = "Dia 14",
                meditationDay = "¿QUE FAVOR ESPERAS A TRAVÉS DE MI INTERCESIÓN?",
                imageResId = R.drawable.day14,
                audioResId = R.raw.listenmed14
            ),
            MeditationDto(
                dayNum = 15,
                day = "Dia 15",
                meditationDay = "ESPERO QUE CADA DÍA SIENTAS MÁS LA NECESIDAD DE ENCONTRARTE CONMIGO EN MI CARPINTERÍA",
                imageResId = R.drawable.day15,
                audioResId = R.raw.listenmed15
            ),
            MeditationDto(
                dayNum = 16,
                day = "Dia 16",
                meditationDay = "EN ESTE DÍA PROPONTE VENCERTE A TI MISMO",
                imageResId = R.drawable.day16,
                audioResId = R.raw.listenmed16
            ),
            MeditationDto(
                dayNum = 17,
                day = "Dia 17",
                meditationDay = "AQUÍ EN MI CARPINTERÍA, TOMAD LA DECISIÓN DE VIVIR EN DIOS Y PARA DIOS",
                imageResId = R.drawable.day17,
                audioResId = R.raw.listenmed17
            ),
            MeditationDto(
                dayNum = 18,
                day = "Dia 18",
                meditationDay = "EN ESTE DÍA TOMO EN MIS MANOS LAS INTENCIONES DE TU ORACIÓN",
                imageResId = R.drawable.day18,
                audioResId = R.raw.listenmed18
            ),
            MeditationDto(
                dayNum = 19,
                day = "Dia 19",
                meditationDay = "HOY CAMINA EN LA CERTEZA DE QUE TU ORACIÓN HA SIDO ESCUCHADA POR EL SEÑOR",
                imageResId = R.drawable.day19,
                audioResId = R.raw.listenmed19
            ),
            MeditationDto(
                dayNum = 20,
                day = "Dia 20",
                meditationDay = "MI TALLER DEBE SER PARA TI LUGAR DE CONSOLACIÓN",
                imageResId = R.drawable.day20,
                audioResId = R.raw.listenmed20
            ),
            MeditationDto(
                dayNum = 21,
                day = "Dia 21",
                meditationDay = "COMO PATRONO DE LA VIDA INTERIOR CUIDARÉ SIEMPRE DE TI",
                imageResId = R.drawable.day21,
                audioResId = R.raw.listenmed21
            ),
            MeditationDto(
                dayNum = 22,
                day = "Dia 22",
                meditationDay = "EN MI TALLER PODRÁS VER REALIZADOS GRANDES MILAGROS",
                imageResId = R.drawable.day22,
                audioResId = R.raw.listenmed22
            ),
            MeditationDto(
                dayNum = 23,
                day = "Dia 23",
                meditationDay = "SIEMPRE ASISTO A MIS DEVOTOS, NO LOS DEJO SOLOS EN SUS ANGUSTIAS",
                imageResId = R.drawable.day23,
                audioResId = R.raw.listenmed23
            ),
            MeditationDto(
                dayNum = 24,
                day = "Dia 24",
                meditationDay = "EN ESTE DÍA QUIERO DEJAR EN TI UN BELLO RECUERDO",
                imageResId = R.drawable.day24,
                audioResId = R.raw.listenmed24
            ),
            MeditationDto(
                dayNum = 25,
                day = "Dia 25",
                meditationDay = "¿QUÉ PUEDO HACER POR TI EN ESTE DÍA?",
                imageResId = R.drawable.day25,
                audioResId = R.raw.listenmed25
            ),
            MeditationDto(
                dayNum = 26,
                day = "Dia 26",
                meditationDay = "NO TE DEJES PERTURBAR NI PIENSES MÁS EN LA DERROTA",
                imageResId = R.drawable.day26,
                audioResId = R.raw.listenmed26
            ),
            MeditationDto(
                dayNum = 27,
                day = "Dia 27",
                meditationDay = "HOY DIME QUE SIENTES LA NECESIDAD DE CAMBIAR EN TU VIDA",
                imageResId = R.drawable.day27,
                audioResId = R.raw.listenmed27
            ),
            MeditationDto(
                dayNum = 28,
                day = "Dia 28",
                meditationDay = "¿QUÉ GRACIA ESPECIAL ESPERAS OBTENER EN ESTA VISITA?",
                imageResId = R.drawable.day28,
                audioResId = R.raw.listenmed28
            ),
            MeditationDto(
                dayNum = 29,
                day = "Dia 29",
                meditationDay = "MI CARPINTERÍA ESTÁ ABIERTA LAS 24 HORAS DEL DIA",
                imageResId = R.drawable.day29,
                audioResId = R.raw.listenmed29
            ),
            MeditationDto(
                dayNum = 30,
                day = "Dia 30",
                meditationDay = "EN ESTA TREINTENA EL SOL SE MANTENDRÁ SOBRE TI",
                imageResId = R.drawable.day30,
                audioResId = R.raw.listenmed30
            ),
            MeditationDto(
                dayNum = 31,
                day = "Consagración",
                meditationDay = "FINALIZADOS LOS 30 DÍAS DE ORACIÓN, CONSÁGRATE A SAN JOSÉ",
                imageResId = R.drawable.day1,
                audioResId = R.raw.listenmed1
            )
        )
    }
}