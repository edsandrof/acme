package br.edu.infnet.acme;

import br.edu.infnet.acme.domain.service.Exercise3Service;
import br.edu.infnet.acme.domain.service.ExerciseList1Service;
import br.edu.infnet.acme.domain.service.ExerciseList2Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info(" --- Exercise list 1 --- ");
        ExerciseList1Service.runExerciseList1();

        log.info(" --- Exercise list 2 --- ");
        ExerciseList2Service.runExerciseList2();

        log.info(" --- Exercise list 3 --- ");
        Exercise3Service.runExercise3();
    }
}
