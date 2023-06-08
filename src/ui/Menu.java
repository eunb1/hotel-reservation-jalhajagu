package ui;

import entity.Customer;
import entity.Hotel;

import java.util.Scanner;

public class Menu {
    // public static Boolean flag = false;
    private Scanner scanner;
    private Console console;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.console = new Console();
    }

    public void show() {
        displayMainMenu();
    }

    private void displayMainMenu() {
        System.out.println("[ 호텔 예약 프로그램 ]");
        System.out.println("환영합니다! 본인의 역할은 무엇입니까?");
        System.out.println("1. 관리자");
        System.out.println("2. 고객");
        printLine();
        int inputNum = selectMenu();
        if (inputNum == 1) {
            displayManagerMenu(null);
        } else if (inputNum == 2){
            displayCustomerMenu(null);
        } else {
            System.out.println("역할을 다시 입력해주세요");
            displayMainMenu();
        }
    }

    private void displayManagerMenu(Hotel hotel) {
        System.out.println("(호텔 관리자용)");
        System.out.println("아래 메뉴를 선택해주세요");
        if (hotel == null) {
            System.out.println("1. 로그인");
            System.out.println("2. 종료");
            printLine();
            int inputNum = selectMenu();
            if (inputNum == 1) {
                displayManagerMenu(console.adminLogin());
            } else if (inputNum == 2) {
                displayMainMenu();
            } else {
                System.out.println("메뉴를 다시 선택해주세요");
                displayManagerMenu(null);
            }
        } else {
            System.out.println("1. 보유 자산 조회");
            System.out.println("2. 전체 예약 목록 조회");
            System.out.println("3. 종료");
            printLine();
            int inputNum = selectMenu();
            if (inputNum == 1) {
                console.checkTotalSales(hotel);
                displayManagerMenu(hotel);
            } else if (inputNum == 2) {
                console.checkAllReservations();
                displayManagerMenu(hotel);
            } else if (inputNum == 3){
                displayMainMenu();
            } else {
                System.out.println("메뉴를 다시 선택해주세요");
                displayManagerMenu(hotel);
            }
        }
    }

    private void displayCustomerMenu(Customer customer) {
        System.out.println("(일반 고객용)");
        System.out.println("아래 메뉴를 선택해주세요");
        if (customer == null) {
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 종료");
            printLine();
            int inputNum = selectMenu();
            if (inputNum == 1) {
                displayCustomerMenu(console.customerLogin());
            } else if (inputNum == 2) {
                console.resisterCustomer();
                displayCustomerMenu(null);
            } else if (inputNum == 3){
                displayMainMenu();
            } else {
                System.out.println("메뉴를 다시 선택해주세요");
                displayCustomerMenu(null);
            }
        } else {
            System.out.println("1. 소지금 조회");
            System.out.println("2. 객실 신규 예약");
            System.out.println("3. 객실 예약 취소");
            System.out.println("4. 예약 조회");
            System.out.println("5. 종료");
            printLine();
            int inputNum = selectMenu();
            switch (inputNum) {
                case 1: {
                    console.checkMoney(customer);
                    break;
                }
                case 2: {
                    console.makeReservation(customer);
                    break;
                }
                case 3: {
                    console.cancelReservation(customer);
                    break;
                }
                case 4: {
                    console.checkCustomerReservations(customer);
                    break;
                }
                case 5: {
                    displayMainMenu();
                    break;
                }
                default:
                    System.out.println("메뉴를 다시 선택해주세요");
            }
            displayCustomerMenu(customer);
        }
    }


    private void printLine() {
        System.out.println("-----------------------------------");
    }

    private int selectMenu() {
        System.out.print("메뉴선택 : ");
        int menuNumber = scanner.nextInt();
        scanner.nextLine(); // 버프를 비우기 위함.
        printLine();
        return menuNumber;
    }
}

