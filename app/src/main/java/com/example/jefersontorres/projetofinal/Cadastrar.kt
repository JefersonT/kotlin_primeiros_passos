package com.example.jefersontorres.projetofinal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Jeferson Torres on 04/11/2017.
 */

val INPUT_PESSOA = "INPUT_PESSOA"
val INPUT_MEDIDA = "INPUT_MEDIDA"

class Cadastrar : AppCompatActivity() {

    lateinit var pessoa : Pessoa
    lateinit var medidas : Medidas
    lateinit var edData : EditText
    lateinit var edAltura: EditText
    lateinit var edNome: EditText
    lateinit var edPeso: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_usuario)

        edNome = findViewById<EditText>(R.id.nome)
        edData = findViewById<EditText>(R.id.data)
        edAltura = findViewById<EditText>(R.id.altura)
        edPeso = findViewById<EditText>(R.id.massa)
        pessoa = Pessoa("","")
        medidas = Medidas(.0f, .0f,"")

    }

    private fun validarCampos() : Boolean {
        if (edAltura.text.length == 0) {
            edAltura.setError("Campo necessário")

            Toast.makeText(this, "Deve fornecer um valor de altura", Toast.LENGTH_SHORT).show()
            return false
        }
        if (edData.text.length == 0) {
            edData.setError("Campo necessário")

            Toast.makeText(this, "Deve fornecer sua data de nascimento", Toast.LENGTH_SHORT).show()
            return false
        }
        if (edPeso.text.length == 0) {
            edPeso.setError("Campo necessário")

            Toast.makeText(this, "Deve fornecer um valor de Massa", Toast.LENGTH_SHORT).show()
            return false
        }
        if (edNome.text.length == 0) {
            edNome.setError("Campo necessário")

            Toast.makeText(this, "Deve fornecer Seu Nome", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun obterDadosDaView(): Boolean {
        if (!validarCampos()) {
            return false
        }
        val entradaNome = edNome.text.toString()
        val entradaData = edData.text.toString()
        val entradaAltura = edAltura.text.toString().toFloat()
        val entradaMassa = edPeso.text.toString().toFloat()

        medidas = Medidas(entradaAltura,entradaMassa)
        pessoa = Pessoa(entradaNome, entradaData)

        return true
    }

    fun calcularIMC(view: View?){
        if (obterDadosDaView()) {
            (findViewById<TextView>(R.id.resultadoIMC)).text = medidas.imc.toString()
        }
    }

    fun salvarERetornar(view: View) {
        if (obterDadosDaView()) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(INPUT_PESSOA, pessoa)
            intent.putExtra(INPUT_MEDIDA, medidas)
            startActivity(intent)
        }
    }
}
