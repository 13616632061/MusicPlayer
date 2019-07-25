package lyf.com.musicplayer.ui.PlayMusicActivity.presenter;

import lyf.com.musicplayer.bean.MusicInfo;

/**
 * Created by Administrator on 2019/7/18.
 */

public interface IPlayMusicActivityPresenterImp {

    void setMusicBgIcon(String bgIcon);

    void setMusicIcon(String picture);

    void setMusicName(String name);

    void playMusic(MusicInfo musicInfo);
}
