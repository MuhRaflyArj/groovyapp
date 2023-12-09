package main;

import dao.SongDAO;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import object.Playlist;
import object.Song;

public class PlaylistView {
    public static void display(BorderPane root, Playlist playlist) {
        VBox allSong = new VBox(5);
        Text temp = new Text(playlist.getName());
        Text temp2 = new Text(playlist.getDesc());
        allSong.getChildren().addAll(temp, temp2);

        for (String songID: playlist.getSongList()) {
            Song currSong = SongDAO.getSongById(songID);
            Text temp3 = new Text(currSong.getTitle() + " - " + currSong.getArtist());
            allSong.getChildren().add(temp3);
        }


        root.setCenter(allSong);

    }
}
