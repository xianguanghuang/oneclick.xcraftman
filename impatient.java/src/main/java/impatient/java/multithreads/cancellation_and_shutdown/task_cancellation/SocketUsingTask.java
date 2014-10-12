package impatient.java.multithreads.cancellation_and_shutdown.task_cancellation;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.jcip.annotations.GuardedBy;

/*
 * 
 * socket的read 方法是没有cancel 方法
 * 因此可以通过用 Future 封装 使用socket的任务，让其支持cancle/interrupt
 * 
 * 其实本质是调用socket的close方法
 * 使用future封装原来没有cancel方法 的 socket使用线程*/
interface CancellableTask<T> extends Callable<T> {
	void cancel();
	RunnableFuture<T> newTask();
}

class CancellingExecutor extends ThreadPoolExecutor{

	public CancellingExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		// TODO Auto-generated constructor stub
	}
	
	protected<T> RunnableFuture<T> newTaskFor(Callable<T> callable){
		if(callable instanceof CancellableTask)
			return ((CancellableTask<T>)callable).newTask();
		else
			return super.newTaskFor(callable);
	}
	
}



public abstract class SocketUsingTask<T> implements CancellableTask<T> {
	
	@GuardedBy("this")
	private Socket socket;
	
	protected synchronized void setSocket(Socket s) { socket = s;}
	
	public synchronized void cancel() {
		try{
			if(socket != null)
				socket.close();
		}catch (IOException ignore){
			
		}
	}
	
	public RunnableFuture<T> newTask(){
		return new FutureTask<T>(this){
			public boolean cancel(boolean mayInterruptIfRunning){
				try{
					SocketUsingTask.this.cancel();
				}finally{
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}

}
