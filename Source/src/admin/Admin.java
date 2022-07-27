package admin;

import com.google.gson.Gson;
import data.DataBase;
import models.Cars;
import service.AdminService;


import java.io.*;
import java.util.Scanner;

public class Admin implements AdminService {
    private Scanner scanner=new Scanner(System.in);
    @Override
    public void adminMenu() {
    boolean isTrue=true;
        while (isTrue){
            System.out.println("Welcome Admin Panel");
            System.out.println("Choose Options: ");
            System.out.println("     1->Show Cars\n" +
                    "     2->Applications\n     0->Exit");

            int n=scanner.nextInt();
            switch (n){
                case 0->{isTrue=false;}
                case 1->{showCars();}
                case 2->{applications();}
            }
        }
    }

    @Override
    public void showCars() {
    boolean isTrue=true;
          File file=new File("CarsData.txt");
          try {
              FileInputStream inputStream=new FileInputStream(file);
              BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
              String row;
              StringBuilder stringBuilder=new StringBuilder();
              while ((row=bufferedReader.readLine())!=null){
                  stringBuilder.append(row);
              }
              inputStream.close();

              String jsonString=stringBuilder.toString();
              Gson gson=new Gson();
              Cars[] users = gson.fromJson(jsonString, Cars[].class);
              int i=0;
              for (Cars user : users) {
                  System.out.println((i+1)+"->"+user.getModel());
                  i++;
                  DataBase.carsList.add(user);
              }
              System.out.println("Choose Cars: ");
              int n=scanner.nextInt()-1;
              System.out.println(DataBase.carsList.get(n));

          } catch (Exception e) {
              e.printStackTrace();
          }

      }

    @Override
    public void applications() {
        if(!DataBase.userList.isEmpty()){
            for (int i = 0; i < DataBase.userList.size(); i++) {
                System.out.println((i+1)+" -> "+DataBase.userList.get(i).getFullName());
            }
            int n=scanner.nextInt()-1;
            System.out.println(DataBase.userList.get(n));
        }

    }
}
