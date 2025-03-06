package model;

    public class Client implements Comparable {
        public int getTimpSosire() {
            return timpSosire;
        }

        public Client(int timpSosire, int timpProcesare) {
            this.timpSosire = timpSosire;
            this.timpProcesare = timpProcesare;
        }

        public int getTimpProcesare() {
            return timpProcesare;
        }

        public void setTimpProcesare(int timpProcesare) {
            this.timpProcesare = timpProcesare;
        }

        private int timpSosire;
        private int timpProcesare;

        @Override
        public int compareTo(Object o) {
          Client c=(Client)o;
          if(this.timpSosire<=((Client) o).timpSosire)
              return 0;

            return -1;
        }
    }

