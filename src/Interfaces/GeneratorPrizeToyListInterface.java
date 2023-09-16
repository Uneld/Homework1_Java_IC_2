package Interfaces;

import ToyData.Toy;

import java.util.LinkedList;
import java.util.PriorityQueue;

public interface GeneratorPrizeToyListInterface {
    LinkedList<Toy> generatePrizeToyList(PriorityQueue<Toy> toyTypesQueue, int sizePrizeList);
}
