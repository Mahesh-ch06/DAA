import java.util.*;
class Node{
    int profit,weight;
    double ratio;
    Node(int weight,int profit){
        this.weight = weight;
        this.profit = profit;
        this.ratio=(double)profit/weight;
    }
}
public class KnapsackZeroBYOne {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("enter the total itmes: ");
        int n=scan.nextInt();
        System.out.print("enter the total capacity: ");
        int total_capacity=scan.nextInt();
        int current_weight=0,max_profit=0;
        Node items []= new Node[n];
        System.out.println("enter the weights and profits(weight profit): ");
        for(int i=0;i<n;i++){
            items[i]=new Node(scan.nextInt(),scan.nextInt());
        }
        Arrays.sort(items,(a,b)->Double.compare(b.ratio, a.ratio));
        for(Node item:items){
            if(item.weight+current_weight<=total_capacity){
                current_weight+=item.weight;
                max_profit+=item.profit;
            }else{
                break;
            }
        }
        System.out.println("max profit: "+max_profit);
    }
}