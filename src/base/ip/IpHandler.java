package base.ip;

public class IpHandler {
	private static IpHandler ipHandler = null;
	private static String ip = null;
	private IpHandler() {
	}
	
	public synchronized static IpHandler getInstance() {
		if(ipHandler == null) {
			ipHandler = new IpHandler();
		}
		return ipHandler;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getIp() {
		return this.ip;
	}
}
