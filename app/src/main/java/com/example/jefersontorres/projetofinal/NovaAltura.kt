package com.example.jefersontorres.projetofinal

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Jeferson Torres on 08/11/2017.
 */
val NOVAALT = "NOVAALT"
class NovaAltura : AppCompatActivity(){

    lateinit var medida : Medidas
    lateinit var edAltura: EditText
    lateinit var edMassa: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nova_altura)

        edAltura = findViewById<EditText>(R.id.altura)
        edMassa = findViewById<TextView>(R.id.massa)
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
        edMassa.setText(medida.massa.toString())
        edAltura.text.append(medida.altura.toString())
    }

    private fun validarCampos() : Boolean {
        if (edAltura.text.length == 0) {
            edAltura.setError("Campo necessário")

            Toast.makeText(this, "Deve fornecer um valor de Altura", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun obterDadosDaView(): Boolean {
        if (!validarCampos()) {
            return false
        }
        val entradaAltura = edAltura.text.toString().toFloat()
        val entradaMassa = medida.massa

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