import java.util.*;
class Node{
    int weight,profit;
    double pbyw;
    public Node(int weight,int profit){
        this.weight = weight;
        this.profit = profit;
        this.pbyw = (double) profit / weight;
    }
}

public class KnapsackFractional {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("enter the total capacity: ");
        int total_capacity=scan.nextInt();
        System.out.print("enter the no  of items: ");
        int n=scan.nextInt();
        Node[] items = new Node[n];
        double current_weight=0,max_profit=0;
        System.out.println("enter the weights and profits(weight profits): ");
        for(int i=0;i<n;i++){
            items[i] = new Node(scan.nextInt(),scan.nextInt());
        }
        Arrays.sort(items, (a, b) -> Double.compare(b.pbyw, a.pbyw));        
        for(Node item:items){
            if(total_capacity<=current_weight+item.weight){
                max_profit+=item.profit;
                current_weight+=item.weight;
            }else{
                max_profit += item.profit * ((total_capacity - current_weight) / item.weight);
                current_weight = total_capacity;
                break;
            }
        }
        System.out.println("Maximum profit: "+max_profit);
    }
}