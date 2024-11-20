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
    public static final String splitDiagonal="\\s+";
    public static final String space=" ";
    public static final String linebreak="\n";
    public static final int count=1;
    public String getResult(String inputStr){
        if (inputStr.split(splitDiagonal).length==1) {
            return inputStr + " 1";
        }

            try {
                //split the input string with 1 to n pieces of spaces
                String[] arr = inputStr.split(splitDiagonal);

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                wordFrequencyList=Arrays.stream(arr)
                        .map(s -> new WordFrequency(s, 1))
                        .collect(Collectors.toList());

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> map =getListMap(wordFrequencyList);

                List<WordFrequency> frequencyList = new ArrayList<>();
                frequencyList =map.entrySet().stream()
                        .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                        .collect(Collectors.toList());
                wordFrequencyList = frequencyList;

                wordFrequencyList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(linebreak);
                wordFrequencyList.stream()
                        .map(w -> w.getValue() + space + w.getWordCount())
                        .forEach(joiner::add);
                return joiner.toString();
            } catch (Exception e) {


                return e.toString();
            }

    }


    private Map<String,List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequency.getValue())){
                List<WordFrequency> wordList = new ArrayList<>();
                wordList.add(wordFrequency);
                map.put(wordFrequency.getValue(), wordList);
            }

            else {
                map.get(wordFrequency.getValue()).add(wordFrequency);
            }
        }


        return map;
    }


}