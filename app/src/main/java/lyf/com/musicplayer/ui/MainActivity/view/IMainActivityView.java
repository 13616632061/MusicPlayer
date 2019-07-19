package lyf.com.musicplayer.ui.MainActivity.view;

import java.util.List;

import lyf.com.musicplayer.bean.MusicInfo;

/**
 * Created by Administrator on 2019/7/18.
 */

public interface IMainActivityView {
    /**
     * 设置Grid适配器
     */
    void setGridAdapter(List<MusicInfo> mMusicGridData);


    /**
     * 设置Linner线性适配器
     */
    void setLinnerAdapter(List<MusicInfo> mMusicGridData);
}
