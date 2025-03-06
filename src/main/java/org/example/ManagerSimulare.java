package org.example;

import model.Client;
import model.Coada;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ManagerSimulare implements Runnable{
    public int pragTimp=20;
    public JTextArea textArea;
    public int min_simulare;
    public int max_simulare;
    public double flt1;
    public double flt2;

    public int timpMaxProcesare=10;
    public int timpMinProcesare=2;
    public int timpMaxSosire=10;
    public int timpMinSosire=2;
    public int nrCozi=3;
    public int nrClienti=6;
    public int timp_procesare_mediu=0;
    public int oraVarf;
    public int maxi;
    public int timp_asteptare_mediu;
    FileWriter fisier;
    {
        try{
            fisier = new FileWriter("fisier.txt");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public PoliticaDeSelectie politicaDeSelectie=PoliticaDeSelectie.CEL_MAI_SCURT_TIMP;
    private Organizator organizator;
private  List<Client> clientiGenerati=new ArrayList<>();


    public ManagerSimulare(int nrclienti, int nrcozi, int minsimulare, int maxsimulare, int minsosire, int maxsosire,int minprocesare,int maxprocesare,JTextArea text)
    {
        this.nrClienti=nrclienti;
        this.nrCozi=nrcozi;
        this.min_simulare=minsimulare;
        this.max_simulare=maxsimulare;
        this.timpMinSosire=minsosire;
        this.timpMaxSosire=maxsosire;
        this.timpMinProcesare=minprocesare;
        this.timpMaxProcesare=maxprocesare;
        this.textArea=text;
        genereazaNClienti();
        organizator=new Organizator(this.nrCozi,100);


    }
    public void scrieInFrame(String text)
    { textArea.setText(null);
        textArea.append(text);
    }
    public void scrie(String text) {
        try {
            fisier.write(text);

            scrieInFrame(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void genereazaNClienti()
    {     Random rand = new Random();
        int timp_sosire=0,timp_procesare=0;
        pragTimp=max_simulare;
        for(int i=0;i<nrClienti;i++)
        {
            timp_sosire=rand.nextInt(timpMinSosire,timpMaxSosire);
            timp_procesare=rand.nextInt(timpMinProcesare,timpMaxProcesare);
            Client client = new Client(timp_sosire,timp_procesare);
            clientiGenerati.add(client);
            this.timp_procesare_mediu+=client.getTimpProcesare();
        }
        Collections.sort(clientiGenerati);
         flt1=this.timp_procesare_mediu*1.0/nrClienti;
    }

    @Override
    public void run() {
        int timpCurent = 0;

        while (timpCurent < pragTimp) {
            System.out.println("\ntimpul curent = "+timpCurent);
            synchronized (clientiGenerati) {
                Iterator<Client> iterator = clientiGenerati.iterator();
                while (iterator.hasNext()) {

                    Client c = iterator.next();

                    if (c.getTimpSosire() == timpCurent) {
                          organizator.plaseazaClient(c);
                        iterator.remove();
                    }
                }
            }
            calculeazaSiAfiseaza(timpCurent);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            scrie(scrieInFisier(timpCurent));

            timpCurent++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        scrie(avgTime());
        try {
            fisier.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
public  void calculeazaSiAfiseaza(int timp_curent)

{     int nr=0;
    for(Coada s : organizator.getCozi()){
        nr += s.getClienti().size();
    }
    if(nr >maxi){
        maxi = nr;
        oraVarf = timp_curent;
    }


    if(timp_curent==oraVarf) {
        timp_asteptare_mediu=0;
        for (Coada s : organizator.getCozi()) {
            timp_asteptare_mediu += s.getPerioadaAsteptare().intValue();

        }
    }
    flt2 = timp_asteptare_mediu / organizator.getCozi().size();
}

    public String scrieInFisier(int timp){
        int contor = 0;
        StringBuilder text = new StringBuilder();
        text.append("Timpul ").append(timp).append("\n").append("Clienti :");
        for(Client c : clientiGenerati){
            text.append(" (" + c.getTimpSosire() + "," + c.getTimpProcesare() + ")").append(" ");
        }

        for(Coada s : organizator.getCozi()){
            text.append("\nCoada ").append(contor+1).append(": ");
            for(Client c : s.getClienti()){
                text.append(" (" + c.getTimpSosire() + "," + c.getTimpProcesare() + ")").append(" ");
            }
            contor++;
        }
        text.append("\n\n");
        return text.toString();
    }
    public String avgTime()
    { StringBuilder texttt = new StringBuilder();
        texttt.append("timpul de asteptare mediu: ").append(flt2);
        texttt.append("\ntimpul de procesare mediu: ").append(flt1);
        texttt.append("\nora de varf este la timp= ").append(oraVarf);
        return texttt.toString();
    }
}
