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
    static int duplicateCount = 0;
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

    public static String importFolder(String filePath){
        String status = "";
        int jumlahLagu = 0;

        System.out.println("Handling selected folder: " + filePath);
        if(!Objects.equals(filePath, "")){
            File directory = new File(filePath);
            File[] files = directory.listFiles();

            if(files != null){

                for(File file : files){
                    String name = file.getName();

                    if(file.isFile() && name.endsWith("mp3")){
                        jumlahLagu++;
                        status = importFile(file);
                        if (status.equals("duplicated_song")){
                            duplicateCount++;
                        }
                    }
                }
            }
        }

        if (jumlahLagu == 0){
            status = "no_song_found_in_folder";
        }

        return status;
    }

    public static String importDragAndDrop(List<File> droppedFiles) {
        String status = "";

        for (File file : droppedFiles) {
            String name = file.getName();
            if (file.isFile() && !name.endsWith("mp3")) {
                status = "format_not_supported";
                return status;
            }
        }

        for (File file : droppedFiles) {
            System.out.println("Handling dropped file: " + file.getAbsolutePath());
            status = importFile(file);
            if (status.equals("duplicated_song")){
                duplicateCount++;
            }
        }

        return status;
    }

    public static String importFile(File file){
        String status;
        try{
            Mp3File mp3 = new Mp3File(file.getPath());
            ID3v2 tag = mp3.getId3v2Tag();

            Song song = new Song();

            if(SongDAO.getAllSong().isEmpty()){
                song.setSongID("S001");
            }else{
                List<Song> allSong = SongDAO.getAllSong();

                for (Song songInAllSong : allSong){
                    if (songInAllSong.getTitle().equals(tag.getTitle()) && songInAllSong.getArtist().equals(tag.getArtist())){
                        status = "duplicated_song";
                        return status;
                    }
                }

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
            status = "import_success";

        } catch(Exception e) {
            e.printStackTrace();
            status = "import_failure";
            return status;
        }
        return status;
    }
}
