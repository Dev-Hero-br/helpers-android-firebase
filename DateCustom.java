package com.cursoandroid.cliente.doacao.Activity.helper;
/*
* create Dev hero in 08/10/2021
*/

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String dataAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");// hh:mm:ss
        String dataString =  simpleDateFormat.format(data);
        return dataString;
    }

    public static String hashDatahoraAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");//yyyy-MM-dd'T'HH:mm:ss.SSS = 2001-07-04 12:08:56.235
        String dataString =  simpleDateFormat.format(data);
        return dataString;
    }

    public static String horaAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");//yyyy-MM-dd'T'HH:mm:ss.SSS = 2001-07-04 12:08:56.235
        String horaString =  simpleDateFormat.format(data);
        return horaString;
    }

    public static String dataKeyAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");//yyyy-MM-dd'T'HH:mm:ss.SSS = 2001-07-04 12:08:56.235
        String dataKey =  simpleDateFormat.format(data);
        return dataKey;
    }
}
