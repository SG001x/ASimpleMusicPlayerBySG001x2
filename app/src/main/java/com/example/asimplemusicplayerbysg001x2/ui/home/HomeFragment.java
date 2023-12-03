package com.example.asimplemusicplayerbysg001x2.ui.home;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.asimplemusicplayerbysg001x2.MusicAdapter;
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


//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_PERMISSIONS) {
//            // 处理权限请求结果
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // 权限已授予，执行获取本地音乐的操作
//                // 获取本地音乐
//                fetchLocalMusic();
//            } else {
//                // 用户拒绝了权限请求
//            }
//        }
//    }

    private void fetchLocalMusic() {
        // 获取本地音乐的逻辑
        // 这里可以使用 MediaStore 或其他方法获取设备上的音乐文件信息

        // 示例代码：获取 MediaStore 中的音乐
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST
        };

        Cursor cursor = requireContext().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        if (cursor != null) {
            List<String> musicList = new ArrayList<>();
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));

                // TODO: 将获取的音乐信息添加到列表中
                musicList.add(title + " - " + artist);
            }

            cursor.close();

            // 将音乐列表展示在界面上，使用 RecyclerView
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            binding.recyclerView.setAdapter(new MusicAdapter(musicList));
        }
    }
}