package application;

import java.io.Serializable;

public class SavedParams implements Serializable {
	String ipSend ="";
	String portSend ="";
	String ipReceive ="";
	String portReceive ="";
	String [] packets;
	
	public SavedParams (){

		
	}
	
	void setPacketArray(String[] pcks) {
		this.packets = pcks;
	}
	
	String[] getPacketArray() {
		return this.packets;
	}
	
	
	void setIpSend(String idS) {
		this.ipSend = idS;
	}
	void setIpReceive(String idR) {
		this.ipReceive = idR;
	}
	void setPortSend(String pS) {
		this.portSend = pS;
	}
	void setPortReceive(String pR) {
		this.portReceive = pR;
	}
	
	
	String getIpSend() {
		return this.ipSend;
	}
	String getIpReceive() {
		return this.ipReceive;
	}
	String getPortSend() {
		return this.portSend;
	}
	String getPortReceive() {
		return this.portReceive;
	}
}
