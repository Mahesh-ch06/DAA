import java.util.*;
class Job{
    int job_id,deadline,profit;
    Job(int job_id,int deadline,int profit){
        this.job_id = job_id;
        this.deadline = deadline;
        this.profit = profit;
    }
}
public class JobScheduling {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the no of jobs: ");
        int n= scan.nextInt();
        Job jobs[]= new Job[n];
        System.out.println("enter the deadlines and profits of jobs line by line(deadline profit): ");
        int max_dl=0;
        for(int i=0;i<n;i++){
            int deadline=scan.nextInt();
            jobs[i]=new Job(i+1,deadline,scan.nextInt());
            if(max_dl<deadline){
                max_dl=deadline;
            }
        }
        Arrays.sort(jobs,(a,b)->Integer.compare(b.profit ,a.profit));
        int result []= new int[max_dl];
        int slot[]=new int[max_dl];
        Arrays.fill(result,-1);
        int total_profit=0;
        for(int i=0;i<n;i++){
            for(int j=Math.min(n,jobs[i].deadline)-1;j>=0;j--){
                if(slot[j]==0){
                    result[j]=jobs[i].job_id;
                    slot[j]=1;
                    total_profit+=jobs[i].profit;
                    break;
                }
            }
        }
        System.out.println("scheduled tasks sequence is: " + Arrays.toString(result));
        System.out.println("total profit is: "+total_profit);
    }
}
