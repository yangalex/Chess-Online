package Client.Windows;

public class DashBoardPlayersThread extends Thread{
	private DashBoardWindow dbw;
	public DashBoardPlayersThread(DashBoardWindow dbw){
		this.dbw = dbw;
	}
	@Override
	public void run(){
		while(true){
			dbw.requestOnlinePlayers();
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
