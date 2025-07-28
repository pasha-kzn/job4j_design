package ru.job4j.serialization.json;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class MainPojo {
    public static void main(String[] args) throws Exception {
        House house = new House(2010, "wood", true,
                new Room[]{new Room(4, 3), new Room(8, 4)});
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(House.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(house, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            House result = (House) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
