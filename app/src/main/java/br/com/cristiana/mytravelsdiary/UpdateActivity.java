package br.com.cristiana.mytravelsdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.cristiana.mytravelsdiary.dao.TravelDAO;
import br.com.cristiana.mytravelsdiary.model.Travel;
import br.com.cristiana.mytravelsdiary.utils.Constantes;

public class UpdateActivity extends AppCompatActivity {

    TextView tilDestinyU, tilDaysU, tilValueU, tilDepartureDateU, tilReturnDateU,  tilHotelU, tilTouristHotspotsU;
    Travel travel = new Travel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        tilDestinyU = (TextView) findViewById(R.id.tilDestinyU);
        tilDaysU = (TextView) findViewById(R.id.tilDaysU);
        tilValueU = (TextView) findViewById(R.id.tilValueU);
        tilDepartureDateU = (TextView) findViewById(R.id.tilDepartureDateU);
        tilReturnDateU = (TextView) findViewById(R.id.tilReturnDateU);
        tilHotelU = (TextView) findViewById(R.id.tilHotelU);
        tilTouristHotspotsU = (TextView) findViewById(R.id.tilTouristHotspotsU);

        if (getIntent() != null){
            travel = getIntent().getParcelableExtra(Constantes.KEY_TRAVEL);

            tilDestinyU.setText(travel.getDestiny());
            tilDaysU.setText(travel.getDays());
            tilValueU.setText(travel.getValue());
            tilDepartureDateU.setText(travel.getDepartureDate());
            tilReturnDateU.setText(travel.getReturnDate());
            tilHotelU.setText(travel.getHotel());
            tilTouristHotspotsU.setText(travel.getTouristsHotspots());
        }

    }


    public  void update(View v){

        boolean error = validation();

        if (error){
            return;
        }else {

            try {
                TravelDAO dao = new TravelDAO(this);

                travel.setDestiny(tilDestinyU.getText().toString());
                travel.setDays(tilDaysU.getText().toString());
                travel.setValue(tilValueU.getText().toString());
                travel.setDepartureDate(tilDepartureDateU.getText().toString());
                travel.setReturnDate(tilReturnDateU.getText().toString());
                travel.setHotel(tilHotelU.getText().toString());
                travel.setTouristsHotspots(tilTouristHotspotsU.getText().toString());

                dao.updateTravel(travel);

                Toast.makeText(this, getString(R.string.message_update), Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);

                finish();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validation(){

        boolean erro = false;

        String destiny = tilDestinyU.getText().toString();
        String days = tilDaysU.getText().toString();
        String value = tilValueU.getText().toString();
        String departureDate = tilDepartureDateU.getText().toString();
        String returnDate = tilReturnDateU.getText().toString();
        String hotel = tilHotelU.getText().toString();
        String touristHotspots = tilTouristHotspotsU.getText().toString();


        if(TextUtils.isEmpty(destiny)) {
            tilDestinyU.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(days)) {
            tilDaysU.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(value)) {
            tilValueU.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(departureDate)) {
            tilDepartureDateU.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(returnDate)) {
            tilReturnDateU.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(hotel)) {
            tilHotelU.setError(getString(R.string.app_null));
            erro = true;
        } else if(TextUtils.isEmpty(touristHotspots)) {
            tilTouristHotspotsU.setError(getString(R.string.app_null));
            erro = true;
        }

        return erro;
    }

}
