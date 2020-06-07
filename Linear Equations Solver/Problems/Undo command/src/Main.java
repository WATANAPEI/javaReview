interface Movable {
    int getX();
    int getY();
    void setX(int newX);
    void setY(int newY);
}

interface Storable {
    int getInventoryLength();
    String getInventoryItem(int index);
    void setInventoryItem(int index, String item);
}

interface Command {
    void execute();
    void undo();
}

class CommandMove implements  Command{
    Movable entity;
    int xMovement;
    int yMovement;

    @Override
    public void execute() {
        xMovement = entity.getX();
        yMovement = entity.getY();
        entity.setX(xMovement);
        entity.setY(yMovement);
    }

    @Override
    public void undo() {
        entity.setX(-xMovement);
        entity.setY(-yMovement);
        this.xMovement = 0;
        this.yMovement = 0;
    }
}

class CommandPutItem implements Command {
    Storable entity;
    String item;

    @Override
    public void execute() {
        for(int i = 0; i < entity.getInventoryLength(); i++) {
            String item = entity.getInventoryItem(i);
            if(item == null) {
                entity.setInventoryItem(i,entity.toString());
                this.item = entity.toString();
            }
        }

    }

    @Override
    public void undo() {
        for(int i = 0; i < entity.getInventoryLength(); i++) {
            String item = entity.getInventoryItem(i);
            if(item.contentEquals(this.item)) {
                entity.setInventoryItem(i, null);
                this.item = null;
            }
        }

    }
}