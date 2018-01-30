package apps;


import queue.ListQueue;
import queue.Queue;

public class SkiLiftApp {
    // Queue<LiftChair> skiLiftQueue = new BasicQueue<>();
    Queue<LiftChair> skiLiftQueue = new ListQueue<>();

    public static void main(String[] args) {
        SkiLiftApp skiLiftApp = new SkiLiftApp();
        skiLiftApp.runLift();
    }

    public void runLift() {
        loadLift();

        //print out the lift size
        System.out.println(skiLiftQueue.size());

        //does the lift contain skiers Mary and Anna
        System.out.println("Lift has Mary and Anna on it: " + skiLiftQueue.contains(new LiftChair("Mary", "Anna")));

        //who's at the 2nd position
        System.out.println("2nd chair has: " + skiLiftQueue.access(1).listChairRiders());

        unloadLift();

        //if the lift is unloaded again, an error should occur since no one is left on it
        try {
            unloadLift();
        } catch (IllegalStateException t) {
            System.out.println("Can't unload any more skiers because the lift is empty: " + t.getMessage());
        }
    }

    private void loadLift() {
        //skiers in line to get on lift
        LiftChair chair1 = new LiftChair("John", "Dave");
        LiftChair chair2 = new LiftChair("Samantha", "Kelly");
        LiftChair chair3 = new LiftChair("Mary", "Anna");
        LiftChair chair4 = new LiftChair("Robert", "Chad");
        LiftChair chair5 = new LiftChair("Sarah", "Bill");

        //load them onto the lift
        skiLiftQueue.enQueue(chair1);
        skiLiftQueue.enQueue(chair2);
        skiLiftQueue.enQueue(chair3);
        skiLiftQueue.enQueue(chair4);
        skiLiftQueue.enQueue(chair5);
    }

    private void unloadLift() {
        LiftChair chair = skiLiftQueue.deQueue();

        //should be John and Dave
        System.out.println(chair.listChairRiders());

        chair = skiLiftQueue.deQueue();

        //should be Samantha and Kelly
        System.out.println(chair.listChairRiders());

        chair = skiLiftQueue.deQueue();

        //should be Mary and Anna
        System.out.println(chair.listChairRiders());

        chair = skiLiftQueue.deQueue();

        //should be Robert and Chad
        System.out.println(chair.listChairRiders());

        chair = skiLiftQueue.deQueue();

        //should be Sarah and Bill
        System.out.println(chair.listChairRiders());
    }

    class LiftChair {
        private String seat1Name;
        private String seat2Name;

        LiftChair(String skierInFirstSeat, String skierInSecondSeat) {
            this.seat1Name = skierInFirstSeat;
            this.seat2Name = skierInSecondSeat;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((seat1Name == null) ? 0 : seat1Name.hashCode());
            result = prime * result + ((seat2Name == null) ? 0 : seat2Name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            LiftChair other = (LiftChair) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (seat1Name == null) {
                if (other.seat1Name != null)
                    return false;
            } else if (!seat1Name.equals(other.seat1Name))
                return false;
            if (seat2Name == null) {
                return other.seat2Name == null;
            } else {
                return seat2Name.equals(other.seat2Name);
            }
        }

        String listChairRiders() {
            return this.seat1Name + ", " + this.seat2Name;
        }

        private SkiLiftApp getOuterType() {
            return SkiLiftApp.this;
        }
    }
}
