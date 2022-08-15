package ru.job4j.template;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Ignore
class TemplateGenTest {

    @Test
    public void whenGenerate() {
        String template = "I am a ${name}, Who are ${subject}?";
        TemplateGen templateGen = new TemplateGen();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Max");
        args.put("subject", "you");
        String rsl = templateGen.produce(template, args);
        String expected = "I am a Max, Who are you?";
        Assert.assertEquals(expected, rsl);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemplateIsNull() {
        String template = null;
        TemplateGen temp = new TemplateGen();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Max");
        args.put("subject", "you");
        temp.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeysInTheTemplateThatNotInTheMap() {
        String template = "I am a ${name}, Who are ${subject}?";
        TemplateGen temp = new TemplateGen();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Max");
        temp.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExtraKeysInTheMap() {
        String template = "I am a ${name}";
        TemplateGen temp = new TemplateGen();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Max");
        args.put("subject", "you");
        temp.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsIncorrect() {
        String template = "I am a ${name}, Who are ${subject}?";
        TemplateGen temp = new TemplateGen();
        Map<String, String> args = new HashMap<>();
        args.put("man", "Max");
        args.put("woman", "you");
        temp.produce(template, args);
    }
}