package impatient.java.annotation.runtime.handler;

import impatient.java.annotation.Assignment;
import impatient.java.annotation.AssignmentUsage;

public class AssignmentHandler {
	
	public static void main(String[] args){
		Assignment assignment = AssignmentUsage.class.getAnnotation(Assignment.class);
		
		System.out.println(assignment.assignee());
	}

	
}
