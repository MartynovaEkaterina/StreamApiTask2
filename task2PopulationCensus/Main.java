package task2PopulationCensus;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long countMinors = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println(countMinors);

        List<String> conscripts = persons.stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .filter(p -> p.getAge() < 27)
                .filter(p -> p.getAge() >= 18)
                .map(p -> p.getFamily())
                .collect(Collectors.toList());
        printList(conscripts);

        List<Person> worker = persons.stream()
                .filter(p -> (p.getEducation() == Education.HIGHER))
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getSex() == Sex.WOMAN && p.getAge() < 60 || p.getSex() == Sex.MAN && p.getAge() < 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        printList2(worker);

    }

    public static void printList(List<String> persons) {
        for (String p : persons) {
            System.out.println(p);
        }
    }

    public static void printList2(List<Person> persons) {
        for (Person p : persons) {
            System.out.println(p.toString());
        }
    }

}

