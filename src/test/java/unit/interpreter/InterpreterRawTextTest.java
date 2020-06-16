package unit.interpreter;

import data.raw.process.interpreter.RawDataRequestInterpreter;
import org.junit.Test;

public class InterpreterRawTextTest {

    @Test
    public void processRawTextInterpreter() {
        String sentence = "Zebras are several species of African equids (horse family) united by their distinctive black and white stripes. Their stripes come in different patterns, unique to each individual. They are generally social animals that live in small harems to large herds. Unlike their closest relatives, horses and donkeys, zebras have never been truly domesticated. There are three species of zebras: the plains zebra, the Grevy's zebra and the mountain zebra. The plains zebra and the mountain zebra belong to the subgenus Hippotigris, but Grevy's zebra is the sole species of subgenus Dolichohippus. The latter resembles an ass, to which it is closely related, while the former two are more horse-like. All three belong to the genus Equus, along with other living equids. The unique stripes of zebras make them one of the animals most familiar to people. They occur in a variety of habitats," +
                "such as grasslands, savannas, woodlands, thorny scrublands, mountains, and coastal hills. However, various anthropogenic factors\n" +
                "have had a severe impact on zebra populations, in particular hunting for skins and habitat destruction. Grevy's zebra and the mountain zebra are endangered. While plains zebras are much more plentiful, one subspecies - the Quagga - became extinct in the late 19th century. Though there is currently a plan, called the Quagga Project, that aims to breed zebras that are phenotypically similar to the Quagga, in a process called breeding back.";
        String questions = "Which Zebras are endangered? What is the aim of the Quagga Project? Which animals are some of their closest relatives? Which are the three species of zebras? Which subgenus do the plains zebra and the mountain zebra belong to?";
        String answers = "subgenus Hippotigris; the plains zebra, the Grevy's zebra and the mountain zebra;horses and donkeys;aims to breed zebras that are phenotypically similar to the quagga; Grevy's zebra and the mountain zebra";
        RawDataRequestInterpreter rawDataRequestInterpreter = new RawDataRequestInterpreter();
        //List<QuestionAnswerPair> questionAnswerPairs = rawDataRequestInterpreter.rawDataRequestInterpreter();
       // System.out.println(questionAnswerPairs);
    }
}
