package org.example;

import model.Client;
import model.Coada;

import java.util.List;

public class StrategieDupaLungimeaCozii implements Strategie {
    @Override
    public void adaugaClient(List<Coada> cozi, Client c) {
        int size_max=100000;
        Coada coada_min=new Coada(null);
        for(Coada coada: cozi)
        {
            if(coada.getClienti().size()<size_max)
                coada_min=coada;

        }
        coada_min.adaugaClient(c);

    }
}
