package com.example.myapplication;

import android.content.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class AlarmReceiver extends BroadcastReceiver {
    MediaPlayer mediaPlayer;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    // implement onReceive() method
    public void onReceive(Context context, Intent intent) {
        // Use vibrator first
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(4000);  // For example, vibrate for 4000 milliseconds
        }

        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        mediaPlayer = MediaPlayer.create(context, R.raw.alertsound);
        if (mediaPlayer != null) {
            mediaPlayer.start();
        } else {
            // Handle MediaPlayer initialization error
        }
        // Attempt to play the default alarm sound
//        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        if (alarmUri == null) {
//            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }
//
//        Log.d("AlarmReceiver", "Alarm URI: " + (alarmUri != null ? alarmUri.toString() : "null"));
//        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
//        if (ringtone != null) {
//            try {
//                ringtone.play();
//            } catch (Exception e) {
//                Log.e("AlarmReceiver", "Error playing ringtone", e);
//                playFallbackSound(context);
//            }
//        } else {
//            Log.e("AlarmReceiver", "Failed to load ringtone from URI");
//            playFallbackSound(context);
//        }
    }
//    protected void onDestroy() {
//        super.onDestroy();
//        // Release the MediaPlayer when the activity is destroyed
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }

    private void playFallbackSound(Context context) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alertsound);
        if (mediaPlayer != null) {
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.e("MediaPlayerError", "Playback error: " + what + ", " + extra);
                    mp.release();
                    return true;  // Error was handled
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            mediaPlayer.start();
        } else {
            Log.e("MediaPlayerError", "MediaPlayer initialization failed");
        }
    }
}
