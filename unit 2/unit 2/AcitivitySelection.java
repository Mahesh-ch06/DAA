import java.util.*;

class Activities{
    int start,end;
    Activities(int start,int end){
        this.start = start;
        this.end = end;
    }
}

class AcitivitySelection{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("enter the no of activities: ");
        int n=scan.nextInt();
        Activities activities []= new Activities[n];
        System.out.println("Enter start and end time of activities:");
        for(int i=0;i<n;i++){
            activities[i] = new Activities(scan.nextInt(),scan.nextInt());
        }
        Arrays.sort(activities, (a,b) ->Integer.compare(a.end, b.end));
        int count = 1;
        int end = activities[0].end;
        for(int i=1;i<n;i++){
            if(activities[i].start >= end){
                count++;
                end = activities[i].end;
            }
        }
        System.out.println("Maximum number of non-conflicting activities are: " + count);
    }
}