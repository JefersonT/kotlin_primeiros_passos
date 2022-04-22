package com.example.jefersontorres.projetofinal

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat

/**
 * Created by Jeferson Torres on 09/11/2017.
 */
class AlarmReceicer: BroadcastReceiver() {
    var usuario = PessoaDAO.read()
    var medidas = MedidasDAO.read()[MedidasDAO.read().size - 1]
    var verificaimc = IMC(medidas.imc)
    override fun onReceive(ctx: Context?, intent: Intent?) {
        val builder = NotificationCompat.Builder(ctx!!, "MainActivity")
                .setSmallIcon(R.drawable.notify_panel_notification_icon_bg)
                .setContentTitle("Olá ${usuario[usuario.size -1].nome}! Veja como esta seu IMC!")
                .setContentText("Você esta ${verificaimc}.")
                .setTimeout(2000)// Devido a versão do NotificationCompat utilizado pela versão do meu android studio a função .setTimeoutAfter() é diferente

        //number++

        val resultIntent = Intent(ctx, MainActivity::class.java)
        val resultPendingIntent = PendingIntent.getActivity(
                ctx,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        builder.setContentIntent(resultPendingIntent)

        val notifyId = 1;
        val notifyMgr = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyMgr.notify(notifyId, builder.build())
    }

    fun IMC(imc : Float) : String{
        if (imc < 17){
            return "Muito abaixo do peso"
        }
        if ((imc >= 17) && (imc < 18.5)){
            return "Abaixo do peso"
        }
        if ((imc >= 18.5) && (imc < 25)){
            return "com Peso normal"
        }
        if ((imc >= 25) && (imc < 30)){
            return "Acima do peso"
        }
        if ((imc >= 30) && (imc < 35)){
            return "com Obesidade I"
        }
        if ((imc >= 35) && (imc < 40)){
            return "com Obesidade II (severa)"
        }
        if (imc >= 40){
            return "com Obesidade III (mórbida)"
        }
        return "Erro! IMC INVÁLIDO!"
    }
}