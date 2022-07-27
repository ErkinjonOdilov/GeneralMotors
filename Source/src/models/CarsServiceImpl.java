package models;

import com.google.gson.Gson;
import data.DataBase;
import service.CarsService;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class CarsServiceImpl extends Thread implements CarsService {

     Thread thread=new Thread();
    @Override
    public void downloadInfo() {
        {
            File file = new File("CarsData.txt");
            try {
                file.createNewFile();
                URL url = new URL("https://private-anon-a36966c174-carsapi1.apiary-mock.com/cars");
                URLConnection connection = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                String row;
                StringBuilder stringBuilder = new StringBuilder();
                while ((row = bufferedReader.readLine()) != null) {
                    stringBuilder.append(row);
                }
                bufferedReader.close();
                String jsonString = stringBuilder.toString();
                Gson gson = new Gson();
                Cars[] cars = gson.fromJson(jsonString, Cars[].class);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(jsonString.getBytes());
                fileOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void writeCarList() {
        File file = new File("CarsData.txt");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String row;
            StringBuilder stringBuilder = new StringBuilder();
            while ((row = bufferedReader.readLine()) != null) {
                stringBuilder.append(row);
            }
            inputStream.close();
            String jsonString = stringBuilder.toString();
            Gson gson = new Gson();
            Cars[] users = gson.fromJson(jsonString, Cars[].class);
            for (Cars user : users) {
                DataBase.carsList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }




    }

    @Override
    public void run() {
        writeCarList();
    }
}