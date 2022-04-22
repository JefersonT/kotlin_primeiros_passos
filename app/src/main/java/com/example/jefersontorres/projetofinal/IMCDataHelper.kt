package com.example.jefersontorres.projetofinal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Jeferson Torres on 04/11/2017.
 */
class IMCDataHelper(context: Context) : SQLiteOpenHelper(context, "imcbase", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
 		createPessoa(db)
 		createMedidas(db)
    }

    private fun createPessoa(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Pessoa(_id INTEGER PRIMARY KEY,nome TEXT, datanasc TEXT);")
    }

    private fun createMedidas(db: SQLiteDatabase?) {
    	db?.execSQL("CREATE TABLE Medidas(_id INTEGER PRIMARY KEY, altura DOUBLE DEFAULT 0.0, massa DOUBLE DEFAULT 0.0, imc DOUBLE DEFAULT 0.0, datam TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, versaoPrevisao: Int, novaVersao: Int) {
    }

}

object IMCDataHelperSingleton {
    lateinit var helper: IMCDataHelper
}