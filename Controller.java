package sample;

import com.sun.media.jfxmedia.control.MediaPlayerOverlay;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.util.Random;


public class Controller {
    public int rand;
    public boolean player1Turn=true;
    public boolean player2Turn=true;
    public static int player1Xpos= 20;
    public static int player1Ypos= 380;
    public int posCir1=1;


    public static int player2Xpos= 20;
    public static int player2Ypos= 380;
    public int posCir2=1;
    public int turn=1;
    public int p1Box1=0;
    public int p2Box1=0;


    public boolean gamestart=false;

//    public boolean p1Chance=false;
//    public boolean p2Chance=false;



    @FXML
    void gamebutton(MouseEvent event) {
        start.setText("Restart");                          //start button becomes restart button
        //HelloApplication.launch();

        rollButton.setDisable(false);
        player1.setDisable(true);
        player2.setDisable(true);

        player1Xpos= 0;
        player1Ypos= 0;
        p1.setLayoutX(20);
        p1.setLayoutY(380);
        p1.setTranslateX(0);
        p1.setTranslateY(0);
        p1.setVisible(false);
//        p1.setLayoutX(20);
//        p1.setLayoutY(420);
        player2Xpos=0;
        player2Ypos= 0;
        p2.setLayoutX(20);
        p2.setLayoutY(380);
        p2.setTranslateX(0);
        p2.setTranslateY(0);
        p2.setVisible(false);
        rand=0;
        dice_result.setText("Dice");
        gamestart=true;
        turn=1;
        p1Box1=0;
        p2Box1=0;

    }

    @FXML
    private Button start;

    @FXML
    private Label dice_result;

    @FXML
    private Button player1;

    @FXML
    private Button player2;


    @FXML
    private Circle p2;


    @FXML
    private ImageView img;

    @FXML
    private ImageView img2;

    @FXML
    private Label locvar;

    @FXML
    private Circle p1;


    Random random = new Random();

    @FXML
    private ImageView diceImage;

    @FXML
    private Button rollButton;


    @FXML
    void dice( ){

//        dice_result.setText(String.valueOf(rand));

    }

