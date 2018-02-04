package epitech.todolist;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Lucas Thevenard on 02/02/2018.
 */

public class Task implements Serializable {
    private int _id;
    private String _title;
    private String _description;
    private String _due_date;
    private boolean _state;

    public Task(int id, String title, String description, String due_date, boolean state) {
        this._id = id;
        this._title = title;
        this._description = description;
        this._due_date = due_date;
        this._state = state;
    }

    public int getId() {
        return this._id;
    }

    public String getTitle() {
        return this._title;
    }

    public String getDescription() {
        return this._description;
    }

    public String getDueDate() {
        return this._due_date;
    }

    public boolean getState() {
        return this._state;
    }

    @Override
    public String toString() {
        return "Task [id="+_id+", title="+_title+", description="+_description+", dueDate="+_due_date+", state="+_state+"]";
    }
}
