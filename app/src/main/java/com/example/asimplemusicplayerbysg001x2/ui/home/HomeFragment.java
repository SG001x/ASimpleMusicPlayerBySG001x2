package com.example.asimplemusicplayerbysg001x2.ui.home;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.asimplemusicplayerbysg001x2.LocalMusicUtils;
import com.example.asimplemusicplayerbysg001x2.MusicAdapter;
import com.example.asimplemusicplayerbysg001x2.R;
import com.example.asimplemusicplayerbysg001x2.bean.Song;
import com.example.asimplemusicplayerbysg001x2.databinding.FragmentHomeBinding;
import com.example.asimplemusicplayerbysg001x2.ui.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MusicAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private SharedViewModel sharedViewModel;
    private static final int REQUEST_PERMISSIONS = 1001;
    private boolean isMusicFetched = false; // 添加标志位

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // 先初始化 binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 初始化 sharedViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // 使用 sharedViewModel 初始化 homeViewModel
        homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory(sharedViewModel))
                .get(HomeViewModel.class);

        // 初始化 RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // 处理自动获取本地音乐按钮点击事件
        binding.btnAutoFetchMusic.setOnClickListener(v -> {
            checkAndRequestPermissions();
        });

        if (binding.recyclerView.getAdapter() == null) {
            // 初始化 RecyclerView
            // binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            // 初始化音乐列表数据
            List<Song> musicList = checkAndRequestPermissions();

            // 初始化适配器
            MusicAdapter musicAdapter = new MusicAdapter(musicList, this);

            // 设置适配器
            binding.recyclerView.setAdapter(musicAdapter);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<Song> checkAndRequestPermissions() {
        // Android 6.0 (API 23) 之前应用的权限在安装时全部授予，运行时应用不再需要询问用户。
        // 在 Android 6.0 或更高版本对权限进行了分类，对某些涉及到用户隐私的权限可在运行时根据用户的需要动态授予。
        // 这样就不需要在安装时被强迫同意某些权限。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查读取外部存储权限
            int storagePermission = ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);

            List<String> permissions = new ArrayList<>();

            if (storagePermission != PackageManager.PERMISSION_GRANTED) {
                // 未授予读取外部存储权限
                permissions.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            if (!permissions.isEmpty()) {
                // 存在未授予的权限，请求权限
                String[] permissionsArray = permissions.toArray(new String[0]);
                requestPermissions(permissionsArray, REQUEST_PERMISSIONS);
            } else {
                if (!isMusicFetched) { // 添加条件，只有在音乐未被获取时执行
                    // 权限已授予，执行获取本地音乐的操作
                    List<Song> musicList = fetchLocalMusic();
                    isMusicFetched = true; // 设置标志位为 true，表示音乐已被获取
                    return musicList;
                }
            }
        } else {
            // 不需要动态请求权限，直接执行获取本地音乐的操作
            if (!isMusicFetched) { // 添加条件，只有在音乐未被获取时执行
                // 权限已授予，执行获取本地音乐的操作
                List<Song> musicList = fetchLocalMusic();
                isMusicFetched = true; // 设置标志位为 true，表示音乐已被获取
                return musicList;
            }
        }
        return null;
    }

    private List<Song> fetchLocalMusic() {
        // 获取本地音乐的逻辑
        // 使用 MediaStore 或其他方法获取设备上的音乐文件信息
        // 检查当前是否已经有音乐列表
        if (binding.recyclerView.getAdapter() == null) {
            // 初始化 RecyclerView
            //binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            // 初始化音乐列表数据
            List<Song> musicList = LocalMusicUtils.getmusic(requireContext());

            // 初始化适配器
            MusicAdapter musicAdapter = new MusicAdapter(musicList);

            // 设置适配器
            binding.recyclerView.setAdapter(musicAdapter);
            return musicList;
        }
        return null;
    }

    // 创建 HomeViewModel 的工厂类

    static class HomeViewModelFactory implements ViewModelProvider.Factory {
        private final SharedViewModel sharedViewModel;

        HomeViewModelFactory(SharedViewModel sharedViewModel) {
            this.sharedViewModel = sharedViewModel;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(HomeViewModel.class)) {
                return (T) new HomeViewModel(sharedViewModel);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

    // 实现 OnItemClickListener 接口的方法
    @Override
    public void onItemClick(Song song) {
        // 处理点击事件，调用 SharedViewModel 中的 homeViewModel.onSongSelected(song) 设置选中的歌曲
        homeViewModel.onSongSelected(song);
        // 将选中的歌曲传递给 SharedViewModel
        sharedViewModel.selectSong(song);
        // 切换播放状态
        sharedViewModel.playPauseToggle();
        // 导航到 DashboardFragment
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_dashboardFragment);
    }

}