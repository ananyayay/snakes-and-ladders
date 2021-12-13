package com.example.project;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Controller {
    public int rand;
    public boolean player1Turn=true;
    public boolean player2Turn=true;
    public static int player1Xpos= 20;
    public static int player1Ypos= 380;
    public boolean gamestart=false;
    public int posCir1=1;
    //public int posCir[][];


    @FXML
    void gamebutton(MouseEvent event) {
        start.setText("Restart");                          //start button becomes restart button
       //HelloApplication.launch();

        player1Xpos=0;
        player1Ypos=0;
        p1.setLayoutX(20);
        p1.setLayoutY(380);
        p1.setTranslateX(0);
        p1.setTranslateY(0);
        p1.setVisible(true);
        rand=0;
        dice_result.setText("Dice");
        gamestart=true;

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
    void p1click(MouseEvent event) {                                   // clicking player1 button rolls dice and moves p1

        if(gamestart) {                                //if gamestarted and player1 turn
            if(player1Turn) {
                rand = (int) (Math.random() * 6 + 1);
                dice_result.setText(String.valueOf(rand));

                moveplayer1();
                translateplayer(player1Xpos,player1Ypos,p1);


                if(player1Xpos==360 && player1Ypos==-40 ){            //SNAKE 1

                    player1Ypos+=40;               //resets position counter (relative movement ie steps moved )
                    player1Xpos-=40;
                    translateplayer(320,0,p1);      //moves the player (here absolute coordinate system of player followed)
                    posCir1--;                              // if start and destination rows both not odd or both even, posCir -- is required
                }










                    System.out.println("player1xpos" + player1Xpos);
                    System.out.println("player1ypos" + player1Ypos);

            }
        }
    }
    private void moveplayer1(){
//        System.out.println("player1xpos"+player1Xpos);
//        System.out.println("player1ypos"+player1Ypos);
        for(int i=0;i<rand;i++){
                                                                            //posCircle initially =1

            if(posCir1%2==1) {                               //here posCircle odd so player moves right
                player1Xpos += 40;
                //p1.setLayoutX(+80);
            }
            if (posCir1%2==0){                              //if posCircle even then player moves left
                player1Xpos-=40;
            }
            if(player1Xpos>360){                            //reaching right corner of board
                player1Ypos-=40;
                player1Xpos-=40;
                posCir1++;

            }
            if(player1Xpos<0){                               //reaching left corner of board
                player1Ypos-=40;
                player1Xpos+=40;
                posCir1++;
            }
            if(player1Xpos <20 && player1Ypos==-360 ){                 //winning condition
                player1Xpos=0;
                player1Ypos=-360;
                p1.setTranslateX(0);
                p1.setTranslateY(-360);
                dice_result.setText("Player 1 won!");
                p1.setVisible(false);                             //p1 disappers once it wins
                start.setText("Play Again?");
                gamestart=false;

            }

        }

    }

    @FXML
    void p2click(MouseEvent event) {                            // clicking player1 button rolls dice and moves p1
        rand=(int)(Math.random()*6+1);
        dice_result.setText(String.valueOf(rand));
    }

    @FXML
    private ImageView img;

    @FXML
    private Label locvar;

    @FXML
    private Circle p1;

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
        TranslateTransition anm=new TranslateTransition(Duration.millis(500),c);
        anm.setToX(x);
        anm.setToY(y);
        anm.setAutoReverse(false);
        anm.play();
//        c.setTranslateX(x);            //moves the player (here absolute coordinate system of player followed)
//        c.setTranslateY(y);
    }
}

