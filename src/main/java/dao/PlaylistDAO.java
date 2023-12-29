package dao;
import com.mongodb.*;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import object.Playlist;

import java.util.List;
import java.util.ArrayList;

public class PlaylistDAO {
    public static MongoClient mongoClient;
    public static DB database;
    public static DBCollection collection;

    public PlaylistDAO() {
        mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://raflyarj:groovy@groovycluster.ejkhj6t.mongodb.net/?retryWrites=true&w=majority"));
        database = mongoClient.getDB(System.getProperty("user.name"));
        collection = database.getCollection(System.getProperty("user.name")+"-playlist");
    }

    public static List<Playlist> getAllPlaylist() {
        List<Playlist> playlists = new ArrayList<>();
        DBCursor cursor = collection.find();

        try {
            while (cursor.hasNext()) {
                DBObject document = cursor.next();
                Playlist playlist = convertDBObjectToPlaylist(document);
                playlists.add(playlist);
            }
        } finally {
            cursor.close();
        }

        return playlists;
    }

    public static Playlist getPlaylistById(String playlistId) {
        BasicDBObject query = new BasicDBObject("playlistID", playlistId);

        DBCursor cursor = collection.find(query);
        try {
            if (cursor.hasNext()) {
                return convertDBObjectToPlaylist(cursor.next());
            }
        } finally {
            cursor.close();
        }

        // Return null if no matching song is found
        return null;
    }

    public static void createSong(Playlist playlist) {
        BasicDBObject document = new BasicDBObject();
        document = convertPlaylistToDBObject(playlist);

        collection.insert(document);
    }

    public static void updatePlaylist(String playlistID, Playlist updatedPlaylist) {
        // Create a query to find the document with the given songID
        BasicDBObject query = new BasicDBObject("playlistID", playlistID);

        // Create an update document with the new values
        BasicDBObject update = new BasicDBObject();
        update.put("$set", convertPlaylistToDBObject(updatedPlaylist));

        // Update the document in the "song" collection
        collection.update(query, update);
    }

    public static void deletePlaylist(String playlistId) {
        // Create a query to find the document with the given songID
        BasicDBObject query = new BasicDBObject("playlistID", playlistId);

        // Delete the document from the "song" collection
        collection.remove(query);
    }

    public static void deleteSongFromPlaylist(String playlistId, String songId) {
        BasicDBObject query = new BasicDBObject("playlistID", playlistId);
        BasicDBObject update = new BasicDBObject("$pull", new BasicDBObject("songList", songId));

        // Update the document in the "playlist" collection
        collection.update(query, update);
    }


    private static Playlist convertDBObjectToPlaylist(DBObject document) {
        Playlist playlist = new Playlist();

        playlist.setPlaylistID((String) document.get("playlistID"));
        playlist.setName((String) document.get("name"));
        playlist.setDesc((String) document.get("desc"));
        List<String> songList = (List<String>) document.get("songList");
        playlist.setSongList(songList);
        playlist.setCoverPath((String) document.get("coverPath"));

        return playlist;
    }

    private static BasicDBObject convertPlaylistToDBObject(Playlist playlist) {
        BasicDBObject document = new BasicDBObject();

        document.put("playlistID", playlist.getPlaylistID());
        document.put("name", playlist.getName());
        document.put("desc", playlist.getDesc());
        document.put("songList", playlist.getSongList());
        document.put("coverPath", playlist.getCoverPath());

        return document;
    }
}

