package fr.sts.gestiontaches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    int REQUEST_CODE = 1;
    ArrayAdapter<String> lvAdaptateur;
    ListView lvTaches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Mes tâches");
        lvTaches = (ListView)findViewById(R.id.lvTaches);
        
        // L'adaptateur adapte la vue (layout) et le modèle des éléments
        // Le layout pour visualiser une chaîne existe nativement dans Android
        // simple_list_item_1
        lvAdaptateur =  new ArrayAdapter<String>(this ,
                android.R.layout.simple_list_item_1);
        lvTaches.setAdapter( lvAdaptateur );

        lvAdaptateur.add("Comprendre les Intents -> 16/11/2020");
        lvAdaptateur.add("Comprendre les ListViews -> 16/11/2020");
        lvAdaptateur.add("Faire le TP android -> 23/11/2020");

    }

    public void AjoutTache(View view) {
        Intent i = new Intent(this, GestionTacheActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }
}