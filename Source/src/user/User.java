package user;

import com.google.gson.Gson;
import data.DataBase;
import models.Cars;
import service.UserService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class User implements UserService {
    private Scanner scanner = new Scanner(System.in);
    private String fullName;
    private String birthday;
    private String number;
    private String birthPlace;
    private Cars cars;
    private int month;
    private double percent;
    private double remainMoney;

    public User() {
    }

    public User(String fullName, String birthday, String number, String birthPlace, Cars cars, int month, double percent) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.number = number;
        this.birthPlace = birthPlace;
        this.cars = cars;
        this.month = month;
        this.percent = percent;
        this.remainMoney = (((cars.getPrice() / 100) * percent) + cars.getPrice());
    }

    @Override
    public void userMenu() {
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("Welcome User Panel");
            System.out.println("Choose Options: ");
            System.out.println("     1->Show Cars\n" +
                    "     2->Calculate Credit\n     3->Fill Application\n    0->Exit");

            int n = scanner.nextInt();
            switch (n) {
                case 0 -> {
                    isTrue = false;
                }
                case 1 -> {
                    showCars();
                }
                case 2 -> {
                    calculateCredit();
                }
                case 3 -> {
                    fillApplication();
                }

            }
        }
    }

    @Override
    public void showCars() {
        boolean isTrue = true;
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
            int i = 0;
            for (Cars user : users) {
                System.out.println((i + 1) + "->" + user.getModel());
                i++;
                DataBase.carsList.add(user);
            }
            System.out.println("Choose Cars: ");
            int n = scanner.nextInt() - 1;
            System.out.println(DataBase.carsList.get(n));
            DataBase.carsList.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void calculateCredit() {
        System.out.println("Welcome Credit Calculate System");
        boolean isTrue = true;
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
            int i = 0;
            for (Cars user : users) {
                System.out.println((i + 1) + "->" + user.getModel());
                i++;
            }
            System.out.println("Choose Cars: ");
            int n = scanner.nextInt() - 1;
            System.out.println();
            System.out.println(DataBase.carsList.get(n).getModel());
            System.out.println();
            int price = DataBase.carsList.get(n).getPrice();
            System.out.println("How many month take Credit this car");
            int month = scanner.nextInt();  //36
            System.out.println("How much percent You want added Car price in " + month + " month");
            double percentage = scanner.nextDouble();  //5%
            double monthlyPercent = (((DataBase.carsList.get(n).getPrice() / 100) * percentage) + DataBase.carsList.get(n).getPrice()) / month;
            double returnMoney = monthlyPercent * month;
            System.out.println("You should return " + returnMoney + "$" + "in " + month + " month");
            System.out.println("You pay monthly " + monthlyPercent + "$");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fillApplication() {
        System.out.print("Full name: ");
        scanner = new Scanner(System.in);
        String fullName = scanner.nextLine();
        System.out.print("Birthday: ");
        String birthday = scanner.next();
        System.out.print("Phone number: ");
        scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        System.out.print("Birth Place: ");
        scanner = new Scanner(System.in);
        String birthPlace = scanner.nextLine();
        for (int i = 0; i < DataBase.carsList.size(); i++) {
            System.out.println((i + 1) + "->" + DataBase.carsList.get(i).getModel());
        }
        System.out.print("Which Car: ");
        int carsModel = scanner.nextInt() - 1;
        System.out.print("How many month: ");
        scanner = new Scanner(System.in);
        int month = scanner.nextInt();
        System.out.print("How many Percent per Year: ");
        scanner = new Scanner(System.in);
        double percent = scanner.nextDouble();
        User u1 = new User(fullName, birthday, number, birthPlace, DataBase.carsList.get(carsModel), month, percent);
        DataBase.userList.add(u1);
        System.out.println("Successfully filled");

    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return
                "Full Name=" + fullName + "\n" +
                        "Birthday=" + birthday + "\n" +
                        "Number=" + number + "\n" +
                        "Birth Place='" + birthPlace + "\n" +
                        cars +
                        "Month=" + month + "\n" +
                        "Percent=" + percent + "\n" +
                        "Remain Money=" + remainMoney + "\n";
    }
}
