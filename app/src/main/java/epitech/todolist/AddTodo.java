package epitech.todolist;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


// Activité Add_Todo
public class AddTodo extends FragmentActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    EditText title, description, dueDate;
    Button btnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo);

        title = (EditText)findViewById(R.id.editTitle);
        description = (EditText)findViewById(R.id.editDescription);
        dueDate = (EditText)findViewById(R.id.dueDate);
        btnAddTask = (Button)findViewById(R.id.buttonConfirm);

        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });

        addData();
    }

    // Calendrier
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerTodo();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    // Ajouter la tâche à la bdd
    public void addData() {
        btnAddTask.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (title.getText().toString().length() == 0 ||
                                description.getText().toString().length() == 0 ||
                                dueDate.getText().toString().length() == 0)
                        {
                            Toast.makeText(AddTodo.this, "You must put something in the text fields", Toast.LENGTH_LONG).show();
                            return ;
                        }

                        boolean isInserted = db.insertData(title.getText().toString(), description.getText().toString(), dueDate.getText().toString(), false);

                        if (isInserted == true) {
                            Toast.makeText(AddTodo.this, "Task added", Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(AddTodo.this, MainActivity.class);
                            startActivity(myIntent);
                            AddTodo.this.finish();
                        }
                        else
                            Toast.makeText(AddTodo.this, "Error: Task not added", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
