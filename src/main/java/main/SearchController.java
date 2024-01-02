package main;

import dao.PlaylistDAO;
import dao.SongDAO;
import javafx.scene.control.Button;
import object.Playlist;
import object.Song;

import java.util.ArrayList;
import java.util.List;

public class SearchController {
    private static final Playlist allSong = new Playlist();

    public static void display(String keyword) {
        List<Song> searchedSong = SongDAO.searchSong(keyword);
        System.out.println(searchedSong.isEmpty());

        SearchView.display(Main.root, searchedSong, keyword);
    }

    public static void playSong(Song selected) {
        List<String> songIdList = new ArrayList<>();
        SongDAO.getAllSong()
                .forEach(song ->
                        songIdList.add(song.getSongID())
                );
        allSong.setSongList(songIdList);
        allSong.setDesc("");
        allSong.setName("");

        MusicPlayerController.play(allSong, selected);
    }

    public static void deleteSong(Song song) {
        System.out.println(song.getSongID() + " deleted!!");
        // SongDAO.deleteSong(song.getSongID());
    }
}
