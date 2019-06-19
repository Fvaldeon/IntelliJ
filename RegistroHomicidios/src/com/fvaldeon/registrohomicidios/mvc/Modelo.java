package com.fvaldeon.registrohomicidios.mvc;

import com.fvaldeon.registrohomicidios.base.Homicida;
import com.fvaldeon.registrohomicidios.base.Victima;

import com.fvaldeon.registrohomicidios.util.Util;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by PROFESOR on 17/01/2019.
 */
public class Modelo {

    private SessionFactory factoria;
    private Session sesion;
    private Connection conexion;

    public void conectar(boolean mysql){
        Configuration configuracion = new Configuration();

        if(mysql) {
            //Cargo el fichero Hibernate.cfg.xml para conectar con mysql
            configuracion.configure();
        }else {
            //Para conectar con postgresql
            configuracion.configure("hibernatePostgre.cfg.xml");
        }

        configuracion.addAnnotatedClass(Homicida.class);
        configuracion.addAnnotatedClass(Victima.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuracion.getProperties()).build();
        factoria = configuracion.buildSessionFactory(serviceRegistry);

        //Creo una sesion a partir de la factoria
        sesion = factoria.openSession();
    }

    public void desconectar() {
        if(sesion != null){
            sesion.close();
        }
        if(factoria != null){
            factoria.close();
        }
        if(conexion != null){
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void altaVictima(String nombre, boolean esHombre, LocalDate fechaDefuncion, String causaMuerte, Homicida homicida){
        Victima victima = new Victima(nombre, esHombre, fechaDefuncion, causaMuerte, homicida);
        sesion.beginTransaction();
        sesion.saveOrUpdate(victima);
        sesion.getTransaction().commit();
    }

    public void modificarVictima(Victima victima){
        sesion.beginTransaction();
        sesion.saveOrUpdate(victima);
        sesion.getTransaction().commit();
    }

    public void eliminarVictima(Victima victima){
        sesion.beginTransaction();
        sesion.delete(victima);
        sesion.getTransaction().commit();
    }
    public void altaHomicida(String apodo, String arma, boolean asesinoSerie, int annosCarcel, String rutaFoto){
        Homicida homicida = new Homicida(apodo, arma, asesinoSerie, annosCarcel);
        homicida.setNombreFoto(rutaFoto);
        sesion.beginTransaction();
        sesion.saveOrUpdate(homicida);
        sesion.getTransaction().commit();
    }

    public void modificarHomicida(Homicida homicida){
        sesion.beginTransaction();
        sesion.saveOrUpdate(homicida);
        sesion.getTransaction().commit();
    }

    public void eliminarHomicida(Homicida homicida){
        sesion.beginTransaction();
        //Si quiero solo eliminar al homicida sin perder las victimas
        homicida.removeAllVictimas();
        sesion.delete(homicida);
        sesion.getTransaction().commit();
    }

    public List<Homicida> getHomicidas() {
        Query query = sesion.createQuery("FROM Homicida");
        List<Homicida> lista = query.list();
        return lista;
    }

    public List<Victima> getVictimas(){
        Query query = sesion.createQuery("FROM Victima");
        List<Victima> lista = query.list();
        return lista;
    }

    private void crearConexionInformes() throws SQLException {
        if(conexion == null) {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/registro_homicidas", "root", "");
        }
    }

    public JasperPrint getJasperPrint(int tipo) throws SQLException, JRException {
        crearConexionInformes();
        JasperReport report = null;
        switch (tipo) {
            case Util.INFORME_VICTIMAS:
                //Mostrar obtener el reporte sin compilar
                report = (JasperReport) JRLoader.loadObjectFromFile("reports/VictimasMysql.jasper");
                //JasperReport report = JasperCompileManager.compileReport("reports/VictimasMysql.jrxml");
                break;
            case Util.INFORME_HOMICIDAS_COMPLETO:
                //Mostrar obtener el reporte sin compilar
                report = (JasperReport) JRLoader.loadObjectFromFile("reports/Completo-homicidas.jasper");
                //JasperReport report = JasperCompileManager.compileReport("reports/VictimasMysql.jrxml");
                break;
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, conexion);
        //Ignorar imagenes solo cuando exporto a XLS (excel)
        jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");

        //Exportar a pdf
        //JasperExportManager.exportReportToPdfFile(jasperPrint, "rutaFichero");
        return jasperPrint;
    }

    public JasperPrint getJasperPrintConParametros(int tipo, Map<String, Object> parametros) throws SQLException, JRException {
        crearConexionInformes();
        JasperReport report = null;
        switch (tipo) {
            case Util.INFORME_HOMICIDAS_CONCRETO:
                System.out.println(parametros.get("idHomicidaJava"));
                //Mostrar obtener el reporte sin compilar
                report = (JasperReport) JRLoader.loadObjectFromFile("reports/ConcretoHomicida.jasper");
                //JasperReport report = JasperCompileManager.compileReport("reports/VictimasMysql.jrxml");
                break;
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, conexion);
        //Ignorar imagenes solo cuando exporto a XLS (excel)
        jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");

        //Exportar a pdf
        //JasperExportManager.exportReportToPdfFile(jasperPrint, "rutaFichero");
        return jasperPrint;
    }


}
