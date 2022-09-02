
class Odd extends Thread{
	Program p;
	Odd(Program k){p=k;}
	public void run(){
		p.displayOdd();
	}
}
class Even extends Thread{
	Program p;
	Even(Program k){p=k;}
	public void run(){
		p.displayEven();
		
	}
}
class Program{
	/*synchronized public void displayEven(){
		int i=0;
		while(i<=10){
			if(i%2==0){
				System.out.print(i+" ");
			}
			i++;
		}
		System.out.println();
	}
	synchronized public void displayOdd(){
		int i=0;
		while(i<=10){
			if(i%2!=0){
				System.out.print(i+" ");
			}
			i++;
		}
		System.out.println();
	}*/
	
	boolean flag=false;
	int count=0;
	int max=10;
	synchronized public void displayEven(){
		while(count<=max){
			while(!flag){
				try{
					wait();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			System.out.println("Odd Thread "+count);
			count++;
			flag = !flag;
			notify();
			
		}
	}
	synchronized public void displayOdd(){
		while(count<=max){
			while(flag){
				try{
					wait();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			System.out.println("Even Thread "+count);
			count++;
			flag = !flag;
			notify();
			
		}
	}
	
}
public class ThreadPractice{
	public static void main(String args[])throws Exception{
		Program p = new Program();
		Odd o = new Odd(p);
		Even e = new Even(p);
		
		o.start();
		e.start();
	}
}