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
class AlarmLemb : BroadcastReceiver() {
    var usuario = PessoaDAO.read()
    override fun onReceive(ctx: Context?, intent: Intent?) {
        val builder = NotificationCompat.Builder(ctx!!, "MainActivity")
                .setSmallIcon(R.drawable.notify_panel_notification_icon_bg)
                .setContentTitle("Olá ${usuario[usuario.size -1].nome}! Saudades!")
                .setContentText("Já fazem 3 dias desde a ultima vez que você informou sua massa!")
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

}