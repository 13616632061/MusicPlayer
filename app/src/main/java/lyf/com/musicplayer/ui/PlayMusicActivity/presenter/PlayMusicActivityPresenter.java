package lyf.com.musicplayer.ui.PlayMusicActivity.presenter;

import lyf.com.musicplayer.bean.MusicInfo;
import lyf.com.musicplayer.ui.PlayMusicActivity.view.PlayMusicActivity;

/**
 * Created by Administrator on 2019/7/18.
 */

public class PlayMusicActivityPresenter implements IPlayMusicActivityPresenterImp {

    private PlayMusicActivity mView;

    public PlayMusicActivityPresenter(PlayMusicActivity mView) {
        this.mView = mView;
    }

    @Override
    public void setMusicBgIcon(String bgIcon) {
        mView.setMusicBgIcon(bgIcon);
    }

    @Override
    public void setMusicIcon(String picture) {
        mView.setMusicIcon(picture);
    }

    @Override
    public void setMusicName(String name) {
        mView.setMusicName(name);
    }

    @Override
    public void playMusic(MusicInfo musicInfo) {
        mView.playMusic(musicInfo);
    }
}
