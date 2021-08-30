package risovashki;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import java.util.UUID;
import java.util.concurrent.FutureTask;

public class FractalLine extends Line {

    private boolean isMain;
    public FractalLine(double startX, double startY, double endX, double endY, double XAngle, double height){
        super(startX,startY,endX,endY);
        /*вычисление высоты*/
        this.height = height;
        this.setStrokeWidth(3);
        setGradient();

        this.isMain = true;
        this.XAngle = XAngle;
    }


    public FractalLine(double startX, double startY,
                       double endX, double endY,
                       FractalLine prevLine, double XAngle, double height,
                       boolean isRight){
        super(startX, startY, endX, endY);
        this.prevLine = prevLine;
        this.height = height;
        this.setStrokeWidth(3);
        setGradient();

        this.isMain = false;
        this.isRight = isRight;
        this.XAngle = XAngle;
    }

    private double XAngle;

    private boolean isRight;

    private FractalLine prevLine;
    private double height;
    public double getHeight(){return this.height;}


    private FractalLine createLine(boolean isRight){

        double startX, startY, endX, endY;
        double newHeight = Math.sqrt(2*Math.pow((this.height/2), 2));
        double newAngle;

        startX = this.getStartX() + ( Math.cos(Math.toRadians(this.XAngle)) * (this.height/3*2) );
        startY = this.getStartY() - ( Math.sin(Math.toRadians(this.XAngle)) * (this.height/3*2) );

        if(isRight){
            newAngle = this.XAngle - 45;
        }else{
            newAngle = this.XAngle + 45;
        }

        endX = startX + Math.cos(Math.toRadians(newAngle))*newHeight;
        endY = startY - Math.sin(Math.toRadians(newAngle))*newHeight;

        return new FractalLine(startX, startY, endX, endY, this, newAngle, newHeight, isRight);
    }

    public FractalLine[] CreateNewLines(){
        FractalLine [] lines = new FractalLine[2];

        lines[0] = createLine(true);
        System.out.println("startX - " + lines[0].getStartX() +
                " startY - " + lines[0].getStartY() +
                " endX - " + lines[0].getEndX() +
                " endY - " + lines[0].getEndY());

        lines[1] = createLine(false);
        System.out.println("startX - " + lines[1].getStartX() +
                " startY - " + lines[1].getStartY() +
                " endX - " + lines[1].getEndX() +
                " endY - " + lines[1].getEndY());

        return lines;
    }



    private void setGradient(){
        String uuid = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();
        uuid = uuid.replace("-","").substring(0,6);
        uuid2 = uuid2.replace("-","").substring(0,6);
        LinearGradient linearGradient = new LinearGradient(this.getStartX(), this.getStartY(),
                this.getEndX(), this.getEndY(),
                false, CycleMethod.REFLECT,
                new Stop(0, Color.valueOf(uuid)),
                new Stop(1, Color.valueOf(uuid2)));
        //String tt = "-fx-background-color:linear-gradient(to top right,#" + uuid + ", #" + uuid2+");";
        this.setStroke(linearGradient);
    }


}
