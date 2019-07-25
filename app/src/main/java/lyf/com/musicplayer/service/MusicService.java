package lyf.com.musicplayer.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;

import lyf.com.musicplayer.R;
import lyf.com.musicplayer.bean.MusicInfo;
import lyf.com.musicplayer.ui.MainActivity.view.MainActivity;
import lyf.com.musicplayer.utils.MediaPlayHelp;

/**
 * 通过Service连接PlayMusic与MediaPlayHelp
 * PlayMusic---Service
 * 1.播放音乐，暂停播放
 * 2.启动Service，绑定Service，解除绑定Service
 * MediaPlayHelp---Service
 * 1.播放音乐，暂停播放
 * 2.监听音乐完成，停止Service
 */
public class MusicService extends Service {

    public final static int RESQUEST_CODE = 0;
    //    不可为 0
    public static final int NOTIFICATION_ID = 1;


    private MediaPlayHelp mMediaPlayHelp;
    private MusicInfo mMusicInfo;

    public MusicService() {
    }

    public class MusicBind extends Binder {

        //设置音乐
        public void setMusic(MusicInfo musicInfo) {
            mMusicInfo = musicInfo;
            setForegroundVisiable();
        }

        //播放音乐
        public void playMusic() {
            /**
             * 1.判断当前音乐是否已经在播放
             * 2.是，调用start
             * 3.当前播放的音乐不是需要播放的音乐，调用setpath
             */
            if (mMediaPlayHelp.getMusicPath() != null && mMediaPlayHelp.getMusicPath().equals(mMusicInfo.getUrl())) {
                mMediaPlayHelp.setPlayMusci();
            } else {
                mMediaPlayHelp.setMusciPath(mMusicInfo.getUrl());
                mMediaPlayHelp.setOnMediaPlayHelpPreparedListener(new MediaPlayHelp.OnMediaPlayHelpPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayHelp.setPlayMusci();
                    }
                });
            }
        }

        //暂停音乐
        public void PauseMuscic() {
            mMediaPlayHelp.setPauseMusci();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new MusicBind();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayHelp = MediaPlayHelp.getInstance(this);
    }

    /**
     * 设置前台可见（系统默认不允许不可见的后台服务播放音乐）
     */
    public void setForegroundVisiable() {

        //通知栏点击跳转的intent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, RESQUEST_CODE,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = null;
        /**
         * android API 26 以上 NotificationChannel 特性适配
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = createNotificationChannel();
            notification = new Notification.Builder(this, notificationChannel.getId())
                    .setContentTitle(mMusicInfo.getName())
                    .setContentText(mMusicInfo.getPsesoner())
                    .setSmallIcon(R.mipmap.music)
                    .setContentIntent(pendingIntent)
                    .build();
            //notificationManager 中创建通知渠道
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        } else {
            notification = new Notification.Builder(this)
                    .setContentTitle(mMusicInfo.getName())
                    .setContentText(mMusicInfo.getPsesoner())
                    .setSmallIcon(R.mipmap.music)
                    .setContentIntent(pendingIntent)
                    .build();
        }
        startForeground(NOTIFICATION_ID, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel createNotificationChannel() {
        String channelId = "imooc";
        String channelName = "ImoocMusicService";
        String Description = "ImoocMusic";
        // 传入参数：通道ID，通道名字，通道优先级（类似曾经的 builder.setPriority()）
        NotificationChannel channel = new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_HIGH);
        // 配置通知渠道的属性
        channel.setDescription(Description);
        // 设置通知出现时的闪灯（如果 android 设备支持的话）
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        // 设置通知出现时的震动（如果 android 设备支持的话）
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        channel.setShowBadge(false);

        return channel;
    }
}
