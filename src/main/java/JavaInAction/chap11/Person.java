package JavaInAction.chap11;


import java.util.Optional;

public class Person {

    private Optional<Car> car;
    private int age;

    public Optional<Car> getCar() {
        return car;
    }

    public int getAge() {
        return age;
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person
            .filter(opt -> opt.getAge() > minAge)
            .flatMap(opt -> opt.getCar().map(Car::getInsurance))
            .flatMap(optCar -> optCar.map(Insurance::getName))
            .orElseGet(() -> "panda");
    }
}
