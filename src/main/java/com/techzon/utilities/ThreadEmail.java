package com.techzon.utilities;

public class ThreadEmail extends Thread {

	private InvioMail invioEmail;
	
	public ThreadEmail(String emailDestinatario,String testoEmail,String oggettoEmail) {
		this.invioEmail=new InvioMail(emailDestinatario,testoEmail,oggettoEmail);
	}
	
	public void run() {
		invioEmail.invia();
	}
}
