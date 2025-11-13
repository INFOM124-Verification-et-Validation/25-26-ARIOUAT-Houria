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
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.*;

import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;
import static delft.Field.*;
import static delft.Property.*;
import static delft.SportsHallPlanner.planHalls;

class SportsHallPlannerTests {

    //Arrange
    //requirements to create a request and sporthall
    Set<Property> propertiesforrequest = new HashSet<>();
    Set<Property> propertiesforrequest2 = new HashSet<>();
    Set<Property> propertiesforsport = new HashSet<>();
    Set<Property> propertiesforsport1 = new HashSet<>();
    List<Request> requestlist = new ArrayList<>();
    List<SportsHall> sporthalllist = new ArrayList<>();

    @Test
    public void requestassigntohall(){
        //Arrange
        Field requiredFieldType = BADMINTON;
        Map<Field, Integer> fields = new HashMap<>();
        int minNumberOfFields = 1;
        Property p1 = HAS_RESTAURANT;
        Field f1 = BADMINTON;
        propertiesforrequest.add(p1);
        propertiesforsport.add(p1);
        fields.put(f1, 1);
        //creating a request
        Request request = new Request(propertiesforrequest, requiredFieldType, minNumberOfFields);
        SportsHall sporthall = new SportsHall(propertiesforsport, fields);
        //Adding them to lists
        sporthalllist.add(sporthall);
        requestlist.add(request);

        //Act
        //function call
        Map<SportsHall, Request> plannedrequest = SportsHallPlanner.planHalls(requestlist, sporthalllist);


        //Assert for request
        assertTrue(request.getProperties().equals(propertiesforrequest));
        assertFalse(request.getProperties().isEmpty());
        assertTrue(request.getRequiredFieldType().equals(requiredFieldType));
        assertTrue(request.getMinNumberOfFields() == 1);
        assertTrue(request.toString().equals("Request{" +
                "properties=" + propertiesforrequest +
                ", requiredFieldType=" + requiredFieldType +
                ", minNumberOfFields=" + minNumberOfFields +
                '}'));
        //assert for sport
        assertTrue(sporthall.toString().equals("SportsHall{" +
                "properties=" + propertiesforsport +
                ", fields=" + fields +
                '}'));
        //assert the assignment
        assertTrue(plannedrequest.containsValue(request));

    }

    @Test
    public void cannotassign(){
        //Arrange
        Field requiredFieldType = BADMINTON;
        Map<Field, Integer> fields = new HashMap<>();
        int minNumberOfFields = 1;
        Property p1 = HAS_RESTAURANT;
        Field f1 = TENNIS;
        propertiesforrequest.add(p1);
        propertiesforsport.add(p1);
        fields.put(f1, 1);
        //creating a request
        Request request = new Request(propertiesforrequest, requiredFieldType, minNumberOfFields);
        SportsHall sporthall = new SportsHall(propertiesforsport, fields);
        //Adding them to lists
        sporthalllist.add(sporthall);
        requestlist.add(request);

        //Act
        //function call
        Map<SportsHall, Request> plannedrequest = SportsHallPlanner.planHalls(requestlist, sporthalllist);

        //assert the assignment
        assertNull(plannedrequest);

    }


    @Test
    public void assignmorethanone(){
        //Arrange
        Field requiredFieldType = BADMINTON;
        Field requiredFieldType2 = VOLLEYBALL;
        Map<Field, Integer> fields = new HashMap<>();
        Map<Field, Integer> fields2 = new HashMap<>();
        int minNumberOfFields = 1;
        Property p1 = HAS_RESTAURANT;
        Field f1 = VOLLEYBALL;
        Field f2 = BADMINTON;
        propertiesforrequest.add(p1);
        propertiesforsport.add(p1);
        fields.put(f1, 1);
        fields2.put(f2, 1);
        //creating a request
        Request request = new Request(propertiesforrequest, requiredFieldType, minNumberOfFields);
        Request request2 = new Request(propertiesforrequest, requiredFieldType2, minNumberOfFields);
        SportsHall hall1 = new SportsHall(propertiesforsport, fields);
        SportsHall hall2 = new SportsHall(propertiesforsport, fields2);
        //Adding them to lists
        sporthalllist.add(hall1);
        sporthalllist.add(hall2);
        requestlist.add(request);
        requestlist.add(request2);

        //Act
        //function call
        Map<SportsHall, Request> plannedrequest = SportsHallPlanner.planHalls(requestlist, sporthalllist);
        System.out.println(plannedrequest.toString());


        //assert the assignment
        assert(plannedrequest.size() == 2);
        assert(request != request2);
        assert(plannedrequest.containsValue(request));
        assert(plannedrequest.containsValue(request2));


    }