    @FXML
    void roll(ActionEvent event) {

        if( !gamestart) return;

        player1.setDisable(true);
        player2.setDisable(true);

        rollButton.setDisable(true);



        Thread thread = new Thread(){
            public void run(){
                System.out.println("Thread Running");
                try {
                    for (int i = 0; i < 15; i++) {
                        rand = random.nextInt(6)+1;

                        File file = new File("src/sample/dice/dice" + (rand)+".png");
                        diceImage.setImage(new Image(file.toURI().toString()));

                        Thread.sleep(35);


                    }


                    if(turn%2==1)
                    {
                        if(p1Box1==0){
                            if(rand==1) {
                                p1Box1 +=1;
                                player1.setDisable(false);

                            }
                            else{
                                player1.setDisable(true);
                                rollButton.setDisable(false);
                            }

//                            if (player1Xpos == 0 && player1Ypos == 0 && rand != 1) {
//                                player1.setDisable(true);
//                                rollButton.setDisable(false);
//                            } else if (player1Xpos == 0 && player1Ypos == 0 && rand == 1) {
//                                player1.setDisable(false);
//                                p1Box1=1;

                        } else  {
                            player1.setDisable(false);
                            p1Box1 +=1;

                        }
                        if ( player1Xpos<=200 && player1Ypos==-360 && rand*40 > player1Xpos){
                            player1.setDisable(true);
                            rollButton.setDisable(false);
                        }
                    }


                    if (turn % 2 == 0)
                    {

                        if(p2Box1==0){
                            if(rand==1) {
                                p2Box1 +=1;
                                player2.setDisable(false);

                            }
                            else{
                                player2.setDisable(true);
                                rollButton.setDisable(false);
                            }
                        }

                        else {
                            p2Box1 +=1;
                            player2.setDisable(false);

                        }
                        if ( player2Xpos<=200 && player2Ypos==-360 && rand*40 > player2Xpos){
                            player2.setDisable(true);
                            rollButton.setDisable(false);
                        }
                    }

                    turn++;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };
        thread.start();
//        System.out.println("player1xpos" + player1Xpos);
//        System.out.println("player1ypos" + player1Ypos);



//        dice_result.setText(String.valueOf(rand));
    }

    @FXML
    void p1click(MouseEvent event) {                                   // clicking player1 button rolls dice and moves p1



        if(gamestart) {

            prevX1= player1Xpos;
            prevY1= player1Ypos;
            moveplayer(1, player1Xpos, player1Ypos, posCir1);

            translateplayer(player1Xpos,player1Ypos,p1);

            int temp1= player1Xpos;
            int temp2= player1Ypos;


                snakes(1, player1Xpos, player1Ypos, posCir1);
                ladders(1, player1Xpos, player1Ypos, posCir1);

                if(temp1 == player1Xpos && temp2== player1Ypos)
                {

                }
                else {
                    prevX1 = temp1;
                    prevY1 = temp2;
                }
            translateplayer(player1Xpos,player1Ypos,p1);


            System.out.println("1 prevX  " + prevX1);
            System.out.println("1 prevY  " + prevY1);

        }

        player1.setDisable(true);
        rollButton.setDisable(false);

    }
    public int prevX1;
    public int prevY1;
    public int prevX2;
    public int prevY2;


    @FXML
    void p2click(MouseEvent event) {                            // clicking player1 button rolls dice and moves p1

        if( !gamestart) return;

        prevX2= player2Xpos;
        prevY2= player2Ypos;
        moveplayer(2, player2Xpos, player2Ypos, posCir2);

        translateplayer(player2Xpos,player2Ypos,p2);

        int temp1= player2Xpos;
        int temp2= player2Ypos;

        snakes(2, player2Xpos, player2Ypos, posCir2);


        ladders(2, player2Xpos, player2Ypos, posCir2);
        if(temp1 == player2Xpos && temp2== player2Ypos)
        {

        }
        else {
            prevX2 = temp1;
            prevY2 = temp2;
        }

        translateplayer(player2Xpos,player2Ypos,p2);




        player2.setDisable(true);
        rollButton.setDisable(false);


    }


    private void snakes(int playerNum, int x, int y, int pos){


        if(x ==360 && y ==-40 ){            //SNAKE 11 to 9
            x -=40;
            y +=40;               //resets position counter (relative movement ie steps moved )

            pos--;                              // if start and destination rows both not odd or both even, posCir -- is required
        }

        if(x==160 && y==-120 ){            //SNAKE 36 to 14
            y+= 80;
            x+= 80;

            pos= pos-2;

        }

        if(x==80 && y==-160 ){            //SNAKE 43 to 22
            y+= 80;
            x-= 40;

            pos= pos-2;
        }
        if(x==120 && y==-200 ){            //SNAKE 57 to 9
            y= -40;
            x= 40;
            pos= pos-4;
        }
        if(x==200 && y==-240 ){            //SNAKE 66 to 47
            y+= 80;
            x+= 40;
            pos= pos-2;
        }
        if(x==0 && y==-320 ){            //SNAKE 81 to 63
            y+= 80;
            x+= 80;
            pos= pos-2;
        }
        if(x==360 && y==-320 ){            //SNAKE 90 to 49
            y+= 160;
            x-= 40;
            pos= pos-4;
        }
        if(x==240 && y==-360 ){            //SNAKE 94 to 48
            y+= 200;
            x+= 40;
            pos= pos-5;
        }
        if(x==160 && y==-360 ){            //SNAKE 96 to 65
            y+= 120;
            pos= pos-3;
        }

        if(x==40 && y==-360 ){            //SNAKE 99 to 78
            y+= 80;
            x+= 40;
            pos= pos-2;
        }


        if(playerNum==1){
            posCir1 = pos;
            player1Xpos= x;
            player1Ypos= y;

        }

        if(playerNum ==2){
            posCir2 = pos;
            player2Xpos= x;
            player2Ypos= y;
        }


    }


    private void ladders(int playerNum, int x, int y, int pos){


        if(x == 120 && y ==0  ){            //LADDER 4 to 26
            x += 80;
            y -= 80;               //resets position counter (relative movement ie steps moved )

            pos+=2;                              // if start and destination rows both not odd or both even, posCir -- is required
        }

        if(x== 280 && y== 0 ){            //LADDER 8 to 51
            y= -200;
            x+= 80;

            pos= pos+ 5;

        }

        if(x== 280 && y==-80){            //LADDER 28 to 46
            y= -160 ;
            x-= 80;

            pos= pos +2;
        }
        if(x== 40 && y==- 120 ){            //LADDER 39 to 60
            y= -200;
            x= 0;
            pos= pos+2;
        }
        if(x== 120 && y==-160 ){            //LADDER 44 to 80
            y= -280;
            x= 0;
            pos= pos+3;
        }
        if(x== 120 && y== -240 ){            //LADDER 64 to 85
            y -= 80;
            x+= 40;
            pos= pos+2;
        }
        if(x== 120 && y==-320 ){            //LADDER 84 to 98
            y-= 40;
            x-= 40;
            pos= pos+1;
        }

        if(x== 320 && y==-200 ){            //LADDER 52 to 68
            y -= 40;
            x-=40;
            pos= pos+1;
        }

        if(x== 320 && y== -240 ){            //LADDER 69 to 93
            y = -360;
            x-= 40;
            pos= pos +3;
        }
        if(x== 360 && y== -280 ){            //LADDER 71 to 92
            y = -360;
            x-= 40;
            pos= pos +2;
        }


        if(playerNum==1){
            posCir1 = pos;
            player1Xpos= x;
            player1Ypos= y;

        }

        if(playerNum ==2){
            posCir2 = pos;
            player2Xpos= x;
            player2Ypos= y;
        }


    }


    private void moveplayer(int playerNum, int x, int y, int pos) {

        if (playerNum == 1)
            if (p1Box1 == 1) {
//            System.out.println("returns");
                p1.setVisible(true);
                return;
            }

        if (playerNum == 2)
            if (p2Box1 == 1) {
                p2.setVisible(true);
                return;
            }


        if (y == -360) {
            if (x <= 200) {


                if (rand * 40 == x) {
                    x = 0;
                    y = -360;
                    if (playerNum == 1) {
                        p1.setTranslateX(0);
                        p1.setTranslateY(-360);
                        dice_result.setText("Player 1 won!");
                        p1.setVisible(false);                             //p1 disappers once it wins
                        start.setText("Play Again?");
                        gamestart = false;
                    }
                    if (playerNum == 2) {
                        p2.setTranslateX(0);
                        p2.setTranslateY(-360);
                        dice_result.setText("Player 2 won!");
                        p2.setVisible(false);                             //p1 disappers once it wins
                        start.setText("Play Again?");
                        gamestart = false;
                    }

                    return;

                }

            }

        }

        for (int i = 0; i < rand; i++) {
            //posCircle initially =1

            if (pos % 2 == 1) {                               //here posCircle odd so player moves right
                x += 40;
                //p1.setLayoutX(+80);
            }
            if (pos % 2 == 0) {                              //if posCircle even then player moves left
                x -= 40;
            }
            if (x > 360) {                            //reaching right corner of board
                y -= 40;
                x -= 40;
                pos++;
            }
            if (x < 0) {                               //reaching left corner of board
                y -= 40;
                x += 40;
                pos++;
            }



        if (playerNum == 1) {
            posCir1 = pos;
            player1Xpos = x;
            player1Ypos = y;
            translateplayer(x,y, p1);
        }

        if (playerNum == 2) {
            posCir2 = pos;
            player2Xpos = x;
            player2Ypos = y;
            translateplayer(x,y, p2);
        }


        }
    }




    @FXML
    void findloc(MouseEvent event) {
        locvar.setText(String.valueOf(event.getSceneX())+" "+String.valueOf(event.getSceneY()));
    }
    @FXML
    void move(MouseEvent event){



    }
    public void initializer(){
        //Bounds bs=p1.localToScene(p1.getBoundsInLocal());

    }
    public void translateplayer(int x, int y, Circle c){
        TranslateTransition anm = new TranslateTransition(Duration.millis(500),c);


        if (c==p1) {
            System.out.println("translating from "+ prevX1+ " and " + prevY1 );
            anm.setFromX(prevX1);
            anm.setFromY(prevY1);
        }
        else {
            anm.setFromX(prevX2);
            anm.setFromY(prevY2);
        }
        anm.setToX(x);
        anm.setToY(y);
        anm.setAutoReverse(false);
        anm.play();
//        System.out.println("t");

//        c.setTranslateX(x);            //moves the player (here absolute coordinate system of player followed)
//        c.setTranslateY(y);
    }
}

