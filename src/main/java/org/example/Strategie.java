package org.example;

import model.Client;
import model.Coada;

import java.util.List;

public interface Strategie {
    public void adaugaClient(List<Coada> cozi, Client c);
}
