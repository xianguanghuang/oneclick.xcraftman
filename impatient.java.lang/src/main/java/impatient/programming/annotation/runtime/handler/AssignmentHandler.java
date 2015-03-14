package impatient.programming.annotation.runtime.handler;

import impatient.programming.annotation.Assignment;
import impatient.programming.annotation.AssignmentUsage;

public class AssignmentHandler {
	
	public static void main(String[] args){
		Assignment assignment = AssignmentUsage.class.getAnnotation(Assignment.class);
		
		System.out.println(assignment.assignee());
	}

	
}
