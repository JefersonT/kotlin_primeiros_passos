package com.example.jefersontorres.projetofinal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Jeferson Torres on 07/11/2017.
 */

class MostrarMedida : AppCompatActivity(){

    lateinit var medida : Medidas
    lateinit var edImc : TextView
    lateinit var edAltura: TextView
    lateinit var edPeso: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostrar_medida)

        edImc = findViewById<TextView>(R.id.imc)
        edAltura = findViewById<TextView>(R.id.alt)
        edPeso = findViewById<TextView>(R.id.mas)
        medida = Medidas(.0f, .0f, "")


        val tmpmedida = MedidasDAO.read()

        if (tmpmedida.size > 0){
            medida = tmpmedida[tmpmedida.size-1]
            carregarDadosNasViews()
        } else {
            Toast.makeText(this, "Não há medidas cadastradas!", Toast.LENGTH_LONG).show()
        }
    }


    private fun carregarDadosNasViews() {
        edImc.setText(medida.imc.toString())
        edAltura.setText(medida.altura.toString())
        edPeso.setText(medida.massa.toString())
    }

}