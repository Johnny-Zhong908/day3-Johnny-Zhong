import java.util.*;
import java.util.stream.Collectors;

//rename,getResult,input,arr,exception msg
//Steam instead of for loop
//useless code,if else,import
//temp field ? inputList = list
//reformat,empty space
//extract method
//temp field?inputList = list;
//magic String
public class WordFrequencyGame {
    public static final String SPLIT_DIAGONAL ="\\s+";
    public static final String SPACE =" ";
    public static final String LINE_BREAK ="\n";
    public static final int count=1;
    public String getResult(String inputStr){
        if (inputStr.split(SPLIT_DIAGONAL).length==1) {
            return inputStr + " 1";
        }

            try {
                //split the input string with 1 to n pieces of spaces
                String[] Words = inputStr.split(SPLIT_DIAGONAL);
                List<WordFrequency> wordFrequencyList = createWordFrequencyList(Words);
                Map<String, List<WordFrequency>> map = getListMap(wordFrequencyList);
                List<WordFrequency> frequencyList = generateWordFrequencyList(map);
                return generateResultString(frequencyList);
            } catch (Exception e) {

                return e.toString();
            }

    }
    private List<WordFrequency> createWordFrequencyList(String[] arr) {
        return Arrays.stream(arr)
                .map(s -> new WordFrequency(s, 1))
                .collect(Collectors.toList());
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            map.computeIfAbsent(wordFrequency.getValue(), k -> new ArrayList<>()).add(wordFrequency);
        }
        return map;
    }

    private List<WordFrequency> generateWordFrequencyList(Map<String, List<WordFrequency>> map) {
        return map.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private String generateResultString(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
                .sorted(Comparator.comparingInt(WordFrequency::getWordCount).reversed())
                .map(w -> w.getValue() + SPACE + w.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }


}