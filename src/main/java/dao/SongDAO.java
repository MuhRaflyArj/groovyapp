package dao;
import com.mongodb.*;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import object.Song;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {
    public static MongoClient mongoClient;
    public static DB database;
    public static DBCollection collection;

    public SongDAO() {
        mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://raflyarj:groovy@groovycluster.ejkhj6t.mongodb.net/?retryWrites=true&w=majority"));
        database = mongoClient.getDB("groovy");
        collection = database.getCollection("song");
    }

    public static List<Song> getAllSong() {
        List<Song> songs = new ArrayList<>();
        DBCursor cursor = collection.find();

        try {
            while (cursor.hasNext()) {
                DBObject document = cursor.next();
                Song song = convertDBObjectToSong(document);
                songs.add(song);
            }
        } finally {
            cursor.close();
        }

        return songs;
    }

    public static Song getSongById(String songId) {
        BasicDBObject query = new BasicDBObject("songID", songId);

        DBCursor cursor = collection.find(query);
        try {
            if (cursor.hasNext()) {
                return convertDBObjectToSong(cursor.next());
            }
        } finally {
            cursor.close();
        }

        // Return null if no matching song is found
        return null;
    }

    public static List<Song> recentlyPlayed(int limit) {
        BasicDBObject query = new BasicDBObject();
        BasicDBObject sort = new BasicDBObject("lastPlayed", -1); // Sort by lastPlayed in descending order

        DBCursor cursor = collection.find(query).sort(sort).limit(limit);
        List<Song> recentSongs = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                recentSongs.add(convertDBObjectToSong(dbObject));
            }
        } finally {
            cursor.close();
        }

        return recentSongs;
    }

    public static void createSong(Song song) {
        BasicDBObject document = new BasicDBObject();
        document = convertSongToDBObject(song);

        collection.insert(document);
    }

    public static void updateSong(String songId, Song updatedSong) {
        // Create a query to find the document with the given songID
        BasicDBObject query = new BasicDBObject("songID", songId);

        // Create an update document with the new values
        BasicDBObject update = new BasicDBObject();
        update.put("$set", convertSongToDBObject(updatedSong));

        // Update the document in the "song" collection
        collection.update(query, update);
    }

    public static List<Song> getMostPlayedSongs(int limit) {
        BasicDBObject query = new BasicDBObject();
        BasicDBObject sort = new BasicDBObject("countPlayed", -1); // Sort by countPlayed in descending order

        DBCursor cursor = collection.find(query).sort(sort).limit(limit);
        List<Song> mostPlayedSongs = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                mostPlayedSongs.add(convertDBObjectToSong(dbObject));
            }
        } finally {
            cursor.close();
        }

        return mostPlayedSongs;
    }

    public static void deleteSong(String songId) {
        // Create a query to find the document with the given songID
        BasicDBObject query = new BasicDBObject("songID", songId);

        // Delete the document from the "song" collection
        collection.remove(query);
    }

    public static List<Song> searchSong(String substring) {
        BasicDBObject query = new BasicDBObject("$or",
                new BasicDBObject[] {
                        new BasicDBObject("title", new BasicDBObject("$regex", substring).append("$options", "i")),
                        new BasicDBObject("albumName", new BasicDBObject("$regex", substring).append("$options", "i")),
                        new BasicDBObject("artist", new BasicDBObject("$regex", substring).append("$options", "i"))
                }
        );

        DBCursor cursor = collection.find(query);
        List<Song> matchingSongs = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                matchingSongs.add(convertDBObjectToSong(dbObject));
            }
        } finally {
            cursor.close();
        }

        return matchingSongs;
    }

    private static Song convertDBObjectToSong(DBObject document) {
        Song song = new Song();
        song.setSongID((String) document.get("songID"));
        song.setTitle((String) document.get("title"));
        song.setArtist((String) document.get("artist"));
        song.setAlbumName((String) document.get("albumName"));
        song.setYear((int) document.get("year"));
        song.setLength((int) document.get("length"));
        song.setTrackNo((int) document.get("trackNo"));
        List<Integer> genreList = (ArrayList<Integer>) document.get("genre");
        song.setGenre(genreList);
        song.setFilePath((String) document.get("filePath"));
        song.setImagePath((String) document.get("imagePath"));
        song.setLastPlayed((Date) document.get("lastPlayed"));
        song.setCountPlayed((int) document.get("countPlayed"));

        return song;
    }

    private static BasicDBObject convertSongToDBObject(Song song) {
        BasicDBObject document = new BasicDBObject();
        document.put("songID", song.getSongID());
        document.put("title", song.getTitle());
        document.put("artist", song.getArtist());
        document.put("albumName", song.getAlbumName());
        document.put("year", song.getYear());
        document.put("length", song.getLength());
        document.put("trackNo", song.getTrackNo());

        document.put("genre", song.getGenre());
        document.put("filePath", song.getFilePath());
        document.put("imagePath", song.getImagePath());
        document.put("lastPlayed", song.getLastPlayed());
        document.put("countPlayed", song.getCountPlayed());

        return document;
    }

}
