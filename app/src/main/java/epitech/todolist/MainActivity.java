package epitech.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    Cursor res;
    ArrayList<Task> listTask = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Button to get activity addTask
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start addTodo.class
                Intent intentAdd = new Intent(MainActivity.this,
                        AddTodo.class);
                startActivity(intentAdd);
            }
        });

        // Afficher toutes les taches
        listView = (ListView)findViewById(R.id.list_task);
        getTasks();
        CustomAdaptor customAdaptor = new CustomAdaptor();
        listView.setAdapter(customAdaptor);

    }

    // get boolean of state
    public boolean getStateBool(int state) {
        return (state == 1) ? true : false;
    }

    // Fill array listTask from db
    public void getTasks() {
        res = db.getAllTask();

        while (res.moveToNext()) {
            listTask.add(new Task(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), getStateBool(res.getInt(4))));
        }
    }

    // ListView "complexe" pour inclure la checkbox
    class CustomAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return db.countTask();
        }

        @Override
        public Task getItem(int i) {
            return listTask.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final Task task = listTask.get(i);

            view = getLayoutInflater().inflate(R.layout.item_list, null);
            TextView title = (TextView)view.findViewById(R.id.title);
            final CheckBox done = (CheckBox)view.findViewById(R.id.done);

            title.setText(task.getTitle());
            done.setChecked(task.getState());

            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (done.isChecked()){
                        db.updateTask(task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), true);
                    }
                    else {
                        db.updateTask(task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), false);
                    }
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ShowTask.class);
                    intent.putExtra("task", task);
                    startActivity(intent);
                }
            });
            return view;
        }
    }

}
