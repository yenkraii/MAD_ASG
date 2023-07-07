package sg.edu.np.mad.productivibe;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.io.IOException;

// Singleton design so that the MediaPlayer is accessible across the whole app
public class MediaPlayerManager {
    private static MediaPlayerManager instance;
    private MediaPlayer mediaPlayer;

    private MediaPlayerManager() {
        // Initialize your MediaPlayer instance here
        mediaPlayer = new MediaPlayer();
    }

    public static synchronized MediaPlayerManager getInstance() {
        if (instance == null) {
            instance = new MediaPlayerManager();
        }
        return instance;
    }

    public void start() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void resume() {
        mediaPlayer.start();
    }

    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        //isMediaPlayerStarted = false;
    }

    public void setMusicSource(Context context, int rawResourceId) {
        String newMusicSource = context.getResources().getResourceName(rawResourceId);

//        // Check if the new music source is the same as the current one
//        String musicSource = null;
//        if (musicSource != null && musicSource.equals(newMusicSource)) {
//            return; // Skip setting the new music source
//        }
//        try {
//            AssetFileDescriptor fileDescriptor = context.getResources().openRawResourceFd(rawResourceId);
//            if (fileDescriptor != null) {
//                mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
//                mediaPlayer.prepare();
//                fileDescriptor.close();
//
//                // Update the current music source
//                musicSource = newMusicSource;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
        try {
            AssetFileDescriptor fileDescriptor = context.getResources().openRawResourceFd(rawResourceId);
            if (fileDescriptor != null) {
                mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                mediaPlayer.prepare();
                fileDescriptor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLooping(boolean isLooping) {
        mediaPlayer.setLooping(isLooping);
    }
}
