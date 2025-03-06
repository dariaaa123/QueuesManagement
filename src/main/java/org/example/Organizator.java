package org.example;

import model.Coada;
import model.Client;

import java.util.*;

public class Organizator {
    private List<Coada> cozi;
    private int nrMaxCozi;
    private int nrMaxClientiPerCoada;
    private  Strategie strategie;

    public Organizator(int nrMaxCozi, int nrMaxClientiPerCoada )
    {
        this.nrMaxCozi=nrMaxCozi;
        this.nrMaxClientiPerCoada=nrMaxClientiPerCoada;
        strategie=new StrategieDupaTimp();
        cozi=new ArrayList<>();
        for(int i=1;i<=nrMaxCozi;i++)
        {
            String nume= String.valueOf(i);
            Coada coada= new Coada(nume);
            Thread t = new Thread(coada,"Coada "+i);
            cozi.add(coada);
            t.start();
        }

    }
    public void schimbaStrategia( PoliticaDeSelectie politica)
    {
        if(politica==PoliticaDeSelectie.CEL_MAI_SCURT_TIMP)
            this.strategie=new StrategieDupaTimp();
        if(politica==PoliticaDeSelectie.CEA_MAI_SCURTA_COADA)
            this.strategie=new StrategieDupaLungimeaCozii();
    }
    public void plaseazaClient(Client c) {
        if (this.strategie instanceof StrategieDupaTimp)
            this.strategie.adaugaClient(this.cozi, c);
        if (this.strategie instanceof StrategieDupaLungimeaCozii)
            this.strategie.adaugaClient(cozi, c);
    }
    public List<Coada> getCozi()
    {
        return cozi;
    }

}