    @Test
    public void assingonerequesttomorethanonehall(){
        //Arrange
        Field requiredFieldType = BADMINTON;
        Property p1 = HAS_RESTAURANT;
        int minNumberOfFields = 2;
        Map<Field, Integer> fields = new HashMap<>();
        Map<Field, Integer> fields2 = new HashMap<>();
        Map<Field, Integer> fields3 = new HashMap<>();
        Field f1 = VOLLEYBALL;
        Field f2 = BADMINTON;
        Field f3 = BADMINTON;
        propertiesforrequest.add(p1);
        propertiesforsport.add(p1);
        fields.put(f1, 1);
        fields2.put(f2, 4);
        fields3.put(f3, 2);
        //creating a request
        Request request = new Request(propertiesforrequest, requiredFieldType, minNumberOfFields);
        SportsHall hall1 = new SportsHall(propertiesforsport, fields);
        SportsHall hall2 = new SportsHall(propertiesforsport, fields2);
        SportsHall hall3 = new SportsHall(propertiesforsport, fields3);
        //Adding them to lists
        sporthalllist.add(hall1);
        sporthalllist.add(hall2);
        sporthalllist.add(hall3);
        requestlist.add(request);

        //Act
        //function call
        Map<SportsHall, Request> plannedrequest = SportsHallPlanner.planHalls(requestlist, sporthalllist);
        System.out.println(plannedrequest.toString());


        //assert the assignment
        assertTrue(plannedrequest.size() == 1);
        assertTrue(hall1 != hall2);
        assertTrue(hall1 != hall3);
        assertTrue(hall2 != hall3);
        assert(plannedrequest.containsKey(hall2));
        assert !plannedrequest.containsKey(hall3);


    }


    @Test
    public void verifyequals(){
        //Arrange
        Map<Field, Integer> fields = new HashMap<>();
        Map<Field, Integer> fields2 = new HashMap<>();
        Map<Field, Integer> fields3 = new HashMap<>();
        Property p1 = HAS_RESTAURANT;
        Property p2 = HAS_RESTAURANT;
        Property p3 = CLOSE_PUBLIC_TRANSPORT;
        Field f1 = BADMINTON;
        Field f2 = TENNIS;

        propertiesforrequest.add(p1);
        propertiesforrequest2.add(p3);
        propertiesforsport.add(p1);
        propertiesforsport1.add(p3);
        fields.put(f1, 1);
        fields2.put(f2, 1);
        fields3.put(f1,1);
        //creating a request
        Request request = new Request(propertiesforrequest, BADMINTON, 2);
        Request request2 = new Request(propertiesforrequest2, BADMINTON, 2);
        Request request3 = new Request(propertiesforrequest, BADMINTON, 4);
        Request request4 = new Request(propertiesforrequest, TENNIS, 2);

        SportsHall s1 = new SportsHall(propertiesforsport, fields);
        SportsHall s2 = new SportsHall(propertiesforsport, fields2);
        SportsHall s3 = new SportsHall(propertiesforsport, fields3);
        SportsHall s4 = new SportsHall(propertiesforsport1, fields3);

        //Adding them to lists
        sporthalllist.add(s1);
        requestlist.add(request);

        //Act


        //Assert properties
        assertFalse(request.equals(null));
        assertTrue(p1.equals(p1));
        assertTrue(p2.equals(p1));
        assertFalse(p1.equals(p3));

        //Assert request
        assertTrue(request.equals(request));
        assertFalse(request.equals(request2));
        assertFalse(request2.equals(request));
        assertFalse(request.equals(request3));
        assertFalse(request.equals(request4));
        assertFalse(request.equals(null));
        assertFalse(request.equals(s1));

        //assert sporthall
        assertTrue(s1.equals(s1));
        assertFalse(s1.equals(s2));
        assertFalse(s2.equals(s1));
        assertTrue(s1.equals(s3));
        assertFalse(s3.equals(s4));
        assertFalse(s1.equals(request));
        assertFalse(s1.equals(null));


        //assert class
        assertEquals(request.getClass(), request2.getClass());
        assertEquals(request.getClass(), request.getClass());
        assertFalse(request.getClass().equals(s1.getClass()));


    }

