package com.fvaldeon.colegio.mvc;

import com.fvaldeon.colegio.base.Alumno;
import com.fvaldeon.colegio.base.Asignatura;
import com.fvaldeon.colegio.base.Profesor;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public class Controlador extends WindowAdapter implements ActionListener, ListSelectionListener {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {

        this.vista = vista;
        this.modelo = modelo;

        addActionListener(this);
        addListSelecctionListener(this);
        addWindowListener(this);

        modelo.conectar();

        refrescarSeccionProfesores();
        refrescarSeccionAsignaturas();
        refrescarSeccionAlumnos();
    }

    private void addActionListener(ActionListener listener){
        vista.desmatricularAlumnoBtn.addActionListener(listener);
        vista.desmatricularDeAsignaturaBtn.addActionListener(listener);
        vista.eliminarAlumnoBtn.addActionListener(listener);
        vista.eliminarAsignaturaBtn.addActionListener(listener);
        vista.matricularAlumnoBtn.addActionListener(listener);
        vista.matricularEnAsignaturaBtn.addActionListener(listener);
        vista.nuevoAlumnoBtn.addActionListener(listener);
        vista.nuevoAsignaturaBtn.addActionListener(listener);
        vista.nuevoProfesorBtn.addActionListener(listener);
        vista.eliminarProfesorBtn.addActionListener(listener);
        vista.asignarAsignaturaProfesorBtn.addActionListener(listener);
        vista.desvincularAsignaturaProfesorBtn.addActionListener(listener);
    }

    private void addListSelecctionListener(ListSelectionListener listener){
        vista.listAlumnos.addListSelectionListener(listener);
        vista.listAsignaturas.addListSelectionListener(listener);
        vista.listProfesores.addListSelectionListener(listener);
    }

    private void addWindowListener(WindowListener listener){
        vista.frame.addWindowListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String comando = actionEvent.getActionCommand();

        switch(comando){
            //Seccion alumnos
            case "DesmatricularAlumno":{
                Alumno alumno = vista.listAlumnos.getSelectedValue();
                alumno.desmatricularDeAsignaturas(vista.listAsignaturasAlumno.getSelectedValuesList());
                modelo.guardarAlumno(alumno);
            }
            break;

            case "MatricularAlumno":{
                Alumno alumno = vista.listAlumnos.getSelectedValue();
                alumno.matricularEnAsignaturas(vista.listAsignaturasDisponiblesAlumno.getSelectedValuesList());
                modelo.guardarAlumno(alumno);
            }
            break;

            case "NuevoAlumno":{
                Alumno alumno = new Alumno(vista.nombreAlumnoTxt.getText(), vista.apellidosAlumnoTxt.getText(), Date.valueOf(vista.alumnoDPicker.getDate()));
                modelo.guardarAlumno(alumno);
            }
            break;

            case "EliminarAlumno":{
                Alumno alumno = vista.listAlumnos.getSelectedValue();
                modelo.eliminarAlumno(alumno);
            }
            break;

            //Seccion Asignaturas
            case "MatricularAsignatura":{
                Asignatura asignatura = vista.listAsignaturas.getSelectedValue();
                asignatura.matricularAlumnos(vista.listAlumnosDisponiblesAsignatura.getSelectedValuesList());
                modelo.guardarAsignatura(asignatura);
            }
            break;

            case "DesmatricularAsignatura":{
                Asignatura asignatura = vista.listAsignaturas.getSelectedValue();
                asignatura.desmatricularAlumnos(vista.listAlumnosAsignatura.getSelectedValuesList());
                modelo.guardarAsignatura(asignatura);
            }
            break;

            case "NuevaAsignatura":{
                Asignatura asignatura = new Asignatura(vista.nombreAsignaturaTxt.getText(), (String)vista.departamentoCB.getSelectedItem(), (Integer) vista.horasSpinner.getValue(), (Profesor)vista.profesorCB.getSelectedItem());
                modelo.guardarAsignatura(asignatura);
            }
            break;

            case "EliminarAsignatura":{
                Asignatura asignatura = vista.listAsignaturas.getSelectedValue();
                //Al ser la entidad Padre de la relacion alumno-asignatura, debo eliminar las relaciones primero
                asignatura.desmatricularTodos();
                modelo.eliminarAsignatura(asignatura);
            }
            break;


            //Seccion Profesores
            case "AsignarAsignatura":{
                Profesor profesor = vista.listProfesores.getSelectedValue();
                profesor.anadirAsignaturas(vista.listAsignaturasDisponiblesProfesor.getSelectedValuesList());
                modelo.guardarProfesor(profesor);
            }
            break;

            case "DesvincularAsignatura": {
                Profesor profesor = vista.listProfesores.getSelectedValue();
                profesor.desvincularAsignaturas(vista.listAsignaturasProfesor.getSelectedValuesList());
                modelo.guardarProfesor(profesor);
            }
            break;

            case "NuevoProfesor":{
                Profesor profesor = new Profesor(vista.nombreProfesorTxt.getText(), vista.dniProfesorTxt.getText());
                modelo.guardarProfesor(profesor);
            }
            break;

            case "EliminarProfesor":{
                Profesor profesor = vista.listProfesores.getSelectedValue();
                //Al ser la entidad Padre, si no quiero eliminar asignaturas debo quitar las relaciones primero
                profesor.desvincularTodas();
                modelo.eliminarProfesor(profesor);
            }
            break;
        }
        refrescarSeccionAlumnos();
        refrescarSeccionAsignaturas();
        refrescarSeccionProfesores();
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if(listSelectionEvent.getSource() == vista.listAsignaturas && vista.listAsignaturas.getSelectedValue() != null){
            Asignatura asignatura = vista.listAsignaturas.getSelectedValue();
            listarAlumnosAsignatura(asignatura);
            listarAlumnosDisponiblesParaAsignatura(asignatura);
        }else if(listSelectionEvent.getSource() == vista.listAlumnos && vista.listAlumnos.getSelectedValue() != null){
            Alumno alumno = vista.listAlumnos.getSelectedValue();
            listarAsignaturasAlumno(alumno);
            listarAsignaturasDisponiblesParaAlumno(alumno);
        }else if(listSelectionEvent.getSource() == vista.listProfesores && vista.listProfesores.getSelectedValue() != null){
            Profesor profesor = vista.listProfesores.getSelectedValue();
            listarAsignaturasProfesor(profesor);
            listarAsignaturasDisponiblesProfesor(profesor);
        }

    }


    /*
        Pestana Alumnos
     */

    private void listarAlumnos(){
        List<Alumno> alumnos = modelo.obtenerAlumnos();
        vista.dlmAlumnos.clear();
        for(Alumno alumno : alumnos){
            vista.dlmAlumnos.addElement(alumno);
        }
    }

    private void listarAsignaturasDisponiblesParaAlumno(Alumno alumno){
        List<Asignatura> asignaturas = modelo.obtenerAsignaturasDisponiblesParaAlumno(alumno);
        vista.dlmAsignaturasDisponiblesAlumno.clear();
        for(Asignatura asignatura : asignaturas){
            vista.dlmAsignaturasDisponiblesAlumno.addElement(asignatura);
        }
    }

    private void listarAsignaturasAlumno(Alumno alumno){
        Set<Asignatura> asignaturas = alumno.getAsignaturas();
        vista.dlmAsignaturasAlumno.clear();
        for(Asignatura asignatura : asignaturas){
            vista.dlmAsignaturasAlumno.addElement(asignatura);
        }
    }

    public void refrescarSeccionAlumnos(){
        vista.nombreAlumnoTxt.setText("");
        vista.apellidosAlumnoTxt.setText("");
        vista.alumnoDPicker.setText("");
        vista.dlmAsignaturasDisponiblesAlumno.clear();
        vista.dlmAsignaturasAlumno.clear();
        listarAlumnos();
    }

    /*
        Pestana Asignaturas
     */

    private void listarAsignaturas(){
        List<Asignatura> asignaturas = modelo.obtenerAsignaturas();
        vista.dlmAsignaturas.clear();
        for(Asignatura asignatura : asignaturas){
            vista.dlmAsignaturas.addElement(asignatura);
        }
    }

    private void listarAlumnosAsignatura(Asignatura asignatura){
        Set<Alumno> alumnos = asignatura.getAlumnos();
        vista.dlmAlumnosAsignatura.clear();
        for(Alumno alumno : alumnos){
            vista.dlmAlumnosAsignatura.addElement(alumno);
        }
    }

    private void listarAlumnosDisponiblesParaAsignatura(Asignatura asignatura){
        List<Alumno> alumnos = modelo.obtenerAlumnosDisponiblesParaAsignatura(asignatura);
        vista.dlmAlumnosDisponiblesAsignatura.clear();
        for(Alumno alumno : alumnos){
            vista.dlmAlumnosDisponiblesAsignatura.addElement(alumno);
        }
    }

    public void listarProfesoresAsignatura(){
        List<Profesor> profesores = modelo.obtenerProfesores();
        vista.dcbProfesorAsignatura.removeAllElements();
        for(Profesor profesor : profesores){
            vista.dcbProfesorAsignatura.addElement(profesor);
        }
    }

    public void refrescarSeccionAsignaturas(){
        vista.nombreAsignaturaTxt.setText("");
        vista.horasSpinner.setValue(0);
        vista.dlmAlumnosAsignatura.clear();
        vista.dlmAlumnosDisponiblesAsignatura.clear();
        listarAsignaturas();
        listarProfesoresAsignatura();
    }

    /*
        Pestana Profesores
     */
    public void listarProfesores(){
        List<Profesor> lista = modelo.obtenerProfesores();
        vista.dlmProfesores.clear();
        for(Profesor profesor : lista){
            vista.dlmProfesores.addElement(profesor);
        }
    }

    public void listarAsignaturasProfesor(Profesor profesor){
        List<Asignatura> asignaturas = profesor.getAsignaturas();
        vista.dlmAsignaturasProfesor.clear();
        for(Asignatura asignatura : asignaturas){
            vista.dlmAsignaturasProfesor.addElement(asignatura);
        }
    }

    public void listarAsignaturasDisponiblesProfesor(Profesor profesor){
        List<Asignatura> asignaturas = modelo.obtenerAsignaturasDisponiblesParaProfesor(profesor);
        vista.dlmAsignaturasDisponiblesProfesor.clear();
        for(Asignatura asignatura : asignaturas){
            vista.dlmAsignaturasDisponiblesProfesor.addElement(asignatura);
        }
    }

    public void refrescarSeccionProfesores(){
        vista.dniProfesorTxt.setText("");
        vista.nombreProfesorTxt.setText("");
        vista.dlmAsignaturasDisponiblesProfesor.clear();
        vista.dlmAsignaturasProfesor.clear();
        listarProfesores();
    }

    /**
     *  Método invocado al cerrar la ventana
     *  Se usa para desconectar
     * @param windowEvent
     */

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        modelo.desconectar();
    }
}
