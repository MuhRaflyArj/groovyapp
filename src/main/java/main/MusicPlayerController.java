package main;

import dao.PlaylistDAO;
import dao.SongDAO;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import object.Playlist;
import object.Song;

import java.time.Duration;
import java.util.*;

public class MusicPlayerController {
    private static boolean isShuffled = false;
    private static boolean isRepeated = false;
    private static boolean isPlaying = false;
    private static boolean isMuted = false;
    private static Media media;
    private static MediaPlayer mediaPlayer;
    public static Song currentSong;
    public static Playlist currentPlaylist;
    public static void display(BorderPane root, Song playedSong) {
        MusicPlayerView.setRoot(root);
        MusicPlayerView.display(playedSong);
//        MusicPlayerController.play(PlaylistDAO.getPlaylistById("P004"), SongDAO.getSongById("S010"));
    }

    public static void setIsShuffled(boolean shuffleStatus) {
        if(shuffleStatus) {
            MusicPlayerController.isShuffled = true;
            MusicPlayerController.isRepeated = false;
        } else if (!shuffleStatus) {
            MusicPlayerController.isShuffled = false;
        }

    }
    public static void setIsRepeated(boolean repeatStatus) {
        if(repeatStatus) {
            MusicPlayerController.isRepeated = true;
            MusicPlayerController.isShuffled = false;
        } else if (!repeatStatus) {
            MusicPlayerController.isRepeated = false;
        }
    }
    public static void setIsPlaying(boolean playingStatus) {
        if (!playingStatus) {
            MusicPlayerController.isPlaying = !MusicPlayerController.isPlaying;
            MusicPlayerController.mediaPlayer.pause();
        } else {
            MusicPlayerController.isPlaying = !MusicPlayerController.isPlaying;
            MusicPlayerController.mediaPlayer.play();
        }

    }
    public static void setIsMuted() {
        if (MusicPlayerController.isMuted) {
            MusicPlayerController.isMuted = !MusicPlayerController.isMuted;
            MusicPlayerController.mediaPlayer.setMute(false);
        } else {
            MusicPlayerController.isMuted = !MusicPlayerController.isMuted;
            MusicPlayerController.mediaPlayer.setMute(true);
        }

    }

    public static void play(Playlist currentPlaylist, Song currentSong) {
        MusicPlayerController.currentPlaylist = currentPlaylist;
        MusicPlayerController.currentSong = currentSong;


        String audioFilePath = currentSong.getFilePath();

        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("mac")) {
            MusicPlayerController.media = new Media("file://" + audioFilePath.replace(" ", "%20"));
        } else if (osName.contains("win")) {
            audioFilePath = audioFilePath.replace("\\", "/");
            MusicPlayerController.media = new Media("file:///" + audioFilePath.replace(" ", "%20"));
        }

        if (MusicPlayerController.mediaPlayer != null) {
            MusicPlayerController.mediaPlayer.stop();
        }
        MusicPlayerController.mediaPlayer = new MediaPlayer(media);
        MusicPlayerController.mediaPlayer.play();
        MusicPlayerView.setVolumeSlider(mediaPlayer.getVolume());
        MusicPlayerView.display(currentSong);
        MusicPlayerView.togglePlayIcon(true);

        MusicPlayerController.mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            MusicPlayerView.setSongSeeker(newValue.toSeconds());
        });

        currentSong.setCountPlayed(currentSong.getCountPlayed()+1);
        currentSong.setLastPlayed(new Date());

        SongDAO.updateSong(currentSong.getSongID(), currentSong);
    }

    public static void next() {
        if (isRepeated) {
            play(MusicPlayerController.currentPlaylist, MusicPlayerController.currentSong);
        } else if (isShuffled) {
            List<String> songList = MusicPlayerController.currentPlaylist.getSongList();
            Random random = new Random();
            String choosenSong = songList.get(random.nextInt(songList.size()));

            play(MusicPlayerController.currentPlaylist, SongDAO.getSongById(choosenSong));
        } else {
            List<String> songList = MusicPlayerController.currentPlaylist.getSongList();
            String currentSongID = MusicPlayerController.currentSong.getSongID();
            int currentSongIndex = songList.indexOf(currentSongID);

            if (currentSongIndex == songList.size() - 1) {
                String nextSong = songList.get(0);
                play(MusicPlayerController.currentPlaylist, SongDAO.getSongById(nextSong));
            } else {
                String nextSong = songList.get(currentSongIndex + 1);
                play(MusicPlayerController.currentPlaylist, SongDAO.getSongById(nextSong));
            }
        }
    }

    public static void prev() {
        if (isRepeated) {
            play(MusicPlayerController.currentPlaylist, MusicPlayerController.currentSong);
        } else if (isShuffled) {
            List<String> songList = MusicPlayerController.currentPlaylist.getSongList();
            Random random = new Random();
            String choosenSong = songList.get(random.nextInt(songList.size()));

            play(MusicPlayerController.currentPlaylist, SongDAO.getSongById(choosenSong));
        } else {
            List<String> songList = MusicPlayerController.currentPlaylist.getSongList();
            String currentSongID = MusicPlayerController.currentSong.getSongID();
            int currentSongIndex = songList.indexOf(currentSongID);

            if (currentSongIndex == 0) {
                String nextSong = songList.get(songList.size() - 1);
                play(MusicPlayerController.currentPlaylist, SongDAO.getSongById(nextSong));
            } else {
                String nextSong = songList.get(currentSongIndex - 1);
                play(MusicPlayerController.currentPlaylist, SongDAO.getSongById(nextSong));
            }
        }


    }
    public static void seekSong(Number newValue) {
        MusicPlayerController.mediaPlayer.seek(javafx.util.Duration.seconds(newValue.doubleValue()));
    }

    public static void setVolume(double level) {
        mediaPlayer.setVolume(level);
    }


}
