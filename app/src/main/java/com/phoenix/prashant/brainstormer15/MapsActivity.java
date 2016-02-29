package com.phoenix.prashant.brainstormer15;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity  implements GoogleMap.OnMapClickListener {



    private GoogleMap map;
    LatLng nowhere = new LatLng(22.5176286,88.335355);
    static final CameraPosition SYDNEY = new CameraPosition.Builder()
            .target(new LatLng(22.5176286,88.335355)).zoom(15.5f).bearing(0)
            .tilt(25).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        try {
            if (map == null)
                map = ((SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map)).getMap();
            LatLng sydney = new LatLng(22.5176286,88.335355);

            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(nowhere, 13));

            map.addMarker(
                    new MarkerOptions().rotation(5).title("Brainstormer")
                            .snippet("Ahindra Manch,Kolkata").position(sydney))
                    .setFlat(true);
            map.setOnMapClickListener(this);
        } catch (OutOfMemoryError e) {

        }
    }

    private void changeCamera(CameraUpdate update, GoogleMap.CancelableCallback callback) {

        map.animateCamera(update, Math.max(5000, 1), callback);

    }

    @Override
    public void onMapClick(LatLng arg0) {
        // TODO Auto-generated method stub
        changeCamera(CameraUpdateFactory.newCameraPosition(SYDNEY),
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        Toast.makeText(getBaseContext(), "Welcome to Brainstormer 2015 !!",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {

}
});
        }
}