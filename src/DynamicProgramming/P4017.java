package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*public class P4017 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Animal[] animals = new Animal[n+1];
        List<Animal> freeAnimals = new ArrayList<>();
        List<Animal> topAnimals = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            animals[i] = new Animal(n);
            animals[i].index = i;
        }

        for (int i = 1; i <= m; i++) {
            int eaten = sc.nextInt();
            int eat = sc.nextInt();
            animals[eaten].eaten[eat] = 1;
            animals[eaten].eatenSize += 1;
            animals[eat].eat[eaten] = 1;
            animals[eat].eatSize += 1;
        }
        for (int i = 1 ; i <= n ; i++) {
            if (animals[i].eatSize == 0) {
                freeAnimals.add(animals[i]);
                animals[i].sum = 1;
            }
            if (animals[i].eatenSize== 0) {
                topAnimals.add(animals[i]);
            }
        }
        int result = 0;
        while (freeAnimals.size() > 0) {
            Animal animal = freeAnimals.remove(0);
            for (int i = 1; i <= n; i++) {
                if (animal.eaten[i] == 1) {
                    animals[i].eat[i] = 0;
                    animals[i].eatSize -= 1;
                    animals[i].sum = (animals[i].sum + animal.sum) % 80112002;
                    if (animals[i].eatSize == 0) {
                        freeAnimals.add(animals[i]);
                    }
                }

            }
        }
        for (Animal topAnimal : topAnimals) {
            result = (result + topAnimal.sum) % 80112002;
        }
        System.out.println(result);
    }
}
class Animal {
    int index;
    int sum = 0;
    //List<Integer> eat = new ArrayList<>();
    //List<Integer> eaten = new ArrayList<>();
    int[] eat;
    int[] eaten;
    int eatSize = 0;
    int eatenSize = 0;

    public Animal(int n) {
        this.eat = new int[n+1];
        this.eaten = new int[n+1];
    }
}*/

public class P4017 {
    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        Animal[] animals = new Animal[n+1];
        List<Animal> freeAnimals = new ArrayList<>();
        List<Animal> topAnimals = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            animals[i] = new Animal(n);
            animals[i].index = i;
        }
        for (int i = 1; i <= m; i++) {
            String[] temp = reader.readLine().split(" ");
            int eaten = Integer.parseInt(temp[0]);
            int eat = Integer.parseInt(temp[1]);
            animals[eaten].eaten.add(eat);
            animals[eat].eat.add(eaten);
        }
        for (int i = 1 ; i <= n ; i++) {
            if (animals[i].eat.size() == 0) {
                freeAnimals.add(animals[i]);
                animals[i].sum = 1;
            }
            if (animals[i].eaten.size() == 0) {
                topAnimals.add(animals[i]);
            }
        }
        int result = 0;
        while (freeAnimals.size() > 0) {
            Animal animal = freeAnimals.remove(0);
            animals[animal.index] = null;
            animal.eaten.forEach(integer -> {
                animals[integer].eat.removeIf(integer1 -> integer1 == animal.index);
                animals[integer].sum = (animals[integer].sum + animal.sum) % 80112002;
                if (animals[integer].eat.size() == 0) {
                    freeAnimals.add(animals[integer]);
                }
            });
        }
        for (Animal topAnimal : topAnimals) {
            result = (result + topAnimal.sum) % 80112002;
        }
        System.out.println(result);
    }
}
class Animal {
    int index;
    int sum = 0;
    List<Integer> eat;
    List<Integer> eaten;

    public Animal(int n) {
        this.eat = new ArrayList<>(1);
        this.eaten = new ArrayList<>(1);
    }
}
