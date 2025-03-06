package model;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import java.util.concurrent.atomic.AtomicInteger;



public class Coada implements Runnable {
    private BlockingQueue<Client> clienti;
    private AtomicInteger perioadaAsteptare;

    public String getNume() {
        return nume;
    }

    private String nume;
    public AtomicInteger getPerioadaAsteptare() {
        return perioadaAsteptare;
    }

    public void setPerioadaAsteptare(int i) {
        this.perioadaAsteptare.set(i);
    }

    public Coada(String nume)
    {
        clienti= new LinkedBlockingQueue<>();
        this.perioadaAsteptare=new AtomicInteger(0);
        this.nume=nume;
    }
    public void adaugaClient(Client clientNou)
    {
        clienti.add(clientNou);
        //perioadaAsteptare.incrementAndGet();

    }
    public void run() {
        while (1==1) {
            synchronized (clienti) {
                synchronized (Thread.currentThread()){

                try {
                    if (clienti.size() > 0) {
                        int timp_procesare = clienti.peek().getTimpProcesare();
                        for (int i = 0; i < timp_procesare; i++) {
                            Thread.sleep(1000);
                            clienti.peek().setTimpProcesare(clienti.peek().getTimpProcesare() - 1);
                            System.out.println("in coada"+this.nume+": ");
                            for (Client c : clienti)
                                System.out.println(" (" + c.getTimpSosire() + "," + c.getTimpProcesare() + ")");
                                this.getPerioadaAsteptare().decrementAndGet();

                        }

                        clienti.take();
                    }}
                     catch(InterruptedException e){
                            e.printStackTrace();
                        }
            }}
        }
    }

    public BlockingQueue<Client> getClienti()
    {
        return clienti;
    }
}
