package divas.dazzle.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

import divas.dazzle.Globals.Globals;

/**
 * Created by Wiseman on 2018-03-25.
 */

public class Sensor extends Service implements LocationListener{
    static Context context;
    static RequestQueue requestQueue;
    private LocationManager mlocManager;
    private DatabaseReference mdatabaseRef;
    public Sensor(Context context)
    {
        super();
        this.context = context;
       // mdatabaseRef =  FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseInstanceId.getInstance().getToken());
    }
    public Sensor()
    {

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        context = this;
        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            Intent i = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           try
           {
               context.startActivity(i);
           }
           catch (Exception e)
           {

           }

        }
        try
        {
            mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,this);
        }
        catch(SecurityException e)
        {

        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent("RestartService");
        sendBroadcast(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        requestQueue = Volley.newRequestQueue(this.context);
        double latitude=location.getLatitude();
        double longitude=location.getLongitude();
        updateCoordinates(FirebaseInstanceId.getInstance().getToken()+"",latitude+"",longitude+"");
    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }
    public void updateCoordinates(final String Token, final String Latitude, final String Longitude) {

        StringRequest request = new StringRequest(Request.Method.POST, Globals.UPDATE_MAP_COORDINATES_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put(Globals.TOKEN, Token);
                parameters.put(Globals.LATITUDE, Latitude);
                parameters.put(Globals.LONGITUDE, Longitude);
                return parameters;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
}
