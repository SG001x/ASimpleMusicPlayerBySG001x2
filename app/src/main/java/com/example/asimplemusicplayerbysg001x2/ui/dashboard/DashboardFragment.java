package com.example.asimplemusicplayerbysg001x2.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.asimplemusicplayerbysg001x2.R;
import com.example.asimplemusicplayerbysg001x2.bean.Song;
import com.example.asimplemusicplayerbysg001x2.databinding.FragmentDashboardBinding;
import com.example.asimplemusicplayerbysg001x2.ui.SharedViewModel;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private SharedViewModel sharedViewModel;

    public DashboardFragment() {
        // Required empty public constructor
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        // 初始化 binding
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 获取 SharedViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // 观察 SharedViewModel 中的选定歌曲,监听选中的歌曲变化
        sharedViewModel.getSelectedSong().observe(getViewLifecycleOwner(), song -> {
            // 更新 Dashboard 界面元素，显示所选歌曲的信息
            updateDashboardUI(song);
        });

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    // 更新 Dashboard 界面的方法
    private void updateDashboardUI(Song song) {
        // 在这里更新 Dashboard 界面元素，显示所选歌曲的信息
        // 设置音乐名称和歌手
        binding.tvMusicTitle.setText(song.getName());
        binding.tvArtist.setText(song.getSinger());

        // 进度条等其他设置...

        // 处理按钮点击事件
        binding.btnPlayPause.setOnClickListener(v -> playPauseClicked());
        binding.btnPrevious.setOnClickListener(v -> previousClicked());
        binding.btnNext.setOnClickListener(v -> nextClicked());
    }
    private void playPauseClicked() {
        // 处理播放/暂停按钮点击事件
        // 可以使用 sharedViewModel 控制音乐的播放/暂停状态
        boolean isPlaying = sharedViewModel.togglePlayPause();
        // 在这里根据播放状态更新界面，例如切换按钮图标
        binding.btnPlayPause.setBackgroundResource(isPlaying ? R.drawable.baseline_stop_circle_24 : R.drawable.baseline_play_circle_24);
    }

    private void previousClicked() {
        // 处理上一首按钮点击事件
        // 可以使用 sharedViewModel 控制切换到上一首歌曲
        sharedViewModel.playPreviousSong();
    }

    private void nextClicked() {
        // 处理下一首按钮点击事件
        // 可以使用 sharedViewModel 控制切换到下一首歌曲
        sharedViewModel.playNextSong();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}