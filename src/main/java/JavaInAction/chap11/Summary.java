package JavaInAction.chap11;

import java.util.Optional;

public class Summary {

  public static void main(String[] args) {
    Car car = new Car();

    Optional<Object> empty = Optional.empty();
    Optional<Car> optionalCar = Optional.of(car);
    Optional<Car> optional = Optional.ofNullable(null);

    System.out.println("optionalCar = " + optionalCar);
    System.out.println("optional = " + optional);

    String name = optionalCar
        .flatMap(Car::getInsurance)
        .map(Insurance::getName)
        .orElseGet(() -> "panda");
    System.out.println("name = " + name);

    Person person = new Person();
    String carInsuranceName = person.getCarInsuranceName(Optional.of(person), 15);
    System.out.println("carInsuranceName = " + carInsuranceName);
  }
}
