package user;

import com.google.gson.Gson;
import data.DataBase;
import models.Cars;
import service.UserService;

import javax.security.sasl.SaslClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class User implements UserService {
    private Scanner scanner=new Scanner(System.in);
private String fullName;
private String birthday;
private String number;
private String birthPlace;


    @Override
    public void userMenu() {
        boolean isTrue=true;
        while (isTrue){
            System.out.println("Welcome User Panel");
            System.out.println("Choose Options: ");
            System.out.println("     1->Show Cars\n" +
                    "     2->Calculate Credit\n     3->Fill Application\n    0->Exit");

            int n=scanner.nextInt();
            switch (n){
                case 0->{isTrue=false;}
                case 1->{showCars();}
                case 2->{calculateCredit();}
                case 3->{fillApplication();}

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
    public void calculateCredit() {
        System.out.println("Welcome Credit Calculate System");
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
            System.out.println();
            System.out.println(DataBase.carsList.get(n).getModel());
            System.out.println();
            int price=DataBase.carsList.get(n).getPrice();
            System.out.println("How many month take Credit this car");
            int month=scanner.nextInt();  //36
            System.out.println("How much percent You want added Car price in "+month+" month");
            double percentage=scanner.nextDouble();  //5%
            double monthlyPercent=(((DataBase.carsList.get(n).getPrice()/100)*percentage)+DataBase.carsList.get(n).getPrice())/month;
            double returnMoney=monthlyPercent*month;
            System.out.println("You should return "+returnMoney+"$"+"in "+month+" month");
            System.out.println("You pay monthly "+monthlyPercent+"$");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fillApplication() {

    }


}
