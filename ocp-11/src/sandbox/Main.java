package sandbox;

public class Main {

    public static void main (String [] args) {
        final int score1 = 1, score2 = 7;
        char myScore = 7;
        switch(myScore) {
            default:
            case 2:
            case 6:
            case score1:
                System.out.println();
                break;
            case score2:
                System.out.println();
        }

        int monday = 3 + (int)2.799;
        double tuesday = 56L;
        int wednesday = Integer.MAX_VALUE;
        short thursday = (short)Integer.MAX_VALUE;
        float friday = 2.7_9_9f;

        System.out.print(monday+"|"+tuesday+"|"+wednesday+"|"+thursday+"|"+friday);
    }

}
