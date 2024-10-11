import java.util.*;

class Student {
    private String nume;
    private String grupa;
    private List<Integer> note;

    public Student(String nume, String grupa) {
        this.nume = nume;
        this.grupa = grupa;
        this.note = new ArrayList<>();
    }

    public String getNume() {
        return nume;
    }

    public String getGrupa() {
        return grupa;
    }

    public void adaugaNota(int nota) {
        note.add(nota);
    }

    public double medie() {
        return note.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public boolean esteIntegralist() {
        return note.stream().allMatch(nota -> nota >= 5);
    }

    public int numarRestante() {
        return (int) note.stream().filter(nota -> nota < 5).count();
    }

    @Override
    public String toString() {
        return String.format("Student: %s, Grupa: %s, Note: %s", nume, grupa, note);
    }
}

class ByGroupComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getGrupa().compareTo(s2.getGrupa());
    }
}

class ByAverageComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s2.medie(), s1.medie());
    }
}

class ByRestanteComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.numarRestante(), s2.numarRestante());
    }
}

public class Problema_2_7_2 {
    public static void main(String[] args) {
        Random rand = new Random();
        List<Student> studenti = new ArrayList<>();

        Student s1 = new Student("Popescu Ion", "Grupa 101");
        Student s2 = new Student("Ionescu Maria", "Grupa 102");
        Student s3 = new Student("Georgescu Andrei", "Grupa 101");
        Student s4 = new Student("Dumitrescu Alina", "Grupa 103");

        studenti.add(s1);
        studenti.add(s2);
        studenti.add(s3);
        studenti.add(s4);

        for (Student s : studenti) {
            for (int i = 0; i < 5; i++) {
                s.adaugaNota(rand.nextInt(7) + 4);  
            }
        }

        studenti.sort(new ByGroupComparator());
        System.out.println("Studentii sortati alfabetic pe grupe:");
        studenti.forEach(System.out::println);

        List<Student> integralisti = new ArrayList<>();
        for (Student s : studenti) {
            if (s.esteIntegralist()) {
                integralisti.add(s);
            }
        }
        integralisti.sort(new ByAverageComparator());
        System.out.println("\nIntegralistii sortati descrescator dupa medie:");
        integralisti.forEach(System.out::println);

       
        List<Student> restantieri = new ArrayList<>();
        for (Student s : studenti) {
            if (!s.esteIntegralist()) {
                restantieri.add(s);
            }
        }
        restantieri.sort(new ByRestanteComparator());
        System.out.println("\nRestantierii sortati crescator dupa numarul de restante:");
        restantieri.forEach(System.out::println);
    }
}
