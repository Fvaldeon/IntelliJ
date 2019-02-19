package com.fvaldeon.mongoconciertos.mvc;

import com.fvaldeon.mongoconciertos.base.Artista;
import com.fvaldeon.mongoconciertos.base.Concierto;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;

public class Modelo {

    static final private String BBDD = "musica";
    static final private String COLECCION_ARTISTAS = "artistas";
    static final private String COLECCION_CONCIERTOS = "conciertos";

    private MongoClient cliente;
    private MongoDatabase bbdd;
    private MongoCollection colArtistas;
    private MongoCollection colConciertos;

    public void conectar(){
        cliente = new MongoClient();
        bbdd = cliente.getDatabase(BBDD);
        colArtistas = bbdd.getCollection(COLECCION_ARTISTAS);
        colConciertos = bbdd.getCollection(COLECCION_CONCIERTOS);
    }

    public void desconectar(){
        cliente.close();
    }

    public Document artistaToDocument(Artista artista){
        Document doc = new Document();
        doc.append("nombre", artista.getNombre())
                .append("estilo", artista.getEstilo())
                .append("cache", artista.getCache());
        return doc;
    }

    public Artista documentToArtista(Document docArtista){
        Artista artista = new Artista();
        artista.setId(docArtista.getObjectId("_id"));
        artista.setNombre(docArtista.getString("nombre"));
        artista.setEstilo(docArtista.getString("estilo"));
        artista.setCache(docArtista.getDouble("cache"));

        return artista;
    }

    public void insertarArtista(Artista artista){
        colArtistas.insertOne(artistaToDocument(artista));
    }

    public void actualizarArtista(Artista artista){
        colArtistas.replaceOne(new Document("_id", artista.getId()), artistaToDocument(artista));
    }

    public HashSet<Artista> obtenerArtistas(){
        HashSet<Artista> setArtista = new HashSet<>();
        Iterator<Document> it = colArtistas.find().iterator();

        while(it.hasNext()){
            setArtista.add(documentToArtista(it.next()));

        }

        return setArtista;
    }

    public void eliminarArtista(Artista eliminado) {
        colArtistas.deleteOne(artistaToDocument(eliminado));
    }


    public Document conciertoToDocument(Concierto concierto){
        Document doc = new Document();
        doc.append("nombre", concierto.getNombre())
                .append("fecha", concierto.getFecha().toString());
        return doc;
    }

    public Concierto documentToConcierto(Document docConcierto){
        Concierto concierto = new Concierto();
        concierto.setId(docConcierto.getObjectId("_id"));
        concierto.setNombre(docConcierto.getString("nombre"));
        concierto.setFecha(LocalDate.parse(docConcierto.getString("fecha")));
        return concierto;
    }

    public void insertarConcierto(Concierto concierto){
        colConciertos.insertOne(conciertoToDocument(concierto));
    }



    public HashSet<Concierto> obtenerConciertos(){
        HashSet<Concierto> setConcierto = new HashSet<>();
        Iterator<Document> it = colConciertos.find().iterator();

        while(it.hasNext()){
            setConcierto.add(documentToConcierto(it.next()));
        }

        return setConcierto;
    }

    public void eliminarConcierto(Concierto eliminado) {
        colConciertos.deleteOne(conciertoToDocument(eliminado));
    }

}
