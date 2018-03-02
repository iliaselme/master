package com.tesseract.quiz20;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
//https://www.youtube.com/watch?v=p2ffzsCqrs8
public class MyService extends Service {
    private MediaPlayer song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        song = MediaPlayer.create(this,R.raw.vald);
        song.setLooping(true);
        song.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        song.stop();
    }
}
