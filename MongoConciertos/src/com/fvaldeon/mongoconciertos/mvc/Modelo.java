package com.fvaldeon.mongoconciertos.mvc;

import com.fvaldeon.mongoconciertos.base.Artista;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashSet;
import java.util.Iterator;

public class Modelo {

    static final private String BBDD = "musica";
    static final private String COLECCION_ARTISTAS = "artistas";

    private MongoClient cliente;
    private MongoDatabase bbdd;
    private MongoCollection coleccion;

    public void conectar(){
        cliente = new MongoClient();
        bbdd = cliente.getDatabase(BBDD);
        coleccion = bbdd.getCollection(COLECCION_ARTISTAS);
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

    public void insertar(Artista artista){
        coleccion.insertOne(artistaToDocument(artista));
    }

    public void actualizarArtista(Artista artista){
        coleccion.replaceOne(new Document("_id", artista.getId()), artistaToDocument(artista));
    }

    public HashSet<Artista> obtenerArtistas(){
        HashSet<Artista> setArtista = new HashSet<>();
        Iterator<Document> it = coleccion.find().iterator();

        while(it.hasNext()){
            setArtista.add(documentToArtista(it.next()));

        }

        return setArtista;
    }

    public void eliminarArtista(Artista eliminado) {
        coleccion.deleteOne(artistaToDocument(eliminado));
    }
}
