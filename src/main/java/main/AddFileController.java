package main;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import dao.SongDAO;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import object.Song;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AddFileController {
    public static void display(BorderPane root) {
        AddFileView.display(root);
    }

    public static void browseFile(TextField filePath){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Folder");

        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File selectedDirectory = directoryChooser.showDialog(new Stage());

        if (selectedDirectory != null) {
            filePath.setText(selectedDirectory.getAbsolutePath());
        }
    }

    public static void importFolder(String filePath){
        if(!Objects.equals(filePath, "")){
            File directory = new File(filePath);
            File[] files = directory.listFiles();

            if(files != null){

                for(File file : files){
                    String name = file.getName();

                    if(file.isFile() && name.endsWith("mp3")){
                        try{
                            Mp3File mp3 = new Mp3File(file.getPath());
                            ID3v2 tag = mp3.getId3v2Tag();

                            Song song = new Song();

                            if(SongDAO.getAllSong().isEmpty()){
                                song.setSongID("S001");
                            }else{
                                List<Song> allSong = SongDAO.getAllSong();
                                Song lastSong = allSong.get(allSong.size()-1);

                                int lastID = Integer.parseInt(lastSong.getSongID().substring(1)) + 1;

                                song.setSongID(String.format("S%03d", lastID));
                            }


                            song.setTitle(tag.getTitle());
                            song.setArtist(tag.getArtist());
                            song.setAlbumName(tag.getAlbum());
                            song.setYear(Integer.parseInt(tag.getYear()));
                            song.setLength((int)mp3.getLengthInSeconds());
                            song.setTrackNo(Integer.parseInt(tag.getTrack()));

                            List<Integer> genreList = new ArrayList<>();
                            genreList.add(tag.getGenre());
                            song.setGenre(genreList);

                            song.setFilePath(file.getAbsolutePath());
                            song.setImagePath("");

                            Date date = new Date(0);
                            song.setLastPlayed(date);

                            song.setCountPlayed(0);

                            song.display();
                            SongDAO.createSong(song);
                        }
                        catch(Exception e) {e.printStackTrace();}
                    }
                }
            }
        }
    }

    public static void importFile(File file){
        String name = file.getName();
        if(file.isFile() && name.endsWith("mp3")){
            try{
                Mp3File mp3 = new Mp3File(file.getPath());
                ID3v2 tag = mp3.getId3v2Tag();

                Song song = new Song();

                if(SongDAO.getAllSong().isEmpty()){
                    song.setSongID("S001");
                }else{
                    List<Song> allSong = SongDAO.getAllSong();
                    Song lastSong = allSong.get(allSong.size()-1);

                    int lastID = Integer.parseInt(lastSong.getSongID().substring(1)) + 1;

                    song.setSongID(String.format("S%03d", lastID));
                }


                song.setTitle(tag.getTitle());
                song.setArtist(tag.getArtist());
                song.setAlbumName(tag.getAlbum());
                song.setYear(Integer.parseInt(tag.getYear()));
                song.setLength((int)mp3.getLengthInSeconds());
                song.setTrackNo(Integer.parseInt(tag.getTrack()));

                List<Integer> genreList = new ArrayList<>();
                genreList.add(tag.getGenre());
                song.setGenre(genreList);

                song.setFilePath(file.getAbsolutePath());
                song.setImagePath("");

                Date date = new Date(0);
                song.setLastPlayed(date);

                song.setCountPlayed(0);

                song.display();
                SongDAO.createSong(song);
            }
            catch(Exception e) {e.printStackTrace();}
        }
    }
}
