package com.fvaldeon.colegio.mvc;

import com.fvaldeon.colegio.base.Alumno;
import com.fvaldeon.colegio.base.Asignatura;
import com.fvaldeon.colegio.base.Profesor;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

public class Vista {
    JFrame frame;
    JTabbedPane tabbedPane1;
    JPanel panel1;
    JTextField nombreAsignaturaTxt;
    JComboBox departamentoCB;
    JSpinner horasSpinner;
    JComboBox profesorCB;
    JTextField nombreAlumnoTxt;
    JTextField apellidosAlumnoTxt;
    DatePicker alumnoDPicker;
    JTextField nombreProfesorTxt;
    JTextField dniProfesorTxt;
    JButton eliminarAlumnoBtn;
    JButton nuevoAlumnoBtn;
    JButton matricularEnAsignaturaBtn;
    JButton desmatricularDeAsignaturaBtn;
    JButton desmatricularAlumnoBtn;
    JButton matricularAlumnoBtn;
    JButton nuevoAsignaturaBtn;
    JButton eliminarAsignaturaBtn;
    JList<Asignatura> listAsignaturasDisponiblesProfesor;
    JList<Profesor> listProfesores;
    JList<Asignatura> listAsignaturasAlumno;
    JList<Alumno> listAlumnosAsignatura;
    JList<Asignatura> listAsignaturasProfesor;
    JList<Alumno> listAlumnos;
    JList<Asignatura> listAsignaturasDisponiblesAlumno;
    JList<Alumno> listAlumnosDisponiblesAsignatura;
    JList<Asignatura> listAsignaturas;
    JButton desvincularAsignaturaProfesorBtn;
    JButton asignarAsignaturaProfesorBtn;
    JButton nuevoProfesorBtn;
    JButton eliminarProfesorBtn;
    DefaultListModel<Asignatura> dlmAsignaturasDisponiblesProfesor;
    DefaultListModel<Profesor> dlmProfesores;
    DefaultListModel<Asignatura> dlmAsignaturasAlumno;
    DefaultListModel<Alumno> dlmAlumnosAsignatura;
    DefaultListModel<Asignatura> dlmAsignaturasProfesor;
    DefaultListModel<Alumno> dlmAlumnos;
    DefaultListModel<Asignatura> dlmAsignaturasDisponiblesAlumno;
    DefaultListModel<Alumno> dlmAlumnosDisponiblesAsignatura;
    DefaultListModel<Asignatura> dlmAsignaturas;
    DefaultComboBoxModel<Profesor> dcbProfesorAsignatura;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(600,600));
        iniciarListas();
    }

    private void iniciarListas() {
        dlmAsignaturasDisponiblesProfesor = new DefaultListModel<>();
        listAsignaturasDisponiblesProfesor.setModel(dlmAsignaturasDisponiblesProfesor);
        dlmProfesores = new DefaultListModel<>();
        listProfesores.setModel(dlmProfesores);
        dlmAsignaturasAlumno = new DefaultListModel<>();
        listAsignaturasAlumno.setModel(dlmAsignaturasAlumno);
        dlmAlumnosAsignatura = new DefaultListModel<>();
        listAlumnosAsignatura.setModel(dlmAlumnosAsignatura);
        dlmAsignaturasProfesor = new DefaultListModel<>();
        listAsignaturasProfesor.setModel(dlmAsignaturasProfesor);
        dlmAlumnos = new DefaultListModel<>();
        listAlumnos.setModel(dlmAlumnos);
        dlmAsignaturasDisponiblesAlumno = new DefaultListModel<>();
        listAsignaturasDisponiblesAlumno.setModel(dlmAsignaturasDisponiblesAlumno);
        dlmAlumnosDisponiblesAsignatura = new DefaultListModel<>();
        listAlumnosDisponiblesAsignatura.setModel(dlmAlumnosDisponiblesAsignatura);
        dlmAsignaturas = new DefaultListModel<>();
        listAsignaturas.setModel(dlmAsignaturas);
        dcbProfesorAsignatura = new DefaultComboBoxModel<>();
        profesorCB.setModel(dcbProfesorAsignatura);
        horasSpinner.setModel(new SpinnerNumberModel());
    }
}
