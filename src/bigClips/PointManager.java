package bigClips;

public class PointManager {

    private int points;

    public PointManager(){
        points=0;
        System.out.println("pointmanager initiated"); // test line
    }

    public void setPoints(){


    }

    public void addPoints(int points){
        System.out.println("points added, currently: " + points);
        this.points += points;
    }


    public int getPoints(){
        System.out.println("points returned");
        return points;
    }

}
