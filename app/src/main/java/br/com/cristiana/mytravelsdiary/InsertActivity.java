package br.com.cristiana.mytravelsdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cristiana.mytravelsdiary.dao.TravelDAO;
import br.com.cristiana.mytravelsdiary.model.Travel;

public class InsertActivity extends AppCompatActivity {

    String destiny, departureDate, returnDate, touristHotspots, value, hotel, days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }

    public void create(View v) {
        TravelDAO dao = new TravelDAO(this);

        destiny = ((TextInputLayout) findViewById(R.id.tilDestiny)).getEditText().getText().toString();
        days = ((TextInputLayout) findViewById(R.id.tilDays)).getEditText().getText().toString();
        value = ((TextInputLayout) findViewById(R.id.tilValue)).getEditText().getText().toString();
        departureDate = ((TextInputLayout) findViewById(R.id.tilDepartureDate)).getEditText().getText().toString();
        returnDate = ((TextInputLayout) findViewById(R.id.tilReturnDate)).getEditText().getText().toString();
        hotel = ((TextInputLayout) findViewById(R.id.tilHotel)).getEditText().getText().toString();
        touristHotspots = ((TextInputLayout) findViewById(R.id.tilTouristHotspots)).getEditText().getText().toString();

        boolean error = validation();

        if (error) {
            return;
        } else {
            try {

                Travel travel = new Travel(destiny, days, value,
                        departureDate, returnDate, hotel, touristHotspots);

                dao.add(travel);

                Toast.makeText(this, getString(R.string.message_create), Toast.LENGTH_LONG).show();
                finish();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validation(){

        boolean erro = false;

        EditText edtDestiny = (EditText) findViewById(R.id.edtDestiny);
        EditText edtDays = (EditText) findViewById(R.id.edtDays);
        EditText edtValue = (EditText) findViewById(R.id.edtValue);
        EditText edtDepartureDate = (EditText) findViewById(R.id.edtDepartureDate);
        EditText edtReturnDate = (EditText) findViewById(R.id.edtReturnDate);
        EditText edtHotel = (EditText) findViewById(R.id.edtHotel);
        EditText edtTouristHotspots = (EditText) findViewById(R.id.edtTouristHotspots);



        if(TextUtils.isEmpty(destiny)) {
            edtDestiny.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(days)) {
            edtDays.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(value)) {
            edtValue.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(departureDate)) {
            edtDepartureDate.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(returnDate)) {
            edtReturnDate.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(hotel)) {
            edtHotel.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(touristHotspots)) {
            edtTouristHotspots.setError(getString(R.string.app_null));
            erro = true;
        }

        return erro;
    }
}
