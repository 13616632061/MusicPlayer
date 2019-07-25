package lyf.com.musicplayer.ui.PlayMusicActivity.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import lyf.com.musicplayer.R;
import lyf.com.musicplayer.base.BaseActivity;
import lyf.com.musicplayer.bean.MusicInfo;
import lyf.com.musicplayer.ui.PlayMusicActivity.presenter.PlayMusicActivityPresenter;
import lyf.com.musicplayer.view.PlayMusic;

public class PlayMusicActivity extends BaseActivity implements IPlayMusicActivityView {


    @InjectView(R.id.iv_bg_icon)
    ImageView ivBgIcon;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.play_music)
    PlayMusic playMusic;
    @InjectView(R.id.tv_name)
    TextView tvName;

    private MusicInfo mMusicInfo;
    private PlayMusicActivityPresenter mPresenter;

    @Override
    public int initContentView() {
        return R.layout.activity_play_music;
    }

    @Override
    public void initView() {
        super.initView();
        mMusicInfo = (MusicInfo) getIntent().getExtras().get("musicInfo");
        mPresenter = new PlayMusicActivityPresenter(this);
        mPresenter.setMusicBgIcon(mMusicInfo.getPicture());
        mPresenter.setMusicIcon(mMusicInfo.getPicture());
        mPresenter.setMusicName(mMusicInfo.getName());

        mPresenter.playMusic(mMusicInfo);
    }

    @OnClick({R.id.iv_back})
    public void setOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void setMusicBgIcon(String bgIcon) {
        Glide.with(this).load(bgIcon)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(50)))
                .into(ivBgIcon);
    }

    @Override
    public void setMusicIcon(String picture) {
        playMusic.setMusicIcon(picture);
    }

    @Override
    public void setMusicName(String name) {
        tvName.setText(name);
    }

    @Override
    public void playMusic(MusicInfo musicInfo) {
        playMusic.setmMusicInfo(musicInfo);
        playMusic.playMusic(musicInfo.getUrl());
    }
}
