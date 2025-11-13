package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.within;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.time.*;

class AutoAssignerTest {
    //Arrange
    List<Workshop> workshopslist =  new ArrayList<>();
    List<Student> studentList =  new ArrayList<>();
    Map<ZonedDateTime, Integer> spotsPerDate = new HashMap<>();
    AutoAssigner autoAssigner = new AutoAssigner();



    private ZonedDateTime date(int year, int month, int day, int hour, int minute) {
        return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, ZoneId.systemDefault());
    }

    @Test
    public void assignstudent() {
        // Pour run avec coverage juste appuyer sur le bouton run et selectionner le coverage

        //Arrange
        ZonedDateTime days = date(2022, 12, 3, 9, 59);
        spotsPerDate.put(days, 10);
        Workshop workshop= new Workshop(10, "Couture", spotsPerDate);
        workshopslist.add(workshop);
        Student student = new Student(10, "Etudiant", "email");
        studentList.add(student);

        //Act
        AssignmentsLogger assignement = autoAssigner.assign(studentList, workshopslist);
        List<String> assignements = assignement.getAssignments();
        List<String> errors = assignement.getErrors();

        //Assert
        //Student
        assertEquals(student.getName(), "Etudiant");
        assertEquals(student.getEmail(), "email");
        assertEquals(student.getId(), 10);
        //workshop
        assertEquals(workshop.getName(), "Couture");
        assertEquals(workshop.getId(), 10);
        assertNotEquals(workshop.getSpotsPerDate().get(days), spotsPerDate.get(days));


        //Assignement
        assertEquals(assignements.get(0).contains("Etudiant"), true);
        assertEquals(assignements.get(0).contains("Couture"), true);
        assertEquals(assignements.size(), 1);
        //error
        assertEquals(errors.size(), 0);
        //date
        assertTrue(assignements.get(0).contains("03/12/2022 09:59"));
        assertFalse(days.equals(null));
        assertTrue(days.getMonthValue() == 12);
        assertTrue(days.getDayOfMonth() == 3);
        assertTrue(days.getHour() == 9);
        assertTrue(days.getMinute() == 59);
        assertTrue(days.getYear() == 2022);

   }

    @Test
    public void assignmorethan1student() {
        // Pour run avec coverage juste appuyer sur le bouton run et selectionner le coverage

        //Arrange
        ZonedDateTime days = date(2022, 12, 3, 9, 59);
        spotsPerDate.put(days, 10);
        Workshop workshop= new Workshop(10, "Couture", spotsPerDate);
        workshopslist.add(workshop);
        Student student = new Student(10, "Etudiant", "email");
        Student student2 = new Student(10, "Etudiant2", "email");
        studentList.add(student);
        studentList.add(student2);

        //Act
        AssignmentsLogger assignement = autoAssigner.assign(studentList, workshopslist);
        List<String> assignements = assignement.getAssignments();
        List<String> errors = assignement.getErrors();

        //Assert
        assertEquals(assignements.get(0).contains("Etudiant"), true);
        assertEquals(assignements.get(1).contains("Etudiant2"), true);
        assertEquals(assignements.get(0).contains("Couture"), true);
        assertEquals(assignements.size(), 2);
        assertEquals(errors.size(), 0);
        assertNotEquals(workshop.getNextAvailableDate(), null);


    }


    @Test
    public void cannotassignstudent() {
        // Pour run avec coverage juste appuyer sur le bouton run et selectionner le coverage

        //Arrange
        ZonedDateTime days = date(2022, 12, 3, 9, 59);
        spotsPerDate.put(days, 1);
        Workshop workshop= new Workshop(10, "Couture", spotsPerDate);
        workshopslist.add(workshop);
        Student student = new Student(10, "Etudiant", "email");
        Student student2 = new Student(10, "Etudiant2", "email");
        studentList.add(student);
        studentList.add(student2);

        //Act
        AssignmentsLogger assignement = autoAssigner.assign(studentList, workshopslist);
        List<String> assignements = assignement.getAssignments();
        List<String> errors = assignement.getErrors();

        //Assert
        assertEquals(assignements.get(0).contains("Etudiant"), true);
        assertEquals(assignements.get(0).contains("Couture"), true);
        assertEquals(assignements.size(), 1);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).contains("Etudiant2"), true);
        assertEquals(errors.get(0).contains("Couture"), true);

    }


    @Test
    public void verifyhashcode() {

        //Arrange
        Student student = new Student(10, "Etudiant", "email");
        Student student2 = new Student(10, "Etudiant", "email");
        Student student3 = new Student(1, "Etudiant2", "email2");
        Workshop w1 = new Workshop(10, "Couture", spotsPerDate);
        Workshop w2 = new Workshop(10, "Couture", spotsPerDate);
        Workshop w3 = new Workshop(1, "Sport", spotsPerDate);

        //Act

        //Assert
        assertEquals(student.hashCode(), student.hashCode());
        assertEquals(w1.hashCode(), w1.hashCode());
        assertEquals(student.hashCode(), student2.hashCode());
        assertEquals(w1.hashCode(), w2.hashCode());
        assertNotEquals(w1.hashCode(), w3.hashCode());
        assertNotEquals(student.hashCode(), student3.hashCode());





    }


    @Test
    public void verifyequals() {

        //Arrange
        Student student = new Student(10, "Etudiant", "email");
        Student student2 = new Student(10, "Etudiant", "email");
        Student student3 = new Student(11, "Etudiant3", "email3");


        Workshop w1 = new Workshop(10, "Couture", spotsPerDate);
        Workshop w2 = new Workshop(10, "Couture", spotsPerDate);
        Workshop w3 = new Workshop(1, "Sport", spotsPerDate);

        //Act
        AssignmentsLogger assignement = autoAssigner.assign(studentList, workshopslist);

        //Assert
        //Student
        assertFalse(student.equals(null));
        assertFalse(student.equals(student3));
        assertTrue(student.equals(student2));
        assertTrue(student.equals(student));
        assertFalse(student.equals(w1));

        //assignement
        assertFalse(assignement.equals(null));

        //workshop
        assertFalse(w1.equals(null));
        assertFalse(w1.equals(w3));
        assertTrue(w1.equals(w2));
        assertTrue(w1.equals(w1));
        assertFalse(w1.equals(student));

        //getclass
        assertTrue(student.getClass().equals(student2.getClass()));
        assertTrue(student.getClass().equals(student.getClass()));
        assertFalse(student.getClass().equals(w1.getClass()));
        assertTrue(w1.getClass().equals(w2.getClass()));
        assertTrue(w1.getClass().equals(w1.getClass()));
    }


    @Test
    public void verifyspotsperdate() {

        //Arrange
        ZonedDateTime days = date(2022, 12, 3, 9, 59);
        spotsPerDate.put(days, 10);

        Workshop w1 = new Workshop(10, "Couture", spotsPerDate);

        //Act

        //Assert
        assertEquals(w1.getSpotsPerDate().get(days), spotsPerDate.get(days));
        assertEquals(w1.getSpotsPerDate().size(), spotsPerDate.size());
        assertFalse(w1.getSpotsPerDate().equals(null));
        assertTrue(w1.getSpotsPerDate().size() >= 1);
        w1.getSpotsPerDate().containsValue(10);

    }


    @Test
    public void verifynextdate() {

        //Arrange
        ZonedDateTime days = date(2022, 12, 3, 9, 59);
        ZonedDateTime days2 = date(2022, 12, 3, 10, 59);
        spotsPerDate.put(days, 0);
        spotsPerDate.put(days2, 10);

        Workshop w1 = new Workshop(10, "Couture", spotsPerDate);

        //Act

        //Assert
        assertEquals(w1.getNextAvailableDate(), days2);


    }

}
