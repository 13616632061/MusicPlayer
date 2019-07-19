package lyf.com.musicplayer.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ClickUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;

import lyf.com.musicplayer.R;
import lyf.com.musicplayer.utils.MediaPlayHelp;

/**
 * Created by Administrator on 2019/7/18.
 */

public class PlayMusic extends FrameLayout {

    private Context mContext;
    private ImageView iv_tip_cion, iv_circle_icon, iv_play;
    private RelativeLayout layout_play;
    private boolean isPlaying;
    private String mPath;

    private MediaPlayHelp mMediaPlayHelp;

    private Animation mPlayMusicAnimation, mPlayTipAnimation, mStopTipAnimation;

    public PlayMusic(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusic(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusic(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusic(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = View.inflate(context, R.layout.play_musi_layout, null);

        iv_tip_cion = view.findViewById(R.id.iv_tip_cion);
        iv_circle_icon = view.findViewById(R.id.iv_circle_icon);
        iv_play = view.findViewById(R.id.iv_play);
        layout_play = view.findViewById(R.id.layout_play);

        layout_play.setOnClickListener(new ClickUtils.OnDebouncingClickListener() {
            @Override
            public void onDebouncingClick(View v) {
                palyStatus();

            }
        });

        /**
         * 添加动画
         */
        mPlayMusicAnimation = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        mPlayTipAnimation = AnimationUtils.loadAnimation(mContext, R.anim.tip_play_anim);
        mStopTipAnimation = AnimationUtils.loadAnimation(mContext, R.anim.tip_stop_anim);


        mMediaPlayHelp = MediaPlayHelp.getInstance(mContext);
        addView(view);


    }

    /**
     * 音乐播放状态切换
     *
     * @throws IOException
     */
    private void palyStatus() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic(mPath);
        }
    }

    /**
     * 播放音乐
     */
    public void playMusic(String path) {
        mPath = path;
        isPlaying = true;
        iv_play.setVisibility(GONE);
        iv_circle_icon.startAnimation(mPlayMusicAnimation);
        iv_tip_cion.startAnimation(mPlayTipAnimation);

        /**
         * 1.判断当前音乐是否已经在播放
         * 2.是，调用start
         * 3.当前播放的音乐不是需要播放的音乐，调用setpath
         */
        if (mMediaPlayHelp.getMusicPath() != null && mMediaPlayHelp.getMusicPath().equals(path)) {
            mMediaPlayHelp.setPlayMusci();
        } else {
            mMediaPlayHelp.setMusciPath(path);
            mMediaPlayHelp.setOnMediaPlayHelpPreparedListener(new MediaPlayHelp.OnMediaPlayHelpPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayHelp.setPlayMusci();
                }
            });
        }
    }

    /**
     * 停止播放
     */
    public void stopMusic() {
        isPlaying = false;
        iv_play.setVisibility(VISIBLE);
        iv_circle_icon.clearAnimation();
        iv_tip_cion.startAnimation(mStopTipAnimation);
        mMediaPlayHelp.setStopMusci();

    }

    /**
     * 音乐圆形图片
     *
     * @param url
     */
    public void setMusicIcon(String url) {
        Glide.with(mContext).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_circle_icon);
    }

}
