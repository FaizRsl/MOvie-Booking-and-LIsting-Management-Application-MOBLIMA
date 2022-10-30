import java.util.Scanner;

public class Seats {

    private boolean adjMatrix[][];
    private static int totalSeats;

    public Seats(){
        adjMatrix = new boolean[5][5];
        for(int i = 0; i<adjMatrix.length;i++){
            for(int j = 0; j<adjMatrix[i].length; j++){
                adjMatrix[i][j] = false;
            }
        }
        totalSeats = 25;
    }

    public int getTotalSeats(){
        return totalSeats;
    }

    public int[] bookSeats(int seats){
        int[] list = new int[seats];

        if(totalSeats==0){
            System.out.println("No seats available. Full");
            return null;
        }
        String available;
        System.out.println("         column 1    column 2    column 3    column 4    column 5");
        for(int i = 0; i<adjMatrix.length;i++){
            System.out.print("row " + (i+1) + ": ");
            for(int j = 0; j<adjMatrix[i].length; j++){
                if(!adjMatrix[i][j]){
                    available = "available";
                }
                else
                    available = "unavailable";
                System.out.print("[" + available + "] ");
            }
            System.out.println();
        }

        Scanner sc = new Scanner(System.in);

        int temp = 0;
        while(temp!=seats){
            System.out.println("seat " + (temp+1) + ":");
            System.out.println("Please select the row you want to book: ");
            int row = sc.nextInt();
            System.out.println("Please select the column you want to book: ");
            int column = sc.nextInt();

            if((row > 0 && row < 6) && (column>0 && column < 6)){
                if(!adjMatrix[row-1][column-1]){
                    adjMatrix[row-1][column-1] = true;
                    list[temp] = row*column;
                    temp++;
                }
                else{
                    System.out.println("Seats taken! Please choose a different seat");
                }
            }
            else{
                System.out.println("Invalid seats. Please try again with proper row and columns");
            }
        }

        System.out.println("         column 1    column 2    column 3    column 4    column 5");
        for(int i = 0; i<adjMatrix.length;i++){
            System.out.print("row " + (i+1) + ": ");
            for(int j = 0; j<adjMatrix[i].length; j++){
                if(!adjMatrix[i][j]){
                    available = "available";
                }
                else
                    available = "unavailable";
                System.out.print("[" + available + "] ");
            }
            System.out.println();
        }

        totalSeats -= seats;
        return list;
    }
}
