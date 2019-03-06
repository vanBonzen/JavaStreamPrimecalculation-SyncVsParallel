import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        int maxPrime = 10000000;

         Instant start = Instant.now();

         List<Integer> parallel = IntStream.range(1, maxPrime)
                .parallel()
                .filter(Main::isPrime)
                .boxed()
                .collect(Collectors.toList());

         Instant finish = Instant.now();
         long timeElapsed = Duration.between(start, finish).toMillis();

         Instant start2 = Instant.now();
         List<Integer> snychronized = IntStream.range(1, maxPrime)
                 .filter(Main::isPrime)
                 .boxed()
                 .collect(Collectors.toList());

         Instant finish2 = Instant.now();
         long timeElapsed2 = Duration.between(start2, finish2).toMillis();

         System.out.println("Benchmark Primes from 1 to " + maxPrime);
         System.out.println("Time to execute parallel but sorted (ms): " + timeElapsed);
         System.out.println("Time to execute synchronized (ms): " + timeElapsed2);

    }

    private static boolean isPrime(int n) {
        if (n%2==0) return false;

        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}