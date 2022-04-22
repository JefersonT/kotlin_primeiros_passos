package com.example.jefersontorres.projetofinal

import android.annotation.TargetApi
import android.os.Build
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@TargetApi(Build.VERSION_CODES.O)
/**
 * Created by Jeferson Torres on 06/11/2017.
 */
class Medidas (var altura: Float, var massa: Float, val data: String?=null, val IDD: Long?=null) : Serializable {

    //val data  = LocalDateTime.now().toString()

    val imc: Float
        get() {
            return massa/(altura * altura)
        }
}