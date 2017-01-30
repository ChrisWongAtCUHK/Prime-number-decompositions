import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class PrimeNumberDecomposer {
	/**
	 * Return value: List of all prime factors of a given number n
	 */
	public static Long[] getAllPrimeFactors(long n) {
		List<Long> list = new ArrayList<>();
		for (BigInteger prime = BigInteger.valueOf(2L); n > 1L; prime = prime
				.nextProbablePrime()) {
			for (long factor = prime.longValue(); n % factor == 0L; n /= factor) {
				list.add(factor);
			}
		}
		return list.toArray(new Long[0]);
	}

	/**
	 * Return value: List containing two lists, first containg all prime factors
	 * without duplicates, second containing the count, how often each prime
	 * factor occured. Return code always contains two lists.
	 * 
	 * e.g.: getUniquePrimeFactorsWithCount(100) = {{2,5},{2,2}) // prime 2
	 * occurs 2 times, prime 2 occurs 5 times,
	 */
	public static Long[][] getUniquePrimeFactorsWithCount(long n) {
		Map<Long, Long> count = Arrays.stream(getAllPrimeFactors(n)).collect(
				Collectors.groupingBy(Function.identity(), TreeMap::new,
						Collectors.counting()));
		return new Long[][] { count.keySet().toArray(new Long[0]),
				count.values().toArray(new Long[0]) };
	}

	/**
	 * Return value: List containing product of same prime factors, e.g.: 45 =
	 * 3*3*5 ==> {3^2,5^1} == {9,5} e.g.: getUniquePrimeFactorsWithCount(100) =
	 * {{2,5},{2,2}) // prime 2 occurs 2 times, prime 2 occurs 5 times,
	 */
	public static Long[] getPrimeFactorPotencies(long n) {
		Long[][] uniquePrimeFactorsWithCount = getUniquePrimeFactorsWithCount(n);
		return IntStream
				.range(0, uniquePrimeFactorsWithCount[0].length)
				.mapToLong(
						i -> (long) Math.pow(uniquePrimeFactorsWithCount[0][i],
								uniquePrimeFactorsWithCount[1][i])).boxed()
				.toArray(Long[]::new);
	}
}
