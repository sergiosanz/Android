package com.example.sergio.ejemploexamen;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by 2h on 18/10/2017.
 */

public class RssHandler extends DefaultHandler {

    private List<Numero> numeros = new ArrayList<Numero>();
    private Numero numeroActual;
    private StringBuilder sbTexto;

    public List<Numero> getNum() {
        return numeros;
    }

    @Override
    public void startDocument() throws SAXException { //este metodo se ejecuta al iniciar la pagina y se ejecuta una sola vez
        super.startDocument();
        numeros = new ArrayList<Numero>();
        sbTexto = new StringBuilder();

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException { //este metodo cada vez que hay una marca de apertura se ejecuta
        super.startElement(uri, localName, qName, attributes);

        if(localName.equals("Numero")){
            numeroActual = new Numero();
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException { //este metodo lee los caracteres de texto que hay y los mete en sbtexto que es de string
        super.characters(ch, start, length);

        if (numeroActual != null){
            sbTexto.append(ch, start, length); //append es el metodo añadir
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException { //lee las marcas de cerrar
        super.endElement(uri, localName, qName);
        if (numeroActual != null)
        {
            if (localName.equals("int")){
                numeroActual.setNumero(parseInt((sbTexto.toString())));

            }else if (localName.equals("Numero")) {
                numeros.add(numeroActual);
            }

            sbTexto.setLength(0);//con esto sbTexto se vacia
        }

    }


}



