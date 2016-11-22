package com.software2_ucc.proy2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Establecer extends AppCompatActivity {

    TextView Long,Lat,Dir;Double LatG,LonG;String DirG;
    public Double LatEst,LonEst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecer);
        Long=(TextView)findViewById(R.id.NLO);
        Lat=(TextView)findViewById(R.id.NLA);
        Dir=(TextView)findViewById(R.id.SDir);

        LocationManager MUM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new  Localizacion();
        Local.setEstablecer(this);

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            //Requiere permisos para Android 6.0
            Log.e("Location", "No se tienen permisos necesarios!, se requieren.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 225);
            return;
        }else{
            Log.i("Location", "Permisos necesarios OK!.");
            MUM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
        }

        MUM.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                (LocationListener) Local);

        Long.setText("");
        Lat.setText("");
        Dir.setText("");
    }
    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        Establecer mainActivity;

        public Establecer getMainActivity() {
            return mainActivity;
        }

        public void setEstablecer(Establecer mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            //loc.getLatitude();
            //loc.getLongitude();
            LatG=loc.getLatitude();
            LonG=loc.getLongitude();
            Lat.setText(LatG.toString());
            Long.setText(LonG.toString());
            this.mainActivity.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            Dir.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            Dir.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Este metodo se ejecuta cada vez que se detecta un cambio en el
            // status del proveedor de localizacion (GPS)
            // Los diferentes Status son:
            // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
            // TEMPORARILY_UNAVAILABLE -> Temporalmente no disponible pero se
            // espera que este disponible en breve
            // AVAILABLE -> Disponible
        }

    }/* Fin de la clase localizacion */
    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    Dir.setText(""+ DirCalle.getAddressLine(0));
                    DirG=Dir.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void eventEst(View view){
        if(DirG.length()>0){
            LatEst=LatG;
            LonEst=LonG;
            Toast.makeText(this,"Ubicación establecida correctamente",Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else{
            Toast.makeText(this,"Esperando ubicación",Toast.LENGTH_SHORT).show();
        }
        this.finish();

    }
    UsuarioSQLiteHelper db=new UsuarioSQLiteHelper(this,"DBAlert",null,1);
    public void eventAlert(View view2){

        if(DirG.length()>0){
            db.abrirBD();
            db.InsertReg2(LonG);
            //db2.InsertReg(LatG,LonG);
            db.cerrarBD();
            Toast.makeText(this,"Alerta generada correctamente",Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else{
            Toast.makeText(this,"Esperando ubicación",Toast.LENGTH_SHORT).show();
        }
        this.finish();
    }

}
