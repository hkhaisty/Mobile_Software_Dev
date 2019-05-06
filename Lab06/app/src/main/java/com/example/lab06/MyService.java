package com.example.lab06;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.Random;

public class MyService extends Service {
    private int myRandomCharacter;
    private boolean isRandomGeneratorOn;

    private final int MIN = 65;
    private final int MAX = 90;

    private final String TAG = "Random Char Service: ";

    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class RandomCharacterServiceBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    private IBinder myBinder = new RandomCharacterServiceBinder();

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i(TAG, )
    }
}


