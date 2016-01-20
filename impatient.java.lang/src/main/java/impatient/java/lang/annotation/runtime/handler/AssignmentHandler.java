package impatient.java.lang.annotation.runtime.handler;

import impatient.java.lang.annotation.Assignment;
import impatient.java.lang.annotation.AssignmentUsage;

public class AssignmentHandler {
	
	public static void main(String[] args){
		Assignment assignment = AssignmentUsage.class.getAnnotation(Assignment.class);
		
		System.out.println(assignment.assignee());
	}

	
}
