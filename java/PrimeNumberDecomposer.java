import java.util.ArrayList;
import java.util.HashMap;

class PrimeNumberDecomposer {
	private final static Long[] primes = {2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L};
	
	/**
	 * @param n
	 *            integer to be decomposed
	 * @return list of all prime factors of a given number n
	 */
	public static Long[] getAllPrimeFactors(long n) {
		// edge cases
		if(n == 0) return new Long[0];
		if(n == 1) return new Long[]{1L};
		if(n == 2) return new Long[]{2L};
		
		// normal cases
		ArrayList<Long> result = new ArrayList<>();
		while(true){
			for(int i = 0; i < primes.length; i++){
				// find a factor
				if(n % primes[i] == 0){
					result.add(primes[i]);
					n = n / primes[i];
					break;
				}
			}
			
			// no more factor
			if(n == 1) break;
		}
		return result.toArray(new Long[0]);
	}

	/**
	 * Return code always contains two lists. e.g.:
	 * getUniquePrimeFactorsWithCount(100) = {{2,5},{2,2}) // prime 2 occurs 2
	 * times, prime 2 occurs 5 times,
	 * 
	 * @param n
	 *            integer to be decomposed
	 * @return list containing two lists, first containing all prime factors
	 *         without duplicates, second containing the count, how often each
	 *         prime factor occurred.
	 */
	public static Long[][] getUniquePrimeFactorsWithCount(long n) {
		// edge cases
		if(n == 0) return new Long[][]{{}, {}};
		if(n == 1) return new Long[][]{{1L}, {1L}};
		if(n == 2) return new Long[][]{{2L}, {2L}};
				
		System.out.println("n="+n);
		// get the prime factors
		Long[] allPrimeFactors = getAllPrimeFactors(n);
		
		// store in hashmap
		HashMap<Long, Integer> hm = new HashMap<Long, Integer>();
		for(Long primeFactor: allPrimeFactors){
			if(hm.containsKey(primeFactor)){
				// plus 1
				hm.put(primeFactor, hm.get(primeFactor) + 1);
			} else {
				// 1
				hm.put(primeFactor, 1);
			}
		}
		// convert to 2d array
		Long[][] result = new Long[hm.size()][hm.size()];
		int i = 0;
		for(Long key: hm.keySet()){
			result[0][i] = key;
			result[1][i++] = hm.get(key).longValue();
		}
		return result;
	}

	/**
	 * Return value: List containing product of same prime factors, e.g.: 45 =
	 * 3*3*5 ==> {3^2,5^1} == {9,5} e.g.: getUniquePrimeFactorsWithCount(100) =
	 * {{2,5},{2,2}) // prime 2 occurs 2 times, prime 2 occurs 5 times,
	 * 
	 * @param n
	 *            integer to be decomposed
	 * @return
	 */
	public static Long[] getPrimeFactorPotencies(long n) {
		// edge cases
		if(n == 0) return new Long[0];
		if(n == 1) return new Long[]{1L};
		if(n == 2) return new Long[]{2L};
				
		// get the prime factors with count
		Long[][] uniquePrimeFactorsWithCount = getUniquePrimeFactorsWithCount(n);
		
		Long[] result = new Long[uniquePrimeFactorsWithCount[0].length];
		// calculate the power
		for(int i = 0; i < uniquePrimeFactorsWithCount[0].length; i++){
			result[i] = 1L;
			for(Long j = 0L; j < uniquePrimeFactorsWithCount[1][i]; j++){
				result[i] *= uniquePrimeFactorsWithCount[0][i];
			}
		}
		return result;
	}
}