    @Test
    public void verifyehashcode(){
        //Arrange
        Map<Field, Integer> fields = new HashMap<>();
        Map<Field, Integer> fields2 = new HashMap<>();
        Map<Field, Integer> fields3 = new HashMap<>();
        Property p1 = HAS_RESTAURANT;
        Property p2 = HAS_RESTAURANT;
        Property p3 = CLOSE_PUBLIC_TRANSPORT;
        Field f1 = BADMINTON;
        Field f2 = TENNIS;

        propertiesforrequest.add(p1);
        propertiesforrequest2.add(p3);
        propertiesforsport.add(p1);
        fields.put(f1, 1);
        fields2.put(f2, 1);
        fields3.put(f1,1);

        //creating a request
        Request request = new Request(propertiesforrequest, BADMINTON, 2);
        Request request2 = new Request(propertiesforrequest2, BADMINTON, 2);
        Request request3 = new Request(propertiesforrequest, BADMINTON, 4);
        Request request4 = new Request(propertiesforrequest, TENNIS, 2);
        Request request6 = new Request(propertiesforrequest, BADMINTON, 8);

        SportsHall s1 = new SportsHall(propertiesforsport, fields);
        SportsHall s2 = new SportsHall(propertiesforsport, fields2);
        SportsHall s3 = new SportsHall(propertiesforsport, fields3);

        //Adding them to lists
        sporthalllist.add(s1);
        requestlist.add(request);

        //Act

        //Assert properties
        assertEquals(p1.hashCode(), p2.hashCode());
        assertEquals(p1.hashCode(), p1.hashCode());
        assertNotEquals(p1.hashCode(), p3.hashCode());

        //request hashCode
        assertEquals(request.hashCode(), request.hashCode());
        assertNotEquals(request.hashCode(), request2.hashCode());
        assertNotEquals(request2.hashCode(), request.hashCode());
        assertNotEquals(request.hashCode(), request3.hashCode());
        assertNotEquals(request.hashCode(), request4.hashCode());
        assertNotEquals(request.hashCode(), request6.hashCode());
        assertFalse(request.hashCode() == 0);

        //sporthall hashCode
        assertEquals(s1.hashCode(), s1.hashCode());
        assertNotEquals(s1.hashCode(), s2.hashCode());
        assertEquals(s1.hashCode(), s3.hashCode());
        assertFalse(s1.hashCode() == 0);


    }


    @Test
    public void doublon(){
        //Arrange
        Field requiredFieldType = BADMINTON;
        Map<Field, Integer> fields = new HashMap<>();
        Property p1 = HAS_RESTAURANT;
        Field f1 = BADMINTON;
        propertiesforrequest.add(p1);
        propertiesforsport.add(p1);
        fields.put(f1, 1);
        //creating a request
        Request request = new Request(propertiesforrequest, requiredFieldType, 2);
        SportsHall sporthall = new SportsHall(propertiesforsport, fields);
        SportsHall sporthall2 = new SportsHall(propertiesforsport, fields);
        //Adding them to lists
        sporthalllist.add(sporthall);
        sporthalllist.add(sporthall2);
        requestlist.add(request);

        //Act



        //Assert properties
        assertThrows(IllegalArgumentException.class, () -> {
            SportsHallPlanner.planHalls(requestlist, sporthalllist);
        });



    }

    //Fait pas gpt pas obligatoire mais ça permet de tout cover
    @Test
    public void privateConstructorCanBeCalledViaReflection() throws Exception {
        Constructor<SportsHallPlanner> constructor = SportsHallPlanner.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(); // ne lance rien, juste exécute la ligne
    }




}
