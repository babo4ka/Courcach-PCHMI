package risovashki;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;

import java.util.UUID;

public class MyLine extends Line {

    public MyLine(double startX, double startY, double endX, double endY){
        super(startX, startY, endX,endY);
        this.height = this.getStartY()-this.getEndY();
        this.setStrokeWidth(10);
        setGradient();
    }

    public MyLine(double startX, double startY, double endX,  MyLine prevLine){
        super(startX, startY, endX, startY - prevLine.getHeight()/3*2);
        this.prevLine = prevLine;
        this.height = prevLine.getHeight()/3*2;
        this.setStrokeWidth(10);
        setGradient();
    }


    private void setGradient(){
        String uuid = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();
        uuid = uuid.replace("-","").substring(0,6);
        uuid2 = uuid2.replace("-","").substring(0,6);
        LinearGradient linearGradient = new LinearGradient(this.getStartX(), this.getStartY(),
                this.getEndX(), this.getEndY(),
                false, CycleMethod.REFLECT,
                new Stop(0,Color.valueOf(uuid)),
                new Stop(1, Color.valueOf(uuid2)));
        //String tt = "-fx-background-color:linear-gradient(to top right,#" + uuid + ", #" + uuid2+");";
        this.setStroke(linearGradient);
    }



    private MyLine prevLine;
    private void setPrevLine(MyLine line){
        this.prevLine = line;
    }

    private double height;
    public double getHeight(){return this.height;}


    //private MyLine[] newLines;
    public MyLine[] CreateNewLines(){
        MyLine[] newLines = new MyLine[2];

        newLines[0] = new MyLine(this.getStartX()+150,
                this.getStartY(),
                this.getEndX()+150,
                this);

        newLines[1] = new MyLine(this.getStartX()-150,
                this.getStartY(),
                this.getEndX()-150,
                this);
        return newLines;
    }
}
