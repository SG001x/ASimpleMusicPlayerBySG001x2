package com.example.asimplemusicplayerbysg001x2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimplemusicplayerbysg001x2.bean.Song;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private List<Song> musicList;
    private final OnItemClickListener onItemClickListener;

    public MusicAdapter(List<Song> musicList, OnItemClickListener onItemClickListener) {
        this.musicList = musicList;
        this.onItemClickListener = onItemClickListener != null ? onItemClickListener : song -> {};
    }

    public MusicAdapter(List<Song> musicList) {
        this.musicList = musicList;
        onItemClickListener = null;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_music_item_layout, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Song song = musicList.get(position);
        // 解析音乐信息并绑定到视图
        // 这里简单地将音乐名称和歌手显示在 TextView 中
        String musicInfo = song.getName();
        // 设置到视图上
        holder.tvSongTitle.setText(musicInfo);
        holder.tvArtist.setText("");
        // 添加点击事件
        holder.itemView.setOnClickListener(v -> {
            // 处理列表项点击事件
            // 在这里处理跳转到 DashboardFragment 的逻辑
            // 你可以使用 Navigation 组件来导航到 DashboardFragment

            // 获取当前的 NavController
            NavController navController = Navigation.findNavController(holder.itemView);

            // 使用 SafeArgs 传递歌曲信息到 DashboardFragment
//            DashboardFragmentDirections.ActionDashboardFragmentToPlayerFragment action =
//                    DashboardFragmentDirections.actionDashboardFragmentToPlayerFragment(song.getName(), song.getSinger());

            // 导航到 DashboardFragment，并传递参数
//            navController.navigate(action);

            // 在这里处理歌曲点击事件
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(musicList.get(position));
            }
        });
    }
    // 歌曲点击事件接口
    public interface OnItemClickListener {
        void onItemClick(Song song);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    static class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView tvSongTitle;
        TextView tvArtist;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSongTitle = itemView.findViewById(R.id.tvSongTitle);
            tvArtist = itemView.findViewById(R.id.tvArtist);
        }
    }


}
