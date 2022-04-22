package com.example.jefersontorres.projetofinal

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Jeferson Torres on 09/11/2017.
 */
val INPUT_MEDIDAS = "INPUT_MEDIDAS"

class CadMedidas : AppCompatActivity() {

    lateinit var medidas : Medidas
    lateinit var edAltura: EditText
    lateinit var edPeso: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cad_medidas)

        edAltura = findViewById<EditText>(R.id.altura)
        edPeso = findViewById<EditText>(R.id.massa)
        medidas = Medidas(.0f, .0f,"")

    }

    private fun validarCampos() : Boolean {
        if (edAltura.text.length == 0) {
            edAltura.setError("Campo necessário")

            Toast.makeText(this, "Deve fornecer um valor de altura", Toast.LENGTH_SHORT).show()
            return false
        }
        if (edPeso.text.length == 0) {
            edPeso.setError("Campo necessário")

            Toast.makeText(this, "Deve fornecer um valor de Massa", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun obterDadosDaView(): Boolean {
        if (!validarCampos()) {
            return false
        }
        val entradaAltura = edAltura.text.toString().toFloat()
        val entradaMassa = edPeso.text.toString().toFloat()

        medidas = Medidas(entradaAltura,entradaMassa)

        return true
    }

    fun calcularIMC(view: View?){
        if (obterDadosDaView()) {
            (findViewById<TextView>(R.id.resultadoIMC)).text = medidas.imc.toString()
        }
    }

    fun salvarER(view: View) {
        if (obterDadosDaView()) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(INPUT_MEDIDAS, medidas)
            startActivity(intent)
        }
    }
}
