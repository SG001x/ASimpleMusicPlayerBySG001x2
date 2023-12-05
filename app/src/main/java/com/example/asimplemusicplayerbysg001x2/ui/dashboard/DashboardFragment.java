package com.example.asimplemusicplayerbysg001x2.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 观察 SharedViewModel 中的选定歌曲
        sharedViewModel.getSelectedSong().observe(getViewLifecycleOwner(), song -> {
            // 更新 Dashboard 界面元素，显示所选歌曲的信息
            updateDashboardUI(song);
        });

        // TODO: 添加相应的点击事件处理逻辑
//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    // 更新 Dashboard 界面的方法
    private void updateDashboardUI(Song song) {
        // 在这里更新 Dashboard 界面元素，显示所选歌曲的信息
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}