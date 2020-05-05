public static void moveRobot(Robot robot, int toX, int toY){
        // first, move X axis direction.
        int dirX=toX-robot.getX();
        int dirY=toY-robot.getY();

        if(dirX>0){
        while(true){
        robot.turnRight();
        if(robot.getDirection()==Direction.RIGHT){
        break;
        }
        }
        }else if(dirX< 0){
        while(true){
        robot.turnLeft();
        if(robot.getDirection()==Direction.LEFT){
        break;
        }
        }
        }
        while(true){
        if(robot.getX()!=toX){
        robot.stepForward();
        }else{
        break;
        }
        }
        if(dirY>0){
        while(true){
        robot.turnRight();
        if(robot.getDirection()==Direction.UP){
        break;
        }
        }
        }else if(dirY< 0){
        while(true){
        robot.turnLeft();
        if(robot.getDirection()==Direction.DOWN){
        break;
        }
        }
        }
        while(true){
        if(robot.getY()!=toY){
        robot.stepForward();
        }else{
        break;
        }
        }
        }
