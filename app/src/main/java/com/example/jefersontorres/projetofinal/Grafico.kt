package com.example.jefersontorres.projetofinal

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

/**
 * Created by marce on 08/11/2017.
 */
data class MeusDados(val x:Float, var y:Float)

class Grafico : Activity() {

    lateinit var lineChart:LineChart
    lateinit var medida : Medidas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_grafico_linha)

        lineChart = findViewById<LineChart>(R.id.simpleLineChart)

        val tmpmedida = MedidasDAO.read()
        val meusdados = arrayListOf<MeusDados>()

        var i = 0.0f

        for(medida in tmpmedida) {
            meusdados.add(MeusDados(i, medida.imc))
            i++
        }

        val entries = ArrayList<Entry>()

        for (dado in meusdados) {
            entries.add(Entry(dado.x, dado.y))
        }

        val dataSet = LineDataSet(entries, "Evolução IMC")
        dataSet.setColor(Color.RED)
        dataSet.valueTextColor = Color.RED

        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()
    }
}