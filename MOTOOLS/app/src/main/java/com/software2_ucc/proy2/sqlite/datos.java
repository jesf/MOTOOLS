package com.software2_ucc.proy2.sqlite;

import java.util.UUID;

/**
 * Created by User on 8/11/2016.
 */

public class datos {
    interface ColumnasAlertas{
        String PLACA="Placa";
        String Longitud="Longitud";
        String Latitud="Latitud";
        String Date="Date";
        String ID="ID";
    }

    interface ColumnasUbicaciones{
        String PLACA="Placa";
        String Longitud="Longitud";
        String Latitud="Latitud";
        String Date="Date";
    }
    public static class Alertas implements ColumnasAlertas {
        public static String generarIdAlerta() {
            return "CP-" + UUID.randomUUID().toString();
        }
    }
    public static class Ubicaciones implements ColumnasUbicaciones {
        public static String generarIdCUbicaciones() {
            return "CP-" + UUID.randomUUID().toString();
        }
    }
}
