package com.fvaldeon.registrohomicidios.mvc;

import com.fvaldeon.registrohomicidios.base.Homicida;
import com.fvaldeon.registrohomicidios.base.Victima;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

/**
 * Created by PROFESOR on 17/01/2019.
 */
public class Vista {
    JTabbedPane tabbedPane1;
    private JPanel panel1;
    JFrame frame;
    JTextField nombreTxt;
    JRadioButton hombreRB;
    JRadioButton mujerRadioButton;
    JTextField causaMuerteTxt;
    JComboBox<Homicida> cbHomicidaVictima;
    JList<Victima> listVictimas;
    JButton eliminarVictimaBtn;
    JButton altaVictimaBtn;
    JButton modificarVictimaBtn;
    JTextField apodoTxt;
    JTextField armaTxt;
    JTextField tiempoCarcelTxt;
    JCheckBox asesinoSerieCBox;
    JList<Victima> listVictimasHomicida;
    JButton modificarHomicidaBtn;
    JButton altaHomicidaBtn;
    JButton eliminarHomicidaBtn;
    JList<Homicida> listHomicidas;
    DatePicker datePicker;
    JButton mostrarGraficoBtn;
    JLabel fotolbl;
    JButton seleccionarFotoBtn;
    JLabel rutafotoLbl;
    JButton borrarFotoBtn;
    JButton informeCompletoVictimasBtn;
    JButton informeConcretoHomicidaBtn;
    JComboBox cBHomicidasInforme;
    JButton informeCompletoHomicidasBtn;
    JButton informeVictimasSinBbddBtn;
    DefaultListModel<Homicida> dlmHocimidas;
    DefaultListModel<Victima> dlmVictimas;
    DefaultListModel<Victima> dlmVictimasHomicida;
    DefaultComboBoxModel<Homicida> dcbmHomicidaVictima;
    JRadioButtonMenuItem mysqlItem;
    JRadioButtonMenuItem postgreItem;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iniciarModelos();
        iniciarMenu();
        frame.pack();
        frame.setLocationRelativeTo(null);

    }

    private void iniciarModelos() {
        dlmHocimidas = new DefaultListModel<>();
        dlmVictimas = new DefaultListModel<>();
        dlmVictimasHomicida = new DefaultListModel<>();
        dcbmHomicidaVictima = new DefaultComboBoxModel<>();
        listHomicidas.setModel(dlmHocimidas);
        listVictimas.setModel(dlmVictimas);
        listVictimasHomicida.setModel(dlmVictimasHomicida);
        cbHomicidaVictima.setModel(dcbmHomicidaVictima);

        listHomicidas.setCellRenderer(new HomicidaListCellRenderer());

        rutafotoLbl.setText(null);
    }

    private void iniciarMenu(){
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Conexion");
        mysqlItem = new JRadioButtonMenuItem("Conectar a MySql");
        mysqlItem.setSelected(true);
        mysqlItem.setActionCommand("mysql");
        postgreItem = new JRadioButtonMenuItem("Conectar a PostgreSql");
        postgreItem.setActionCommand("postgre");
        menu.add(mysqlItem);
        menu.add(postgreItem);
        barra.add(menu);
        frame.setJMenuBar(barra);

        ButtonGroup grupoConexion = new ButtonGroup();
        grupoConexion.add(mysqlItem);
        grupoConexion.add(postgreItem);
    }

    public void mostrar(){
        frame.setVisible(true);
    }
}
