package com.gamsion.chris.encryptor;

public class Balance {
	public static int balance(int numb, int mod){
		while(numb<0)numb+=mod;
		return numb % mod;
	}

}
