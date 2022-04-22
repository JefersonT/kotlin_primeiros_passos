package com.example.jefersontorres.projetofinal

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

/**
 * Created by Jeferson Torres on 08/11/2017.
 */


class ListaMedidaAdapter(val actvity:Activity, var lista: MutableList<Medidas>) : BaseAdapter() {

    override fun getView(id: Int, viewReserva: View?, pai: ViewGroup?): View {
        val view = actvity.layoutInflater.inflate(R.layout.layout_lista_medidas, pai, false)
        val field1 = view.findViewById<TextView>(R.id.field1)
        val field2 = view.findViewById<TextView>(R.id.field2)
        val checkbox = view.findViewById<CheckBox>(R.id.checkBox)

        val medida = getItem(id)

        checkbox.setOnCheckedChangeListener{ view, checked ->
            if (checked) {
                medidasSelecionadas.add(id)
            } else {
                medidasSelecionadas.remove(id)
            }
            Toast.makeText(actvity, "Qtd items: ${medidasSelecionadas.size}",
                    Toast.LENGTH_SHORT).show()
        }

        field1.text = (id + 1).toString()
        field2.text = medida.data
        return view
    }

    override fun getItem(id: Int): Medidas {
        return lista[id]
    }

    override fun getItemId(id: Int): Long {
        return id.toLong()
    }

    override fun getCount(): Int {
        return lista.size
    }
}