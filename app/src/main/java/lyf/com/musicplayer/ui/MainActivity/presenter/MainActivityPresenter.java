package lyf.com.musicplayer.ui.MainActivity.presenter;

import java.util.ArrayList;
import java.util.List;

import lyf.com.musicplayer.bean.MusicInfo;
import lyf.com.musicplayer.ui.MainActivity.view.MainActivity;

/**
 * Created by Administrator on 2019/7/18.
 */

public class MainActivityPresenter implements IMainActivityPresenterImp {

    private MainActivity mView;

    public MainActivityPresenter(MainActivity mView) {
        this.mView = mView;
    }


    @Override
    public void getMusicGridData() {
        List<MusicInfo> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MusicInfo musicInfo = new MusicInfo();
            musicInfo.setName("最初的梦想");
            musicInfo.setPsesoner("关淑怡");
            musicInfo.setCount("11.3万");
            musicInfo.setPicture("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=755537835,4133665607&fm=26&gp=0.jpg");
            musicInfo.setUrl("http://stor.cloudmusics.cn/mp3/2019/07/d0702cd0c7bb82328a84c07a771cea40.mp3");
            list.add(musicInfo);
            MusicInfo musicInfo1 = new MusicInfo();
            musicInfo1.setName("Nostalgic Piano");
            musicInfo1.setPsesoner("Rafael Krux");
            musicInfo1.setCount("203.3万");
            musicInfo1.setPicture("http://res.lgdsunday.club/poster-1.png");
            musicInfo1.setUrl("http://res.lgdsunday.club/Nostalgic%20Piano.mp3");
            list.add(musicInfo1);
        }
        mView.setGridAdapter(list);
        mView.setLinnerAdapter(list);
    }
}
