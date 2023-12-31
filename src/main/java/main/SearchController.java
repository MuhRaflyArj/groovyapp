package main;

import dao.SongDAO;
import object.Song;

import java.util.List;

public class SearchController {

    public static void display(String keyword) {
        List<Song> searchedSong = SongDAO.searchSong(keyword);

        SearchView.display(Main.root, searchedSong);

    }
}
