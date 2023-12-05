package com.example.asimplemusicplayerbysg001x2.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.asimplemusicplayerbysg001x2.bean.Song;
import com.example.asimplemusicplayerbysg001x2.ui.SharedViewModel;

public class HomeViewModel extends ViewModel {
    private final SharedViewModel sharedViewModel;
    private final MutableLiveData<Song> selectedSong = new MutableLiveData<>();
    public HomeViewModel(SharedViewModel sharedViewModel) {
        this.sharedViewModel = sharedViewModel;
    }

//    public void onSongSelected(Song song) {
//        // 当歌曲被选中时，调用 homeViewModel.onSongSelected(selectedSong)
//        sharedViewModel.selectSong(song);
//    }
    public void onSongSelected(Song song) {
        // 当歌曲被选中时，调用 homeViewModel.onSongSelected(selectedSong)
        selectedSong.setValue(song);
    }
}