package com.company;

import com.company.PokedexType;
import com.company.PokemonType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main_Pokemon {

    public static void main(String[] args) throws JAXBException {

        resumen();
        añadirPokemon();

    }

    private static void añadirPokemon() throws JAXBException{

        File f1 = new File("pokemons.xml");
        PokemonType pkt = new PokemonType();

        pkt.setEtapa("Legendario");
        pkt.setAtaque1("Llama Azul");
        pkt.setPV("130");


        //Factoria, en este caso se llama contexto
        JAXBContext context = JAXBContext.newInstance(PokedexType.class);

        //Umarshaller es para desnuir el fichero xml;
        //desunir significa separar la lógica xml y pasarla a listado de clases Java
        Unmarshaller um = context.createUnmarshaller();

        PokedexType pxt = (PokedexType) um.unmarshal(f1);

        pxt.getPokemon().add(pkt);

        guardar(pkt);
    }

    private static void guardar(PokemonType pxt) throws JAXBException {

        File flg = new File("pokemons2.xml");

        JAXBContext context = JAXBContext.newInstance(PokedexType.class);

        Marshaller n = context.createMarshaller();
        n.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        n.marshal(pxt, flg);
    }

    private static void resumen() throws JAXBException {

        File f = new File("pokemons.xml");

        PokemonType pkt = new PokemonType();

        JAXBContext context = JAXBContext.newInstance(PokedexType.class);

        Unmarshaller um = context.createUnmarshaller();

        PokedexType pxt = (PokedexType) um.unmarshal(f);

        System.out.println("Hi ha " + pxt.getPokemon().size() + " pokemons");
    }
}
