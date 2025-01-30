package se2203.assignment1;

public abstract class SortingStrategy implements Runnable {
    public abstract void sort(int[] number);
    public void shuffle(int[] number){
        int idx; int temp;
        for(int i = 0; i < number.length; i++){
            idx = (int) (Math.random() * number.length);
            temp = number[i]; number[i] = number[idx]; number[idx] = temp;
        }
    }
    public void reverse(int[] number){
        int i = 0; int j = number.length - 1;
        int temp;
        while(i < j){
            temp = number[i]; number[i] = number[j]; number[j] = temp;
            i++; j--;
        }
    }

}
