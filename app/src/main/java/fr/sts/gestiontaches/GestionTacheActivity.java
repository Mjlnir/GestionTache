package fr.sts.gestiontaches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GestionTacheActivity extends AppCompatActivity {

    EditText etNomTache;
    DatePicker dpDateTache;
    int iPositionTache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_tache);

        etNomTache = (EditText) findViewById(R.id.etNomTache);
        dpDateTache = (DatePicker) findViewById(R.id.dpDate);

        String _sNomTache = getIntent().getStringExtra("nomTache");
        String _sDateTache = getIntent().getStringExtra("dateTache");
        iPositionTache = getIntent().getIntExtra("position", 0);

        if (_sNomTache != "" && _sDateTache != null) {
            String[] _arrDateTache = _sDateTache.trim().split("/");
            etNomTache.setText(_sNomTache);
            try {
                //Calendar month is 0 based
                dpDateTache.updateDate(Integer.parseInt(_arrDateTache[2]), Integer.parseInt(_arrDateTache[1]) - 1, Integer.parseInt(_arrDateTache[0]));
            } catch (Exception e) {
                String _sErrorMessage = e.getMessage();
            }
        }
    }

    public void CMTache(View view) {
        etNomTache = (EditText) findViewById(R.id.etNomTache);
        dpDateTache = (DatePicker) findViewById(R.id.dpDate);
        String dateString = dpDateTache.getDayOfMonth() + "/" + (dpDateTache.getMonth() + 1) + "/" + dpDateTache.getYear();


        Intent i = new Intent();
        i.putExtra("nomTache", etNomTache.getText().toString());
        i.putExtra("dateTache", dateString);
        i.putExtra("position", iPositionTache);
        setResult(this.RESULT_OK, i);
        finish();
    }

    public void AnnulerTache(View view) {
        Intent i = new Intent();
        setResult(this.RESULT_CANCELED, i);
        finish();
    }
}