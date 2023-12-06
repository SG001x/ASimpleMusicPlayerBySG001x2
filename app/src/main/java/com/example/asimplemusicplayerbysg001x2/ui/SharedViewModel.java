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
    private int pausedPosition = 0;

    // 设置音乐播放进度
    public void setMusicProgress(int progress) {
        if (mediaPlayer != null) {
            int duration = mediaPlayer.getDuration();
            int newPosition = (int) ((float) duration * ((float) progress / 100.0));
            mediaPlayer.seekTo(newPosition);
        }
    }

    public SharedViewModel() {
        // 初始化 MediaPlayer
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(mp -> onSongComplete());
    }


    public void playPauseToggle(int comeFrom) {

        if(comeFrom == 1){
            play(comeFrom);
        }else{
            if (Boolean.TRUE.equals(isPlaying.getValue())) {
                play(comeFrom);
            } else {
                pause();
            }
        }
    }

    // 播放
    private void play(int comeFrom) {
        if (selectedSong.getValue() != null) {
            try {
                if (comeFrom == 1) {
                    mediaPlayer.reset();
                    pausedPosition = 0;
                }
                if (!mediaPlayer.isPlaying()) {
                    if (pausedPosition == 0) {
                        // 如果是刚开始播放，设置数据源
                        mediaPlayer.setDataSource(selectedSong.getValue().getPath());
                        mediaPlayer.prepare();
                    } else {
                        // 如果是从暂停状态恢复，直接播放
                        mediaPlayer.seekTo(pausedPosition);
                    }

                    mediaPlayer.start();
                    isPlaying.setValue(true);
                }
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
            // 记录当前播放位置
            pausedPosition = mediaPlayer.getCurrentPosition();
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

    // 获取当前播放位置
    public int getCurrentPosition() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }
    // 获取当前音乐的总时长
    public int getDuration() {
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
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
