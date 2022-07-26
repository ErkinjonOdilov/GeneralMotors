package models;

import com.google.gson.Gson;
import data.DataBase;
import service.CarsService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.atomic.DoubleAccumulator;

public class CarsServiceImpl implements CarsService {
    {
        File file=new File("CarsData.txt");
            try {
                URL url = new URL("https://private-anon-a36966c174-carsapi1.apiary-mock.com/cars");
                URLConnection connection = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                String row;
                StringBuilder stringBuilder=new StringBuilder();
                while ((row = bufferedReader.readLine()) != null) {
                   stringBuilder.append(row);
                }
                bufferedReader.close();
                String jsonString=stringBuilder.toString();
                Gson gson=new Gson();
                Cars[] cars=gson.fromJson(jsonString,Cars[].class);
                FileOutputStream fileOutputStream=new FileOutputStream(file);
                fileOutputStream.write(jsonString.getBytes());
                fileOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
    }


}
