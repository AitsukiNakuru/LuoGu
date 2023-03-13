
import java.util.Scanner;

public class Test {
    static int[] primes;
    static boolean[] notPrime;
    static int primei=0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt(),sulen=4000;
        int[] su=getPrimes(sulen);
        for(int i=0;i<T;i++) {
            int num=sc.nextInt();
            System.out.println(judge(num));
        }
    }
    static String judge(int num) {
        for(int i=0;i<primei;i++){
            if(num%primes[i]!=0)
                continue;
            else{
                int cnt=0;
                while(num%primes[i]==0){
                    num/=primes[i];
                    cnt++;
                }
                if(cnt==1){
                    return "no";
                }
            }
        }
        return "yes";
    }
    static int[] getPrimes(int sulen){
        primes=new int[sulen+1];
        notPrime=new boolean[sulen+1];
        notPrime[0]=true;notPrime[1]=true;
        for(int i=2;i<=sulen;i++){
            if(!notPrime[i])
                primes[primei++]=i;
            for(int j=0;j<primei&&primes[j]*i<=sulen;j++){
                notPrime[i*primes[j]]=true;
                if(i%primes[j]==0){
                    break;
                }
            }
        }
        return primes;
    }
}
