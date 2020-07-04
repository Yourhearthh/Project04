package notepad.mythread;

import jdialog.CalendarDialog;

public class TimeThread implements Runnable{
	private CalendarDialog dialog;
	private boolean loop=true;
	
    public TimeThread(CalendarDialog dialog) {
		this.dialog=dialog;
	}
    
    public void setloop(boolean loop){
    	this.loop=loop;
    }
	
	
	
	@Override
	public void run() {
		while(loop){
			this.dialog.setlblTime();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
