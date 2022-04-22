package com.example.jefersontorres.projetofinal

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.webkit.WebView
import android.widget.Toast


class MainActivity : Activity() {

    private lateinit var pessoas: MutableList<Long>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra(INPUT_PESSOA)) {
            val novaP = intent.getSerializableExtra(INPUT_PESSOA)
            val novaM = intent.getSerializableExtra(INPUT_MEDIDA)
            salvar(novaP as Pessoa, novaM as Medidas)
        }
        if (intent.hasExtra(INPUT_MEDIDAS)) {
            val novaM = intent.getSerializableExtra(INPUT_MEDIDAS)
            salvarM(novaM as Medidas)
        }
        if (intent.hasExtra(NOVAMA)){
            val novaMa = intent.getSerializableExtra(NOVAMA)
            salvarM(novaMa as Medidas)
        }
        if (intent.hasExtra(NOVAALT)){
            val novaAl = intent.getSerializableExtra(NOVAALT)
            salvarM(novaAl as Medidas)
        }

        //Notificação
        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(this, AlarmReceicer::class.java)
        val pendingAlarmIntent = PendingIntent.getBroadcast(this,0, alarmIntent,0)
        //manager.set (AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+ 20*1000, pendingAlarmIntent)
        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+7*24*60*60*1000, 7*24*60*60*1000, pendingAlarmIntent)

    }

    private fun salvar(pessoaNova: Pessoa, medidaN: Medidas){
        PessoaDAO.create(pessoaNova)
        MedidasDAO.create(medidaN)
    }

    private fun salvarM(medidaN: Medidas){
        MedidasDAO.create(medidaN)
    }

    fun primeiroCad(view: View){
        val teste = PessoaDAO.read()
        val teste2 = MedidasDAO.read()
        if (teste.size > 0) {
            if (teste2.size > 0) {
                Toast.makeText(this, "O Usuário já possue cadastro!\n" +
                        "Nome: ${teste[teste.size - 1].nome} \n" +
                        "Data de Nascimento: ${teste[teste.size - 1].dtnasc}\n" +
                        "Altura: ${teste2[teste2.size - 1].altura}\n" +
                        "Massa: ${teste2[teste2.size - 1].massa}\n" +
                        "IMC: ${teste2[teste2.size - 1].imc}", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "ERRO! Não há medidas cadastrada!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, CadMedidas::class.java)
                startActivity(intent)
            }

        } else {
            val intent = Intent(this, Cadastrar::class.java)
            startActivity(intent)
        }
    }

    fun mostrarMedida(view: View){
        val tmpmedida = MedidasDAO.read()
        val tmppessoa = PessoaDAO.read()
        if (tmppessoa.size > 0){
            if (tmpmedida.size > 0) {
                var intent = Intent(this, MostrarMedida::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "ERRO! Não há medidas cadastrada!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, CadMedidas::class.java)
                startActivity(intent)
            }
        } else{
            Toast.makeText(this, "ERRO! Faça seu cadastro!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Cadastrar::class.java)
            startActivity(intent)
        }

    }
    fun novaMassa(view: View){

        val tmpmedida = MedidasDAO.read()
        val tmppessoa = PessoaDAO.read()
        if (tmppessoa.size > 0){
            if (tmpmedida.size > 0) {
                var intent = Intent(this, NovaMassa::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "ERRO! Não há medidas cadastrada!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, CadMedidas::class.java)
                startActivity(intent)
            }
        } else{
            Toast.makeText(this, "ERRO! Faça seu cadastro!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Cadastrar::class.java)
            startActivity(intent)
        }

    }
    fun novaAltura(view: View){
        val tmpmedida = MedidasDAO.read()
        val tmppessoa = PessoaDAO.read()
        if (tmppessoa.size > 0){
            if (tmpmedida.size > 0) {
                var intent = Intent(this, NovaAltura::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "ERRO! Não há medidas cadastrada!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, CadMedidas::class.java)
                startActivity(intent)
            }

        } else{
            Toast.makeText(this, "ERRO! Faça seu cadastro!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Cadastrar::class.java)
            startActivity(intent)
        }


    }
    fun listaMedidas(view: View){
        val tmpmedida = MedidasDAO.read()
        val tmppessoa = PessoaDAO.read()
        if (tmppessoa.size > 0){
            if (tmpmedida.size > 0) {
                var intent = Intent(this, ListaMedidas::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "ERRO! Não há medidas cadastrada!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, CadMedidas::class.java)
                startActivity(intent)
            }
        } else{
            Toast.makeText(this, "ERRO! Faça seu cadastro!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Cadastrar::class.java)
            startActivity(intent)
        }


    }
    fun analisarIMC(view: View) {
        val tmpmedida = MedidasDAO.read()
        val tmppessoa = PessoaDAO.read()
        if (tmppessoa.size > 0){
            if (tmpmedida.size > 0) {
                val intent = Intent(this, Grafico::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "ERRO! Não há medidas cadastrada!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, CadMedidas::class.java)
                startActivity(intent)
            }
        } else{
            Toast.makeText(this, "ERRO! Faça seu cadastro!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Cadastrar::class.java)
            startActivity(intent)
        }

    }
}
