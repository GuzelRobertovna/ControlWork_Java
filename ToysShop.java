package ru.geekbrains.api;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * ToysShop
 */


public class ToysShop {
    private static List<Toy> toys = new ArrayList();

    public static void main(String[] args) {
        //File filename = new File("toys.txt");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("1.Добавить новую игрушку");
            System.out.println("2.Изменить частоту выпадения игрушки");
            System.out.println("3.Организовать розыгрыш игрушек");
            System.out.println("4.Выйти");

            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    addToys();
                    break;
                case 2:
                    ChangeToysFrequency();
                    break;
                case 3:
                    ToyLottery();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз");
                    break;
            }
        } while (choice != 4);
    }

    private static void addToys() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите id игрушки: ");
        int id = scanner.nextInt();
        System.out.print("Введите название игрушки: ");
        String name = scanner.next();
        System.out.print("Введите количество игрушки: ");
        int count = scanner.nextInt();
        System.out.print("Введите частоту выпадения игрушки: ");
        double frequency = scanner.nextDouble();
        Toy toy = new Toy(id, name, count, frequency);
        toys.add(toy);
        System.out.println("Игрушка успешно добавлена");
    }


    private static void ChangeToysFrequency() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите id игрушки: ");
        int id = scanner.nextInt();
        boolean check = false;
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                System.out.print("Введите новую частоту выпадения игрушки (в %): ");
                double frequency = scanner.nextDouble();
                toy.setFrequency(frequency);
                System.out.println("Частота выпадения игрушки успешно изменена");
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("Игрушка с такой id не найдена");
        }
    }

    public static void ToyLottery() {
        double totalFrequency = 0;
        for (Toy toy : toys) {
            totalFrequency += toy.getFrequency();
        }
        if (totalFrequency == 0) {
            System.out.println("Нет игрушек для розыгрыша");
            return;
        }
        Random random = new Random();
        double randomFrequency = random.nextDouble() * totalFrequency;
        System.out.println("Случайное число "+randomFrequency);
        double sumFrequency = 0;
        Toy selectedToy = null;
        for (Toy toy : toys) {
            sumFrequency += toy.getFrequency();
            if (randomFrequency <= sumFrequency) {
                selectedToy = toy;
                break;
            }
        }
        if (selectedToy != null) {
            if (selectedToy.getCount() == 0) {
                System.out.println("Все игрушки выбранного типа разыграны");
            } else {
                selectedToy.setCount(selectedToy.getCount() - 1);
                System.out.println(("Вы выиграли " + selectedToy.getName()));
            }
        }
    }



}
