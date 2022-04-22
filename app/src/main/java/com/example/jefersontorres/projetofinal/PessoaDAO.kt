package com.example.jefersontorres.projetofinal

import android.annotation.TargetApi
import android.content.ContentValues
import android.os.Build
import java.time.LocalDateTime
import java.util.*

/**
 * Created by Jeferson Torres on 04/11/2017.
 */
object PessoaDAO {
    fun create(pessoa: Pessoa): Boolean {
        val db = IMCDataHelperSingleton.helper.writableDatabase
        val content1 = ContentValues()

        content1.put("nome", pessoa.nome)
        content1.put("datanasc", pessoa.dtnasc)

        return db.insert("Pessoa", null, content1) > 0

    }

    fun read(): MutableList<Pessoa>{
        val db = IMCDataHelperSingleton.helper.readableDatabase
        val cursor = db.rawQuery("select * from Pessoa", null)
        val lista = mutableListOf<Pessoa>()
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("_id"))
            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            val data = cursor.getString(cursor.getColumnIndex("datanasc"))
            lista.add(Pessoa(nome, data, id))
        }
        return lista
    }

}