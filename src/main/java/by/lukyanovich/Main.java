package by.lukyanovich;

import by.lukyanovich.model.*;
import by.lukyanovich.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();
//        task13();
//        task14();
//        task15();
        task16();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() <= 20 & animal.getAge() >= 10)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(14)
                .limit(7)
                .forEach(System.out::println);

    }

    private static void task2() throws IOException {

        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()))
                .map(Main::breadToUpperCase)
                .forEach(animal -> System.out.println(animal.getBread()));


    }

    private static Animal breadToUpperCase(Animal animal) {
        if ("Female".equals(animal.getGender())) {
            animal.setBread(animal.getBread().toUpperCase());
        }
        return animal;
    }


    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30 && animal.getOrigin().startsWith("A"))
                .toList()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        Integer countOfAnimals = Math.toIntExact(animals.stream()
                .filter(animal -> "Female".equals(animal.getGender()))
                .count());
        System.out.println(countOfAnimals);


    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean haveHungarian = animals.stream()
                .filter(animal -> animal.getAge() <= 30 & animal.getAge() >= 20)
                .anyMatch(animal -> "Hungarian".equals(animal.getOrigin()));
        System.out.println(haveHungarian);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean isGenderRegular = animals.stream()
                .allMatch(animal ->
                        "Male".equals(animal.getGender()) || "Female".equals(animal.getGender()));
        System.out.println(isGenderRegular);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean haveOceaniaOrigin = animals.stream()
                .noneMatch(animal -> "Oceania".equals(animal.getOrigin()));
        System.out.println(haveOceaniaOrigin);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .forEach(System.out::println);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparing(chars -> chars.length))
                .stream()
                .forEach(chars -> System.out.println(chars.length));

    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long ageSum = animals.stream().mapToLong(Animal::getAge).sum();
        System.out.println(ageSum);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        double averageAge = animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .getAsDouble();
        System.out.println(averageAge);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        int youngAge = 18;
        int oldAge = 27;
        LocalDate presentDate = LocalDate.now();

        people.stream()
                .filter(person -> "Male".equals(person.getGender()))
                .filter(person -> (youngAge <= (ChronoUnit.YEARS.between(person.getDateOfBirth(), presentDate))
                        && (ChronoUnit.YEARS.between(person.getDateOfBirth(), presentDate)) <= oldAge))
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);

    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        List<Person> hospitalPeople = houses.stream()
                .filter(house -> "Hospital".equals(house.getBuildingType()))
                .map(House::getPersonList)
                .flatMap(List::stream)
                .toList();

        List<Person> seniorAndYangPeople = houses.stream()
                .filter(house -> !"Hospital".equals(house.getBuildingType()))
                .map(House::getPersonList)
                .flatMap(List::stream)
                .filter(Main::isSeniorOrYang)
                .toList();

        List<Person> otherPeople = houses.stream()
                .filter(house -> !"Hospital".equals(house.getBuildingType()))
                .map(House::getPersonList)
                .flatMap(List::stream)
                .filter(person -> !isSeniorOrYang(person))
                .toList();

        Stream.of(hospitalPeople, seniorAndYangPeople, otherPeople)
                .flatMap(List::stream)
                .limit(500)
                .toList()
                .forEach(System.out::println);
    }

    private static boolean isSeniorOrYang(Person person) {

        final int seniorAge = 60;
        final int yangAge = 18;
        LocalDate dateOfBirth = person.getDateOfBirth();
        LocalDate now = LocalDate.now();
        boolean isLessAdultAge = yangAge < ChronoUnit.YEARS.between(dateOfBirth, now);
        boolean isSenior = seniorAge <= ChronoUnit.YEARS.between(dateOfBirth, now);
        return isLessAdultAge && isSenior;
    }


    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        List<Car> turkmenistanCarsJaguar = cars.stream()
                .filter(car -> "Jaguar".equals(car.getCarMake()))
                .toList();

        List<Car> turkmenistanCarsWhite = cars.stream()
                .filter(car -> "White".equals(car.getColor()))
                .toList();

        List<Car> turkmenistanCars = Stream.concat(turkmenistanCarsJaguar.stream(), turkmenistanCarsWhite.stream()).toList();

        int turkmenistanTotalMassKgs = getTotalMassCarsFor14Task(turkmenistanCars);
        double turkmenistanCosts = calculateTransportCost(turkmenistanTotalMassKgs);
        System.out.println("Turkmenistan cost = " + turkmenistanCosts);

        List<Car> filteredCars = cars.stream().filter(car -> !turkmenistanCars.contains(car)).toList();

        List<String> uzbekistanConditionCars = Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota");

        List<Car> uzbekistanCars = filteredCars.stream()
                .filter(car -> car.getMass() < 1500 && uzbekistanConditionCars.contains(car.getCarMake()))
                .toList();

        int uzbekistanTotalMassKgs = getTotalMassCarsFor14Task(uzbekistanCars);
        double uzbekistanCosts = calculateTransportCost(uzbekistanTotalMassKgs);
        System.out.println("Uzbekistan cost = " + uzbekistanCosts);

        filteredCars = cars.stream().filter(car -> !uzbekistanCars.contains(car)).toList();

        List<String> kazahstanConditionCars = Arrays.asList("GMC", "Dodge");

        List<Car> kazahstanCarsBlackLargeMass = filteredCars.stream()
                .filter(car -> car.getMass() > 4000 && "Black".equals(car.getColor()))
                .toList();

        List<Car> kazahstanCarsGmcDodge = filteredCars.stream()
                .filter(car -> kazahstanConditionCars.contains(car.getCarModel()))
                .toList();

        List<Car> kazahstanCars = Stream.concat(kazahstanCarsBlackLargeMass.stream(), kazahstanCarsGmcDodge.stream()).toList();

        int kazahstanTotalMassKgs = getTotalMassCarsFor14Task(kazahstanCars);
        double kazahstanCosts = calculateTransportCost(kazahstanTotalMassKgs);
        System.out.println("Kazahstan cost = " + kazahstanCosts);

        filteredCars = cars.stream().filter(car -> !kazahstanCars.contains(car)).toList();
        List<String> kirgistanConditionCars = Arrays.asList("Civic", "Cherokee");
        List<Car> kirgistanCars = filteredCars.stream()
                .filter(car -> 1982 < car.getReleaseYear() && kirgistanConditionCars.contains(car.getCarModel()))
                .toList();

        int kirgistanTotalMassKgs = getTotalMassCarsFor14Task(kirgistanCars);
        double kirgistanCosts = calculateTransportCost(kirgistanTotalMassKgs);
        System.out.println("Kirgistan cost = " + kirgistanCosts);

        filteredCars = cars.stream().filter(car -> !kirgistanCars.contains(car)).toList();
        List<String> russiaConditionCars = Arrays.asList("Yellow", "Red", "Green", "Blue");
        List<Car> russiaCars = filteredCars.stream()
                .filter(car -> 40000 < car.getPrice() && !russiaConditionCars.contains(car.getColor()))
                .toList();

        int russiaTotalMassKgs = getTotalMassCarsFor14Task(russiaCars);
        double russiaCosts = calculateTransportCost(russiaTotalMassKgs);
        System.out.println("Russia cost = " + russiaCosts);

        filteredCars = cars.stream().filter(car -> !russiaCars.contains(car)).toList();
        List<Car> mongoliaCars = filteredCars.stream()
                .filter(car -> car.getVin().contains("59"))
                .toList();

        int mongoliaTotalMassKgs = getTotalMassCarsFor14Task(mongoliaCars);
        double mongoliaCosts = calculateTransportCost(mongoliaTotalMassKgs);
        System.out.println("Mongolia cost = " + mongoliaCosts);

        System.out.println("Total countries costs " + (turkmenistanCosts + uzbekistanCosts + kazahstanCosts
                + kirgistanCosts + russiaCosts + mongoliaCosts));
    }

    private static double calculateTransportCost(int totalMassInKgs) {

        final double transportCostByTonn = 7.14;

        return (transportCostByTonn * totalMassInKgs) / 1000;
    }

    private static int getTotalMassCarsFor14Task(List<Car> cars) {

        return cars.stream()
                .map(Car::getMass)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private static void task15() throws IOException {

        List<Flower> flowers = Util.getFlowers();
        List<Flower> sortedFlowersByReverseOrigin = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed())
                .toList();

        List<Flower> sortedFlowersByReversePriceAndWaterConsumption = sortedFlowersByReverseOrigin.stream()
                .sorted(Comparator.comparing(Flower::getPrice).thenComparing(Flower::getWaterConsumptionPerDay).reversed())
                .toList();

        String regexCondition = "[c-sC-S]";
        List<Flower> filteredFlowersByNameCondition = sortedFlowersByReversePriceAndWaterConsumption.stream()
                .filter(flower -> flower.getCommonName().substring(0, 1).matches(regexCondition))
                .toList();
        for (Flower f : filteredFlowersByNameCondition) {
            System.out.println(f.getCommonName());
        }

        List<String> vaseMaterialConditionList = Arrays.asList("Steel", "Glass", "Aluminium");
        List<Flower> filteredFlowersByVaseMaterial = filteredFlowersByNameCondition.stream()
                .filter(flower -> flower.isShadePreferred() &&
                        flower.getFlowerVaseMaterial().stream().anyMatch(vaseMaterialConditionList::contains))
                .toList();
        filteredFlowersByVaseMaterial.stream()
                .map(Main::calculationPriceAndPriceConsumption)
                .toList();


        for (Flower flower : filteredFlowersByVaseMaterial) {
            System.out.println("Price + price water consumption in 5 years: "
                    + flower.getCommonName() + " = "
                    + calculationPriceAndPriceConsumption(flower));
        }

        System.out.println("Total price: = " + calculationTotalPriceAndPriceConsumption(filteredFlowersByVaseMaterial));

    }

    private static double calculationPriceAndPriceConsumption(Flower flower) {
        return flower.getPrice() + (1.39 * flower.getWaterConsumptionPerDay() * 5 * 365);
    }

    private static double calculationTotalPriceAndPriceConsumption(List<Flower> flowers) {
        double totalPrice = 0;
        for (Flower f : flowers) {
            totalPrice += calculationPriceAndPriceConsumption(f);
        }
        return totalPrice;
    }

    private static void task16() throws IOException {
        List<Subscriber> subscribers = Util.getSubscribers();

        int quantitySubscribersSubscribedGroupClasses = subscribers.stream()
                .filter(subscriber -> "group".equals(subscriber.getTypeOfBoughtSubscription()))
                .toList()
                .size();
        System.out.println("Всего абонентов купиших абонементы 3-го типа: - " + quantitySubscribersSubscribedGroupClasses);

        int howManyComesOnMonday = subscribers.stream()
                .filter(subscriber -> subscriber.getDays().contains("Monday"))
                .toList()
                .size();
        System.out.println("Количество человек приходящих по понедельникам: - " + howManyComesOnMonday);

        int sumOfClasses = subscribers.stream()
                .mapToInt(Subscriber::getQuantityOfClasses)
                .sum();
        System.out.println("Общее количество занятий по проданным абонементам: - " + sumOfClasses);

        int costOfSwimAndSaunaSubscription = subscribers.stream()
                .filter(subscriber -> "swimAndSauna".equals(subscriber.getTypeOfBoughtSubscription()))
                .mapToInt(value -> Integer.parseInt(value.getCostOfSubscription()))
                .sum();
        System.out.println("Продано абонементов 2-го типа: - " + costOfSwimAndSaunaSubscription);

        int costOfAllSaleSubscriptions = subscribers.stream()
                .mapToInt(value -> Integer.parseInt(value.getCostOfSubscription()))
                .sum();
        System.out.println("Всего продано абонементов: - " + costOfAllSaleSubscriptions);


    }
}
