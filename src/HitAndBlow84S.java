import java.util.Random;
import java.util.Scanner;

public class HitAndBlow84S {

	public static void main(String[] args) {

		// ゲームの試行回数
		int guessCount = 0;
		final int MAX_GUESS = 10; // 最大試行回数
		
		// 秘密の答えを格納する配列
		int[] guessNumbers = new int[4];
		// プレイヤーの予想を格納する配列
		int[] secretNumbers = new int[4];

		// F-01
		System.out.println("☆☆ Hit & Blow ☆☆");
		// F-02
		// Randomクラスのインスタンスを作成
		Random rand = new Random();

		// 重複しない4つの数字を生成
		for (int i = 0; i < secretNumbers.length; i++) {
			int num;
			boolean isDuplicate;
			do {
				num = rand.nextInt(10); // 0から9までのランダムな数字を生成
				isDuplicate = false;
				// 生成された数字が既に配列に含まれているかチェック
				for (int j = 0; j < i; j++) {
					if (secretNumbers[j] == num) {
						isDuplicate = true;
						break;
					}
				}
			} while (isDuplicate); // 重複していれば再度生成
			secretNumbers[i] = num; // 重複していなければ配列に追加
		}

		// 生成された秘密の数字を確認（開発用。最終的には非表示にする）
		System.out.print("秘密の数字 (開発用): ");
		for (int num : secretNumbers) {
			System.out.print(num);
		}
		System.out.println();

		System.out.println("重複しない4桁の数字を当ててください。");

		// Scannerクラスの変数を作成
		Scanner scanner = new Scanner(System.in);


		// ゲームのメインループ（現時点では仮の実装）
		while (guessCount < MAX_GUESS) {
			// メッセージの例を「123」から「1234」に変更
			System.out.print((guessCount + 1) + "回目の予想を入力してください (例: 1234): ");
			String input = scanner.next(); // プレイヤーの入力を文字列として受け取る

			// F-04 入力値検証　プレイヤーの入力が4桁の数字であり、重複がないかを検証する。
			if (input.length() != 4) {
				System.out.println("入力は4桁の数字でなければなりません。");
				continue;
			}

			System.out.println("あなたの予想: " + input);

			boolean flg = false;
			for (int i = 0; i < 4; i++) {

				// エラーの場合
				if (flg == true) {
					break;
				}

				if ((input.charAt(i) - '0') >= 0 && (input.charAt(i) - '0') <= 9) {
					// 0～9の数値の場合の処理
					guessNumbers[i] = input.charAt(i) - '0';

				} else {
					// 0～9の数値以外の処理
					// もう1度入力させる処理
					System.out.println("入力は4桁の数字でなければなりません。");
					flg = true;
				}

			}

			if (flg) {
				continue;
			}

			// プレイヤーの配列の確認（開発用）
			for (int n : guessNumbers) {
				System.out.print(n);
			}
			System.out.println();

			// F-05: Hit/Blow判定
			int hit = 0; // Hitの数を初期化
			int blow = 0; // Blowの数を初期化

			// 外側のループ: 秘密の数字 (secretNumbers) を0番目から順に見ていく
			for (int i = 0; i < 4; i++) { // i は secretNumbers の添字番号

				// 内側のループ: プレイヤーの予想数字 (guessNumbers) を0番目から順に見ていく
				for (int j = 0; j < 4; j++) { // j は guessNumbers の添字番号
					// もし、秘密の数字と予想数字の「値」が同じだったら？
					if (secretNumbers[i] == guessNumbers[j]) {
						// さらに、その数字の「位置」（添字）も同じだったら？
						if (i == j) {
							hit++; // Hit数を1増やす (位置も数字も一致)
						} else {
							blow++; // Blow数を1増やす (数字は一致するが位置が違う)
						}

						// HitまたはBlowが確定したら、次の秘密の数字の判定に移るため、内側のループを抜ける。
						break;
					}
				}
			}

			// F-07 ゲームクリア判定
			if (hit == 4) {
				System.out.println("おめでとうございます！" + hit + " HIT " + blow + " Blow！正解です！");
				break; // ゲームクリアなのでループを抜ける
			}
			
			// F-08 試行回数カウント
			guessCount++; // 試行回数を増やす
		}
		

	}

}
