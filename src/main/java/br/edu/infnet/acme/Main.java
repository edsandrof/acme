package br.edu.infnet.acme;

import br.edu.infnet.acme.domain.service.ExerciseList1Service;
import br.edu.infnet.acme.domain.service.ExerciseList2Service;

public class Main {

    public static void main(String[] args) {
        System.out.printf(" --- Exercise list 1 --- %n%n");
        ExerciseList1Service.runExerciseList1();

        System.out.printf("%n --- Exercise list 2 --- %n%n");
        ExerciseList2Service.runExerciseList2();
    }
}
