package lyf.com.musicplayer.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;
import java.io.PipedReader;

/**
 * Created by Administrator on 2019/7/18.
 * 播放器创建3中方式：
 * 1.直接在activity中创建，音乐与activity绑定，activity运行时播放音乐，activity退出时音乐就会停止
 * 2.通过全局的单例类，与application绑定，application（程序）被杀死时，音乐停止播放
 * 3.（推荐）通过service进行播放，service运行时播放音乐，service被杀死时，音乐停止播放
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
        //处理当播放页面退出时，造成内存泄漏
        this.mContext = mContext.getApplicationContext();
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


    /**
     * 步骤
     * 1.当前需要播放的音乐
     * 2.播放
     * 3.暂停
     */

    //当前需要播放的音乐
    public void setMusciPath(String path) {

        //音乐正在播放，或者切换音乐，重置播放状态
        if (mMediaPlayer.isPlaying() || !path.equals(mPath)) {
            mMediaPlayer.reset();
        }
        mPath = path;
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
    public void setPauseMusci() {
        mMediaPlayer.pause();
    }

    public interface OnMediaPlayHelpPreparedListener {
        void onPrepared(MediaPlayer mp);
    }

    public void setOnMediaPlayHelpPreparedListener(OnMediaPlayHelpPreparedListener mOnMediaPlayHelpPreparedListener) {
        this.mOnMediaPlayHelpPreparedListener = mOnMediaPlayHelpPreparedListener;
    }
}
