package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Java8Example {
	
	private enum Status {
        OPEN, CLOSED
    };
    
    private static final class Task {
        private final Status status;
        private final Integer points;

        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }
        
        public Integer getPoints() {
            return points;
        }
        
        public Status getStatus() {
            return status;
        }
        
        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }
	
	@Target( ElementType.TYPE )
	@Retention( RetentionPolicy.RUNTIME )
	public @interface Filters {
		Filter[] value();
    }

	@Target( ElementType.TYPE )
	@Retention( RetentionPolicy.RUNTIME )
	@Repeatable( Filters.class )
	public @interface Filter {
	      String value();
	};
	
	@Filter( "filter1" )
	@Filter( "filter2" )
	public interface Filterable {       
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String,String> stateMap = new Hashtable<String,String>();

		stateMap.put("MN","01");
		stateMap.put("IL","02");
		stateMap.put("WI","03");
		stateMap.put("IA","04");
		
		try 
		{
			//Lambda Expression
			Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
			Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
			
			//Repeating annotations
			for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
				   System.out.println( filter.value() );
			}
			
			//using getordefault
			System.out.println(stateMap.getOrDefault("MN", ""));
			
			//using optional
			Optional< String > firstName = Optional.of( "Tom" );
			System.out.println( "First Name is set? " + firstName.isPresent() );        
			System.out.println( "First Name: " + firstName.orElseGet( () -> "[none]" ) ); 
			System.out.println( firstName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
			System.out.println();
			
			final Collection< Task > tasks = Arrays.asList(
				    new Task( Status.OPEN, 5 ),
				    new Task( Status.OPEN, 13 ),
				    new Task( Status.CLOSED, 8 ) 
				);
			
			// Calculate total points of all active tasks using sum()
			final long totalPointsOfOpenTasks = tasks
			    .stream()
			    .filter( task -> task.getStatus() == Status.OPEN )
			    .mapToInt( Task::getPoints )
			    .sum();
			        
			System.out.println( "Total points: " + totalPointsOfOpenTasks );
			
			// Calculate total points of all tasks
			final double totalPoints = tasks
			   .stream()
			   .parallel()
			   .map( task -> task.getPoints() ) // or map( Task::getPoints ) 
			   .reduce( 0, Integer::sum );
			    
			System.out.println( "Total points (all tasks): " + totalPoints );
			
			// DateTime API
			final Clock clock = Clock.systemUTC();
			System.out.println( clock.instant() );
			System.out.println( clock.millis() );
			
			final LocalDate date = LocalDate.now();
			final LocalDate dateFromClock = LocalDate.now( clock );
			        
			System.out.println( date );
			System.out.println( dateFromClock );
			        
			final LocalTime time = LocalTime.now();
			final LocalTime timeFromClock = LocalTime.now( clock );
			        
			System.out.println( time );
			System.out.println( timeFromClock );
			
			Result result = JUnitCore.runClasses(TestAssertions.class);
			
		      for (Failure failure : result.getFailures()) {
		         System.out.println(failure.toString());
		      }
				
		      System.out.println(result.wasSuccessful());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
