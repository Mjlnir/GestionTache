package fr.sts.gestiontaches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    int REQUEST_CODE_AJOUT = 1;
    int REQUEST_CODE_MODIF = 2;
    ArrayAdapter<String> lvAdaptateur;
    ListView lvTaches;
    long lastClickTime;
    int lastPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Mes tâches");
        lvTaches = (ListView) findViewById(R.id.lvTaches);

        // L'adaptateur adapte la vue (layout) et le modèle des éléments
        // Le layout pour visualiser une chaîne existe nativement dans Android
        // simple_list_item_1
        lvAdaptateur = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        lvTaches.setAdapter(lvAdaptateur);

        lvAdaptateur.add("Comprendre les Intents -> 16/11/2020");
        lvAdaptateur.add("Comprendre les ListViews -> 16/11/2020");
        lvAdaptateur.add("Faire le TP android -> 23/11/2020");
        
        lvTaches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                long currTime = System.currentTimeMillis();
                int currPosition = position;
                if (currTime - lastClickTime <= ViewConfiguration.getDoubleTapTimeout() && currPosition == lastPosition) {
                    onItemDoubleClick(adapterView, view, position, l);
                }
                lastClickTime = currTime;
                lastPosition = currPosition;
            }

            public void onItemDoubleClick(AdapterView<?> adapterView, View view, int position, long l) {
                String[] _arrTache = adapterView.getItemAtPosition(position).toString().trim().split("->");

                Intent i = new Intent(getBaseContext(), GestionTacheActivity.class);
                i.putExtra("nomTache", _arrTache[0]);
                i.putExtra("dateTache", _arrTache[1]);
                i.putExtra("position", position);
                startActivityForResult(i, REQUEST_CODE_MODIF);
            }
        });
    }

    public void AjoutTache(View view) {
        Intent i = new Intent(this, GestionTacheActivity.class);
        startActivityForResult(i, REQUEST_CODE_AJOUT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_AJOUT) {
            lvAdaptateur.add(data.getStringExtra("nomTache") + " -> " + data.getStringExtra("dateTache"));
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_MODIF) {
            String _tTache = lvAdaptateur.getItem(data.getIntExtra("position", 0));
            lvAdaptateur.remove(_tTache);
            _tTache = data.getStringExtra("nomTache") + " -> " + data.getStringExtra("dateTache");
            lvAdaptateur.add(_tTache);
            lvAdaptateur.notifyDataSetChanged();
        }
    }

    public void DeleteTache(View view) {
        lvAdaptateur.clear();
    }
}