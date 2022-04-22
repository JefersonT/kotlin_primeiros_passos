package com.example.jefersontorres.projetofinal

import android.annotation.TargetApi
import android.content.ContentValues
import android.os.Build
import java.time.LocalDateTime
import java.util.*

/**
 * Created by Jeferson Torres on 06/11/2017.
 */
object MedidasDAO {
    @TargetApi(Build.VERSION_CODES.O)
    fun create(medidas: Medidas): Boolean {
        val db = IMCDataHelperSingleton.helper.writableDatabase
        val content = ContentValues()

        val calendar = Calendar.getInstance()
        calendar.time = Date()

        val d = calendar.get(Calendar.DAY_OF_MONTH)
        val m = calendar.get(Calendar.MONTH)
        val a = calendar.get(Calendar.YEAR)

        content.put("altura", medidas.altura)
        content.put("massa", medidas.massa)
        content.put("imc", medidas.imc)
        content.put("datam", "$d/$m/$a")
        return db.insert("Medidas", null, content) > 0
    }


    fun read(): MutableList<Medidas>{
        val db = IMCDataHelperSingleton.helper.readableDatabase
        val cursor = db.rawQuery("select * from Medidas", null)
        val lista = mutableListOf<Medidas>()
        while (cursor.moveToNext()) {
            val idd = cursor.getLong(cursor.getColumnIndex("_id"))
            val altura = cursor.getFloat(cursor.getColumnIndex("altura"))
            val massa = cursor.getFloat(cursor.getColumnIndex("massa"))
            val data = cursor.getString(cursor.getColumnIndex("datam"))
            lista.add(Medidas(altura, massa, data, idd))
        }
        return lista
    }

    fun remove(idd: Int): Boolean {
        val db = IMCDataHelperSingleton.helper.readableDatabase
        val rows = db.delete("Medidas", "_id=$idd", null)
        return rows > 0

    }
}