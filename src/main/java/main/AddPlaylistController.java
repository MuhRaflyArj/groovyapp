package main;

import dao.PlaylistDAO;
import dao.SongDAO;
import javafx.scene.layout.BorderPane;
import object.Playlist;
import object.Song;

import java.util.List;

public class AddPlaylistController {

    public static void display(BorderPane root) {
        AddPlaylistView.display(root);
    }

    public static void createPlaylist(String name, String description, String imagePath) {
        Playlist playlist = new Playlist();

        playlist.setName(name);
        playlist.setDesc(description);
        playlist.setCoverPath(imagePath);

        if (PlaylistDAO.getAllPlaylist().isEmpty()){
            playlist.setPlaylistID("P001");
        }else{
            List<Playlist> allPlaylist = PlaylistDAO.getAllPlaylist();

            Playlist lastPlaylist = allPlaylist.get(allPlaylist.size()-1);

            int lastID = Integer.parseInt(lastPlaylist.getPlaylistID().substring(1)) + 1;

            playlist.setPlaylistID(String.format("P%03d", lastID));
        }

        playlist.display();
        PlaylistDAO.createPlaylist(playlist);
    }
}
