package sg.edu.rp.c346.to_do;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnAdd, btnClear, btnDelete;
    ListView lv;
    ArrayList<String> al;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editTextMovie);
        btnAdd = findViewById(R.id.buttonADD);
        btnDelete = findViewById(R.id.buttonDELETE);
        btnClear = findViewById(R.id.buttonCLEAR);
        lv = findViewById(R.id.listView);
        spn = findViewById(R.id.spinner);

        al = new ArrayList<String>();

        final ArrayAdapter<String> al2 = new ArrayAdapter<String>( MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(al2);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et.getText().toString();
                    al.add(text);
                    al2.notifyDataSetChanged();
                    et.setText("");

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al.clear();
                al2.notifyDataSetChanged();
                et.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    return;
                }
                int pos = Integer.parseInt(et.getText().toString());
                if(pos > al.size()){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    return;
                }
                    al.remove(pos);
                    al2.notifyDataSetChanged();
                    et.setText("");

            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        et.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        et.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
