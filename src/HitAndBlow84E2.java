import java.util.Random;
import java.util.Scanner;

public class HitAndBlow84E2 {

	public static void main(String[] args) {
		
		// テストコメント

		int guessCount = 0;
		final int MAX_GUESS = 10;
		
		final int KETA = 2;
		int[] guessNumbers = new int[KETA];
		int[] secretNumbers = new int[KETA];
		
		final String ERR_MESSAGE_01 = "入力は" + KETA + "桁の数字でなければなりません。";
		final String ERR_MESSAGE_02 = "数字は重複しないように入力してください。";
		
		// F-01
		System.out.println("☆☆ Hit & Blow ☆☆");
		// F-02
		Random rand = new Random();

		for (int i = 0; i < secretNumbers.length; i++) {
			int num;
			boolean isDuplicate;
			do {
				num = rand.nextInt(10);
				isDuplicate = false;
				for (int j = 0; j < i; j++) {
					if (secretNumbers[j] == num) {
						isDuplicate = true;
						break;
					}
				}
			} while (isDuplicate);
			secretNumbers[i] = num;
		}

		System.out.println("重複しない" + KETA + "桁の数字を当ててください。");
		Scanner scanner = new Scanner(System.in);

		while (guessCount < MAX_GUESS) {
			System.out.print((guessCount + 1) + "回目の予想を入力してください (例: 1234): ");
			String input = scanner.next();

			// F-04
			if (input.length() != KETA) {
				System.out.println(ERR_MESSAGE_01);
				continue;
			}

			boolean flg = false;
			for (int i = 0; i < KETA; i++) {
				int num = input.charAt(i) - '0';
				
				if (num >= 0 && num <= 9) {
					for (int j = 0; j < i; j++) {
						if (guessNumbers[j] == num) {
							System.out.println(ERR_MESSAGE_02);
							flg = true;
							break;
						}
					}
					guessNumbers[i] = num;

				} else {
					System.out.println(ERR_MESSAGE_01);
					flg = true;
					break;
				}
			}

			if (flg) {
				continue;
			}

			// F-05
			int hit = 0;
			int blow = 0;

			for (int i = 0; i < KETA; i++) {
				for (int j = 0; j < KETA; j++) {
					if (secretNumbers[i] == guessNumbers[j]) {
						if (i == j) {
							hit++; 
						} else {
							blow++;
						}
						break;
					}
				}
			}
			
			// ヒント表示[F-06]
			System.out.println(input + " -> Hit: [" + hit + "], Blow: [" + blow + "]");
			
			
			// F-07
			if (hit == KETA) {
				System.out.println("おめでとうございます！" + hit + " HIT " + blow + " Blow！正解です！");
				break;
			}
			// F-08
			guessCount++; 
		}
		
		// F-09
		if (guessCount >= MAX_GUESS) {
			int answer = 0; 
			for (int i = 0; i < secretNumbers.length; i++) {
				answer = answer * 10 + secretNumbers[i];
			}
			
			// F-11
			System.out.println("残念！ゲームオーバーです。正解は [" + answer  + "] でした。");
		}
		
	}
}
