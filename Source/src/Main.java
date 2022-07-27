import admin.Admin;
import user.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        boolean isTrue=true;
        while (isTrue){
            System.out.println("Welcome Car Sales System");
            System.out.println("Choose Options");
            System.out.println("1->Admin\n2->User\n0->Exit");
            int n=scanner.nextInt();
            switch (n){
                case 0->{isTrue=false;}
                case 1->{
                    Admin admin=new Admin();
                    admin.adminMenu();
                }
                case 2->{
                    User user=new User();
                    user.userMenu();
                }
            }
        }
    }
}
