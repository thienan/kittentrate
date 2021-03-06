package kittentrate.data.repository.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manuel Lorenzo on 23/03/2017.
 */

public class GameDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kittentrate.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT ";
    private static final String COMMA = ", ";

    private static final String SQL_CREATE_DB = "CREATE TABLE if not exists "
            + GameScoreDbContract.ScoreEntry.TABLE_NAME + " (" +
            GameScoreDbContract.ScoreEntry._ID + TEXT_TYPE + " PRIMARY KEY" + COMMA +
            GameScoreDbContract.ScoreEntry.COLUMN_NAME_PLAYER_NAME + TEXT_TYPE + COMMA +
            GameScoreDbContract.ScoreEntry.COLUMN_NAME_SCORE + " REAL " + ")";

    GameDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GameScoreDbContract.ScoreEntry.TABLE_NAME);
    }
}
