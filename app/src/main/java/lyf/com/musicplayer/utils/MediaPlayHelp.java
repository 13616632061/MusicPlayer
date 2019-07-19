package lyf.com.musicplayer.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;
import java.io.PipedReader;

/**
 * Created by Administrator on 2019/7/18.
 */

public class MediaPlayHelp {

    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private OnMediaPlayHelpPreparedListener mOnMediaPlayHelpPreparedListener;
    private String mPath;
    private static MediaPlayHelp mMediaPlayHelp;

    /**
     * 静态内部类实现单例
     */
    private MediaPlayHelp(Context mContext) {
        this.mContext = mContext;
        mMediaPlayer = new MediaPlayer();
    }

    public static MediaPlayHelp getInstance(Context mContext) {
        if (mMediaPlayHelp == null) {
            synchronized (MediaPlayHelp.class) {
                if (mMediaPlayHelp == null) {
                    mMediaPlayHelp = new MediaPlayHelp(mContext);
                }
            }
        }
        return mMediaPlayHelp;
    }

//    private static class MediaPlayHelpHolder {
//        private static Context mContext;
//        private static final MediaPlayHelp instance = new MediaPlayHelp(mContext);
//    }
//
//    public static MediaPlayHelp getInstance(Context mContext) {
//        MediaPlayHelpHolder.mContext = mContext;
//        return MediaPlayHelpHolder.instance;
//    }

    /**
     * 步骤
     * 1.当前需要播放的音乐
     * 2.播放
     * 3.暂停
     */

    //当前需要播放的音乐
    public void setMusciPath(String path) {
        mPath = path;
        //音乐正在播放，重置播放状态
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.reset();
        }
        //设置播放路径
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //准备播放
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mOnMediaPlayHelpPreparedListener != null) {
                    mOnMediaPlayHelpPreparedListener.onPrepared(mp);
                }
            }
        });
    }

    /**
     * 返回正在播放的音乐路径
     *
     * @return
     */
    public String getMusicPath() {
        return mPath;
    }

    //播放
    public void setPlayMusci() {
        if (mMediaPlayer.isPlaying()) return;
        mMediaPlayer.start();
    }

    //暂停
    public void setStopMusci() {
        mMediaPlayer.pause();
    }

    public interface OnMediaPlayHelpPreparedListener {
        void onPrepared(MediaPlayer mp);
    }

    public void setOnMediaPlayHelpPreparedListener(OnMediaPlayHelpPreparedListener mOnMediaPlayHelpPreparedListener) {
        this.mOnMediaPlayHelpPreparedListener = mOnMediaPlayHelpPreparedListener;
    }
}
