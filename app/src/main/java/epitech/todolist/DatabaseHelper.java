package epitech.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lucas Thevenard on 31/01/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String database_name = "Todo.db";
    public static final String table_name = "task";
    public static final String col_1 = "ID";
    public static final String col_2 = "TITLE";
    public static final String col_3 = "DESCRIPTION";
    public static final String col_4 = "DUE_DATE";
    public static final String col_5 = "STATE";

    public DatabaseHelper(Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT, DUE_DATE TEXT, STATE BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table_name);
    }

    public boolean insertData(String title, String description, String due_date, Boolean state) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, title);
        contentValues.put(col_3, description);
        contentValues.put(col_4, due_date);
        contentValues.put(col_5, state);
        if (sqLiteDatabase.insert(table_name, null, contentValues) == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllTask() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM "+ table_name, null);
        return res;
    }

    public boolean updateTask(int id, String title, String description, String due_date, boolean state) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, title);
        contentValues.put(col_3, description);
        contentValues.put(col_4, due_date);
        contentValues.put(col_5, state);
        if (sqLiteDatabase.update(table_name, contentValues, "ID = ?", new String[] {Integer.toString(id)}) > 0)
            return true;
        else
            return false;
    }

    public Integer deleteTask(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(table_name, "ID = ?", new String[] {Integer.toString(id)});
    }

    public int countTask() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, table_name);
        return (int)count;
    }
}
