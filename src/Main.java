import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<int[]> intArrayHolder = new ArrayList<>();

    public static void main(String[] args) {
        int x = 0;
        int y = 0;

        intArrayHolder = hexagonalGrid();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int s = scanner.nextInt(); //s = 2
//            int[] array1 = {x-1, y-1, s-1}; //-1 -1 +1 = -1
//            int[] array2 = {x, y-1, s-1}; // 0 -1  +1 = 0
//            int[] array3 = {x+1, y, s-1}; // 1 + 0 + 1 = 2
//            int[] array4 = {x+1, y+1, s-1}; //1 + 1 + 1 = 3
//            int[] array5 = {x, y+1, s-1}; // 0 + 1 + 1 = 2
//            int[] array6 = {x-1, y, s-1}; // -1 + 0 + 1 = 0

            //s = 4
//            int[] array7 = {x-2, y+2, s-2};
//            int[] array8 = {x-2, y+1, s-2};
//            int[] array9 = {x+2, y, s-2};
//            int[] array10 = {x-2, y-1, s-2};
//            int[] array11 = {x+2, y-1, s-2};
//            int[] array12 = {x+2, y, s-2};
//            int[] array13 = {x-2, y, s-2};
//            int[] array14 = {x+2, y+1, s-2};
//            int[] array15 = {x-2, y+1, s-2};
//            int[] array16 = {x+1, y+2, s-2};
//            int[] array17 = {x, y+2, s-2};
//            int[] array18 = {x-1, y+2, s-2};

//            6 + 18 + 36
//            intArrayHolder.add(array1);
//            intArrayHolder.add(array2);
//            intArrayHolder.add(array3);
//            intArrayHolder.add(array4);
//            intArrayHolder.add(array5);
//            intArrayHolder.add(array6);
//            intArrayHolder.add(array7);
//            intArrayHolder.add(array8);
//            intArrayHolder.add(array9);
//            intArrayHolder.add(array10);
//            intArrayHolder.add(array11);
//            intArrayHolder.add(array12);
//            intArrayHolder.add(array13);
//            intArrayHolder.add(array14);
//            intArrayHolder.add(array15);
//            intArrayHolder.add(array16);
//            intArrayHolder.add(array17);
//            intArrayHolder.add(array18);

//            int result = calculateDirectionSteps(array1) + calculateDirectionSteps(array2) +  calculateDirectionSteps(array3)
//                    + calculateDirectionSteps(array4)+ calculateDirectionSteps(array5) + calculateDirectionSteps(array6);

            int result = 0;
            for (int[] ints : intArrayHolder) {
                result += calculateDirectionSteps(ints);
            }
            System.out.println(result);
        }
    }

    private static List<int[]> hexagonalGrid() {
        int x = 0;
        int y = 0;
        int size = 14;
        int half = size / 2;
        List<int[]> arrayHolder = new ArrayList<>();
//        for (int i = 1; i <= 14; i++) {
//            if(i%2 == 0) {
//                //even numbers of grid
//                int[] temp = {x, y, i-2};
//                arrayHolder.add(temp);
//            }
//        }

        for (int row = 0; row < size; row++) {
            int cols = size - java.lang.Math.abs(row - half);

            for (int col = 0; col < cols; col++) {
                int xLbl = row < half ? col - row : col - half;
                int yLbl = row - half;
                int highestValue = Math.abs(xLbl) >= Math.abs(yLbl) ? Math.abs(xLbl)-1 : Math.abs(yLbl)-1;
                int[] temp = {xLbl, yLbl, highestValue};

                arrayHolder.add(temp);
            }
        }
        return arrayHolder;
    }

    private static int calculateDirectionSteps(int[] array) {
        int result = array[0] + array[1] + array[2];
        return result;
    }

}
