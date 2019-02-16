package application;
	


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;


public class Main extends Application {
	TextField[] fieldHexs;
	Button saveState;
	SavedParams specsOfSaved;
	File savedParams;
	int selectedSending = 0;
	int lineNumber=0;
	TextField sendIp ;
	TextField sendPort ;
	TextField receiveIp ;
	TextField receivePort ;
	TextField packet ;
	TextField packetHex1 , packetHex2, packetHex3, packetHex4, packetHex5, packetHex6, packetHex7, packetHex8, packetHex9,packetHex10,
	packetHex11 , packetHex12, packetHex13, packetHex14, packetHex15, packetHex16, packetHex17, packetHex18, packetHex19;
	TextField delayDur;
	CheckBox loopCheck;
	TextArea incomming;
	Button sendStart;
	Button receiveStart;
	Button stopSender;
	Button stopReceiver;
	Button clearIncomming;
	ToggleButton soundStream;
	InputReceiver inputReceiver;
	InputSender inputSender;
	 DatagramSocket serverSocket = null;
	DatagramSocket clientSocket;
	CheckBox ch0,ch1, ch2, ch3, ch4, ch5, ch6, ch7,ch8,ch9,ch10,ch11, ch12, ch13, ch14, ch15, ch16, ch17,ch18,ch19;
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane dialog = null;
			try {
				dialog = (AnchorPane) FXMLLoader.load(Main.class.getResource("activation.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Scene scene = new Scene(dialog);
			scene.getStylesheets().add(getClass().getResource("ActivationStyle.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			String [] array = new String[]{"","","","","","","","","","","","","","","","","","","",""};
			
			
			savedParams = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\paramsUDP.ser");
			if(!savedParams.exists()){
				savedParams.createNewFile();
				FileOutputStream f = new FileOutputStream(savedParams);
				ObjectOutputStream o = new ObjectOutputStream(f);
				// Write objects to file
				SavedParams savedInitial = new SavedParams();
				savedInitial.setPacketArray(array);
				savedInitial.setIpSend("");
				savedInitial.setIpReceive("");
				savedInitial.setPortSend("");
				savedInitial.setPortReceive("");
				o.writeObject(savedInitial);
				o.close();
				f.close();
			}
			specsOfSaved = null;
			FileInputStream fi;
			try {
				fi = new FileInputStream(savedParams);
				ObjectInputStream oi = new ObjectInputStream(fi);
				while(fi.available() > 0){
					specsOfSaved = (SavedParams) oi.readObject();
					
				}
				oi.close();
				fi.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
//			 int num1 = 42;
//			  int num2 = 0xF;
//			  System.out.println("XOR Result =" +(num1 ^ num2));
//			  String binary1 = Integer.toBinaryString(num1);
//			  String binary2 = Integer.toBinaryString(num2);
//			  
//			  System.out.println(binary1 +" " +binary2);
//			  
//			  
//			 
//			  binary1 = binary1.substring(0, binary1.length()-1) + '1'; 
//			  int foo = Integer.parseInt(binary1, 2);
//			  
//			  System.out.println(foo);
			  
			saveState = (Button)scene.lookup("#saveState");
			
			
			sendIp = (TextField)scene.lookup("#sendIp");
			sendPort = (TextField)scene.lookup("#sendPort");
			receiveIp = (TextField)scene.lookup("#receiveIp");
			receivePort = (TextField)scene.lookup("#receivePort");
			packet = (TextField)scene.lookup("#packet");
			packetHex1 = (TextField)scene.lookup("#packetHex1");
			packetHex2 = (TextField)scene.lookup("#packetHex2");
			packetHex3 = (TextField)scene.lookup("#packetHex3");
			packetHex4 = (TextField)scene.lookup("#packetHex4");
			packetHex5 = (TextField)scene.lookup("#packetHex5");
			packetHex6 = (TextField)scene.lookup("#packetHex6");
			packetHex7 = (TextField)scene.lookup("#packetHex7");
			packetHex8 = (TextField)scene.lookup("#packetHex8");
			packetHex9 = (TextField)scene.lookup("#packetHex9");
			packetHex10 = (TextField)scene.lookup("#packetHex10");
			packetHex11 = (TextField)scene.lookup("#packetHex11");
			packetHex12 = (TextField)scene.lookup("#packetHex12");
			packetHex13 = (TextField)scene.lookup("#packetHex13");
			packetHex14 = (TextField)scene.lookup("#packetHex14");
			packetHex15 = (TextField)scene.lookup("#packetHex15");
			packetHex16 = (TextField)scene.lookup("#packetHex16");
			packetHex17 = (TextField)scene.lookup("#packetHex17");
			packetHex18 = (TextField)scene.lookup("#packetHex18");
			packetHex19 = (TextField)scene.lookup("#packetHex19");
			incomming = (TextArea)scene.lookup("#incomming");
			loopCheck = (CheckBox)scene.lookup("#loop");
			delayDur = (TextField)scene.lookup("#sendDelay");
			sendStart = (Button)scene.lookup("#sendStart");
			receiveStart = (Button)scene.lookup("#receiveStart");
			stopSender = (Button)scene.lookup("#stopSend");
			stopReceiver = (Button)scene.lookup("#stopReceive");
			clearIncomming = (Button)scene.lookup("#clearIncomming");
			soundStream = (ToggleButton)scene.lookup("#soundStream");
			
			
			ch0 = (CheckBox)scene.lookup("#ch0");
			ch1 = (CheckBox)scene.lookup("#ch1");
			ch2 = (CheckBox)scene.lookup("#ch2");
			ch3 = (CheckBox)scene.lookup("#ch3");
			ch4 = (CheckBox)scene.lookup("#ch4");
			ch5 = (CheckBox)scene.lookup("#ch5");
			ch6 = (CheckBox)scene.lookup("#ch6");
			ch7 = (CheckBox)scene.lookup("#ch7");
			ch8 = (CheckBox)scene.lookup("#ch8");
			ch9 = (CheckBox)scene.lookup("#ch9");
			ch10 = (CheckBox)scene.lookup("#ch10");
			ch11 = (CheckBox)scene.lookup("#ch11");
			ch12 = (CheckBox)scene.lookup("#ch12");
			ch13 = (CheckBox)scene.lookup("#ch13");
			ch14 = (CheckBox)scene.lookup("#ch14");
			ch15 = (CheckBox)scene.lookup("#ch15");
			ch16 = (CheckBox)scene.lookup("#ch16");
			ch17 = (CheckBox)scene.lookup("#ch17");
			ch18 = (CheckBox)scene.lookup("#ch18");
			ch19 = (CheckBox)scene.lookup("#ch19");
		} catch(Exception e) {
			e.printStackTrace();
		}
		fieldHexs = new TextField[20];
		fieldHexs[0] = packet;
		fieldHexs[1] = packetHex1;
		fieldHexs[2] = packetHex2;
		fieldHexs[3] = packetHex3;
		fieldHexs[4] = packetHex4;
		fieldHexs[5] = packetHex5;
		fieldHexs[6] = packetHex6;
		fieldHexs[7] = packetHex7;
		fieldHexs[8] = packetHex8;
		fieldHexs[9] = packetHex9;
		fieldHexs[10] = packetHex10;
		fieldHexs[11] = packetHex11;
		fieldHexs[12] = packetHex12;
		fieldHexs[13] = packetHex13;
		fieldHexs[14] = packetHex14;
		fieldHexs[15] = packetHex15;
		fieldHexs[16] = packetHex16;
		fieldHexs[17] = packetHex17;
		fieldHexs[18] = packetHex18;
		fieldHexs[19] = packetHex19;
		
		String[] rs = specsOfSaved.getPacketArray();
		for(int i = 0; i<rs.length; i++) {
			fieldHexs[i].setText(rs[i]);
		}
		
		sendIp.setText(specsOfSaved.getIpSend());
		receiveIp.setText(specsOfSaved.getIpReceive());
		sendPort.setText(specsOfSaved.getPortSend());
		receivePort.setText(specsOfSaved.getPortReceive());
		
		CheckBox[] chBoxs = new CheckBox[20];
		chBoxs[0] = ch0;
		chBoxs[1] = ch1;
		chBoxs[2] = ch2;
		chBoxs[3] = ch3;
		chBoxs[4] = ch4;
		chBoxs[5] = ch5;
		chBoxs[6] = ch6;
		chBoxs[7] = ch7;
		chBoxs[8] = ch8;
		chBoxs[9] = ch9;
		chBoxs[10] = ch10;
		chBoxs[11] = ch11;
		chBoxs[12] = ch12;
		chBoxs[13] = ch13;
		chBoxs[14] = ch14;
		chBoxs[15] = ch15;
		chBoxs[16] = ch16;
		chBoxs[17] = ch17;
		chBoxs[18] = ch18;
		chBoxs[19] = ch19;
		//int place = 0;
		for (int i = 0; i < chBoxs.length; i++) {
			int j = i;
			chBoxs[i].selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (newValue) {
						//place = i;
						for(int k = 0;k<chBoxs.length; k++) {
							if(k!=j) {
								selectedSending = j;
								chBoxs[k].setSelected(false);
							}
						}
					}
				}
			});
		}
		
		soundStream.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {	
				if(soundStream.isSelected()) {
					
				}else {
					
				}
			}
		});
		
		
		saveState.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {	
				String[] st = new String[20];
				for(int i = 0 ; i < fieldHexs.length; i++ ) {
					st[i] = fieldHexs[i].getText();
				}
				writeObjectSerial(st);
			}
		});
		
		clearIncomming.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {	
				incomming.clear();
				lineNumber = 0;
			}
		});
		
		
		inputReceiver = new InputReceiver();
		inputSender = new InputSender();
		stopSender.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {	
				inputSender.stop();
				if(clientSocket.isBound())
					clientSocket.close();
			}
		});
		
		stopReceiver.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {	
				inputReceiver.stop();
				if(serverSocket.isBound())
					serverSocket.close();
			}
		});
		sendStart.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {	
				if(loopCheck.isSelected()) {
					if(delayDur.getText().isEmpty()) {
						System.err.println("Duration girmen gerek");
					}
					else {
						try {
							clientSocket = new DatagramSocket();
						} catch (SocketException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						inputSender = new InputSender();
						inputSender.setDaemon(true);
						inputSender.start();
					}
				}
				else {
					try {
						clientSocket = new DatagramSocket();
					} catch (SocketException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(selectedSending>=1)
						sendPackageHex();
					else
						sendPackage();
				}
			}
		});
		receiveStart.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {	
				
				try {
					serverSocket = new DatagramSocket(Integer.parseInt(receivePort.getText().toString()));
				} catch (SocketException e) {
					e.printStackTrace();
				}
				lineNumber = 1;
//				receivePackage();
				inputReceiver = new InputReceiver();
				inputReceiver.setDaemon(true);
				inputReceiver.start();
			}
		});
		
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
	private class InputReceiver extends Thread {
		//int angle = 0;

		@Override
		public void run() {
	          
			while (true) {
		        receivePackage();
                lineNumber +=1;
				try {
					Thread.sleep(60);
				} catch (InterruptedException ex) {
					Logger.getLogger(InputReceiver.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		}
	}

   public class InputSender extends Thread {
		@Override
		public void run() {

			while (true) {   
					if(selectedSending == 0)
						sendPackage();
					else
						sendPackageHex();
					
					try {
						Thread.sleep(Integer.parseInt(delayDur.getText().toString()));
					} catch (InterruptedException ex) {
						Logger.getLogger(InputSender.class.getName()).log(Level.SEVERE, null, ex);
					}
				
	    	}			    		
				    	
		
		}
   }
   
   private void receivePackage() {
	   byte[] receiveData ;
	   if(selectedSending>=1) {
		   String[] array = fieldHexs[selectedSending].getText().split(" ");
		   receiveData = new byte[array.length+100];
	   }
	   else
		   receiveData = new byte[packet.getText().length()+100];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
    		//System.err.println(serverSocket.);
			serverSocket.receive(receivePacket);
			//System.err.println(serverSocket.getReceiveBufferSize());
		} catch (IOException e) {
			e.printStackTrace();
		}
        String sentence = new String( receivePacket.getData());
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        
    	char[] inputFromRobot = sentence.toCharArray();
        String comming = "";
        comming = comming + "\n		";
    	for(int i = 0; i<inputFromRobot.length; i++) {
    		comming = comming + decToHex(inputFromRobot[i]).substring(decToHex(inputFromRobot[i]).length()-2) + " ";
    	}
    	
    	comming = comming + "\n		";
 
    	
    	for(int i = 0; i<inputFromRobot.length; i++) {
    		if(selectedSending>=1)
    			try {
        			comming = comming + (int)inputFromRobot[i] + " ";
    			}catch (Exception e) {
					// TODO: handle exception
    				comming = comming + (int)99 + " ";
				}
    		else
    			comming = comming + inputFromRobot[i] + "";
    	}

        incomming.appendText(lineNumber+". Received: " +  sdf.format(cal.getTime()) + comming + "\n");
   }
   
   private void sendPackage() {
	   byte [] robota = fieldHexs[selectedSending].getText().getBytes();
       InetAddress serverAddr;
		try {
			serverAddr = InetAddress.getByName(sendIp.getText().toString());
			DatagramPacket dp = null;
			dp = new DatagramPacket(robota, 0, robota.length, serverAddr, Integer.parseInt(sendPort.getText().toString()));
			clientSocket.send(dp);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (clientSocket != null) {
			}
		}
   }
//   
   private void sendPackageHex() {
	   String[] array = fieldHexs[selectedSending].getText().split(" ");

		byte[] bytes = new byte[array.length];
		for ( int i = 0; i < array.length; i++ ) {
			try {
				 bytes[i] = (byte)Integer.parseInt( array[i], 16 );
			}catch (Exception e) {
				// TODO: handle exception
				bytes[i] = 99; ////Duzgun olmuyanda 99 desek default
			}
		   
		}
	   //byte [] robota = packetHex.getText().getBytes();
       InetAddress serverAddr;
		try {
			serverAddr = InetAddress.getByName(sendIp.getText().toString());
			DatagramPacket dp = null;
			dp = new DatagramPacket(bytes, 0, bytes.length, serverAddr, Integer.parseInt(sendPort.getText().toString()));
			clientSocket.send(dp);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (clientSocket != null) {
			}
		}
   }
   
   private void writeObjectSerial(String[] pcks){
   	FileOutputStream f;
		try {
			f = new FileOutputStream(savedParams);
			ObjectOutputStream o = new ObjectOutputStream(f);
			specsOfSaved.setPacketArray(pcks);
			specsOfSaved.setIpSend(sendIp.getText());
			specsOfSaved.setIpReceive(receiveIp.getText());
			specsOfSaved.setPortSend(sendPort.getText());
			specsOfSaved.setPortReceive(receivePort.getText());

			o.writeObject(specsOfSaved);
			o.close();
			f.close();
		} catch (FileNotFoundException e1) {
		
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
   
   
   
   private static final int sizeOfIntInHalfBytes = 8;
  	private static final int numberOfBitsInAHalfByte = 4;
  	private static final int halfByte = 0x0F;
  	public static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
  	public static String decToHex(int dec) {
  		StringBuilder hexBuilder = new StringBuilder(sizeOfIntInHalfBytes);
  		hexBuilder.setLength(sizeOfIntInHalfBytes);
  		for (int i = sizeOfIntInHalfBytes - 1; i >= 0; --i) {
  			int j = dec & halfByte;
  			hexBuilder.setCharAt(i, hexDigits[j]);
  			dec >>= numberOfBitsInAHalfByte;
  		}
  		
  		return hexBuilder.toString();
  	}
}

