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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.asimplemusicplayerbysg001x2.LocalMusicUtils;
import com.example.asimplemusicplayerbysg001x2.MusicAdapter;
import com.example.asimplemusicplayerbysg001x2.bean.Song;
import com.example.asimplemusicplayerbysg001x2.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private static final int REQUEST_PERMISSIONS = 1001;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //直接使用View Binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Button btnAutoFetchMusic = root.findViewById(R.id.btnAutoFetchMusic);
        binding.btnAutoFetchMusic.setOnClickListener(v -> {
            // 处理自动获取本地音乐按钮点击事件
            checkAndRequestPermissions();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void checkAndRequestPermissions() {
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
                // 权限已授予，执行获取本地音乐的操作
                fetchLocalMusic();
            }
        } else {
            // 不需要动态请求权限，直接执行获取本地音乐的操作
            fetchLocalMusic();
        }
    }


    private void fetchLocalMusic() {
        // 获取本地音乐的逻辑
        // 这里可以使用 MediaStore 或其他方法获取设备上的音乐文件信息

        // 初始化 RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // 初始化音乐列表数据
        List<Song> musicList = LocalMusicUtils.getmusic(requireContext());

        // 初始化适配器
        MusicAdapter musicAdapter = new MusicAdapter(musicList);

        // 设置适配器
        binding.recyclerView.setAdapter(musicAdapter);
    }
}