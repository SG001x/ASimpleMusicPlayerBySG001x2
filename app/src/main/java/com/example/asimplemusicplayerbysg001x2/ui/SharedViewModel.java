package com.example.asimplemusicplayerbysg001x2.ui;

import android.media.MediaPlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.asimplemusicplayerbysg001x2.bean.Song;

import java.io.IOException;

public class SharedViewModel extends ViewModel {
    // 添加了一个 isPlaying 的 MutableLiveData，用于标识当前音乐的播放状态
    private MutableLiveData<Boolean> isPlaying = new MutableLiveData<>(false);
    // 当前选中的歌曲
    private MutableLiveData<Song> selectedSong = new MutableLiveData<>();
    // MediaPlayer 实例
    private MediaPlayer mediaPlayer;

    public SharedViewModel() {
        // 初始化 MediaPlayer
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(mp -> onSongComplete());
    }


    public void playPauseToggle() {
        if (isPlaying.getValue() != null && isPlaying.getValue()) {
            pause();
        } else {
            play();
        }
        // 切换播放/暂停状态
        isPlaying.setValue(!isPlaying.getValue());
    }

    // 播放
    private void play() {
        if (selectedSong.getValue() != null) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(selectedSong.getValue().getPath());
                mediaPlayer.prepare();
                mediaPlayer.start();
                isPlaying.setValue(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 暂停
    private void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlaying.setValue(false);
        }
    }

    // 当歌曲播放完成时的处理
    private void onSongComplete() {
        isPlaying.setValue(false);
        // 在这里处理歌曲播放完成后的逻辑，比如切换到下一首
    }

    // 获取播放状态
    public LiveData<Boolean> getIsPlaying() {
        return isPlaying;
    }

    // 获取选中的歌曲
    public LiveData<Song> getSelectedSong() {
        return selectedSong;
    }

    // 设置选中的歌曲
    public void selectSong(Song song) {
        selectedSong.setValue(song);
    }

    // 切换播放/暂停状态
    public boolean togglePlayPause() {
        boolean currentStatus = isPlaying.getValue();
        isPlaying.setValue(!currentStatus);
        // 在这里实现根据播放状态控制音乐的播放/暂停逻辑
        // 如果使用的是 MediaPlayer，可以在这里调用 start() 或 pause()
        return !currentStatus; // 返回切换后的状态
    }
    // 切换到上一首歌曲
    public void playPreviousSong() {
        // 在这里实现切换到上一首歌曲的逻辑
        // 可以调用你的音乐播放库提供的方法
    }

    // 切换到下一首歌曲
    public void playNextSong() {
        // 在这里实现切换到下一首歌曲的逻辑
        // 可以调用你的音乐播放库提供的方法
    }
}
