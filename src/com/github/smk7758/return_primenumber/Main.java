package com.github.smk7758.return_primenumber;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {

		// 素数の取得。
		List<Integer> primenumbers = new ArrayList<>();
		for (int i = 1; i < 10000; i++) {
			if (isPrimeNumber(i)) primenumbers.add(i);
		}
		// outputNumbers(primenumbers);

		// 素数を逆にした数の取得。
		List<Integer> reverse_primenumbers = new ArrayList<>();
		reverse_primenumbers = reverseNumbers(primenumbers);
		// outputNumbers(reverse_primenumbers);

		// 素数を逆にした数で素数であるものを取得。
		List<Integer> real_reverse_primenumbers = new ArrayList<>();
		for (int i = 1; i < reverse_primenumbers.size(); i++) {
			int number = reverse_primenumbers.get(i);
			if (isPrimeNumber(number)) real_reverse_primenumbers.add(number);
		}
		// outputNumbers(real_reverse_primenumbers);
		List<Integer> real_primenumbers = reverseNumbers(real_reverse_primenumbers);

		// 出力。
		LocalDateTime now = LocalDateTime.now();
		Path path = Paths.get("F:\\users\\smk7758\\Desktop",
				"return_primenumber_" + now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth() + "_"
						+ now.getHour() + "-" + now.getMinute() + "_" + now.getSecond() + ".txt");
		BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		int real_primenumber_counter = 0;
		while (real_primenumber_counter < real_primenumbers.size()
				&& real_primenumber_counter < real_reverse_primenumbers.size()) {
			bw.write(real_primenumbers.get(real_primenumber_counter) + " | "
					+ real_reverse_primenumbers.get(real_primenumber_counter));
			bw.newLine();
			real_primenumber_counter += 1;
		}
		bw.write("Count: " + real_primenumber_counter);
		bw.flush();
		bw.close();
		System.out.println("Finish.");
	}

	public static void outputNumbers(List<Integer> numbers) {
		String numbers_s = "";
		for (int number : numbers) {
			numbers_s += number + ", ";
		}
		// System.out.println(numbers_s);
	}

	public static List<Integer> reverseNumbers(List<Integer> numbers) {
		List<Integer> reverse_numbers = new ArrayList<>();
		for (Integer number : numbers) {
			reverse_numbers.add(reverseNumber(number));
		}
		return reverse_numbers;
	}

	public static Integer reverseNumber(Integer number) {
		String number_s_result = "";
		String number_s = number.toString();
		for (int i = 0; i < number_s.length(); i++) {
			number_s_result = number_s.charAt(i) + number_s_result;
		}
		return Integer.valueOf(number_s_result);
	}

	public static boolean isPrimeNumber(int number) {
		boolean is_prime = true;
		for (int j = 2; j < number; j++) {
			// System.out.println(j);
			if (number % j == 0) {
				is_prime = false;
				// System.out.println("NotPrime: " + i);
				break;
			}
		}
		return is_prime;
	}

	/*
	 * public static List<Integer> getPrimeNumbers(List<Integer> numbers) { boolean is_prime = true; for (int i = 1; i < 100; i++) { is_prime = true;
	 * for (int j = 2; j < i; j++) { System.out.println(j); if (i % j == 0) { is_prime = false; System.out.println("NotPrime: " + i); break; } } if
	 * (is_prime) numbers.add(i); } return numbers; }
	 */
}
