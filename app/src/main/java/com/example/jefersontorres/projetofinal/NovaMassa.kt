package com.example.jefersontorres.projetofinal

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Jeferson Torres on 08/11/2017.
 */
val NOVAMA = "NOVAMA"
class NovaMassa : AppCompatActivity(){

    lateinit var medida : Medidas
    lateinit var edAltura: TextView
    lateinit var edMassa: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nova_massa)

        edAltura = findViewById<TextView>(R.id.altura)
        edMassa = findViewById<EditText>(R.id.massa)
        medida = Medidas(.0f, .0f, "")

        val tmpmedida = MedidasDAO.read()
        if (tmpmedida.size > 0){
            medida = tmpmedida[tmpmedida.size-1]
            carregarDadosNasViews()
        }else{
            Toast.makeText(this, "Não há medidas cadastradas!", Toast.LENGTH_LONG).show()
        }
    }

    private fun carregarDadosNasViews() {
        edAltura.setText(medida.altura.toString())
        edMassa.text.append(medida.massa.toString())
    }

    private fun validarCampos() : Boolean {
        if (edMassa.text.length == 0) {
            edMassa.setError("Campo necessário")

            Toast.makeText(this, "Deve fornecer um valor de Massa", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun obterDadosDaView(): Boolean {
        if (!validarCampos()) {
            return false
        }
        val entradaAltura = medida.altura
        val entradaMassa = edMassa.text.toString().toFloat()

            medida = Medidas(entradaAltura, entradaMassa)

        return true
    }

    fun calcularIMC(view: View?){
        if (obterDadosDaView()) {
            (findViewById<TextView>(R.id.resultadoIMC)).text = medida.imc.toString()
        }
    }

    fun salvar(view: View) {
        if (obterDadosDaView()) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(NOVAALT, medida)
            startActivity(intent)
        }
    }
}