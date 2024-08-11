package com.wallet.tutor.module09;

import com.wallet.tutor.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomTutor {

	public static void main(String[] args) {
		Random random = new Random();
		
		List<Integer> intList = random
				.ints()
				.limit(10)
				.boxed()
				.collect(Collectors.toList());
		
		int[] ints = random
				.ints()
				.limit(10)
				.toArray();
		
		IntStream intStream = Arrays.stream(ints);

		String joined = intStream
				.boxed()
				.map(Object::toString)
				.collect(Collectors.joining(","));

		intStream = Arrays.stream(ints);

		Logger.log(joined);

		Logger.log(intStream.average().getAsDouble());

		Logger.log(intList.stream()
				.mapToInt(Integer::intValue)
				.sum());
	}
}
