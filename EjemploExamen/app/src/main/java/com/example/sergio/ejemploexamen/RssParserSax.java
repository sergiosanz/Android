package com.example.sergio.ejemploexamen;

import android.renderscript.ScriptGroup;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by 2h on 19/10/2017.
 */

public class RssParserSax {

    private URL rssUrl;

    public RssParserSax(String rssUrl) {
        try {
            this.rssUrl = new URL(rssUrl);//la clase url espera recibir un string
            //tiene que estar sometido a tratamiento de exceptciones
        }catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Numero> parse(){ //el metodo parse nos lo inventamos y nos devolvera la lista con las noticias
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            RssHandler handler = new RssHandler();
            parser.parse(abrirParaLectura(), handler);
            return handler.getNum();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private InputStream abrirParaLectura(){
        try {
            return rssUrl.openConnection().getInputStream(); //lee loabierto para lectura
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }
}
