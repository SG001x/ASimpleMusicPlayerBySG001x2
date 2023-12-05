package com.example.asimplemusicplayerbysg001x2.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.asimplemusicplayerbysg001x2.bean.Song;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Song> selectedSong = new MutableLiveData<>();

    public void selectSong(Song song) {
        selectedSong.setValue(song);
    }

    public LiveData<Song> getSelectedSong() {
        return selectedSong;
    }
}
