package main;

import dao.PlaylistDAO;
import dao.SongDAO;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import object.Playlist;
import object.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistController {
    public static void display(BorderPane root, Playlist playlist) {
        PlaylistView.display(root, playlist);
    }

    public static void deleteSong(Playlist playlist, Song song, BorderPane root) {
        List<String> songList = playlist.getSongList();
        songList.remove(song.getSongID());

        playlist.setSongList(songList);
        PlaylistDAO.updatePlaylist(playlist.getPlaylistID(), playlist);
        display(root, playlist);
    }

    public static void addSong(Map<CheckBox, String> checkBoxMap, Playlist playlist, BorderPane root) {
        List<String> songList = playlist.getSongList();
        for(Map.Entry<CheckBox, String> entry : checkBoxMap.entrySet()) {
            CheckBox checkBox = entry.getKey();
            String songId = entry.getValue();

            if(checkBox.isSelected()) {
                songList.add(songId);
            }
        }

        playlist.setSongList(songList);
        PlaylistDAO.updatePlaylist(playlist.getPlaylistID(), playlist);
        PlaylistController.display(root, playlist);
    }

    public static void deletePlaylistSong(Map<CheckBox, String> checkBoxMap, Playlist playlist, BorderPane root) {
        List<String> songList = playlist.getSongList();
        for(Map.Entry<CheckBox, String> entry : checkBoxMap.entrySet()) {
            CheckBox checkBox = entry.getKey();
            String songId = entry.getValue();

            if(checkBox.isSelected()) {
                songList.remove(songId);
            }
        }

        playlist.setSongList(songList);
        PlaylistDAO.updatePlaylist(playlist.getPlaylistID(), playlist);
        PlaylistController.display(root, playlist);
    }

    public static void deletePlaylist(Playlist playlist, BorderPane root) {
        boolean isDelete = DeletePlaylistConfirmationPopup.show(playlist.getName());

        if(isDelete) {
            PlaylistDAO.deletePlaylist(playlist.getPlaylistID());
            HomeController.display(root);
            SideBarController.display(root, PlaylistDAO.getAllPlaylist());
        }
    }

    public static void playSong(Song selected, Playlist playlist) {
        MusicPlayerController.play(playlist, selected);
    }
}
