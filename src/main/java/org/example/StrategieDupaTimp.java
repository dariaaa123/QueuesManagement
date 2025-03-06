package org.example;

import model.Client;
import model.Coada;

import java.util.List;

public class StrategieDupaTimp implements Strategie{
    @Override
    public void adaugaClient(List<Coada> cozi, Client c) {
        int time_max=100000;
        Coada coada_min=new Coada(null);
        int ok=1;
        for(Coada coada: cozi)
        {
                if(coada.getPerioadaAsteptare().intValue()<time_max){
                coada_min=coada;
                time_max=coada_min.getPerioadaAsteptare().intValue();}

        }
        coada_min.adaugaClient(c);
        coada_min.setPerioadaAsteptare(coada_min.getPerioadaAsteptare().intValue()+c.getTimpProcesare());
    }
}
