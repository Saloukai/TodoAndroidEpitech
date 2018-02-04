package epitech.todolist;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


// Activité show_task
public class ShowTask extends FragmentActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    EditText editTitle, editDescription, dueDate;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_task);

        Intent intent = getIntent();
        Task task = (Task)intent.getSerializableExtra("task");
        id = task.getId();

        editTitle = (EditText)findViewById(R.id.editTitle);
        editDescription = (EditText)findViewById(R.id.editDescription);
        dueDate = (EditText)findViewById(R.id.dueDate);

        editTitle.setText(task.getTitle());
        editDescription.setText(task.getDescription());
        dueDate.setText(task.getDueDate());
    }

    public void updateTask(View v) {
        if (db.updateTask(id, editTitle.getText().toString(), editDescription.getText().toString(), dueDate.getText().toString(), false) == false) {
            Toast.makeText(ShowTask.this, "Error: not updated", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(ShowTask.this, "Task updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ShowTask.this, MainActivity.class);
            startActivity(intent);
            ShowTask.this.finish();
        }
    }

    public void deleteTask(View v) {
        if (db.deleteTask(id) > 0) {
            Toast.makeText(ShowTask.this, "Task deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ShowTask.this, MainActivity.class);
            startActivity(intent);
            ShowTask.this.finish();
        }
        else {
            Toast.makeText(ShowTask.this, "Error: not deleted", Toast.LENGTH_LONG).show();
        }
    }

    // Calendrier
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerTodo();
        newFragment.show(getFragmentManager(), "datePicker");
    }

}
