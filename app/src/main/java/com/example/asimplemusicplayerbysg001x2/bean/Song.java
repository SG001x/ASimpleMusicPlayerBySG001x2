package com.example.asimplemusicplayerbysg001x2.bean;

public class Song {
    public String name;//歌曲名
    public String singer;//歌手
    public long size;//歌曲所占空间大小
    public int duration;//歌曲时间长度
    public String path;//歌曲地址
    public long  albumId;//图片id
    public long id;//歌曲id


    public Song() {
    }

    public Song(String name, String singer, long size, int duration, String path, long albumId, long id) {
        this.name = name;
        this.singer = singer;
        this.size = size;
        this.duration = duration;
        this.path = path;
        this.albumId = albumId;
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return singer
     */
    public String getSinger() {
        return singer;
    }

    /**
     * 设置
     * @param singer
     */
    public void setSinger(String singer) {
        this.singer = singer;
    }

    /**
     * 获取
     * @return size
     */
    public long getSize() {
        return size;
    }

    /**
     * 设置
     * @param size
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 获取
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * 设置
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * 获取
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取
     * @return albumId
     */
    public long getAlbumId() {
        return albumId;
    }

    /**
     * 设置
     * @param albumId
     */
    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    /**
     * 获取
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

}
