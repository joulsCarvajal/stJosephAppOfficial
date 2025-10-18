package com.alphazetakapp.stjosephappofficial.data.model

enum class AudioFileMapping(val fileName: String) {
    // Audios fijos
    ROSARY("rosariosanjose.mp4"),
    LITANIES("letanias.mp4"),
    FINAL_PRAY("oracionfinal.mp4"),
    
    // Audios de meditación diaria (días 1-30)
    MEDITATION_DAY_1("listenmed1.mp4"),
    MEDITATION_DAY_2("listenmed2.mp4"),
    MEDITATION_DAY_3("listenmed3.mp4"),
    MEDITATION_DAY_4("listenmed4.mp4"),
    MEDITATION_DAY_5("listenmed5.mp4"),
    MEDITATION_DAY_6("listenmed6.mp4"),
    MEDITATION_DAY_7("listenmed7.mp4"),
    MEDITATION_DAY_8("listenmed8.mp4"),
    MEDITATION_DAY_9("listenmed9.mp4"),
    MEDITATION_DAY_10("listenmed10.mp4"),
    MEDITATION_DAY_11("listenmed11.mp4"),
    MEDITATION_DAY_12("listenmed12.mp4"),
    MEDITATION_DAY_13("listenmed13.mp4"),
    MEDITATION_DAY_14("listenmed14.mp4"),
    MEDITATION_DAY_15("listenmed15.mp4"),
    MEDITATION_DAY_16("listenmed16.mp4"),
    MEDITATION_DAY_17("listenmed17.mp4"),
    MEDITATION_DAY_18("listenmed18.mp4"),
    MEDITATION_DAY_19("listenmed19.mp4"),
    MEDITATION_DAY_20("listenmed20.mp4"),
    MEDITATION_DAY_21("listenmed21.mp4"),
    MEDITATION_DAY_22("listenmed22.mp4"),
    MEDITATION_DAY_23("listenmed23.mp4"),
    MEDITATION_DAY_24("listenmed24.mp4"),
    MEDITATION_DAY_25("listenmed25.mp4"),
    MEDITATION_DAY_26("listenmed26.mp4"),
    MEDITATION_DAY_27("listenmed27.mp4"),
    MEDITATION_DAY_28("listenmed28.mp4"),
    MEDITATION_DAY_29("listenmed29.mp4"),
    MEDITATION_DAY_30("listenmed30.mp4");
    
    companion object {
        /**
         * Obtiene el nombre del archivo de audio para un tipo específico
         */
        fun getFileName(audioType: com.alphazetakapp.stjosephappofficial.presentation.meditation.detail.AudioType, dayNum: Int? = null): String {
            return when (audioType) {
                com.alphazetakapp.stjosephappofficial.presentation.meditation.detail.AudioType.ROSARY -> ROSARY.fileName
                com.alphazetakapp.stjosephappofficial.presentation.meditation.detail.AudioType.LITANIES -> LITANIES.fileName
                com.alphazetakapp.stjosephappofficial.presentation.meditation.detail.AudioType.FINAL_PRAY -> FINAL_PRAY.fileName
                com.alphazetakapp.stjosephappofficial.presentation.meditation.detail.AudioType.DAILY_MEDITATION -> {
                    dayNum?.let { day ->
                        when (day) {
                            1 -> MEDITATION_DAY_1.fileName
                            2 -> MEDITATION_DAY_2.fileName
                            3 -> MEDITATION_DAY_3.fileName
                            4 -> MEDITATION_DAY_4.fileName
                            5 -> MEDITATION_DAY_5.fileName
                            6 -> MEDITATION_DAY_6.fileName
                            7 -> MEDITATION_DAY_7.fileName
                            8 -> MEDITATION_DAY_8.fileName
                            9 -> MEDITATION_DAY_9.fileName
                            10 -> MEDITATION_DAY_10.fileName
                            11 -> MEDITATION_DAY_11.fileName
                            12 -> MEDITATION_DAY_12.fileName
                            13 -> MEDITATION_DAY_13.fileName
                            14 -> MEDITATION_DAY_14.fileName
                            15 -> MEDITATION_DAY_15.fileName
                            16 -> MEDITATION_DAY_16.fileName
                            17 -> MEDITATION_DAY_17.fileName
                            18 -> MEDITATION_DAY_18.fileName
                            19 -> MEDITATION_DAY_19.fileName
                            20 -> MEDITATION_DAY_20.fileName
                            21 -> MEDITATION_DAY_21.fileName
                            22 -> MEDITATION_DAY_22.fileName
                            23 -> MEDITATION_DAY_23.fileName
                            24 -> MEDITATION_DAY_24.fileName
                            25 -> MEDITATION_DAY_25.fileName
                            26 -> MEDITATION_DAY_26.fileName
                            27 -> MEDITATION_DAY_27.fileName
                            28 -> MEDITATION_DAY_28.fileName
                            29 -> MEDITATION_DAY_29.fileName
                            30 -> MEDITATION_DAY_30.fileName
                            else -> MEDITATION_DAY_1.fileName // Fallback
                        }
                    } ?: MEDITATION_DAY_1.fileName
                }
            }
        }
    }
}
