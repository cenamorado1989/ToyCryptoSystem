package toyCryptoSystem;
import java.util.*;

  /*
   * Christian Enamorado
   * 10/5/2018
   * The program needs to be ran a few times in order to test whether or not
   * the orginal message is stored in the list of english message possibilities.
   * Once the program runs and it returns true, you can see that our list of english messages
   * contains the same string. 
   * Must be ran a few times, the brute force loop runs for only 40 iterations. In 40 iterations we can find the string.
   * 
   */
public class cryptoSystem {
	private static String message;
	private static String messageE;
	private static String messageD;
	private static String key;
	
	public static void main(String args[]) {
		
		message = generateMsg();
		System.out.println("Message: " + message + " Size: " + message.length());
		key = generateKey(message.length());
		System.out.println("Key: " + key + " Size: " + key.length());
		System.out.println();

		
		messageE = encryption(key, message);
		System.out.println("Encrypted Message: " + messageE + " Size: " + messageE.length());
		
		messageD = decryption(key, messageE);
		
		System.out.println("Decrypted Message: " + messageD + " Size: " + messageD.length());
		System.out.println();
		
		int i = 0;
		ArrayList<String> words = new ArrayList<>();
		//here
		while(i < 40) {
		words.add(bruteForce(messageE));
		//System.out.println(words.get(i));
		i++;
		}
		System.out.println(words.size());
		
		System.out.println("Original message: " + message);
		System.out.println("Best BF guess: " + words.get(containsWord(words, message)));
		
		
		
		
		
	}
	public static String generateKey(int length) {
		
		Random rand = new Random();
			
		int loop = ((length/2));
		String key2 = "";
		char one = (char)(rand.nextInt(127) + 32);
		char two = (char)(rand.nextInt(127) + 32);
		String key = "" + one + two;	
		while( loop > 0) {
			key2 += key;
			loop--;
		}
		
		return key2;
	}
	public static String generateMsg() {
		char let;
		Random rand = new Random();
		int loop = (rand.nextInt(30/2) + 1) * 2;
		
		//System.out.println("String length" + loop);
		String randWord = "";
		
		while( loop > 0) {
			if(((rand.nextInt(20) + 1) % 2) == 1) {
			let = (char)(rand.nextInt(26) + 65);// can be reduced
			} else {
			let = (char)(rand.nextInt(26) + 97);// can be reduced
			}
			randWord += let;
			loop--;
		}
		
		
		return randWord;
	}
	public static String encryption(String key, String message) {
		int loop = 0;
		String messageE = "";
		while(loop <= message.length() - 1) {
			messageE += (char)((message.charAt(loop))^(key.charAt(loop)));
			loop++;
		}
		
		
		return messageE;
	}
	public static String decryption(String key, String messageE) {
		int loop = 0;
		String messageD = "";
		while(loop <= message.length() - 1) {
			messageD += (char)((messageE.charAt(loop))^(key.charAt(loop)));
			loop++;
		}
		
		return messageD;
	}
	public static String bruteForce(String messageE) {
		
		Random rand = new Random();
		int loop = ((messageE.length()/2));
		String bfMessage = "";
		char one;
		char two;
		int run = 0;
		String fakeKey = "";
		
		while (run == 0) {
		one = (char)(rand.nextInt(127) + 32);
		two = (char)(rand.nextInt(127) + 32);
		fakeKey = "";
		String key = "" + one + two;

			
		while( loop > 0) {
			fakeKey += key;
			loop--;
		}
		loop = ((messageE.length()/2));
		//System.out.println(fakeKey + " " + fakeKey.length() + " and encrypted message " + messageE + " " + messageE.length() + " Message2:" + bfMessage );
		
		int loop2 = 0;
		int flag = 0;
		do {
			bfMessage += (char)(messageE.charAt(loop2)^fakeKey.charAt(loop2));
			if(!(bfMessage.matches("[a-zA-Z]*"))) {
				//System.out.println("Not an english message")
				;
				bfMessage = "";
				break;
			} else {
				loop2++;
				if(loop2 == messageE.length()) {
					flag = 1;
				}
			}
		} while ( loop2 < messageE.length());		
	
		if(flag == 1) {
			run = 1;
		}
		
		}
		//System.out.println(bfMessage);
		return bfMessage;
		
	}
	public static int containsWord(ArrayList<String> list, String message) {
		boolean isTrue = false;
		int store = 0;
		int loop = 0;
		while(loop < list.size()) {
			if(message.compareTo(list.get(loop)) == 0){
				isTrue = true;
				store = loop;
			}	
			loop++;
			
		}
		//return isTrue;
		System.out.println("English text message contained: " + isTrue);
		
		return store;
	}
	
}
