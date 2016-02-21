package org.fossasia.openevent.dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.fossasia.openevent.data.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: championswimmer
 * Date: 17/5/15
 */
public class DbSingleton {

    private static DbSingleton mInstance;

    private static Context mContext;

    private static DbHelper mDbHelper;

    private SQLiteDatabase mDb;

    private DatabaseOperations databaseOperations = new DatabaseOperations();

    // visible for testing
    public DbSingleton(Context context) {
        mContext = context;
        mDbHelper = new DbHelper(mContext);

    }

    /**
     * Only Exposed for testing purposes, either way Singletons suck.
     *
     * @param mDb     the readable/writable database
     * @param context A sane context (Application context please)
     * @param helper  A DB Helper
     */
    public DbSingleton(SQLiteDatabase mDb, Context context, DbHelper helper) {
        this.mDb = mDb;
        mContext = context;
        mDbHelper = helper;
    }

    public static void init(Context context) {
        if (mInstance == null) {
            mInstance = new DbSingleton(context);
        }
    }

    public static DbSingleton getInstance() {
        return mInstance;
    }

    private void getReadOnlyDatabase() {
        if ((mDb == null) || (!mDb.isReadOnly())) {
            mDb = mDbHelper.getReadableDatabase();
        }
    }

    public ArrayList<Session> getSessionList() {
        getReadOnlyDatabase();
        return databaseOperations.getSessionList(mDb);

    }

    public Event getEventDetails() {
        getReadOnlyDatabase();
        return databaseOperations.getEventDetails(mDb);
    }

    public Session getSessionById(int id) {
        getReadOnlyDatabase();
        return databaseOperations.getSessionById(id, mDb);
    }

    public List<Speaker> getSpeakerList() {
        getReadOnlyDatabase();
        return databaseOperations.getSpeakerList(mDb);

    }

    public Version getVersionIds() {
        getReadOnlyDatabase();
        return databaseOperations.getVersionIds(mDb);
    }

    public void addBookmarks(int bookmarkId) {
        databaseOperations.addBookmarksToDb(bookmarkId);
    }

    public void deleteBookmarks(int bookmarkId) {
        databaseOperations.deleteBookmarks(bookmarkId, mDb);
    }

    public Speaker getSpeakerById(int id) {
        getReadOnlyDatabase();
        return databaseOperations.getSpeakerById(id, mDb);
    }

    public List<Track> getTrackList() {
        getReadOnlyDatabase();
        return databaseOperations.getTrackList(mDb);
    }


    public ArrayList<Sponsor> getSponsorList() {
        getReadOnlyDatabase();
        return databaseOperations.getSponsorList(mDb);
    }

    public ArrayList<Microlocation> getMicrolocationsList() {
        getReadOnlyDatabase();
        return databaseOperations.getMicrolocationsList(mDb);
    }

    public Microlocation getMicrolocationById(int id) {
        getReadOnlyDatabase();
        return databaseOperations.getMicroLocationById(id, mDb);
    }

    public ArrayList<Session> getSessionbyTracksname(String trackName) {
        return databaseOperations.getSessionbyTracksname(trackName, mDb);
    }

    public ArrayList<Session> getSessionbySpeakersName(String speakerName) {
        return databaseOperations.getSessionbySpeakersname(speakerName, mDb);
    }

    public ArrayList<Session> getSessionbyLocationName(String locationName) {
        return databaseOperations.getSessionbyLocationname(locationName, mDb);
    }

    public ArrayList<Session> getSessionbySearchTextAndTrack(String searchText, int trackId) {
        return databaseOperations.getSessionbySearchTextAndTrack(searchText, trackId, mDb);
    }

    public ArrayList<Speaker> getSpeakersbySessionName(String sessionName) {
        getReadOnlyDatabase();
        return databaseOperations.getSpeakersbySessionName(sessionName, mDb);
    }


    public Track getTrackbyName(String trackName) {
        return databaseOperations.getTracksbyTracksname(trackName, mDb);
    }

    public Speaker getSpeakerbySpeakersname(String speakerName) {
        return databaseOperations.getSpeakerbySpeakersname(speakerName, mDb);
    }

    public Session getSessionbySessionname(String sessionName) {
        return databaseOperations.getSessionbySessionname(sessionName, mDb);
    }

    public boolean isBookmarked(int sessionId) {
        return databaseOperations.isBookmarked(sessionId, mDb);
    }

    public ArrayList<Integer> getBookmarkIds() throws ParseException {

        getReadOnlyDatabase();
        return databaseOperations.getBookmarkIds(mDb);
    }

    public Microlocation getLocationByLocationname(String LocationName) {
        return databaseOperations.getLocationByName(LocationName, mDb);
    }

    public void insertQueries(ArrayList<String> queries) {
        databaseOperations.insertQueries(queries, mDbHelper);
    }

    public void insertQuery(String query) {
        databaseOperations.insertQuery(query, mDbHelper);
    }

    public void clearDatabase(String table) {
        databaseOperations.clearDatabaseTable(table, mDbHelper);
    }

    public void deleteAllRecords(String tableName) {
        databaseOperations.deleteAllRecords(tableName, mDb);
    }

}
