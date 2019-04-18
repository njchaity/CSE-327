package helpmom;


import android.os.AsyncTask;

import android.util.Log;


import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.JSONObject;


import java.util.HashMap;
import java.util.List;


public class GetNearbyPlacesData extends AsyncTask<Object, String, String> 
{

    String googlePlacesData;
   
 GoogleMap mMap;
    
String url;

   
 @Override
    
protected String doInBackground(Object... params) {
       
 try {
            Log.d("GetNearbyPlacesData", "doInBackground entered");
            
mMap = (GoogleMap) params[0];
            
url = (String) params[1];
            
DownloadUrl downloadUrl = new DownloadUrl();
           
 googlePlacesData = downloadUrl.readUrl(url);
            
Log.d("GooglePlacesReadTask", "doInBackground Exit");
        } 
catch (Exception e) {
            Log.d("GooglePlacesReadTask", e.toString());
       
 }
        
return googlePlacesData;
    }

 
  