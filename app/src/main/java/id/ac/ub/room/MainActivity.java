package id.ac.ub.room;

import static id.ac.ub.room.AppExecutors.getInstance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button bt1,bt2;
    EditText et1,et2;
    RecyclerView rv1;
    public static String TAG = "RV1";
    private AppDatabase appDb;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDb = AppDatabase.getInstance(getApplicationContext());
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        rv1 = findViewById(R.id.rv1);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Item item = new Item();

                item.setNim(et1.getText().toString());
                item.setNama(et2.getText().toString());
                Toast.makeText(getApplicationContext(), "Data berhasil dimasukkan!",Toast.LENGTH_LONG).show();

                getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {

                        appDb.itemDao().insertAll(item);
                    }
                });
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                        public void run () {
                            ArrayList<Item> list = (ArrayList<Item>) appDb.itemDao().getAll();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    MahasiswaAdapter adapter = new MahasiswaAdapter(MainActivity.this, list);
                                    rv1.setAdapter(adapter);
                                }
                            });


                        }

                });

            }
        });
    }

}