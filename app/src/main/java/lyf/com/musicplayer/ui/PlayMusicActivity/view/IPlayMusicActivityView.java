package lyf.com.musicplayer.ui.PlayMusicActivity.view;

import lyf.com.musicplayer.bean.MusicInfo;

/**
 * Created by Administrator on 2019/7/18.
 */

public interface IPlayMusicActivityView {

    void setMusicBgIcon(String bgIcon);

    void setMusicIcon(String picture);

    void setMusicName(String name);

    void playMusic(MusicInfo musicInfo);


}
