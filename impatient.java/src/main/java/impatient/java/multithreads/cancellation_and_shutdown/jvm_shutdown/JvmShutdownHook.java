package impatient.java.multithreads.cancellation_and_shutdown.jvm_shutdown;

public class JvmShutdownHook {
	
	public void stop() throws InterruptedException{
		
	}

	public void start(){
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				try{
					JvmShutdownHook.this.stop();
				}catch(InterruptedException ignore){
					
				}
			}
		});
	}
	
	

}




