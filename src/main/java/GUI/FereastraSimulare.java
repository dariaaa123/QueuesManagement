package GUI;

import org.example.ManagerSimulare;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FereastraSimulare {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextArea textArea1;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton proceseazaButton;
    int nrClienti;
    int nrCozi;
    int min_simulare;
    int max_simulare;
    int timpMinSosire;
    int timpMaxSosire;
    int timpMinProcesare;
    int timpMaxProcesare;
    public FereastraSimulare() {


        proceseazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                nrClienti=Integer.parseInt(textField1.getText());
                nrCozi=Integer.parseInt(textField2.getText());
                min_simulare=Integer.parseInt(textField3.getText());
                timpMinProcesare=Integer.parseInt(textField4.getText());
                max_simulare=Integer.parseInt(textField8.getText());
                timpMinSosire=Integer.parseInt(textField6.getText());
                timpMaxProcesare=Integer.parseInt(textField5.getText());
                timpMaxSosire=Integer.parseInt(textField7.getText());


                ManagerSimulare man=new ManagerSimulare(nrClienti,nrCozi,min_simulare,max_simulare,timpMinSosire,timpMaxSosire,timpMinProcesare,timpMaxProcesare,textArea1);

                Thread thread= new Thread(man);
                thread.start();
            }
        });
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Administrare cozi");
        frame.setContentPane(new FereastraSimulare().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
