public class Rooms {
    int NoOfTables;
    int NoOfChairs;

    public Rooms(int t, int c) {
        this.NoOfTables = t;
        this.NoOfChairs = c;
    }

    public void setRooms(int t, int c) {
        this.NoOfTables = t;
        this.NoOfChairs = c;
    }

    public int getNoOfTables() {
        return this.NoOfTables;
    }

    class ClassRoom {
        int NoOfWhiteBoards;

        public ClassRoom(int w) {
            this.NoOfWhiteBoards = w;
        }

        public void setNoOfWhiteBoards(int w) {
            this.NoOfWhiteBoards = w;
        }

        public int getNoOfWhiteBoards() {
            return this.NoOfWhiteBoards;
        }
    }

    class Labs {
        int NoOfComputers;

        public Labs(int c) {
            this.NoOfComputers = c;
        }

        public void setNoOfComputers(int c) {
            this.NoOfComputers = c;
        }

        public int getNoOfComputers() {
            return this.NoOfComputers;
        }
    }

    class ConferenceRoom {
        int NoOfProjectors;

        public ConferenceRoom(int p) {
            this.NoOfProjectors = p;
        }

        public void setNoOfProjectors(int p) {
            this.NoOfProjectors = p;
        }

        public int getNoOfProjectors() {
            return this.NoOfProjectors;
        }
    }
}