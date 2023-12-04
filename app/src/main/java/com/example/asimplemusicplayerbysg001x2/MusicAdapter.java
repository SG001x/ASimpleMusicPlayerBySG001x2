package com.example.asimplemusicplayerbysg001x2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimplemusicplayerbysg001x2.bean.Song;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private List<Song> musicList;

    public MusicAdapter(List<Song> musicList) {
        this.musicList = musicList;
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
