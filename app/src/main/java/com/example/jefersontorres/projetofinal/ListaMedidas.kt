package com.example.jefersontorres.projetofinal

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListView
import android.widget.Toast

/**
 * Created by Jeferson Torres on 04/11/2017.
 */

val DADOS_DE_PESSOA = "DADOS_DE_PESSOA"

val medidasSelecionadas = ArrayList<Int>()

class ListaMedidas: AppCompatActivity() {

    lateinit var imcList: ListView
    private lateinit var medidas: MutableList<Medidas>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrarimc)

        val tmpmedida = MedidasDAO.read()

        if (tmpmedida.size == 0){
            Toast.makeText(this, "Não há medidas cadastradas!", Toast.LENGTH_LONG).show()
        }

        //para a listagem
        medidas = MedidasDAO.read()
        imcList = findViewById<ListView>(R.id.imcList)
        medidas.sortBy { medida -> medida.IDD }
        imcList.adapter = ListaMedidaAdapter (this, medidas)


        // ação ao clicar nem um listem
        imcList.setOnItemClickListener{parent, view, id, position ->
            Toast.makeText(this,"Altura: ${medidas[position.toInt()].altura} \nMassa: ${medidas[position.toInt()].massa} \n" +
                    "IMC: ${medidas[position.toInt()].imc}", Toast.LENGTH_LONG).show()
        }
    }

    fun removerMedida(view: View) {
        medidasSelecionadas.sortBy { medidasSelecionadas -> medidasSelecionadas }
        var quant = medidasSelecionadas.size
        val adapter = imcList.adapter as ListaMedidaAdapter

        while (quant > 0){
            quant--
            val medida = adapter.getItem(medidasSelecionadas.get(quant))
            //pessoas.removeAt(quant)
            if (!MedidasDAO.remove(medida.IDD!!.toInt())){
                Toast.makeText(this,"Não existe medidas com id informado", Toast.LENGTH_SHORT).show()
            }
        }
        adapter.lista = MedidasDAO.read()
        medidasSelecionadas.clear()
        (imcList.adapter as ListaMedidaAdapter).notifyDataSetChanged()
    }
}