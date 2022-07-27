package admin;

import com.google.gson.Gson;
import data.DataBase;
import models.Cars;
import service.AdminService;


import javax.xml.crypto.Data;
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
                    "     2->Add Cars\n     3->Edit Cars\n     4->Remove Cars\n     5->Applications\n     0->Exit");

            int n=scanner.nextInt();
            switch (n){
                case 0->{isTrue=false;}
                case 1->{showCars();}
                case 2->{addCars();}
                case 3->{editCars();}
                case 4->{removeCars();}
                case 5->{applications();}
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
    public void addCars() {
        System.out.print("Horse Power: ");
        int horsePower=scanner.nextInt();
        System.out.print("Year: ");
        int year=scanner.nextInt();
        System.out.print("Price: ");
        int price=scanner.nextInt();
        System.out.print("Model: ");
        scanner=new Scanner(System.in);
        String model=scanner.nextLine();
        System.out.print("Id: ");
        int id=scanner.nextInt();
        System.out.print("Made: ");
        scanner=new Scanner(System.in);
        String made=scanner.nextLine();
        Cars cars=new Cars(horsePower,year,price,model,id,made);
        File file=new File("CarsData.txt");
        DataBase.carsList.add(cars);
        System.out.println("Successfully added");

    }

    @Override
    public void removeCars() {

    }

    @Override
    public void editCars() {

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
